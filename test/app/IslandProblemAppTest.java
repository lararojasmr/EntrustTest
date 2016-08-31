/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author larar
 */
public class IslandProblemAppTest {
    
    public IslandProblemAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateAreas method, of class IslandProblemApp.
     */
    @Test
    public void testCalculateAreas() throws Exception {
        System.out.println("calculateAreas");
        IslandProblemApp instance = new IslandProblemApp("test.txt");
        instance.processFile();
        int[] results = instance.calculateAreas();
        Assert.assertArrayEquals(new int[]{0,1,1,3,1,9}, results);
    }

    
}
