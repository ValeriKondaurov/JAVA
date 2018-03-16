/**
 JUnit test for check class MyArrays

 * @author Valeriy Kondaurov
 * @version dated MAR 16, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.*;


@RunWith(Parameterized. class)
public class MyArrayTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays. asList(new int [][][]{
                {{1,2,3,4,5,6},   {5,6}},
                {{1,4,3,2,5,6},   {3,2,5,6}},
                {{1,2,3,5,6},   {}},
        });
    }
    private int[] in;
    private int[] out;

    public MyArrayTest(int [] in, int [] out) {
        this.in = in;
        this.out = out;
    }


    MyArrays myarrays;
    @Before
    public void init() {
        myarrays = new MyArrays();
    }
    @Test
    public void massTestArrayAfterFour() {
        Assert.assertArrayEquals(out, myarrays.ArrayAfterFour(in));
    }
    @Test
    public void massTestArrayOnlyOneOrFour() {
        Assert.assertFalse(myarrays.ArrayOnlyOneOrFour(in));
    }
}
