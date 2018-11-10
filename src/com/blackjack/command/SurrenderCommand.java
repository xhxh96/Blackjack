package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Player;

/**
 * Performs Surrender action
 */
public class SurrenderCommand extends Command {
    public static final String COMMAND_WORD = "5";

    @Override
    public void execute(Player player, Deck deck) {
        player.surrenders();
        player.turnEnds();
    }
}
