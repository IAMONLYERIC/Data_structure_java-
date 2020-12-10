package com.helper;

import com.bjtu.ArrayList;

public class main2 {
    public static void main(String[] args) {
        ArrayList<Person> arr = new ArrayList<>();
        
        for(int i = 0; i < 10; i++)
        {
            arr.add(new Person(i, "name:" + i));
        }

        arr.add(null);
        arr.add(null);
        arr.add(null);

        // System.out.println(arr);
        System.out.println(arr.indexOf(new Person(3, "name:2")));

        System.out.println(arr.indexOf(null));

        // java.util.ArrayList




    }
}
