import java.util.ArrayList;

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

    public ArrayList<T> toArraylist (T[] arr) {
        ArrayList <T> a = new ArrayList<T>();
        for (T i:arr) a.add(i);
        return a;
    }

    public ArrayList<T> toArraylist () {
        ArrayList <T> a = new ArrayList<T>();
        for (T i:arr) a.add(i);
        return a;
    }
}

public class Java3HW1 {
    public static void main(String[] args) {
    ArraySwitch <Float> a = new ArraySwitch(new Float[] {1.2f, 2.3f, 4.9f, 7.1f});
    a.print();
    a.arrswitch(0,3);
    a.print();
    ArrayList <Float> al = a.toArraylist(new Float[] {1.2f, 2.3f, 4.9f, 7.1f});
    System.out.println(al.toString());
    al = a.toArraylist();
    System.out.println(al.toString());

    System.out.println();
    ArraySwitch <String> as = new ArraySwitch(new String[] {"Мама", "Папа", "Сына", "Дочь"});
    as.print();
    as.arrswitch(0,1);
    as.print();

    ArrayList <String> als = as.toArraylist(new String[] {"Мама", "Папа", "Сына", "Дочь"});
    System.out.println(als.toString());
    als = as.toArraylist();
    System.out.println(als.toString());

    }
}
