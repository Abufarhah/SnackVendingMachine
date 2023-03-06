package com.freightos.snackvendingmachine.models.emuns;

public enum ItemType {

    COFFEE("Coffee"),
    DRINKS("Drinks"),
    SNACKS("Snacks");

    private final String string;

    ItemType(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
