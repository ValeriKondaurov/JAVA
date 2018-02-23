/**
 * 1. To create three threads, each of which takes a certain letter (A, B, and C) 5 times,
 * the procedure should be exactly ABСABСABС
 * 2. Write a very small method in which 3 streams line by line write data to a file
 * (pieces of 10 records, with a period of 20 m from)
 *
 * @author Valeriy Kondaurov
 * @version dated Feb 23, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */

import java.io.FileWriter;
import java.io.IOException;

public class J3HW4 {
        private final Object mon = new Object();
        private volatile char currentLetter = 'A' ;
        private static FileWriter file;
        public static void main(String[] args) throws IOException {
            J3HW4 w = new J3HW4();
            Thread tA = new Thread(() -> {w.printA();});
            Thread tB = new Thread(() -> {w.printB();});
            Thread tC = new Thread(() -> {w.printС();});
            tA.start();
            tB.start();
            tC.start();
            do {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("Мain thread is interrupted.");
                }
            } while (tA.isAlive() || tB.isAlive()|| tC.isAlive());
            WriteFile();
        }
        public void printA() {
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'A' )
                            mon.wait();
                        System. out.print("A");
                        currentLetter = 'B' ;
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void printB() {
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'B' )
                            mon.wait();
                        System. out.print("B");
                        currentLetter = 'C' ;
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printС() {
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'C' )
                            mon.wait();
                        System. out.print("C");
                        currentLetter = 'A' ;
                        mon.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public static void WriteFile() throws IOException {
        J3HW4 e2 = new J3HW4();
        try {
            file = new FileWriter("Text.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Thread tr1 = new Thread(() -> e2.method1());
        Thread tr2 = new Thread(() -> e2.method1());
        Thread tr3 = new Thread(() -> e2.method1());
        tr1.start();
        tr2.start();
        tr3.start();
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (tr1.isAlive() ||
                tr2.isAlive()||
                tr3.isAlive());
        file.close();
    }

    public  void method1() {
        synchronized (file) {
            for (int i = 0; i < 10; i++) {
                try {
                    file.write(" Write Tread NEW word\n+");
                    Thread. sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

