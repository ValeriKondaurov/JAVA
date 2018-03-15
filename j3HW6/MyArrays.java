import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MyArrays {
    public int[] ArrayAfterFour(int[] a) {
        int i = a.length;
       do {
            i--;
        } while (!(a[i]==4));
        i++;
        int[] b =  new int[a.length-i];
        for (int j = 0;  j<b.length; j++) b[j] = a[i+j];

        return b;
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

    public  boolean print (int[] a) {
        System.out.println(Arrays.toString(a));
        return true;
    }


    public static void main(String[] args) {
        MyArrays m = new MyArrays();
        int[] b = m.ArrayAfterFour(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(Arrays.toString(b));
        System.out.println(m.ArrayOnlyOneOrFour(new int[] {1, 2, 3, 4, 5, 6}));
    }

}
