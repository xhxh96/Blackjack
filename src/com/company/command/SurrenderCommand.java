package com.company.command;

import com.company.Deck;
import com.company.player.Player;

public class SurrenderCommand extends Command {
    public static final String COMMAND_WORD = "5";

    @Override
    public void execute(Player player, Deck deck) {
        player.setSurrender();
        player.setTurnEnds();
    }
}
