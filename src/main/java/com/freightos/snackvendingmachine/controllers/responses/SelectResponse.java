package com.freightos.snackvendingmachine.controllers.responses;

import java.io.Serializable;

public class SelectResponse implements Serializable {
    private String number;
    private double price;

    public SelectResponse() {
    }

    public SelectResponse(String number, double price) {
        this.number = number;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
