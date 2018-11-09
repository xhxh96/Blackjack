package com.company.player;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.company.Card;
import com.company.Deck;

public abstract class Player {
    private Integer bet;
    private Integer money;
    private SortedSet<Card> hand = new TreeSet<>(Comparator.comparing(Card::getIndex));
    private Integer handStrength;
    private boolean turnEnds;
    private boolean surrender;
    private boolean blackJack;

    public Player() {
        this.money = 100;
        this.turnEnds = false;
        this.surrender = false;
        this.blackJack = false;
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
        turnEnds = false;
        surrender = false;
        blackJack = false;
    }

    public void setTurnEnds() {
        turnEnds = true;
    }

    public void setSurrender() {
        surrender = true;
    }

    public void setBlackJack() {
        blackJack = true;
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

    public boolean getTurnEnds() {
        return turnEnds;
    }

    public boolean getSurrender() {
        return surrender;
    }

    public boolean getBlackJack() {
        return blackJack;
    }

    public Integer getHandStrength() {
        return handStrength;
    }

    public SortedSet<Card> getHand() {
        return hand;
    }



    public void checkBlackJack() {
        if (handStrength == 21) {
            setBlackJack();
            setTurnEnds();
        }
    }

    public abstract void play(Deck deck);
}
