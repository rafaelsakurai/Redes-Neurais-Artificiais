package redesneurais;

import java.util.Arrays;
import util.math.MathX;
import util.random.RandomX;

/**
 *
 * @author rafaelsakurai
 */
public class PerceptronMulticamadas {
    public static void main(String[] args) {
        double[] w = treinar();
        analisar(w);
    }
    
    public static double[] treinar() {
        double[][] x = Amostra.getAmostras();
        double[] d = Amostra.getSaidas();
        double[][] w1 = new double[3][];
        w1[0] = RandomX.randomUniform(0, 1, 4);
        w1[1] = RandomX.randomUniform(0, 1, 4);
        w1[2] = RandomX.randomUniform(0, 1, 4);
        
        double[][] w2 = new double[1][];
        w2[0] = RandomX.randomUniform(0, 1, 4);
        
        System.out.println(Arrays.toString(w1));
        double n = 0.0025;
        double e = 10e-6;
        
        int epoca = 0;
        double em = 0;
        double eqmAtual = 0;
        double eqmAnterior = 0;
        
        do {
            eqmAnterior = eqm(x, d, w1);
            for(int k = 0; k < x.length; k++) {
                double u = MathX.sum(MathX.multiply(w1, x[k]));
                w1 = MathX.plus(w1, MathX.multiply(x[k], n * (d[k] - u)));
            }
            epoca++;
            eqmAtual = eqm(x, d, w1);
        } while(Math.abs(eqmAtual - eqmAnterior) > e);
        System.out.println("Epoca: " + epoca);
        System.out.println(Arrays.toString(w1));
        return w1;
    }
    
    public static double eqm(double[][] x, double[] d, double[] w) {
        int p = x.length;
        double eqm = 0;
        
        for(int k = 0; k < x.length; k++) {
            double u = MathX.sum(MathX.multiply(w, x[k]));
            eqm += Math.pow(d[k]- u, 2);
        }
        
        return eqm/p;
    }
    
    public static void analisar(double[] w) {
        double[][] x = Amostra.getAmostras();
        for(int i = 0; i < x.length; i++) {
            double u = MathX.sum(MathX.multiply(w, x[i]));
            double y = sinal(u);
            System.out.print(Arrays.toString(x[i]));
            System.out.println(y);
        }
    }
    
    public static double sinal(double u) {
        return u >= 0.0 ? 1.0 : -1.0;
    }
}
