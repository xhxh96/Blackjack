package com.blackjack.player;

import java.util.concurrent.TimeUnit;

import com.blackjack.Deck;
import com.blackjack.GameLogic;
import com.blackjack.command.Command;
import com.blackjack.command.HitCommand;
import com.blackjack.command.StandCommand;

/**
 * Represents the dealer
 */
public class Dealer extends Player {
    public Dealer(){
        super();
    }

    @Override
    public void play(Deck deck) {
        while (!hasEndedTurn) {
            System.out.println("\n=====================");
            System.out.println("Dealer's Turn: ");

            // Delay 3 seconds to mimic an actual dealer thinking
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (handStrength < 17) {    // if dealer's strength < 17, dealer will hit
                System.out.println("Dealer has chosen to hit.");
                Command hitCommand = new HitCommand();
                hitCommand.execute(this, deck);
            } else {                    // else dealer will stand
                System.out.println("Delaer has chosen to stand.");
                Command standCommand = new StandCommand();
                standCommand.execute(this, deck);
            }


            handStrength = GameLogic.calculateHandStrength(hand);
            checkBust();

            System.out.println("Dealer has the hand " + getHand());

            if (hasBusted) {
                System.out.println("Dealer has busted!");
            }
        }
    }
}
