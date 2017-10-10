/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hjalmar
 */
public class DieTest {
    
        Die instance = new Die(6, 8);
    public DieTest() {
        
   }
    
    
    /**
     * Test of getFirstamount method, of class Die.
     */
    @Test
    public void testGetFirstamount() {
        System.out.println("getFirstamount");
        int expResult = 8;
        int result = instance.getFirstamount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSides method, of class Die.
     */
    @Test
    public void testGetSides() {
        System.out.println("getSides");
        int expResult = 6;
        int result = instance.getSides();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmount method, of class Die.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        int expResult = 8;
        int result = instance.getAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAmount method, of class Die.
     */
    @Test
    public void testSetAmount() {
        System.out.println("setAmount");
        int amount = 6;
        instance.setAmount(amount);
    }


    /**
     * Test of resetAmount method, of class Die.
     */
    @Test
    public void testResetAmount() {
        System.out.println("resetAmount");
        instance.resetAmount();
    }
    
}
