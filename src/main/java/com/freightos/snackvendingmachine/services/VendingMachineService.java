package com.freightos.snackvendingmachine.services;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.models.money.CardInformation;

public interface VendingMachineService {
    ItemSlot selectItem(String number);

    double insertMoney(MoneyDenomination moneyDenomination);

    void payUsingCard(CardInformation cardInformation);

    void cancelTransaction();
}
