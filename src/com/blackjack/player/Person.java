package com.blackjack.player;

import com.blackjack.CommandParser;
import com.blackjack.Deck;
import com.blackjack.command.Command;
import com.blackjack.util.IOUtil;

public class Person extends Player {
    public Person(){
        super();
    }

    @Override
    public void play(Deck deck) {
        while (!getHasEndedTurn()) {
            System.out.println("Your Turn: ");
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

            System.out.println("You have the hand " + getHand());

            if (getHasBusted()) {
                System.out.println("You have busted!");
            }
        }
    }
}
