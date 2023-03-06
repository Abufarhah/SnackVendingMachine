package com.freightos.snackvendingmachine.services.implementations;

import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.services.ItemSlotService;
import com.freightos.snackvendingmachine.vendingmachine.SnackVendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemSlotServiceImpl implements ItemSlotService {

    @Autowired
    SnackVendingMachine snackVendingMachine;

    @Override
    public ItemSlot getItemSlot(String number) {
        return snackVendingMachine.getNumberToSlotMap().get(number);
    }

    @Override
    public void dispenseProduct(String number) {
        ItemSlot itemSlot = snackVendingMachine.getNumberToSlotMap().get(number);
        itemSlot.setStock(itemSlot.getStock() - 1);
    }

    @Override
    public Map<String, ItemSlot> getItems() {
        return snackVendingMachine.getNumberToSlotMap();
    }


}
