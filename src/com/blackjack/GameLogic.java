package com.blackjack;

import com.blackjack.player.Player;

public class GameLogic {
    public void checkGameState(Player person, Player dealer) {
        System.out.println("Dealer's bet is currently " + dealer.getBet() + " with a pool of " + dealer.getMoney());
        System.out.println("Dealer currently has the hand " + dealer.getHand());
        System.out.println();

        System.out.println("Your bet is currently " + person.getBet() + " with a pool of " + person.getMoney());
        System.out.println("You currently have the hand " + person.getHand());
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
    public boolean endGame(Player person, Player dealer) {
        return person.getMoney() < 0 || dealer.getMoney() < 0;
    }

    /**
     * Determine the winner of the game at the end of each round.
     * @return {@code Person} if there is a winner, {@code null} is it is a draw
     */
    public Player determineWinner(Player person, Player dealer) {
        if (person.getHasSurrendered()) {    // player surrendered -> dealer won
            System.out.println("Player has surrendered. Dealer won!");
            return dealer;
        } else if (dealer.getHasSurrendered()) { // dealer surrendered -> player won
            System.out.println("Dealer has surrendered. Player won!");
            return person;
        } else if (person.getHasBlackJack() && !dealer.getHasBlackJack()) {   // person blackjacked while dealer didn't -> player won
            System.out.println("Player has the Blackjack! Player won!");
            return person;
        } else if (!person.getHasBlackJack() && dealer.getHasBlackJack()) {   // dealer blackjacked while player didn't -> dealer won
            System.out.println("Dealer has the Blackjack! Dealer won!");
            return dealer;
        } else if (person.getHasBusted() && !dealer.getHasBusted()) {   // person has busted while dealer didn't -> dealer won
            System.out.println("Player has busted. Dealer won!");
            return dealer;
        } else if (!person.getHasBusted() && dealer.getHasBusted()) {   // dealer has busted while player didn't -> player won
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

    public void initializeCards(Player person, Player dealer, Deck deck) {
        dealer.setHands(deck.getCard());
        dealer.setHands(deck.getCard());
        person.setHands(deck.getCard());
        person.setHands(deck.getCard());

        person.setHandStrength();
        dealer.setHandStrength();
        person.checkBlackJack();
        dealer.checkBlackJack();

        if (person.getHasBlackJack()) {
            System.out.println("You have blackjack!");
        }

        if (dealer.getHasBlackJack()) {
            System.out.println("Dealer has blackjack!");
        }
    }

    public void conferWinner(Player winner, Player person, Player dealer) {
        if (winner == null) {
        } else if (winner == person) {
            person.setMoney(person.getMoney() + dealer.getBet());
            dealer.setMoney(dealer.getMoney() - dealer.getBet());
        } else {
            dealer.setMoney(dealer.getMoney() + person.getBet());
            person.setMoney(person.getMoney() - dealer.getBet());
        }
    }
}
