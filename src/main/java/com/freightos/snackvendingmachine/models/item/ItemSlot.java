package com.freightos.snackvendingmachine.models.item;

public class ItemSlot {
    private String number;
    private double price;
    private int stock;
    private Item item;

    public ItemSlot() {
    }

    public ItemSlot(String number, double price, int stock, Item item) {
        this.number = number;
        this.price = price;
        this.stock = stock;
        this.item = item;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
