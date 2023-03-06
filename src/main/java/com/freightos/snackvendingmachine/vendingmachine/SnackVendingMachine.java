package com.freightos.snackvendingmachine.vendingmachine;

import com.freightos.snackvendingmachine.models.emuns.Currency;
import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.models.item.SnackItem;
import com.freightos.snackvendingmachine.models.money.MoneySlot;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.freightos.snackvendingmachine.models.emuns.ItemType.SNACKS;

@Component
public class SnackVendingMachine extends VendingMachine<SnackItem> {

    public SnackVendingMachine() {
        super(SNACKS);
    }

    public SnackVendingMachine(int numberOfRows, int numberOfColumns, double accountBalance, Currency currency, Map<String, ItemSlot> numberToSlotMap, Map<MoneyDenomination, MoneySlot> denominationToSlotMap) {
        super(SNACKS, numberOfRows, numberOfColumns, accountBalance, currency, numberToSlotMap, denominationToSlotMap);
    }
}
