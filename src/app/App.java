package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import lib.ArrayUtil;

/**
 * Una clase que crea una aplicacion JAVA que permite ser ejecutada para ver el
 * resultado de la implementacion, el eliminar esta clase no afecta en nada el
 * algoritmo.
 *
 * @author larar
 */
public class App {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{

        ArrayUtil au = new ArrayUtil();
        Integer[] result = au.getProbabilities(new int[]{3, 5, 4, 5, 9, 8, 7, 8, 5, 4, 6, 6, 4, 2, 14, 78, 1, 6, 78, 1, 6, 8, 1, 6, 58, 13, 51, 68, 1, 3, 58, 1, 2, 12, 31, 3, 3, 5, 1, 35, 10, 12, 12, 12, 12, 12});
        System.out.println(Arrays.toString(result));
      

        IslandProblemApp ipa = new IslandProblemApp("test.txt");
        ipa.processFile();
        ipa.calculateAreas();
    }
    
}
