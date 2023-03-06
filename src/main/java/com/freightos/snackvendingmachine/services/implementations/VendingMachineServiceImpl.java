package com.freightos.snackvendingmachine.services.implementations;

import com.freightos.snackvendingmachine.exceptions.InvalidInputException;
import com.freightos.snackvendingmachine.exceptions.ItemUnavailableException;
import com.freightos.snackvendingmachine.exceptions.NoEnoughChangeException;
import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.models.money.CardInformation;
import com.freightos.snackvendingmachine.services.ItemSlotService;
import com.freightos.snackvendingmachine.services.PaymentService;
import com.freightos.snackvendingmachine.services.VendingMachineService;
import com.freightos.snackvendingmachine.thirdparties.CardService;
import com.freightos.snackvendingmachine.vendingmachine.statemachine.VendingMachineEvent;
import com.freightos.snackvendingmachine.vendingmachine.statemachine.VendingMachineState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.freightos.snackvendingmachine.vendingmachine.statemachine.VendingMachineEvent.NO_CHANGE;

@Component
@WithStateMachine
public class VendingMachineServiceImpl implements VendingMachineService {
    private static final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);
    private ItemSlotService itemSlotService;

    private PaymentService paymentService;

    private CardService cardService;

    private StateMachine<VendingMachineState, VendingMachineEvent> stateMachine;

    public VendingMachineServiceImpl(ItemSlotService itemSlotService, PaymentService paymentService, CardService cardService, StateMachine<VendingMachineState, VendingMachineEvent> stateMachine) {
        this.itemSlotService = itemSlotService;
        this.paymentService = paymentService;
        this.cardService = cardService;
        this.stateMachine = stateMachine;
    }

    @Override
    public ItemSlot selectItem(String number) {
        ItemSlot itemSlot = itemSlotService.getItemSlot(number);
        if (itemSlot == null) {
            stateMachine.sendEvent(VendingMachineEvent.CANCEL_TRANSACTION);
            throw new InvalidInputException();
        }
        if (itemSlot.getStock() == 0) {
            stateMachine.sendEvent(VendingMachineEvent.OUT_OF_STOCK);
            throw new ItemUnavailableException(itemSlot.getNumber());
        }
        double initialAmount = 0;
        List<MoneyDenomination> moneyDenominationList = new ArrayList<>();
        stateMachine.getExtendedState().getVariables().put("itemNumber", number);
        stateMachine.getExtendedState().getVariables().put("amount", initialAmount);
        stateMachine.getExtendedState().getVariables().put("cost", itemSlot.getPrice());
        stateMachine.getExtendedState().getVariables().put("money", moneyDenominationList);
        stateMachine.sendEvent(VendingMachineEvent.SELECT_ITEM);
        return itemSlot;
    }

    @Override
    public double insertMoney(MoneyDenomination moneyDenomination) {
        double currentAmount = (Double) stateMachine.getExtendedState().getVariables().get("amount");
        double accumulatedAmount = currentAmount + moneyDenomination.getValue();
        stateMachine.getExtendedState().getVariables().put("amount", accumulatedAmount);
        List<MoneyDenomination> moneyDenominationList = (List<MoneyDenomination>) stateMachine.getExtendedState().getVariables().get("money");
        moneyDenominationList.add(moneyDenomination);
        stateMachine.getExtendedState().getVariables().put("money", moneyDenominationList);
        stateMachine.sendEvent(VendingMachineEvent.INSERT_MONEY);
        return accumulatedAmount;
    }

    @Override
    public void payUsingCard(CardInformation cardInformation) {
        cardService.validateCard(cardInformation);
        stateMachine.getExtendedState().getVariables().put("cardInformation", cardInformation);
        stateMachine.sendEvent(VendingMachineEvent.PAY_USING_CARD);
    }

    @Override
    public void cancelTransaction() {
        stateMachine.getExtendedState().getVariables().put("changeMap", stateMachine.getExtendedState().getVariables().get("money"));
        stateMachine.sendEvent(VendingMachineEvent.CANCEL_TRANSACTION);
    }

    @OnTransition(source = "IDLE", target = "SELECTING_PRODUCT")
    public void onSelectingProduct() {
        log.info("onSelectingProduct");
    }

    @OnTransition(source = "SELECTING_ITEM", target = "COLLECTING_MONEY")
    public void onCollectingMoney() {
        log.info("onCollectingMoney");
    }


    @OnTransition(source = "COLLECTING_MONEY", target = "PROCESSING_PAYMENT")
    public void onProcessingMoneyPayment() {
        log.info("onProcessingMoneyPayment");
        List<MoneyDenomination> money = (List<MoneyDenomination>) stateMachine.getExtendedState().getVariables().get("money");
        double amount = (Double) stateMachine.getExtendedState().getVariables().get("amount");
        double cost = (Double) stateMachine.getExtendedState().getVariables().get("cost");
        double change = amount - cost;
        Map<MoneyDenomination, Integer> changeMap = paymentService.getChange(money, change);
        if (changeMap != null) {
            stateMachine.getExtendedState().getVariables().put("change", change);
            stateMachine.getExtendedState().getVariables().put("changeMap", changeMap);
            paymentService.processPayment(money);
        } else {
            stateMachine.getExtendedState().getVariables().put("changeMap", money);
            stateMachine.getExtendedState().getVariables().put("change", amount);
            stateMachine.sendEvent(NO_CHANGE);
            throw new NoEnoughChangeException();

        }
    }

    @OnTransition(source = "SELECTING_ITEM", target = "PROCESSING_PAYMENT")
    public void onProcessingCardPayment() {
        log.info("onProcessingCardPayment");
        CardInformation cardInformation = (CardInformation) stateMachine.getExtendedState().getVariables().get("cardInformation");
        double cost = (Double) stateMachine.getExtendedState().getVariables().get("cost");
        paymentService.processPayment(cardInformation, cost);
        stateMachine.getExtendedState().getVariables().put("change", 0);
    }


    @OnTransition(source = "PROCESSING_PAYMENT", target = "DISPENSING_PRODUCT")
    public void onDispensingProduct() {
        log.info("onDispensingProduct");
        String itemNumber = (String) stateMachine.getExtendedState().getVariables().get("itemNumber");
        log.info(itemNumber);
        itemSlotService.dispenseProduct(itemNumber);
    }

    @OnTransition(target = "DISPENSING_CHANGE")
    public void onDispensingChange() {
        log.info("onDispensingChange");
        double amount = (Double) stateMachine.getExtendedState().getVariables().get("amount");
        List<MoneyDenomination> money = (List<MoneyDenomination>) stateMachine.getExtendedState().getVariables().get("money");
        double change = (Double) stateMachine.getExtendedState().getVariables().get("change");
        if (change != 0) {
            Map<MoneyDenomination, Integer> changeMap = (Map<MoneyDenomination, Integer>) stateMachine.getExtendedState().getVariables().get("changeMap");
            paymentService.dispenseChange(changeMap);
            log.info(changeMap.toString());
        }
    }

    @OnTransition(source = "SELECTING_ITEM", target = "OUT_OF_STOCK")
    public void onOutOfStock() {
        log.info("onOutOfStock");
        String itemNumber = (String) stateMachine.getExtendedState().getVariables().get("itemNumber");
        itemSlotService.dispenseProduct(itemNumber);
    }

    @OnTransition(target = "IDLE")
    public void onIdle() {
        log.info("Thank you for your business!");
        log.info("onIdle");
    }
}
