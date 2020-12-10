package com.helper;

public class Person {
    private final int age;
    private final String name;

    public Person(final int age, final String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }

    @Override
    protected void finalize(){
        System.out.println("Person-name: " + name + " finalize.");
    }

    @Override
    public boolean equals(Object obj) {
    if(obj == null) return false;

    if(obj instanceof Person){
        Person person = (Person) obj;
        return this.age == person.age && this.name == person.name;
    }
    return false;
        
    }
    
}
