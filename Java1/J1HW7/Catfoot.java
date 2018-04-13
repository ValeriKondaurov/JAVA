package ru;

import java.util.Scanner;

class Plate {
    private int food;
    public Plate(int food) {//положили еду в тарелку
        this.food = food;
    }
    public boolean decreaseFood(int n) {//кто то есть из тарелки
        if (food < n) return false;
        food -=n;
        return true;
    }
    public void addFood(int addfood) {//добавление еды в тарелку
        food+=addfood;
    }

    public int getFood() {//вернуть  по запросу сколько еды
        return food;
    }

    public void info() {//сообщить сколько еды
        System.out.println("plate: " + food);
    }
}
 class Cat {
    private String name;
    private int appetite;
    private boolean hunger;
     public Cat(String name, int appetite) {//параметры кота (по умолчанию голоден)
         this.name = name;
         this.appetite = appetite;
         this.hunger = false;//поле сытости
     }
    public Cat(String name, int appetite, boolean hunger) {//параметры кота (возможно кот не голоден)
        this.name = name;
        this.appetite = appetite;
        this.hunger = hunger;
    }
    public void eat(Plate p) {//кушает и проверяется на сытость
        if (p.decreaseFood(appetite)) this.hunger = true;
        else this.hunger = false;
    }

     public void setHunger(boolean hunger) {//добавить признак голоден/сыт
         this.hunger = hunger;
     }

     public void setAppetite(int appetite) {//установить аппетит
         this.appetite = appetite;
     }

     public int getAppetite() {//узнать какой аппетит
         return appetite;
     }

     public boolean isHunger() {//узнать голоден/сыт
         return hunger;
     }

     public String getName() {//узнать имя
         return name;
     }
 }
public class Catfoot {
        public static void main(String[] args) {
            String nameCat;
            int appite;
            Scanner scr = new Scanner(System.in);
            System.out.println("Программа 'Накорми кота'");
            System.out.println("Сколько еды в тарелке?");
            Plate plate = new Plate(scr.nextInt());
            System.out.println("Сколько у вас котов?");
            Cat[] cats = new Cat[scr.nextInt()];

            for (int i=0; i<cats.length; i++)//вводим данным по котам
                  {
                      System.out.println("Как зовут кота?");
                      nameCat = scr.next();
                      System.out.println("Какой у него аппетит?");
                      appite = scr.nextInt();

                      cats[i] = new Cat(nameCat, appite);
            }
            System.out.println("Кошки кушают....");
            for (Cat cat:cats //коты кушают
                 ) {
                if (plate.getFood()<cat.getAppetite()) {
                    System.out.println("Еды не хватает. Осталось " + plate.getFood()+ ". Добавите еды (больше 0) ");
                    plate.addFood(scr.nextInt());
                }
                cat.eat(plate);
            }

            for (int i=0; i<cats.length; i++)//статус котов
            {
                System.out.println(cats[i].getName() + (cats[i].isHunger()?" сыт": " голоден") );
            }

        }
}
