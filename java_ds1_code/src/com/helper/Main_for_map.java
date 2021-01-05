package com.helper;

import com.bjtu.Map.TreeMap;
import com.bjtu.Map.Map.Visitor;

public class Main_for_map {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] arr = new int[] { 70, 56, 87, 22, 62, 74, 96, 20, 55, 68, 90, 50 };

        for (int i : arr) {
            map.put(i, 100-i);
        }

        System.out.println("size: " + map.size());

        map.traversal(new Visitor<Integer,Integer>(){

            @Override
            public boolean visit(Integer key, Integer value) {
                System.out.println(key + ":" + value);

                return false;
            }
            
        } );

    }

}
