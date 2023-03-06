package com.freightos.snackvendingmachine.controllers.requests;

import com.freightos.snackvendingmachine.models.money.CardInformation;

import java.io.Serializable;

public class CardRequest implements Serializable {
    private CardInformation cardInformation;

    public CardRequest() {
    }

    public CardRequest(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }
}
