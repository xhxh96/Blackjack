package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Player;

/**
 * An abstract class for user actions
 */
public abstract class Command {
    public abstract void execute(Player player, Deck deck);
}
