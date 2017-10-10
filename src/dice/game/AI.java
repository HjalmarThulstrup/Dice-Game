/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Hjalmar
 */
public class AI implements Player {

    private int score, ans, tempscore = 0, looseround, counter, roundcount;
    private String temp, strans, name;
    private ArrayList<Integer> saves = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private YesNoHandler yn = new YesNoHandler();

    public AI(String name) {
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
        counter = 1;
        roundcount = 0;
        looseround = d.getAmount();
        outerloop:
        while (rollAgain) {
            int diceamount = d.getAmount();
            d.rollDice();
            System.out.println(name + "'s turn.");

            int[] roll = new int[diceamount];
            for (int i = 0; i < diceamount; i++) {
                roll[i] = d.getDiceResult(i);
            }
            Arrays.sort(roll);
            if (saves.size() != d.getFirstamount()) {
                counter = diceamount;
                int choose = roll.length - 1;
                do {
                    ans = roll[choose];
                    System.out.println(Arrays.toString(roll));
                    if (looseround <= 1) {
                        System.out.println(name + " doesnt have any dice to save, your turn is over and " + name + " you loose your points for this round");
                        System.out.println("-----------------------------------");
                        looseround = d.getAmount();
                        saves.clear();
                        d.resetAmount();
                        roll = null;
                        chooseAgain = false;
                        break outerloop;

                    } else {
                        if (!saves.contains(ans)) {
                            for (int i = 0; i < roll.length; i++) {
                                if (ans == roll[i]) {
                                    saves.add(roll[i]);
                                    tempscore += ans;
                                    roll[i] = 0;
                                    counter--;
                                    looseround = d.getAmount();
                                }
                            }
                            //The AI saves the highest value die 3 times before ending its turn
                            System.out.println(name + " saves the highest value die");
                            System.out.println(this.name + "'s score is " + tempscore);
                            System.out.println("Saved dice: " + saves);
                            System.out.println();
                            //sc.nextLine();
                            roundcount++;
                            d.setAmount(counter);
                            chooseAgain = false;
                            //sc.nextLine();

                        } else {
                            if (roundcount == 3) {
                                System.out.println("-----------------------------------");
                                d.resetAmount();
                                saves.clear();
                                roll = null;
                                rollAgain = false;
                                break outerloop;
                            } else {
                                looseround--;
                                choose--;
                                System.out.println(name + " have already saved those pips");
                                System.out.println(looseround + " attempts left before " + name + " loose.");
                                System.out.println();
                                //sc.nextLine();
                                chooseAgain = true;

                            }
                        }
                        if (saves.size() == d.getFirstamount()) {
                            System.out.println(name + " have saved all the dice!" + name + " get double points!");
                            System.out.println("-----------------------------------");
                            score = tempscore * 2;
                            System.out.println(score);
                            d.resetAmount();
                            saves.clear();
                            roll = null;
                            //sc.nextLine();
                            break outerloop;
                        } else if (roundcount == 3) {
                            System.out.println("-----------------------------------");
                            score = tempscore;
                            d.resetAmount();
                            saves.clear();
                            roll = null;
                            rollAgain = false;
                            break outerloop;
                        }
                    }
                } while (chooseAgain);
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
        return o.getScore() - this.score;
    }

}
