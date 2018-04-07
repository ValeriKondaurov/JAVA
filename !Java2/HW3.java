package HW3;

import java.io.*;
import java.util.*;


/**
 * Write a description of class HW3 here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 26, 2017
 * @link null
 */
public class JavaHW3 {

    public static void textAnalis (String filename) {
        LinkedHashMap <String, Integer> txt =  new LinkedHashMap <String, Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            StringBuilder sb = new StringBuilder(br.readLine());
            while(br.readLine() != null)
                sb.append(br.readLine());
            System.out.println(sb);
            //System.out.println(sb);
            StringTokenizer text = new StringTokenizer(sb.toString(), " .,", false);
            String st = new String();
            while (text.hasMoreElements()) {
                st = text.nextElement().toString();
                if (txt.containsKey(st)) txt.put(st, txt.get(st) + 1);
                else txt.put(st, 1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File for read not found");;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Frequency of meetings the words");
        System.out.println(txt + "\n");

        System.out.println("Unique words:  ");
        for (Map.Entry<String, Integer> key:txt.entrySet())
            if (key.getValue()==1) System.out.print(key.getKey() + ", ");;
        System.out.println();
    }

    static void HashMapExample() {
        Map<String, String> hm = new HashMap<>();
        hm.put("Moscow", "Russia");
        hm.put("Rostov", "Russia");
        hm.put("Paris", "France");
        hm.put("Berlin", "Germany");
        hm.put("Oslo", "Norway");
        Set<Map.Entry<String, String>> set = hm.entrySet();
        for (Map.Entry<String, String> o : set)
            System.out.println(o.getKey() + ": " + o.getValue());
        System.out.println(hm);
        System.out.println(hm.get("Paris"));
    }

    public static void main(String[] args) {
        textAnalis("text.txt");
        PhoneNumber pn = new PhoneNumber ();
        String [][] list = {{"9094000001","Arinov"},
                            {"9195000002","Bironov"},
                            {"9286000003","Kirov"},
                            {"9034500004","Neronov"},
                            {"9051110005","Samoylov"},
                            {"9296400006","Arinov"},
                            {"9094000007","Gitov"},
                            {"9094000008","Jafarov"},
                            {"9094000009","Arinov"}};

        for (int i=0; i<list.length; i++)
            pn.add(list[i][0], list[i][1]);

        pn.print();
        pn.get("Arinov");
        pn.export("phoneNum.txt");
    }

}

