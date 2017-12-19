package util.random;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;
import util.math.MathX;

/**
 *
 * @author Rafael Guimaraes Sakurai
 */
public class RandomX extends Random{
    public RandomX() {
    }

    public RandomX(long seed) {
        super(seed);
    }
    
    public double nextGaussian(final double mean, final double deviation) {
        return mean + deviation * (1 + nextGaussian())/2;
    }
    
    public static double randomUniform() {
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfDouble random = r.doubles(0, 1).iterator();
        return random.nextDouble();
    }
    
    public static double randomUniform(final double a, final double b) {
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfDouble random = r.doubles(a, b).iterator();
        return random.nextDouble();
    }
    
    public static double[] randomUniform(final double a, final double b, final int size) {
        double[] x = new double[size];
        
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfDouble random = r.doubles(x.length, a, b).iterator();
        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextDouble();
        }
        
        return x;
    }
    
    public static double[][] randomUniform(final double a, final double b, final int[] c) {
        double[][] x = MathX.zeros(c);
        
        Random r = new Random(System.nanoTime());
        for (double[] o1 : x) {
            PrimitiveIterator.OfDouble random = r.doubles(o1.length, a, b).iterator();
            for (int j = 0; j < o1.length; j++) {
                Array.set(o1, j, random.nextDouble());
            }
        }
        
        return x;
    }
    
    public static double[][] randomNormal(final double mean, final double deviation, final int[] size) {
        double[][] x = MathX.zeros(size);
        
        RandomX r = new RandomX(System.nanoTime());
        for (double[] o1 : x) {
            for (int j = 0; j < o1.length; j++) {
                Array.set(o1, j, r.nextGaussian(mean, deviation));
            }
        }
        
        return x;
    }
    
    public static double[] randomNormal(final int size) {
        double[] x = new double[size];
        
        RandomX r = new RandomX(System.nanoTime());
        for (int j = 0; j < size; j++) {
            x[j] = r.nextGaussian();
        }
        
        return x;
    }
    
    public static List sample(final List population, final int k) {
        List list = new ArrayList();
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfInt ints = r.ints(k, 0, population.size()).iterator();
        for (int i = 0; i < k; i++) {
            list.add(population.get(ints.nextInt()));
        }
        return list;
    }
    
    public static double[][] choice(final double[][] population, final int k, final double[] prop) {
        double[][] list = new double[k][];
        double[][] newPop = new double[k * 100][];
        int contNP = 0;
        for(int i = 0; i < prop.length; i++) {
            double[] p = population[i];
            for(int j = 0; j < (prop[i] * 100); j++) {
                newPop[contNP++] = p;
            }
        }
        
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfInt ints = r.ints(k, 0, contNP).iterator();
        for (int i = 0; i < k; i++) {
            list[i] = newPop[ints.nextInt()];
        }
        return list;
    }
    
    public static int[] choice(final int ub, final int k, final double[] prop) {
        Random r = new Random(System.nanoTime());
        int[] population = MathX.rangeInt(ub);
        
        int[] newPop = new int[k * 100];
        int contNP = 0;
        for(int i = 0; i < prop.length; i++) {
            int p = population[i];
            for(int j = 0; j < (prop[i] * 100); j++) {
                newPop[contNP++] = p;
            }
        }
        
        int[] list = new int[k];
        PrimitiveIterator.OfInt ints = r.ints(k, 0, contNP).iterator();
        for (int i = 0; i < k; i++) {
            list[i] = newPop[ints.nextInt()];
        }
        return list;
    }
    
    public static List<Integer> choice2(final List<Integer> population, final int k, final double[] prop) {
        List<Integer> list = new ArrayList();
        List<Integer> newPop = new ArrayList();
        
        for(int i = 0; i < prop.length; i++) {
            Integer p = population.get(i);
            for(int j = 0; j < (prop[i] * 100); j++) {
                newPop.add(p);
            }
        }
        
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfInt ints = r.ints(k, 0, newPop.size()).iterator();
        for (int i = 0; i < k; i++) {
            list.add(newPop.get(ints.nextInt()));
        }
        return list;
    }
    
    public static List<Integer> choice2(final int[] population, final int k, final double[] prop) {
        List<Integer> list = new ArrayList();
        List<Integer> newPop = new ArrayList();
        
        for(int i = 0; i < prop.length; i++) {
            Integer p = population[i];
            for(int j = 0; j < (prop[i] * 100); j++) {
                newPop.add(p);
            }
        }
        
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfInt ints = r.ints(k, 0, newPop.size()).iterator();
        for (int i = 0; i < k; i++) {
            list.add(newPop.get(ints.nextInt()));
        }
        return list;
    }
    
    public static List choice(final List<List<Integer>> population, final int k, final double[] prop) {
        List list = new ArrayList();
        List<List<Integer>> newPop = new ArrayList();
        for(int i = 0; i < prop.length; i++) {
            List<Integer> p = population.get(i);
            for(int j = 0; j < (prop[i] * 100); j++) {
                newPop.add(p);
            }
        }
        
        Random r = new Random(System.nanoTime());
        PrimitiveIterator.OfInt ints = r.ints(k, 0, newPop.size()).iterator();
        for (int i = 0; i < k; i++) {
            list.add(newPop.get(ints.nextInt()));
        }
        return list;
    }
    
    public static int randInt(final int start, final int bound) {
        Random r = new Random(System.nanoTime());
        return r.nextInt(bound) + start;
    }
    
    public static int randInt(final int start, final int bound, final int[] out) {
        Random r = new Random(System.nanoTime());
        int x = 0;
        boolean valid;
        do {
            x = r.nextInt(bound) + start;
            valid = true;
            
            for(int i = 0; i < out.length; i++) {
                if (x == out[i]) {
                    valid = false;
                    break;
                }
            }
        } while(!valid);
        
        return x;
    }
}
