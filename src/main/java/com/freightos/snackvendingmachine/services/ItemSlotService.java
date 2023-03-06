package com.freightos.snackvendingmachine.services;

import com.freightos.snackvendingmachine.models.item.ItemSlot;

import java.util.Map;

public interface ItemSlotService {
    ItemSlot getItemSlot(String number);

    void dispenseProduct(String number);

    Map<String, ItemSlot> getItems();
}
