package util.random;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Rafael Guimaraes Sakurai
 */
public class RandomXTest {
    @Test
    public void testRandomSample() {
        List ints = new ArrayList();
        ints.add(10);
        ints.add(5);
        ints.add(20);
        ints.add(15);
        
        List samples = RandomX.sample(ints, 2);
        assertEquals(2, samples.size());
    }
}
