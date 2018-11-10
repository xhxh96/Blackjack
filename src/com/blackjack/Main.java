package com.blackjack;

import com.blackjack.player.Dealer;
import com.blackjack.player.Person;
import com.blackjack.player.Player;
import com.blackjack.util.IOUtil;

/**
 * A classic game of Blackjack.
 *
 * House Rules:
 * 1.   No resplitting - split is only allowed for the first 2 cards.
 * 2.   No other restrictions on split card - split cards are allowed to double down; players can hit more than one card
 *      for splitting of Aces.
 * 3.   Early surrender is allowed. No late surrender is offered in this house.
 * 4.   No insurance bet.
 * 5.   Blackjack payout at 2:1.
 */

public class Main {
    private static final String WELCOME_MESSAGE = "Welcome to Alice Blackjack! House Rules are as follows:\n" +
            "1. Blackjack payout remains at 2:1.\n" +
            "2. There is no insurance bet.\n" +
            "3. Split or Surrender are only valid if they are your first move.\n" +
            "4. No resplitting is allowed - split is limited to at most 2 hands.\n" +
            "5. Buy-in has a minimum of $100 and maximum of (also) $100. Stake is at $10.\n" +
            "6. Doubledown for split is allowed.\n" +
            "To begin game, enter Y. Otherwise, enter N to exit.";

    public static void main(String[] args) {
        String userInput;
        Person person = new Person();
        Dealer dealer = new Dealer();
        GameLogic gameLogic = new GameLogic();

        System.out.println(WELCOME_MESSAGE);
        userInput = IOUtil.input.next();
        userInput = userInput.toUpperCase();

        while (!gameLogic.endGame(person) && userInput.equals("Y")) {
            // Initialize a new deck
            Deck deck = new Deck();

            // Initialize the game for both dealer and person
            gameLogic.startRound(person, dealer);

            // Initialize the round by dealing 2 cards to each player, calculates the hand strength and checks for
            // blackjack
            gameLogic.initializeCards(person, dealer, deck);

            gameLogic.initialGameState(person, dealer);

            // If player does not have Blackjack
            if (!person.getHasBlackJack()) {
                // person starts first
                person.play(deck);

                // if player chooses not to surrender and dealer does not have Blackjack
                if (!person.getHasSurrendered() && !dealer.getHasBlackJack()) {
                    // dealer goes next
                    dealer.play(deck);
                }
            }

            Player winner = gameLogic.determineWinner(person, dealer);
            gameLogic.conferWinner(winner, person, dealer);

            System.out.println("Your current pool is $" + person.getMoney());
            System.out.println("======== END OF ROUND ========\n\n");
            System.out.println("Continue? (Y/N)");
            userInput = IOUtil.input.next();
            userInput = userInput.toUpperCase();
        }
        System.out.println("You have won: " + Integer.toString(person.getMoney() - Person.INITIAL_MONEY));
    }
}
