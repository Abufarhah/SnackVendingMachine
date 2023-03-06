package com.freightos.snackvendingmachine.controllers.requests;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;

import java.io.Serializable;

public class InsertMoneyRequest implements Serializable {
    private MoneyDenomination moneyDenomination;

    public InsertMoneyRequest() {
    }

    public InsertMoneyRequest(MoneyDenomination moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }

    public MoneyDenomination getMoneyDenomination() {
        return moneyDenomination;
    }

    public void setMoneyDenomination(MoneyDenomination moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }
}
