/**
 * 1. method that changes two elements of the array sites.(the array can be of any reference type);

 2. method that converts an array to ArrayList;.

 3. Classes and methods for Box with Fruit
 *
 * @author Valeriy Kondaurov
 * @version dated Feb 02, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */

import java.util.ArrayList;
import java.util.Random;

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

abstract class Fruit {
    float weight;
    Random r;
    protected float calcWieght (float s, float e) {
        r = new Random();
        float f;
        do f = r.nextFloat();
        while (s+f>e);

        return  (float) (Math.ceil((s+f)*100)/100);
    }

    public float getWeight() {
        return weight;
    }
}

class Apple extends Fruit   {
    Apple () {
        super.weight = calcWieght(0.7f, 1.0f);
    }
}
class Orange extends Fruit   {
    Orange () {
        super.weight = calcWieght(1.0f, 1.2f);
    }
}

class Box <T extends Fruit> {
    ArrayList <Float> boxfruit;
    float boxweight = 0;
    Box (Fruit[] fruit) {
        boxfruit = new ArrayList<Float>();
        float fruitweight;
        for (int i = 0; i<fruit.length; i++) {
            fruitweight = fruit[i].getWeight();
            boxfruit.add(fruitweight);
            boxweight+=fruitweight;
        }

    }
    public void addbox(T f) {
        boxfruit.add(f.getWeight());
        boxweight+=f.getWeight();
        //System.out.println("Добавил " + boxfruit.get(boxfruit.size()-1));
    }

    public float getWeight () {
        return boxweight;
    }

    public float getFruit() {
        float f = boxfruit.get(0);
        boxweight-=f;
        boxfruit.remove(0);
        return f;
    }

    public void print () {
        System.out.println("В ящике фрукты со следующим весом " + boxfruit.toString());
    }


    public boolean compar(Box <?> boxother) {
        return (boxweight==boxother.getWeight());
    }

    public void putFruit (Box <T> box) {
        float f;
        while (Float.compare(box.getWeight(),0.000f)==1) {
           f = box.getFruit();
          boxfruit.add(f);
            boxweight+=f;
        }
        ;
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

    Apple[] ap = new Apple[6];
    for (int i = 0;i<ap.length; i++ ) ap[i] = new Apple();
    //System.out.println("Яблоко весит " + ap[1].getWeight());
    Box <Apple> box = new Box<Apple> (ap);
    box.addbox(new Apple());
    box.print();
    System.out.println("Вес коробки " + box.getWeight());

    Orange[] or = new Orange[4];
    for (int i = 0;i<or.length; i++) or[i] = new Orange();
    //System.out.println("Апельсины весят " + or[1].getWeight());
    Box <Orange> boxor = new Box<Orange> (or);
    boxor.addbox(new Orange());
    boxor.print();
    System.out.println("Вес коробки " + boxor.getWeight());

    System.out.println("У коробок с Яблоками и Апельсинами "
            +(box.compar(boxor)?" одинаковый ": " разный ") + "вес");

    Apple[] ap2 = new Apple[5];
    for (int i = 0;i<ap2.length; i++ ) ap2[i] = new Apple();
    //System.out.println("Яблоко весит " + ap2[1].getWeight());
    Box <Apple> box2 = new Box<Apple> (ap2);
    box2.addbox(new Apple());
    box2.print();
    System.out.println("Вес коробки " + box2.getWeight());
    box2.putFruit(box);

    box2.print();
    System.out.println("Вес коробки " + box2.getWeight());
    box.print();
    }


}
