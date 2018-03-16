/**
 1. A method to which a non-empty one-dimensional integer array
 is passed as an argument must return a new array that is obtained
 by pulling elements from the original array coming after the last four.
 The input array must contain at least one four,
 otherwise RuntimeException must be thrown in the method.

 2. A method that verifies that an array consists of only numbers 1 and 4.
 If the array does not have at least one 4 or 1, the method returns false;*

 * @author Valeriy Kondaurov
 * @version dated MAR 16, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */

import java.util.Arrays;

public class MyArrays {
    public int[] ArrayAfterFour(int[] a) {
        int i = a.length;
        try {
            do {
                i--;
            } while (!(a[i] == 4));
        } catch (RuntimeException e){
            e.fillInStackTrace();
        } finally {
            i++;
            int[] b = new int[a.length - i];
            for (int j = 0; j < b.length; j++) b[j] = a[i + j];
            return b;
        }
    }

    public boolean ArrayOnlyOneOrFour(int[] a) {
        boolean four = false;
        boolean one = false;
        int i = 0;
        do {
            switch (a[i]) {
                case 1:
                    four = true;
                    break;
                case 4:
                    one = true;
                    break;
            }
          i++;
        }  while (!(four && one) && i<a.length);

        return four&&one;

    }


    public static void main(String[] args) {
        MyArrays m = new MyArrays();
        int[] b = m.ArrayAfterFour(new int[]{1, 2, 3, 5, 6});
        System.out.println(Arrays.toString(b));
        System.out.println(m.ArrayOnlyOneOrFour(new int[] {1, 2, 3, 4, 5, 6}));
    }

}
