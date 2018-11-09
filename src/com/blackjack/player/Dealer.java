package com.blackjack.player;

import com.blackjack.CommandParser;
import com.blackjack.Deck;
import com.blackjack.command.Command;
import com.blackjack.util.IOUtil;

public class Dealer extends Player {
    public Dealer(){
        super();
    }

    @Override
    public void play(Deck deck) {
        while (!getHasEndedTurn()) {
            System.out.println("Dealer's Turn: ");
            String input;
            Command command;
            input = IOUtil.input.next();

            try {
                command = CommandParser.parseCommand(input);
                command.execute(this, deck);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            setHandStrength();
            checkBust();

            System.out.println("Dealer has the hand " + getHand());

            if (getHasBusted()) {
                System.out.println("Dealer has busted!");
            }
        }
    }
}
