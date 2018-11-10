package com.blackjack.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

import com.blackjack.Card;
import com.blackjack.Deck;

/**
 * An abstract class to represent the players involved
 */
public abstract class Player {
    public static final Integer BLACKJACK_VALUE = 21;

    protected List<Card> hand = new ArrayList<>();
    protected Integer handStrength;
    protected boolean hasEndedTurn;
    protected boolean hasSurrendered;
    protected boolean hasBlackJack;
    protected boolean hasBusted;

    public Player() {
        this.hasEndedTurn = false;
        this.hasSurrendered = false;
        this.hasBlackJack = false;
    }

    /**
     * Adds {@code Card} into the {@code Player} hand
     * @param card to be added
     */
    public void setHand(Card card) {
        hand.add(card);
    }

    /**
     * Reset player stats to start a new round of the game
     */
    public void startNewRound() {
        hand.clear();
        hasEndedTurn = false;
        hasSurrendered = false;
        hasBlackJack = false;
        hasBusted = false;
    }

    public void turnEnds() {
        hasEndedTurn = true;
    }

    public void surrenders() {
        hasSurrendered = true;
    }

    public void blackJack() {
        hasBlackJack = true;
    }

    public void busted() {
        hasBusted = true;
    }

    public void setHandStrength(int handStrength) {
        this.handStrength = handStrength;
    }

    public boolean getHasEndedTurn() {
        return hasEndedTurn;
    }

    public boolean getHasSurrendered() {
        return hasSurrendered;
    }

    public boolean getHasBlackJack() {
        return hasBlackJack;
    }

    public boolean getHasBusted() {
        return hasBusted;
    }

    public Integer getHandStrength() {
        return handStrength;
    }

    public List<Card> getHand() {
        return hand;
    }

    /**
     * Checks of Player has gotten Blackjack
     */
    public void checkBlackJack() {
        if (handStrength.equals(BLACKJACK_VALUE)) {
            blackJack();
            turnEnds();
        }
    }

    /**
     * Checks if player has busted
     */
    public void checkBust() {
        if (handStrength > BLACKJACK_VALUE) {
            busted();
            turnEnds();
        }
    }

    public abstract void play(Deck deck);
}
