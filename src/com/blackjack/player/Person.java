package com.blackjack.player;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.Card;
import com.blackjack.CommandParser;
import com.blackjack.Deck;
import com.blackjack.GameLogic;
import com.blackjack.command.Command;
import com.blackjack.util.IOUtil;

/**
 * Represents the Person (player)
 */
public class Person extends Player {
    public static final Integer INITIAL_MONEY = 100;
    public static final Integer DEFAULT_BET = 10;

    private Integer bet;
    private Integer money;
    private Integer splitBet;
    private Integer splitHandStrength;
    private List<Card> splitHand;
    private boolean hasSplit;
    private boolean hasEndedSplit;
    private boolean hasBustedSplit;

    public Person(){
        super();
        money = INITIAL_MONEY;
        bet = DEFAULT_BET;
        hasSplit = false;
        hasBustedSplit = false;
        hasEndedSplit = false;
        splitHand = null;
    }

    /**
     * Performs the split operation
     */
    public void split() {
        hasSplit = true;

        // Get additional wage for the split hand
        splitBet = bet;
        // Decrease the total money pool by the current wage
        money = money - splitBet;

        splitHand = new ArrayList<>();
        // Set the first card of the split hand to be the first card of the current hand
        setSplitHand(hand.get(0));
        hand.remove(0);

    }

    /**
     * Reset the split attributes
     */
    public void resetSplit() {
        hasSplit = false;
        hasBustedSplit = false;
        hasEndedSplit = false;
        splitHand = null;
    }

    public void endedSplit() {
        hasEndedSplit = true;
    }

    public void bustedSplit() {
        hasBustedSplit = true;
    }

    /**
     * Checks if player has burst the split hand
     */
    public void checkSplitBust() {
        if (splitHandStrength > BLACKJACK_VALUE) {
            bustedSplit();
            endedSplit();
        }
    }

    public Integer getBet() {
        return bet;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getSplitBet() {
        return splitBet;
    }

    public boolean getHasSplit() {
        return hasSplit;
    }

    public boolean getHasBustedSplit() {
        return hasBustedSplit;
    }

    public Integer getSplitHandStrength() {
        return splitHandStrength;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public void setSplitBet(Integer splitBet) {
        this.splitBet = splitBet;
    }

    public List<Card> getSplitHand() {
        return splitHand;
    }

    public void setSplitHand(Card card) {
        splitHand.add(card);
    }

    @Override
    public void startNewRound() {
        super.startNewRound();
        bet = DEFAULT_BET;
        money = money - bet;
        resetSplit();
    }

    @Override
    public void play(Deck deck) {
        while (!hasEndedTurn) {
            System.out.println("\n\n=====================");
            System.out.println("Your Turn: ");
            System.out.println("Your current hand is " + getHand());
            System.out.println("Choose (1)Hit, (2)Stand, (3)Double Down, (4)Splilt, (5)Surrender:");

            String input;
            Command command;
            input = IOUtil.input.next();

            try {
                command = CommandParser.parseCommand(this, input);
                command.execute(this, deck);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            handStrength = GameLogic.calculateHandStrength(hand);
            checkBust();

            System.out.println("Your new hand is " + getHand());


            if (getHasBusted()) {
                System.out.println("You have busted!");
            }
        }

        // Player's turn again if there is a split
        while (hasSplit && !hasEndedSplit) {
            System.out.println("\n\n=====================");
            System.out.println("Your Turn (Split Hand): ");
            System.out.println("Your current split hand is " + getSplitHand());
            System.out.println("Choose (1)Hit, (2)Stand, (3)Double Down, (4)Split, (5)Surrender:");

            String input;
            Command command;
            input = IOUtil.input.next();

            try {
                command = CommandParser.parseCommand(this, input);
                command.execute(this, deck);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            splitHandStrength = GameLogic.calculateHandStrength(splitHand);
            checkSplitBust();

            System.out.println("Your new split hand " + getSplitHand());

            if (hasBustedSplit) {
                System.out.println("You have busted your split hand!");
            }
        }
    }
}
