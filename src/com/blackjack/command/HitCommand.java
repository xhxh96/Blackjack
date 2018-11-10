package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

/**
 * Performs Hit action
 */
public class HitCommand extends Command {
    public static final String COMMAND_WORD = "1";

    @Override
    public void execute(Player player, Deck deck) {
        if (!player.getHasEndedTurn()) {
            player.setHand(deck.getCard());
        } else if (player instanceof Person) {  // if this command is still executed by Person -> Person has split hand
            ((Person) player).setSplitHand(deck.getCard());
        }
    }
}
