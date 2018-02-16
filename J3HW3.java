import java.io.*;
import java.util.*;


public class J3HW3 {
    public static void main(String[] args) {
 ///1.Прочитать файл (около 50 байт) в байтовый массив, вывести массив в консоль;

        byte[] br = new byte[50];
        FileInputStream in = null;

        try {
            in = new FileInputStream("Text1.txt");
            in.read(br);
            in.close();

            for (int i=0; i<br.length; i++) System.out.print( br[i] + " ");

        } catch (Exception e) {
            e.printStackTrace();
        }
//2. Последовательно сшить 5 файлов в один (файлы также ~100 байт).
            FileOutputStream out = null;
            try {
                Vector theStreams = new Vector();
                for (int i = 1; i<=5; i++) theStreams.addElement(new FileInputStream("join"+i+".txt"));
                out = new FileOutputStream("all.txt");
                InputStream ins = new SequenceInputStream(theStreams.elements());
                for (int i = ins.read(); i != -1; i = ins.read()) out.write(i);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try { out.close(); } catch ( IOException e ) { };
            }

// 3.  Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb),
// вводим страницу - программа выводит ее в консоль (за страницу можно принять 1800 символов).
// Время чтения файла должно находится в разумных пределах (программа не должна загружаться дольше 10 секунд),
// чтение тоже не должно занимать >5 секунд.
        BufferedReader brt = null;
        try {
            brt = new BufferedReader(new FileReader(args[0]));
            String tempstr=null, str = "";
            while ((tempstr = brt.readLine()) != null) {
                System.out.println(tempstr.length());
                str = str + tempstr;
                System.out.println(str.length());
                while (str.length() > 8) {
                    System.out.print(str.substring(0, 8) + "/");
                    str = str.substring(8);
                }
                System.out.println(str);
            }
            brt.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
