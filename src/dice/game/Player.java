/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

/**
 *
 * @author Hjalmar
 */
public interface Player extends Comparable<Player>{
    
    
    public void takeTurn(Die d);
    public int getScore();
    
    
    
}
