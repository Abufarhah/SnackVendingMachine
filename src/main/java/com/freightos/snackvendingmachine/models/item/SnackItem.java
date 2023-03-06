package com.freightos.snackvendingmachine.models.item;

import static com.freightos.snackvendingmachine.models.emuns.ItemType.SNACKS;

public class SnackItem extends Item {
    public SnackItem() {
        super(SNACKS);
    }

    public SnackItem(String name) {
        super(SNACKS, name);
    }
}
