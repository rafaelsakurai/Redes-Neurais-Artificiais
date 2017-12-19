package util.math;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Rafael Guimaraes Sakurai
 */
public final class MathX {
    
    public static double[] cos(final double[] a) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = Math.cos(a[i]);
        }
        return x;
    }
    
    public static double[] divide(final double b, final BigDecimal[] a) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = b / a[i].doubleValue();
        }
        return x;
    }
    
    public static double[] divide(final double b, final double[] a) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = b / a[i];
        }
        return x;
    }
    
    public static double[][] divide(final double[][] a, final double[][] b) {
        double[][] x = new double[a.length][];
        for (int i = 0; i < a.length; i++) {
            x[i] = new double[a[i].length];
            for (int j = 0; j < a[i].length; j++) {
              x[i][j] = a[i][j] / b[i][j];
            }
        }
        return x;
    }
    
    public static double[] divide(final double[] a, final double b) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = a[i]/ b;
        }
        return x;
    }
    
    public static double[][] divide(final double[][] a, final double b) {
        double[][] x = new double[a.length][];
        for (int i = 0; i < a.length; i++) {
            x[i] = new double[a[i].length];
            for (int j = 0; j < a[i].length; j++) {
              x[i][j] = a[i][j] / b;
            }
        }
        return x;
    }
    
    public static double[] divideISqrt(final double[] x) {
        double[] a = new double[x.length];
        for (int i = 1; i < x.length; i++) {
            a[i] = x[i] / Math.sqrt(i);
        }
        return a;
    }
    
    public static double[] multiply(final double[] a, final double b) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = a[i] * b;
        }
        return x;
    }
    
    public static double[][] multiply(final double[][] a, final double b) {
        double[][] x = new double[a.length][];
        for (int i = 0; i < a.length; i++) {
            x[i] = new double[a[i].length];
            for(int j = 0; j < a[i].length; j++) {
              x[i][j] = a[i][j] * b;
            }
        }
        return x;
    }
    
    public static double[] multiply(final double[] a, final double[] b) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = a[i] * b[i];
        }
        return x;
    }
    
    public static double[] plus(final double[] a, final double b) {
        double[] x = new double[a.length];
        for(int i = 0; i < a.length; i++) {
            x[i] = a[i] + b;
        }
        return x;
    }
    
    public static double[][] plus(final double[][] a, final double b) {
        double[][] x = new double[a.length][];
        for(int i = 0; i < a.length; i++) {
          double[] xi = new double[a[i].length];
          for(int j = 0; j < a[i].length; j++) {
              xi[j] = a[i][j] + b;
          }
          x[i] = xi;
        }
        return x;
    }
    
    public static double[] plus(final double[] a, final double b[]) {
        double[] x = new double[a.length];
        for(int i = 0; i < a.length; i++) {
            x[i] = a[i] + b[i];
        }
        return x;
    }
    
    public static double[][] plus(final double[][] a, final double b[][]) {
        double[][] x = new double[a.length][];
        for(int i = 0; i < a.length; i++) {
          x[i] = new double[a[i].length];
          for(int j = 0; j < a[i].length; j++) {
              x[i][j] = a[i][j] + b[i][j];
          }
        }
        return x;
    }
    
    public static double[] pow(final double[] a, final double b) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = Math.pow(a[i], b);
        }
        return x;
    }
    
    public static double[][] pow(final double[][] a, final double b) {
        double[][] x = new double[a.length][];
        for (int i = 0; i < a.length; i++) {
            x[i] = new double[a[i].length];
            for(int j = 0; j < a[i].length; j++) {
              x[i][j] = Math.pow(a[i][j], b);
            }
        }
        return x;
    }
    
    public static double[] subtract(final double[] a, final double b) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = a[i] - b;
        }
        return x;
    }
    
    public static double[] subtract(final double[] a, final double[] b) {
        if(a.length != b.length) {
            throw new IllegalArgumentException("Arrays with different size!");
        }
        
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = a[i] - b[i];
        }
        return x;
    }
    
    public static double sum(final double[] a) {
        double x = 0.0;
        
        for (double double1 : a) {
            x += double1;
        }
        
        return x;
    }
    
    public static double sum(final double[][] a) {
        double x = 0.0;
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                x += a[i][j];
            }
        }
        
        return x;
    }
    
    public static double multiply(final double[] a) {
        double x = a[0];
        
        for (int i = 1; i < a.length; i++) {
            x *= a[i];
        }
        
        return x;
    }
    
    public static double[][] zeros(final int ... a) {
        double[][] o = new double[a[0]][];
        
        for(int i = 0; i < a[0]; i++) {
            o[i] = new double[a[1]];
        }
        
        return o;
    }
    
    public static double[][] vstack(final double[][] a, double[] b) {
        double[][] x = new double[a.length + 1][];
        
        System.arraycopy(a, 0, x, 0, a.length);
        x[x.length - 1] = b;
        
        return x;
    }
    
    public static double[][] bounds(final double lb, final double ub, final int dim) {
        double[][] x = new double[dim][2];
        
        for(int i = 0; i < dim; i++) {
            x[i][0] = lb;
            x[i][1] = ub;
        }
        
        return x;
    }
    
    public static double min(final double[] x) {
        double min = Double.MAX_VALUE;
        
        for (double d : x) {
            if(min > d) {
                min = d;
            }
        }
        
        return min;
    }
    
    public static double min(final double a, final double b) {
        return a < b ? a : b;
    }
    
    public static List<Integer> range(final int bound) {
        List<Integer> x = new ArrayList(bound);
        
        for(int i = 0; i < bound; i++) {
            x.add(i);
        }
        
        return x;
    }
    
    public static int[] rangeInt(final int bound) {
        int[] x = new int[bound];
        
        for(int i = 0; i < bound; i++) {
            x[i] = i;
        }
        
        return x;
    }
    
    public static double[] abs(final double[] a) {
        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = Math.abs(a[i]);
        }
        return x;
    }
}
