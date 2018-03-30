package ru.AnimalExplore;

public abstract class Animal {


    public String getLocation() {
        return "Earth";
    }

    public abstract int getNumberOfLegs() ;

}

interface Voice {
    public String voice();
}

interface Run {}
interface Jump {}

