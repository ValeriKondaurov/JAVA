import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;



public class MyArrayTest {
    MyArrays myarrays;
    @Before
    public void init() {
        myarrays = new MyArrays();
    }
    @Test
    public void massTestPrint() {
        Assert.assertTrue(myarrays.print(new int[] {1,2,3,4,5}));
    }
    @Test
    public void massTestArrayAfterFour() {
        Assert.assertArrayEquals(new int[] {5,6}, myarrays.ArrayAfterFour(new int[] {1,2,3,4,5,6}));

    }
    @Test
    public void massTestArrayOnlyOneOrFour() {
        Assert.assertFalse(myarrays.ArrayOnlyOneOrFour(new int[] {1,2,3,4,5,6}));

    }
}
