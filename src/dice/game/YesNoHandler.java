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
public class YesNoHandler {
    
    public Boolean yesNoHandler(String input) {
        input = input.toLowerCase().replaceAll(" ", "");
        return input.startsWith("y");
    }
    
}
