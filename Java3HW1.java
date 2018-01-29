import java.net.SocketTimeoutException;

class ArraySwitch <T> {
    T[] arr;
    ArraySwitch (T[] arr) {
        this.arr = arr;
    }

    public void print () {
        for (int i = 0; i<arr.length; i++)
        System.out.print(arr[i] + " ");
        System.out.println(" ");
    }
    public void arrswitch (int i, int j) {
        T tmp;
        try {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Not correct index for Array switch");
        }
    }
}

public class Java3HW1 {
    public static void main(String[] args) {
    ArraySwitch <Float> a = new ArraySwitch(new Float[] {1.2f, 2.3f, 4.9f, 7.1f});
    a.print();
    a.arrswitch(0,3);
    a.print();
    ArraySwitch <String> as = new ArraySwitch(new String[] {"Мама", "Папа", "Сына", "Дочь"});
        as.print();
        as.arrswitch(0,1);
        as.print();
    }
}
