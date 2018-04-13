package ru;

public class Text {
    public static void main(String[] args) {
        String s = "Предложение один Теперь предложение номер два";
        String s1 = s.replaceAll(" +", " ");
        System.out.println(s1);

        StringBuilder s2 = new StringBuilder(s1);

        for(int i = 1; i < s1.length(); i++) {
            if(s1.charAt(i) >= 'А' && s1.charAt(i) <= 'Я') {
                s2.setCharAt(i - 1, '.');
            }
        }
        System.out.println(s2);
        s1 = s2.toString();

//        Задание.
//        1. Проставить пробелы после точек.
        for(int i = 1; i < s1.length(); i++) {
            if(s1.charAt(i-1) == '.' && s1.charAt(i) >= 'А' && s1.charAt(i) <= 'Я') {
                s2.insert(i," ");
            }
        }
//        2. В конце текста также должена быть точка.
        System.out.println(s2.append("."));

    }
}
