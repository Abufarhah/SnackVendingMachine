package com.freightos.snackvendingmachine.exceptions;

public class NoEnoughChangeException extends RuntimeException {

    public NoEnoughChangeException() {
        super("No enough change!");
    }
}
