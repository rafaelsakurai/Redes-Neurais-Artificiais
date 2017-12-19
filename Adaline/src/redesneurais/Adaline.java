package redesneurais;

import java.util.Arrays;
import util.math.MathX;
import util.random.RandomX;

/**
 *
 * @author rafaelsakurai
 */
public class Adaline {
    public static void main(String[] args) {
        double[] w = treinar();
        analisar(w);
    }
    
    public static double[] treinar() {
        double[][] x = Amostra.getAmostras();
        double[] d = Amostra.getSaidas();
        double[] w = RandomX.randomUniform(0, 1, 5);
        System.out.println(Arrays.toString(w));
        double n = 0.0025;
        double e = 10e-6;
        
        int epoca = 0;
        double eqmAtual = 0;
        double eqmAnterior = 0;
        
        do {
            eqmAnterior = eqm(x, d, w);
            for(int k = 0; k < x.length; k++) {
                double u = MathX.sum(MathX.multiply(w, x[k]));
                w = MathX.plus(w, MathX.multiply(x[k], n * (d[k] - u)));
            }
            epoca++;
            eqmAtual = eqm(x, d, w);
        } while(Math.abs(eqmAtual - eqmAnterior) > e);
        System.out.println("Epoca: " + epoca);
        System.out.println(Arrays.toString(w));
        return w;
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
