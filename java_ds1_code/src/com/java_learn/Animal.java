package com.java_learn;

public class Animal {
    public String name = "Animal";
    public int age = 1;
    
    protected void eat()
    {
        System.out.println("Animal eat!");
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal() {
        // this("Animal",5);
    }

    @Override
    public String toString() {
        return "Animal [age=" + age + ", name=" + name + "]";
    }

    
}

