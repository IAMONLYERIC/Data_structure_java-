package com.helper;

import com.bjtu.Map.HashMap;
import com.bjtu.Map.Person;

public class Main_for_hash {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Person p1 = new Person(18, 105.6f, "tom");
        Person p2 = new Person(18, 105.6f, "tom1");

        // System.out.println(p1.hashCode());
        // System.out.println(p2.hashCode());
        // System.out.println(p1.equals(p2));

        HashMap<Object, Object> hmap = new HashMap<>();

        hmap.put(p1, 1);
        hmap.put(p1, 2);
        hmap.put("tom", 3);
        hmap.put("jack", 4);
        hmap.put(100, 5);
        hmap.put(101, 6);


        System.out.println(hmap.size());
        System.out.println(hmap.get(p1));
        System.out.println(hmap.get("tom"));
        System.out.println(hmap.get("jack"));
        System.out.println(hmap.get(100));
        System.out.println(hmap.get(101));
    }
}
