package com.freightos.snackvendingmachine.thirdparties;

import com.freightos.snackvendingmachine.models.money.CardInformation;

public interface CardService {
    void validateCard(CardInformation cardInformation);

    void processPayment(CardInformation cardInformation);
}
