package com.freightos.snackvendingmachine.exceptions;

public class InvalidInputException extends RuntimeException {
    private String itemNumber;

    public InvalidInputException() {
    }

    public InvalidInputException(String itemNumber) {
        super(String.format("Invalid selection %s !", itemNumber));
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
}
