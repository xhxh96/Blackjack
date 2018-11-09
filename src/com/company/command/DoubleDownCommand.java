package com.company.command;

import com.company.Deck;
import com.company.player.Player;

public class DoubleDownCommand extends Command {
    public static final String COMMAND_WORD = "3";

    @Override
    public void execute(Player player, Deck deck) {
        player.setMoney(player.getMoney() - player.getBet());
        player.setBet(player.getBet() * 2);
        player.setHands(deck.getCard());
        player.setTurnEnds();
    }
}
