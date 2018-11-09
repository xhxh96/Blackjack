package com.company.player;

import com.company.CommandParser;
import com.company.Deck;
import com.company.command.Command;
import com.company.util.IOUtil;

public class Person extends Player {
    public Person(){
        super();
    }

    @Override
    public void play(Deck deck) {
        setHandStrength();
        checkBlackJack();

        while (!getTurnEnds()) {
            System.out.println("Your Turn: ");
            String input;
            Command command;
            input = IOUtil.input.nextLine();

            try {
                command = CommandParser.parseCommand(input);
                command.execute(this, deck);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            setHandStrength();
        }
        setTurnEnds();
    }
}
