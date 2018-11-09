package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Player;

public abstract class Command {
    public abstract void execute(Player player, Deck deck);
}
