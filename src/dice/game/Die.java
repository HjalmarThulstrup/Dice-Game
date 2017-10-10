/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Hjalmar
 */
public class Die {

    private int sides, amount, firstamount;
    private Random rand = new Random();
    private ArrayList<Integer> res = new ArrayList<>();

    public Die(int sides, int amount) {
        this.sides = sides;
        this.amount = amount;
        this.firstamount = amount;
    }

    public void rollDice() {
        res.clear();
        for (int i = 0; i < amount; i++) {
            res.add(rand.nextInt(sides) + 1);
        }

    }

    public int getFirstamount() {
        return firstamount;
    }

    public int getSides() {
        return sides;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiceResult(int diceNumber) {
        return res.get(diceNumber);
    }

    public ArrayList getRolls() {
        Collections.sort(res);
        return res;
    }


    public void resetAmount() {
        this.amount = this.firstamount;
    }

}
