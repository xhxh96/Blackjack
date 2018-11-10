package com.blackjack;

import com.blackjack.command.Command;
import com.blackjack.command.DoubleDownCommand;
import com.blackjack.command.HitCommand;
import com.blackjack.command.SplitCommand;
import com.blackjack.command.StandCommand;
import com.blackjack.command.SurrenderCommand;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

public class CommandParser {
    /**
     * Handles the action for player.
     * @throws Exception if the action is invalid.
     */
    public static Command parseCommand(Person person, String input) throws Exception {
        switch (input) {
        case HitCommand.COMMAND_WORD:
            return new HitCommand();
        case StandCommand.COMMAND_WORD:
            return new StandCommand();
        case DoubleDownCommand.COMMAND_WORD:
            // Checks if this is the first action used
            if (person.getHand().size() > 2) {
                throw new Exception("Invalid command");
            }
            return new DoubleDownCommand();
        case SplitCommand.COMMAND_WORD:
            // Checks if this is the first action used, and if the cards dealt care valid for a split
            if (person.getHand().size() > 2 || person.getHasSplit()
                    || !(person.getHand().get(0).equals(person.getHand().get(1)))) {
                throw new Exception("Invalid command");
            }

            return new SplitCommand();
        case SurrenderCommand.COMMAND_WORD:
            // Checks if this is the first action used
            if (person.getHand().size() > 2 || person.getHasSplit()) {
                throw new Exception("Invalid command");
            }
            return new SurrenderCommand();
        default:
            throw new Exception("Invalid Command");
        }
    }
}
