package com.company;

import javafx.util.Pair;

public class Card {
    private Pair<String, Integer> type;

    public Card(String suit, Integer number) {
        type = new Pair<>(suit, number);
    }

    /**
     * @return Suit of the current card.
     */
    public String getSuit() {
        return type.getKey();
    }

    /**
     * @return index of the current card
     */
    public Integer getIndex() {
        return type.getValue();
    }

    @Override
    public String toString() {
        String cardValue;

        switch (getIndex()) {
        case 11:
            cardValue = "Jack";
            break;
        case 12:
            cardValue = "Queen";
            break;
        case 13:
            cardValue = "King";
            break;
        case 14:
            cardValue = "Ace";
            break;
        default:
            cardValue = Integer.toString(getIndex());
            break;
        }

        return getSuit() + " " + cardValue + " " + Integer.toString(getIndex());
    }
}
