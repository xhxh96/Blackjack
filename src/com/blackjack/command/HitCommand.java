package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Player;

public class HitCommand extends Command {
    public static final String COMMAND_WORD = "1";

    @Override
    public void execute(Player player, Deck deck) {
        player.setHands(deck.getCard());
    }
}
