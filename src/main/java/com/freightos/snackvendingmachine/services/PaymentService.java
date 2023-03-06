package com.freightos.snackvendingmachine.services;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.money.CardInformation;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Map<MoneyDenomination, Integer> getChange(List<MoneyDenomination> money, double change);

    void processPayment(List<MoneyDenomination> money);

    void processPayment(CardInformation cardInformation, double cost);

    void dispenseChange(Map<MoneyDenomination, Integer> change);
}
