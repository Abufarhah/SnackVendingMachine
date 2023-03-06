package com.freightos.snackvendingmachine.models.item;

import com.freightos.snackvendingmachine.models.emuns.ItemType;

public abstract class Item {
    private final ItemType type;
    private String name;

    public Item(ItemType type) {
        this.type = type;
    }

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
