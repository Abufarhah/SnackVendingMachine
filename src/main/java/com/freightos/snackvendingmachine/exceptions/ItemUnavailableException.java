package com.freightos.snackvendingmachine.exceptions;

public class ItemUnavailableException extends RuntimeException {

    private String itemNumber;

    public ItemUnavailableException() {
    }

    public ItemUnavailableException(String itemNumber) {
        super(String.format("The selected number %s is unavailable!", itemNumber));
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
}
