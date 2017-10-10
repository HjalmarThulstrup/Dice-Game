/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author Hjalmar
 */
public class Human implements Player {

    private int score, ans, tempscore, looseround = 3, counter = 1;
    private String temp, strans, name;
    private ArrayList<Integer> saves = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private YesNoHandler yn = new YesNoHandler();

    public Human(String name) {
        this.name = name;
        this.score = 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void takeTurn(Die d) {
        boolean rollAgain = true;
        boolean chooseAgain = true;
        boolean chooseAgain2 = true;
        outerloop:
        while (rollAgain) {
            int diceamount = d.getAmount();
            d.rollDice();
            
            System.out.println(name + "'s turn.");
            if (saves.size() != d.getFirstamount()) {
                int[] roll = new int[diceamount];
                for (int i = 0; i < diceamount; i++) {
                    roll[i] = d.getDiceResult(i);
                }
                System.out.println(Arrays.toString(roll));
                counter = diceamount;

                do {
                    do {
                        System.out.println("Which dice do you want to save?");
                        ans = sc.nextInt();

                        if (looseround == 1) {
                            System.out.println("You dont have any dice to save, your turn is over and you loose your points for this round");
                            System.out.println("-----------------------------------");
                            tempscore = 0;
                            looseround = 2;
                            saves.clear();
                            d.resetAmount();
                            roll = null;
                            break outerloop;

                        } else {
                            if (!saves.contains(ans)) {
                                if (IntStream.of(roll).anyMatch(x -> x == ans)) {
                                    for (int i = 0; i < roll.length; i++) {
                                        if (ans == roll[i]) {
                                            saves.add(roll[i]);
                                            tempscore += ans;
                                            roll[i] = 0;
                                            counter--;
                                            looseround = 3;
                                        }
                                    }
                                    chooseAgain2 = false;
                                    chooseAgain = false;

                                } else {
                                    System.out.println("You didn't roll a " + ans + ", choose again.");
                                    chooseAgain2 = true;
                                }
                            } else {
                                looseround--;
                                System.out.println("You have already saved those pips");
                                System.out.println(looseround + " attempts left before you loose.");
                                chooseAgain = true;
                            }
                        }
                    } while (chooseAgain2);
                } while (chooseAgain);
                if (saves.size() == d.getFirstamount()) {
                    System.out.println("You have saved all the dice! you get double points!");
                    score = tempscore * 2;
                    System.out.println(this.name + "'s score " + score);
                    System.out.println("-----------------------------------");
                    saves.clear();
                    d.resetAmount();
                    break outerloop;
                } else {
                    d.setAmount(counter);
                    System.out.println(this.name + "'s score is " + tempscore);
                    System.out.println("Saved dice: " + saves);
                    roll = null;
                    counter = 1;
                    System.out.println("Do you want to roll again?");
                    strans = sc.next();
                    
                    if (yn.yesNoHandler(strans)) {
                        rollAgain = true;

                    } else {
                        System.out.println("-----------------------------------");
                        score = tempscore;
                        saves.clear();
                        d.resetAmount();
                        rollAgain = false;
                    }
                }
            }
        }
    }
    @Override
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player o) {
        return this.score - o.getScore();
    }

}
