package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

/**
 * Performs Split action
 */
public class SplitCommand extends Command {
    public static final String COMMAND_WORD = "4";

    @Override
    public void execute(Player player, Deck deck) {
        if (player instanceof Person) {
            // Performs split operation
            ((Person) player).split();
            System.out.println("You have chosen to split. Your hands are:");
            System.out.println("Hand: " + player.getHand());
            System.out.println("Split Hand: " + ((Person) player).getSplitHand());
        }
    }
}
