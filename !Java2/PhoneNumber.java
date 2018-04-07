/**
 * Write a description of class PhoneNumber here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 29, 2017
 * @link https://github.com/ValeriKondaurov/Java2HW3
 */

package HW3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class PhoneNumber {
    private Map <String, String> phoneNum;

    public PhoneNumber () {
        phoneNum = new LinkedHashMap<>();
    }
    public void add (String phonenum, String fio) {
        phoneNum.put(phonenum,fio);

    }
    public void print () {
        System.out.println(String.format("%-20s","FIO") + String.format("%-20s","PHONE HUMBER"));
        Set<Map.Entry<String, String>> set = phoneNum.entrySet();
        for (Map.Entry<String, String> p : set)
            System.out.println(String.format("%-20s",p.getValue()) + "|"+ String.format("%-20s",p.getKey()));

    }

    public void get (String fio) {
        if (!phoneNum.containsValue(fio)) System.out.println("Not find Number for " + fio);
        else {
            System.out.println("for " + fio + " find next phone number");
            Set<Map.Entry<String, String>> set = phoneNum.entrySet();
            for (Map.Entry<String, String> p : set)
                if (p.getValue() == fio) System.out.println(p.getKey());
        }
    }

    public void export (String filename) {
        File file = new File(filename);
        FileWriter fr = null;
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> set = phoneNum.entrySet();
        for (Map.Entry<String, String> p : set)
            sb.append(p.getValue() +"; "+ p.getKey() + ";\n");
        try {
            fr = new FileWriter(file);
            fr.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
