package com.freightos.snackvendingmachine.vendingmachine;

import com.freightos.snackvendingmachine.models.emuns.ItemType;
import com.freightos.snackvendingmachine.models.item.Item;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.models.emuns.Currency;
import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.money.MoneySlot;

import java.util.Map;
import java.util.SortedMap;

public abstract class VendingMachine<T extends Item> {
    private final ItemType type;
    private int numberOfRows;
    private int numberOfColumns;
    private double accountBalance;
    private Currency currency;
    private Map<String, ItemSlot> numberToSlotMap;
    private Map<MoneyDenomination, MoneySlot> denominationToSlotMap;

    public VendingMachine(ItemType type) {
        this.type = type;
    }

    public VendingMachine(ItemType type, int numberOfRows, int numberOfColumns, double accountBalance, Currency currency, Map<String, ItemSlot> numberToSlotMap, Map<MoneyDenomination, MoneySlot> denominationToSlotMap) {
        this.type = type;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.numberToSlotMap = numberToSlotMap;
        this.denominationToSlotMap = denominationToSlotMap;
    }

    public ItemType getType() {
        return type;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getCacheBalance() {
        return denominationToSlotMap.values().stream().mapToDouble(MoneySlot::getTotal).sum();
    }

    public double getTotalBalance() {
        return getAccountBalance() + getCacheBalance();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Map<String, ItemSlot> getNumberToSlotMap() {
        return numberToSlotMap;
    }

    public void setNumberToSlotMap(Map<String, ItemSlot> numberToSlotMap) {
        this.numberToSlotMap = numberToSlotMap;
    }

    public Map<MoneyDenomination, MoneySlot> getDenominationToSlotMap() {
        return denominationToSlotMap;
    }

    public void setDenominationToSlotMap(Map<MoneyDenomination, MoneySlot> denominationToSlotMap) {
        this.denominationToSlotMap = denominationToSlotMap;
    }
}
