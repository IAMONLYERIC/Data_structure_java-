package com.bjtu.Map;

public class Person {

    int age;
    float height;
    String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public Person() {
        this(0, 0, null);
    }

    // 计算hash值
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = hashCode * 31 + Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;
    }

    // 用来比较key值是否相等
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true; // 内存地址一样， 同一个对象 为true

        if(obj == null || this.getClass() != obj.getClass()) return false; // obj为空 或类型不一样 返回false
        // if (obj == null || !(obj instanceof Person))
        //     return false; // obj为空 或 类型不一样 返回false

        // 来到此处代表obj非空，且为Person类对象
        Person pObj = (Person) obj;
        return this.age == pObj.age && this.height == pObj.height && pObj.name == null ? this.name == null
                : pObj.name.equals(this.name);

    }

}
