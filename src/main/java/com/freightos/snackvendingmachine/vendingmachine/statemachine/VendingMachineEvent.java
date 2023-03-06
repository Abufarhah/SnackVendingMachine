package com.freightos.snackvendingmachine.vendingmachine.statemachine;

public enum VendingMachineEvent {
    SELECT_ITEM,
    INSERT_MONEY,
    PAY_USING_CARD,
    CANCEL_TRANSACTION,
    DISPENSE_PRODUCT,
    OUT_OF_STOCK,
    NO_CHANGE
}
