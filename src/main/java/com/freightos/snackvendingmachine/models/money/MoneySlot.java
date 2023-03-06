package com.freightos.snackvendingmachine.models.money;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;

public class MoneySlot {
    private int count;
    private MoneyDenomination moneyDenomination;

    public MoneySlot() {
    }

    public MoneySlot(int count, MoneyDenomination moneyDenomination) {
        this.count = count;
        this.moneyDenomination = moneyDenomination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MoneyDenomination getMoneyDenomination() {
        return moneyDenomination;
    }

    public void setMoneyDenomination(MoneyDenomination moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }

    public double getTotal() {
        return count * moneyDenomination.getValue();
    }
}
