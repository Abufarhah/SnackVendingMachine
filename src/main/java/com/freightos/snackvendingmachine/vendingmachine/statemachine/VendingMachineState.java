package com.freightos.snackvendingmachine.vendingmachine.statemachine;

public enum VendingMachineState {
    IDLE,
    SELECTING_ITEM,
    COLLECTING_MONEY,
    PROCESSING_PAYMENT,
    DISPENSING_PRODUCT,
    DISPENSING_CHANGE,
    OUT_OF_STOCK
}
