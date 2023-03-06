package com.freightos.snackvendingmachine.controllers.responses;

public class InsertMoneyResponse {
    private double amount;
    private final String message;

    public InsertMoneyResponse(double amount) {
        this.amount = amount;
        this.message = String.format("Inserted amount: %s", amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }
}
