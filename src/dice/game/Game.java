/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Hjalmar
 */
public class Game {

    private Scanner sc = new Scanner(System.in);
    private int sides, numdie, maxpoints, playercount = 0;
    private String ans, name, winner = "The winner is ";
    private boolean moreplayers = true, isGame = true;
    private ArrayList<Player> playerarr = new ArrayList<>();
    private ArrayList<Player> scoreList = new ArrayList<>();
    private YesNoHandler yn = new YesNoHandler();
    

    public void play() {

        System.out.println("Choose number of die sides");
        sides = sc.nextInt();
        System.out.println("Choose the number of dice");
        numdie = sc.nextInt();
        System.out.println("Choose the number of points where the game ends");
        this.maxpoints = sc.nextInt();

        Die d = new Die(sides, numdie);

        do {
            System.out.println("Do you want to add a player?");
            ans = sc.next();
            if (yn.yesNoHandler(ans)) {
                ans = "";
                System.out.println("Shall the player be an AI?");
                ans = sc.next();
                if (yn.yesNoHandler(ans)) {
                    System.out.println("Enter name of the AI");
                    name = sc.next();
                    playerarr.add(new AI(name));
                    playercount++;
                } else {
                    System.out.println("Enter name of the Human");
                    name = sc.next();
                    playerarr.add(new Human(name));
                    playercount++;
                }
            } else {
                if (playercount < 2) {
                    System.out.println("You need at least 2 players.");
                    moreplayers = true;
                } else {
                    moreplayers = false;
                }
            }
        } while (moreplayers);

//        for (int i = 0; i < 20; i++) {
//            playerarr.add(new AI("com" + i));
//        }


//        for (int i = 0; i < 20; i++) {
//            playerarr.add(new AI("com" + i));
//        }

        outerloop:
        do {
            for (Player player : playerarr) {
                if (player instanceof Human) {
                    scoreList.clear();
                    player.takeTurn(d);
                } else if (player instanceof AI) {
                    scoreList.clear();
                    player.takeTurn(d);
                }
            }

            for (Player player : playerarr) {
                if (player instanceof Human) {
                    scoreList.add(player);

                } else if (player instanceof AI) {
                    scoreList.add(player);

                }
            }
            Collections.sort(scoreList);
            if (scoreList.get(0).getScore() >= maxpoints) {
                isGame = false;
                break outerloop;
            }
        } while (isGame);
        for (Player player : playerarr) {
            System.out.println(player + " has " + player.getScore() + " points");
        }
        System.out.println(winner + scoreList.get(0).toString() + " with " +  scoreList.get(0).getScore() + " points");
        
    }

}
