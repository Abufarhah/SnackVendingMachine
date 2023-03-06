package com.freightos.snackvendingmachine.controllers.requests;

import java.io.Serializable;

public class SelectRequest implements Serializable {
    private String number;

    public SelectRequest() {
    }

    public SelectRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
