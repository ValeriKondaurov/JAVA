/**
 * Work with Thread.
 *
 * @author Valeriy Kondaurov
 * @version dated Jan 12, 2017
 * @link https://github.com/ValeriKondaurov/Java
 *
 *
 * volatile - позволяется обеспечить доступ к общей переменной нескольких поток с возможностью ее обновлением
 * в кажлом потоке и доступности соседним потокам актуального (последнего) значения переченной.
 *
 */

package HW5;

import java.util.Arrays;

public class Java2HW5 {
    static final int size = 100000;
    static final int h = size / 2;


    public static void main(String[] args) {
        arrcalcfull();
        arrcalcc ();
    }

    static void  arrcalcfull () {
         float[] arr = new float[size];
         Arrays.fill(arr, 1);
         long a = System.currentTimeMillis();
         for (int i=0; i<size;i++)
             arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
         System.out.println("Time work first method " + (System.currentTimeMillis()-a));
    }

    static void  arrcalcc () {
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        ArrCalc arrrun1 = new ArrCalc(arr1);
        ArrCalc arrrun2 = new ArrCalc(arr2);
        Thread tr1 = new Thread(arrrun1);
        Thread tr2 = new Thread(arrrun2);
        tr1.setName("TR1");
        tr2.setName("TR2");

        tr1.start();
        tr2.start();
        do {
            //System.out.print("." + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (tr1.isAlive() || // waiting for completion all threads
               tr2.isAlive());

        arr1 = Arrays.copyOf(arrrun1.getArr(), arr1.length);
        arr2 = Arrays.copyOf(arrrun2.getArr(), arr1.length);

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println("Time work second method " + (System.currentTimeMillis()-a));
    }
}

class ArrCalc implements Runnable {
    private  float [] arr;

    ArrCalc (float[] a) {
        arr = Arrays.copyOf(a, a.length);
        System.out.println(this.arr.length);
    }
    @Override
    public void run() {
        try {
            for (int i=0; i<this.arr.length;i++) {
                Thread.sleep(10);
                this.arr[i]= formula(i, arr[i]);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
    }

    private synchronized float formula (int i, float f) {
        return (float) (f * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
    public float [] getArr() {
        return  this.arr;
    }

}



