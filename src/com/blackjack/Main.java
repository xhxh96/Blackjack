package com.blackjack;

import com.blackjack.player.Dealer;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

public class Main {

    public static void main(String[] args) {
        Player person = new Person();
        Player dealer = new Dealer();
        GameLogic gameLogic = new GameLogic();

        while (!gameLogic.endGame(person, dealer)) {
            // Initialize a new deck
            Deck deck = new Deck();

            // Initialize the game for both dealer and person
            gameLogic.startRound(person, dealer);

            // Initialize the round by dealing 2 cards to each player, calculates the hand strength and checks for blackjack
            gameLogic.initializeCards(person, dealer, deck);

            gameLogic.checkGameState(person, dealer);

            // If neither player nor dealer has blackjack
            if (!person.getHasBlackJack() && !dealer.getHasBlackJack()) {
                // person starts first
                person.play(deck);

                // dealer goes next
                dealer.play(deck);
            }

            Player winner = gameLogic.determineWinner(person, dealer);

            gameLogic.conferWinner(winner, person, dealer);
            System.out.println("======== END OF ROUND ========\n\n");
        }
    }
}
