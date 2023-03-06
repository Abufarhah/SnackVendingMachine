package com.freightos.snackvendingmachine.services.implementations;

import com.freightos.snackvendingmachine.helpers.MoneyChangeHelper;
import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.money.CardInformation;
import com.freightos.snackvendingmachine.models.money.MoneySlot;
import com.freightos.snackvendingmachine.services.PaymentService;
import com.freightos.snackvendingmachine.vendingmachine.SnackVendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    SnackVendingMachine snackVendingMachine;

    @Override
    public Map<MoneyDenomination, Integer> getChange(List<MoneyDenomination> money, double change) {
        MoneyChangeHelper moneyChangeHelper = new MoneyChangeHelper(snackVendingMachine);
        return moneyChangeHelper.getChange(money, change);
    }

    @Override
    public void processPayment(List<MoneyDenomination> money) {
        money.forEach(moneyDenomination -> {
            MoneySlot moneySlot = snackVendingMachine.getDenominationToSlotMap().get(moneyDenomination);
            moneySlot.setCount(moneySlot.getCount() + 1);
        });
    }

    @Override
    public void processPayment(CardInformation cardInformation, double cost) {
        snackVendingMachine.setAccountBalance(snackVendingMachine.getAccountBalance() + cost);
    }

    @Override
    public void dispenseChange(Map<MoneyDenomination, Integer> change) {
        change.forEach((moneyDenomination, count) -> {
            MoneySlot moneySlot = snackVendingMachine.getDenominationToSlotMap().get(moneyDenomination);
            moneySlot.setCount(moneySlot.getCount() - count);
        });
    }


}
