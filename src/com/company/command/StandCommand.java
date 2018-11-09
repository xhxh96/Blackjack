package com.company.command;

import com.company.Deck;
import com.company.player.Player;

public class StandCommand extends Command {
    public static final String COMMAND_WORD = "2";

    @Override
    public void execute(Player player, Deck deck) {
        player.setTurnEnds();
    }
}
