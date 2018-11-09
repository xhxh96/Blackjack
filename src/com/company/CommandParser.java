package com.company;

import com.company.command.Command;
import com.company.command.DoubleDownCommand;
import com.company.command.HitCommand;
import com.company.command.StandCommand;
import com.company.command.SurrenderCommand;

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
