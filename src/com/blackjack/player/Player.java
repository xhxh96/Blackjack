package com.blackjack.player;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.blackjack.Card;
import com.blackjack.Deck;

public abstract class Player {
    private Integer bet;
    private Integer money;
    private SortedSet<Card> hand = new TreeSet<>(Comparator.comparing(Card::getIndex));
    private Integer handStrength;
    private boolean hasEndedTurn;
    private boolean hasSurrendered;
    private boolean hasBlackJack;
    private boolean hasBusted;

    public Player() {
        this.money = 100;
        this.hasEndedTurn = false;
        this.hasSurrendered = false;
        this.hasBlackJack = false;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setHands(Card card) {
        hand.add(card);
    }

    public void startNewRound() {
        hand.clear();
        bet = 10;
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

    public void setHandStrength() {
        int strength = 0;

        for (Card card : hand) {

            if (card.getIndex() <= 10) {    // for cards with index between 2 to 10
                strength += card.getIndex();
            } else if (card.getIndex() > 10 && card.getIndex() < 14) {  // for cards J, Q and K
                strength += 10;
            } else if (strength + 11 > 21) {    // for card Ace, with total strength exceeding 21 if Ace = 11
                strength += 1;
            } else {                            // for card Ace for all other cases
                strength += 11;
            }
        }

        handStrength = strength;
    }

    public Integer getBet() {
        return bet;
    }

    public Integer getMoney() {
        return money;
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

    public SortedSet<Card> getHand() {
        return hand;
    }



    public void checkBlackJack() {
        if (handStrength == 21) {
            blackJack();
            turnEnds();
        }
    }

    public void checkBust() {
        if (handStrength > 21) {
            busted();
            turnEnds();
        }
    }

    public abstract void play(Deck deck);
}
