package lib;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba unitaria de la clase ArrayUtilTest.
 * @author larar
 */
public class ArrayUtilTest {

    public ArrayUtilTest() {
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
     * Test of getProbabilities method, of class ArrayUtil.
     */
    @Test
    public void testGetProbabilities() {
        System.out.println("getProbabilities");
        int[] origen = new int[]{3, 5, 4, 5, 9, 8, 7, 8, 5, 4, 6, 6, 4, 2, 14, 78, 1, 6, 78, 1, 6, 8, 1, 6, 58, 13, 51, 68, 1, 3, 58, 1, 2, 12, 31, 3, 3, 5, 1, 35, 10, 12, 12, 12, 12, 12};
        ArrayUtil instance = new ArrayUtil();
        Integer[] expResult = new Integer[]{1, 12, 6, 3, 5, 4, 8, 2, 78, 58, 35, 68, 7, 9, 10, 13, 14, 51, 31};
        Integer[] result = instance.getProbabilities(origen);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getNumberOfIslands method, of class ArrayUtil.
     */
    @Test
    public void testGetNumberOfIslands_intArrArr_int() {
        System.out.println("getNumberOfIslands");
        int[][] origen = new int[][]{{1,0,1,0,1},{0,0,0,0,0},{1,0,1,0,1},{0,0,0,0,0},{1,0,1,0,1}};
        int land_flag = 1;
        ArrayUtil instance = new ArrayUtil();
        int expResult = 9;
        int result = instance.getNumberOfIslands(origen, land_flag);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfIslands method, of class ArrayUtil.
     */
    @Test
    public void testGetNumberOfIslands_intArrArr() {
        System.out.println("getNumberOfIslands");
        int[][] origen = new int[][]{{1,0,1,0,1},{0,0,0,0,0},{1,0,1,0,1},{0,0,0,0,0},{1,0,1,0,1}};
        ArrayUtil instance = new ArrayUtil();
        int expResult = 9;
        int result = instance.getNumberOfIslands(origen);
        assertEquals(expResult, result);
    }

}
