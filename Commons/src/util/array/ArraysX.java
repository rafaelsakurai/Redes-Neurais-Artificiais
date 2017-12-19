package util.array;

import java.util.Arrays;

/**
 *
 * @author Rafael Guimaraes Sakurai
 */
public class ArraysX {
    public static double[][] copy(final double[][] x) {
        double[][] n = new double[x.length][];
        for (int i = 0; i < x.length; i++) {
            n[i] = Arrays.copyOf(x[i], x[i].length);
        }
        return n;
    }
    
    public static double[][] union(double[][] x, double[][] y) {
        double[][] n = new double[x.length + y.length][];
        int cont = 0;
        for (int i = 0; i < x.length; i++, cont++) {
            n[cont] = Arrays.copyOf(x[i], x[i].length);
        }
        for (int i = 0; i < y.length; i++, cont++) {
            n[cont] = Arrays.copyOf(y[i], y[i].length);
        }
        return n;
    }
    
    public static double[][] transpose(double [][] matrix){
        double[][] t = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                t[j][i] = matrix[i][j];
            }
        }
        return t;
    }
}
