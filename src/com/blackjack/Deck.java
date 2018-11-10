package com.blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Represents a deck of 52 poker cards
 */
public class Deck {
    private Stack<Card> cards = new Stack<>();
    private List<String> suits = Arrays.asList("Heart", "Spade", "Club", "Diamond");

    public Deck() {
        initializeDeck();
        shuffleDeck();

    }

    /**
     * Initialize the deck with 52 cards
     * Jack has the index of 11, Queen has the index of 12 and King has the index of 13, Ace has an index of 14
     */
    public void initializeDeck() {
        for (int i = 2; i <= 14; i++) {
            for (String suit : suits) {
                cards.add(new Card(suit, i));

            }
        }
    }

    /**
     * Shuffles the deck
     */
    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /**
     * Get the top card of the deck
     * @return {@code Pair<String, Integer>} that represents the card
     */
    public Card getCard() {
        return cards.pop();
    }
}
