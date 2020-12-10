package com.helper;
import com.bjtu.ArrayList;

public class Main {
    public static void main1(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.println(arr.size());
        

        for(int i = 0; i < 100; i++)
            arr.add(i);
        System.out.println(arr);

        arr.clear();
        for(int i = 0; i < 41; i++)
            arr.add(i);

        System.out.println(arr);




    }
}
