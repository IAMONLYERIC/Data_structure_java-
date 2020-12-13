package com.helper;

import com.bjtu.ArrayList;
import com.bjtu.LinkedList;
import com.bjtu.List;

public class main2 {
    public static void main(String[] args) {
        // ArrayList<Person> arr = new ArrayList<>();
        // LinkedList<Person> arr = new LinkedList<>();

        // List<Person> arr = new ArrayList<>();
        List<Person> arr = new LinkedList<>();
        
        for(int i = 0; i < 10; i++)
        {
            arr.add(new Person(i, "name:" + i));
        }

        arr.add(null);
        arr.add(null);
        arr.add(null);

        arr.remove(5);
        arr.remove(0);

        System.out.println(arr);
        System.out.println(arr.indexOf(new Person(3, "name:2")));

        System.out.println(arr.indexOf(null));


    }
}
