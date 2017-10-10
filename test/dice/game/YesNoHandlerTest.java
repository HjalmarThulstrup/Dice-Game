/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice.game;

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
public class YesNoHandlerTest {
    
    
    public YesNoHandlerTest() {
        
    }


    /**
     * Test of yesNoHandler method, of class YesNoHandler.
     */
    @Test
    public void testYesNoHandler() {
        System.out.println("yesNoHandler");
        String input = "y";
        YesNoHandler instance = new YesNoHandler();
        Boolean expResult = true;
        Boolean result = instance.yesNoHandler(input);
        assertEquals(expResult, result);
    }
    
}
