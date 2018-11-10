package com.blackjack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.blackjack.player.Dealer;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

public class GameLogic {
    public void initialGameState(Person person, Dealer dealer) {
        System.out.println("Dealer currently has the hand " + dealer.getHand().get(0) + " and an unknown card.");
        System.out.println();

        System.out.println("Your bet is currently " + person.getBet() + " with a pool of " + person.getMoney());
        System.out.println("You currently have the hand " + person.getHand());

        if (person.getHasSplit()) {
            System.out.println("You split bet is currently " + person.getSplitBet() + " with a pool of " + person.getMoney());
            System.out.println("You currently have the hand " + person.getSplitHand());
        }
    }

    /**
     * Initialize the game with a bet of $10 for both dealer and person
     */
    public void startRound(Player person, Player dealer) {
        person.startNewRound();
        dealer.startNewRound();
    }

    /**
     * Checks if the game has ended. Game ends when either person or dealer has no money left.
     */
    public boolean endGame(Person person) {
        return person.getMoney() < 0;
    }

    /**
     * Determine the winner of the game at the end of each round.
     * The aim here is to sieve out cases where player did win some money
     * @return {@code Person} if there is a winner, {@code null} is it is a draw
     */
    public Player determineWinner(Person person, Dealer dealer) {
        // If Player chose to split
        if (person.getHasSplit()) {
            if (dealer.getHasBusted()   // if dealer has busted but player has at least one unbusted hand -> player won
                    && (!person.getHasBusted()
                    || !person.getHasBustedSplit())) {
                System.out.println("Dealer has busted. Player has at least one winning hands!");
                return person;
            } else if (!dealer.getHasBusted()   // if dealer did not bust, but player busted both hands -> dealer won
                    && person.getHasBusted()
                    && person.getHasBustedSplit()) {
                System.out.println("Player has busted both split hands. Dealer won!");
                return dealer;
            } else if (person.getHandStrength() > dealer.getHandStrength()  // if person has at least one hand stronger than dealer -> player won
                    || person.getSplitHandStrength() > dealer.getHandStrength()) {
                System.out.println("Player has at least one winning hand.");
                return person;
            } else if (dealer.getHandStrength() > person.getHandStrength()  // if dealer trumps both player's hands -> dealer won
                    && dealer.getHandStrength() > person.getSplitHandStrength()) {
                System.out.println("Dealer's hand is stronger than both of player's hands. Dealer won!");
                return dealer;
            } else {        // for all other instances, it's a draw -> no winner
                System.out.println("It's a draw");
                return null;
            }
        } else {
            if (person.getHasSurrendered()) {    // player surrendered -> dealer won
                System.out.println("Player has surrendered. Dealer won!");
                return dealer;
            } else if (person.getHasBusted()){  // player busted -> dealer won
                System.out.println("Player has busted. Dealer won!");
                return dealer;
            } else if (person.getHasBlackJack() && !dealer.getHasBlackJack()) {   // person blackjacked while dealer didn't -> player won
                System.out.println("Player has the Blackjack! Player won!");
                return person;
            } else if (!person.getHasBlackJack() && dealer.getHasBlackJack()) {   // dealer blackjacked while player didn't -> dealer won
                System.out.println("Dealer has the Blackjack! Dealer won!");
                return dealer;
            } else if (!person.getHasBusted() && dealer.getHasBusted()) {         // dealer has busted while player didn't -> player won
                System.out.println("Dealer has busted. Player won!");
                return person;
            } else if (person.getHandStrength() > dealer.getHandStrength()) {   // person has a stronger hand than dealer -> player won
                System.out.println("Player has the stronger hand. Player won!");
                return person;
            } else if (dealer.getHandStrength() > person.getHandStrength()) {   // dealer has a stronger hand than person -> dealer won
                System.out.println("Dealer has the stronger hand. Dealer won!");
                return dealer;
            } else {    // for all other instances, it's a draw -> no winner
                System.out.println("It's a draw.");
                return null;
            }
        }
    }

    /**
     * Initialize the game for the start of each round, calculates the initial strength of each player and checks if
     * players have gotten blackjack
     */
    public void initializeCards(Person person, Dealer dealer, Deck deck) {
        dealer.setHand(deck.getCard());
        dealer.setHand(deck.getCard());
        person.setHand(deck.getCard());
        person.setHand(deck.getCard());

        person.setHandStrength(calculateHandStrength(person.getHand()));
        dealer.setHandStrength(calculateHandStrength(dealer.getHand()));
        person.checkBlackJack();
        dealer.checkBlackJack();

        if (person.getHasBlackJack()) {
            System.out.println("You have blackjack!");
        }
    }

    /**
     * Calculates the strength of the hand
     * @param hand whose strength is to be calculated
     * @return the strength of the hand in {@code int}
     */
    public static int calculateHandStrength(List<Card> hand) {
        int strength = 0;

        List<Card> sortedHand = new ArrayList<>(hand);

        sortedHand.sort(Comparator.comparing(Card::getIndex));

        for (Card card : sortedHand) {
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
        return strength;
    }

    /**
     * Performs the paying of player should player have won
     */
    public void dealerPaysPlayer(Person person, Dealer dealer, boolean blackjack, boolean split) {
        if (blackjack) {
            person.setMoney(person.getMoney() + person.getBet() + (person.getBet() * 2));
        } else if (split){
            person.setMoney(person.getMoney() + person.getSplitBet() + person.getSplitBet());
        } else {
            person.setMoney(person.getMoney() + person.getBet() + person.getBet());
        }
    }


    /**
     * Determines how much the player won.
     */
    public void conferWinner(Player winner, Person person, Dealer dealer) {
        // if winner == null -> draw, refund player the initial wager
        if (winner == null) {
            person.setMoney(person.getMoney() + person.getBet());
        } else if (winner == dealer && person.getHasSurrendered()) { // if player surrendered -> dealer win -> player get 1/2 wager back
            person.setMoney(person.getMoney() + (person.getBet() / 2));
        } else if (winner == person) {     // if winner is player
            if (person.getHasSplit() && dealer.getHasBusted()) {    // if winner has a split and dealer has busted
                if (!person.getHasBusted()) {   // Checks if original hand has busted
                    dealerPaysPlayer(person, dealer, false, false);
                }

                if (!person.getHasBustedSplit()) {  // Checks if split hand has busted
                    dealerPaysPlayer(person, dealer, false, true);
                }
            } else if (person.getHasSplit() && !dealer.getHasBusted()) {    // if winner has a split and dealer did not bust
                if (!person.getHasBusted() && (person.getHandStrength() > dealer.getHandStrength())) {  //checks if original hand > dealer hand
                    dealerPaysPlayer(person, dealer, false, false);
                }

                if (!person.getHasBustedSplit() && (person.getSplitHandStrength() > dealer.getHandStrength())) { // checks if split hand > dealer hand
                    dealerPaysPlayer(person, dealer, false, true);
                }
            } else if (person.getHasBlackJack()) {
                dealerPaysPlayer(person, dealer, true, false);
            } else {
                dealerPaysPlayer(person, dealer, false, false);
            }
        }
    }
}
