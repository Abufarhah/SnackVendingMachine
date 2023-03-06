package com.freightos.snackvendingmachine.thirdparties.implementations;

import com.freightos.snackvendingmachine.models.money.CardInformation;
import com.freightos.snackvendingmachine.thirdparties.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Override
    public void validateCard(CardInformation cardInformation) {

    }

    @Override
    public void processPayment(CardInformation cardInformation) {
    }
}
