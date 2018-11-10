package com.blackjack.command;

import com.blackjack.Deck;
import com.blackjack.player.Person;
import com.blackjack.player.Player;

/**
 * Performs Double Down action
 */
public class DoubleDownCommand extends Command {
    public static final String COMMAND_WORD = "3";

    @Override
    public void execute(Player player, Deck deck) {
        if (!player.getHasEndedTurn()) {

            if (player instanceof Person) { // if it is not the dealer
                ((Person) player).setMoney(((Person) player).getMoney() - ((Person) player).getBet());
                ((Person) player).setBet(((Person) player).getBet() * 2);
            }

            player.setHand(deck.getCard());
            player.turnEnds();
            System.out.println("You've chosen to double down your hand.");

        } else if (player instanceof Person) {  // if this command is still executed by Person -> Person has split hand
            ((Person)player).setMoney(((Person)player).getMoney() - ((Person) player).getSplitBet());
            ((Person) player).setSplitBet(((Person) player).getSplitBet() * 2);
            ((Person) player).setSplitHand(deck.getCard());
            ((Person) player).endedSplit();
            System.out.println("You've chosen to double down your split hand.");
        }
    }
}
