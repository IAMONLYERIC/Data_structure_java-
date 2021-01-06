package com.helper;

import com.bjtu.Map.HashMap;
import com.bjtu.Map.Key;
import com.bjtu.Map.Person;

public class Main_for_hash {
    public static void main(String[] args) {
        // test1();
        test2();
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


        System.out.println("size:" + hmap.size());
        System.out.println(hmap.get(p1));
        System.out.println(hmap.get("tom"));
        System.out.println(hmap.get("jack"));
        System.out.println(hmap.get(100));
        System.out.println(hmap.get(101));

        System.out.println(hmap.remove(p1));
        System.out.println(hmap.get(p1)); 
        System.out.println("size:" + hmap.size());

        System.out.println(hmap.containsKey(p1));
        System.out.println(hmap.containsKey("jack"));
        System.out.println(hmap.containsValue(6));
        System.out.println(hmap.containsValue(7));

        hmap.print();
    }

    public static void test2(){
        // 通过贴别创建的Key对象来测试bug

        HashMap<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 0; i < 10; i++) {
            map.put(new Key(i), i+10);
        }
        System.out.println(map.size());
        System.out.println(map.get(new Key(1)));
        map.print();

    }
}
