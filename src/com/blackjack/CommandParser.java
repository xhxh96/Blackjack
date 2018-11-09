package com.blackjack;

import com.blackjack.command.Command;
import com.blackjack.command.DoubleDownCommand;
import com.blackjack.command.HitCommand;
import com.blackjack.command.StandCommand;
import com.blackjack.command.SurrenderCommand;

public class CommandParser {
    public static Command parseCommand(String input) throws Exception {
        switch (input) {
        case HitCommand.COMMAND_WORD:
            return new HitCommand();
        case StandCommand.COMMAND_WORD:
            return new StandCommand();
        case DoubleDownCommand.COMMAND_WORD:
            return new DoubleDownCommand();
        case SurrenderCommand.COMMAND_WORD:
            return new SurrenderCommand();
        default:
            throw new Exception("Invalid Command");
        }
    }
}
