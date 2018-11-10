package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

/**
 * Performs Stand action
 */
public class StandCommand extends Command {
    public static final String COMMAND_WORD = "2";

    @Override
    public void execute(Player player, Deck deck) {
        if (!player.getHasEndedTurn()) {
            player.turnEnds();
        } else if (player instanceof Person) {  // if this command is still executed by Person -> Person has split hand
            ((Person) player).endedSplit();
        }
    }
}
