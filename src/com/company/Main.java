package com.company;

import com.company.player.Dealer;
import com.company.player.Person;
import com.company.player.Player;

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

            // Initialize the round by dealing 2 cards to each player
            gameLogic.initializeCards(person, dealer, deck);


            gameLogic.checkGameState(person, dealer);

            // person starts first
            person.play(deck);

            // dealer goes next
            dealer.play(deck);

            gameLogic.checkGameState(person, dealer);

            Player winner = gameLogic.determineWinner(person, dealer);

            if (winner == null) {
                System.out.println("It's a draw .... ");
            } else if (winner == person) {
                System.out.println("You won!");
            } else {
                System.out.println("Dealer won ... ");
            }

            gameLogic.conferWinner(winner, person, dealer);
            System.out.println("======== END OF ROUND ========\n\n");
        }
    }
}
