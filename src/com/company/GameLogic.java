package com.company;

import com.company.player.Player;

public class GameLogic {
    public void checkGameState(Player person, Player dealer) {
        System.out.println("Dealer's bet is currently " + dealer.getBet() + " with a pool of " + dealer.getMoney());
        System.out.println("Dealer currently has the hand " + dealer.getHand());
        System.out.println();

        System.out.println("Your bet is currently " + person.getBet() + " with a pool of " + person.getMoney());
        System.out.println("Your currently have the hand " + person.getHand());
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
        if (person.getSurrender()) {    // player surrendered -> dealer won
            return dealer;
        } else if (dealer.getSurrender()) { // dealer surrendered -> player won
            return person;
        } else if (person.getBlackJack() && !dealer.getBlackJack()) {   // person blackjacked while dealer didn't -> player won
            return person;
        } else if (!person.getBlackJack() && dealer.getBlackJack()) {   // dealer blackjacked while player didn't -> dealer won
            return dealer;
        } else if (person.getHandStrength() > dealer.getHandStrength()) {   // person has a stronger hand than dealer -> player won
            return person;
        } else if (dealer.getHandStrength() > person.getHandStrength()) {   // dealer has a stronger hand than person -> dealer won
            return dealer;
        } else {    // for all other instances, it's a draw -> no winner
            return null;
        }
    }

    public void initializeCards(Player person, Player dealer, Deck deck) {
        person.setHands(deck.getCard());
        person.setHands(deck.getCard());
        dealer.setHands(deck.getCard());
        dealer.setHands(deck.getCard());
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
