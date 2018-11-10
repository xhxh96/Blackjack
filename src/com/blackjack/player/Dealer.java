package com.blackjack.player;

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
            System.out.println("Dealer's Turn: ");

            if (handStrength <= 17) {
                System.out.println("Dealer has chosen to hit.");
                Command hitCommand = new HitCommand();
                hitCommand.execute(this, deck);
            } else {
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
