package com.freightos.snackvendingmachine.controllers;

import com.freightos.snackvendingmachine.controllers.responses.APIResponseBody;
import com.freightos.snackvendingmachine.services.ItemSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemSlotService itemSlotService;

    public ItemController(ItemSlotService itemSlotService) {
        this.itemSlotService = itemSlotService;
    }

    @GetMapping
    public ResponseEntity<APIResponseBody> getItems() {
        APIResponseBody body = new APIResponseBody(itemSlotService.getItems());
        return ResponseEntity.ok().body(body);
    }
}
