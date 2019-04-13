/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class RandomNameGeneratorTest {

    public RandomNameGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of generate method, of class RandomNameGenerator.
     */
    @Test
    public void testGenerate() {
        String result = RandomNameGenerator.generate();
        assertFalse(result.isEmpty());
    }

}
