package com.company.command;

import com.company.Deck;
import com.company.player.Player;

public abstract class Command {
    public abstract void execute(Player player, Deck deck);
}
