/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import lib.ArrayUtil;

/**
 *
 * @author larar
 */
public class IslandProblemApp {

    private final String fileName;
    private final ArrayList<int[][]> samples;

    public IslandProblemApp(String fileName) {
        this.fileName = fileName;
        this.samples = new ArrayList<>();
    }

    public void processFile() throws IOException {

        String linea;
        FileReader f = new FileReader(this.fileName);
        try (BufferedReader b = new BufferedReader(f)) {
            for (int i = 0; (linea = b.readLine()) != null; i++) {
                int[] dimensiones = this.toVector(linea);
                //condicion especial de fin de lectura del archivo
                if (dimensiones[0] == 0 && dimensiones[1] == 0) {
                    break;
                }

                this.samples.add(this.getArray(dimensiones, b));
            }
        }
    }

    private int[] toVector(String linea) {

        String[] data = linea.trim().split(" ");
        int[] data_int = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            data_int[i] = Integer.parseInt(data[i]);
        }

        return data_int;
    }

    private int[][] getArray(int[] dimensiones, BufferedReader b) throws IOException {

        int[][] sample = new int[dimensiones[1]][dimensiones[0]];

        for (int i = 0; i < dimensiones[1]; i++) {
            sample[i] = toVector(b.readLine().trim());
        }

        return sample;
    }

    public int[] calculateAreas() throws IOException {

        int[] result = new int[this.samples.size()];
        ArrayUtil instance = new ArrayUtil();

        for (int i = 0; i < this.samples.size(); ++i) {
            result[i] = instance.getNumberOfIslands(this.samples.get(i));
        }
        this.writeResults(result);
        
        return result;
    }

    private void writeResults(int[] results) throws IOException {

        FileWriter fichero = new FileWriter(this.fileName + "_outputs.txt");
        try (PrintWriter pw = new PrintWriter(fichero)) {
            for (int i = 0; i < results.length; i++) {
                pw.println(results[i]);
            }
        }
    }

}
