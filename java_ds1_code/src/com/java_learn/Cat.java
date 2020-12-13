package com.java_learn;

public class Cat extends Animal {

    public int age = 10;

    public Cat(String name, int age) {
        // super(name, age);
        // this.name = name;
        // this.age = age;
    }


    public Cat() {
        super("good", 10);

        this.name = "Cat";
        this.age = 22;

        // this.name = "good";
        // this.age = 10;
    }

    @Override
    public String toString() {
        return "Cat [ " + this.name + "," + this.age +  "]";
    }

    public void show()
    {
        System.out.println(this.age);
        System.out.println(super.age);
    }

    
}
