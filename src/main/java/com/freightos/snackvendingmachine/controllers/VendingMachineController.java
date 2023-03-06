package com.freightos.snackvendingmachine.controllers;

import com.freightos.snackvendingmachine.controllers.requests.CardRequest;
import com.freightos.snackvendingmachine.controllers.requests.InsertMoneyRequest;
import com.freightos.snackvendingmachine.controllers.requests.SelectRequest;
import com.freightos.snackvendingmachine.controllers.responses.APIResponseBody;
import com.freightos.snackvendingmachine.controllers.responses.InsertMoneyResponse;
import com.freightos.snackvendingmachine.controllers.responses.SelectResponse;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.services.VendingMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vending-machine")
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @PostMapping("/select-item")
    public ResponseEntity<APIResponseBody> select(@RequestBody SelectRequest selectRequest) {
        ItemSlot itemSlot = vendingMachineService.selectItem(selectRequest.getNumber());
        APIResponseBody body = new APIResponseBody(new SelectResponse(itemSlot.getNumber(), itemSlot.getPrice()));
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/insert-money")
    public ResponseEntity<APIResponseBody> insertMoney(@RequestBody InsertMoneyRequest insertMoneyRequest) {
        double amount = vendingMachineService.insertMoney(insertMoneyRequest.getMoneyDenomination());
        APIResponseBody body = new APIResponseBody(new InsertMoneyResponse(amount));
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/pay-using-card")
    public ResponseEntity payUsingCard(@RequestBody CardRequest cardRequest) {
        vendingMachineService.payUsingCard(cardRequest.getCardInformation());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel-transaction")
    public ResponseEntity cancelTransaction() {
        vendingMachineService.cancelTransaction();
        return ResponseEntity.ok().build();
    }
}


