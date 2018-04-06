/**
 * Write a description of class HW2 here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 26, 2017
 * @link https://github.com/ValeriKondaurov/Java2HW2_git
 */
package Java2HW2;


import java.io.*;
import java.util.StringTokenizer;

public class HW2 {
    public static int sumArray(String array[][]) throws ArrayIndexOutOfBoundsException, NumberFormatException  {
        int s=0;
        if (array.length>4 || array[0].length>4) throw new ArrayIndexOutOfBoundsException("Ups");
        for (int i=0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                try {
                    s = s + Integer.parseInt(array[i][j]);
                } catch (NumberFormatException err) {
                    System.out.println("In cell [" + i + "][" + j+"]" + " not convert string");
                    throw new NumberFormatException("Ups");
                }
            }
        }
        return s;
    }

    public static void printArray (String array[][]) {
        for (int i=0; i<array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[][] s = new String[0][]; 
        //чтение файла c использованием StringTokenizer;
        try {
            StringTokenizer st;
            int aWidth, aHeight;
            BufferedReader br = new BufferedReader(new FileReader (new File("arr.txt")));
            String sizes = br.readLine();
            st = new StringTokenizer(sizes, " \n", false);
            //определение размера массива для чтения и запись содержимого
            aHeight = 1;
            aWidth = st.countTokens();
            StringBuilder sb = new StringBuilder(sizes);
            while((sizes = br.readLine()) != null) {
                aHeight++;
                sb.append("\n"+ sizes);
            }

            s = new String[aHeight][aWidth];//переопределение размера массива;
            st = new StringTokenizer(sb.toString(), " \n", false);

            //reading A matrix
            for(int i = 0; i < aHeight; i++){
                for(int j = 0; j < aWidth; j++) {
                    s[i][j] = st.nextToken();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File for read not found");
        } catch (IOException ex) {
                ex.printStackTrace();
        }

        printArray(s);
        try {
            System.out.println("Summary in array: " + sumArray (s));
        } catch (ArrayIndexOutOfBoundsException err1) {
            System.out.println("The boundary of the array");

        } catch (NumberFormatException err1) {
            System.out.println("Invalid number format");
        }
    }
}
