package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Player;

public class StandCommand extends Command {
    public static final String COMMAND_WORD = "2";

    @Override
    public void execute(Player player, Deck deck) {
        player.turnEnds();
    }
}
