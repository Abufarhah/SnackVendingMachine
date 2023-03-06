package com.freightos.snackvendingmachine.models.emuns;

import java.util.Arrays;

import static com.freightos.snackvendingmachine.models.emuns.MoneyType.*;

public enum MoneyDenomination {
    TEN_CENTS(COINS, 0.10),
    TWENTY_CENTS(COINS, 0.20),
    FIFTY_CENTS(COINS, 0.50),
    ONE_DOLLAR(COINS, 1),
    TWENTY_DOLLARS(NOTES, 20),
    FIFTY_DOLLARS(NOTES, 50);

    private final MoneyType moneyType;
    private final double value;

    MoneyDenomination(MoneyType moneyType, double value) {
        this.moneyType = moneyType;
        this.value = value;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public double getValue() {
        return value;
    }

    public static MoneyDenomination getByValue(double value) {
        return Arrays.stream(values()).filter(moneyDenomination -> moneyDenomination.getValue() == value).findAny().orElse(null);
    }
}
