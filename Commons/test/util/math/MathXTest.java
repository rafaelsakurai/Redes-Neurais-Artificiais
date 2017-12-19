package util.math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael Guimaraes Sakurai
 */
public class MathXTest {
    
    @Test
    public void testApplyCosInArray() {
        double[] a = new double[]{0.3, 0.5, 1.0};
        double[] cos = MathX.cos(a);
        assertEquals(Math.cos(0.3), cos[0], 0.1);
        assertEquals(Math.cos(0.5), cos[1], 0.1);
        assertEquals(Math.cos(1.0), cos[2], 0.1);
    }
    
    @Test
    public void testDivideDoubleByElementsArray() {
        double[] a = new double[]{0.3, 0.5, 1.0};
        double[] res = MathX.divide(0.5, a);
        assertEquals(1.6666, res[0], 0.01);
        assertEquals(1.0, res[1], 0.1);
        assertEquals(0.5, res[2], 0.1);
    }
    
    @Test
    public void testSumArray() {
        double[] a = new double[]{0.5, 1.0, 1.5};
        assertEquals(3.0, MathX.sum(a), 0.01);
    }
    
    @Test
    public void testZeros() {
        double[][] x = MathX.zeros(2, 5);
        assertEquals(2, x.length);
        assertEquals(5, x[0].length);
    }
    
    @Test
    public void testVstack() {
        double[][] a = new double[0][0];
        double[] b = new double[] {1.0, 2.0, 3.0};
        double[][] x = MathX.vstack(a, b);
        assertEquals(1, x.length);
        assertEquals(3, x[0].length);
        assertEquals(1.0, x[0][0], 0.1);
    }
    
    @Test
    public void testDivideElementsArrayByDouble() {
        double[] a = new double[]{0.3, 0.5, 1.0};
        double[] res = MathX.divide(a, 0.5);
        assertEquals(0.6, res[0], 0.1);
        assertEquals(1.0, res[1], 0.1);
        assertEquals(2.0, res[2], 0.1);
    }
    
    @Test
    public void testBounds() {
        double[][] x = MathX.bounds(-5, 5, 10);
        
        assertEquals(-5.0, x[0][0], 0.1);
        assertEquals(5.0, x[0][1], 0.1);
        assertEquals(10, x.length);
    }
    
    @Test
    public void testPlus() {
        double[][] x = MathX.plus(MathX.zeros(3, 3), 0.1);
        
        assertEquals(0.1, x[0][0], 0.0001);
        assertEquals(0.1, x[1][1], 0.0001);
        assertEquals(0.1, x[2][2], 0.0001);
    }
}
