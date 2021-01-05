package com.helper;

import com.bjtu.Set.ListSet;
import com.bjtu.Set.Set;
import com.bjtu.Set.TreeSet;
import com.bjtu.Set.Set.Visitor;

public class Main_for_set {

    public static void main(String[] args) {
        // test1();
        test2();
    }

    public static void test1() {
        Set<Integer> set = new ListSet<>();
        int[] arr = new int[] { 70, 56, 87, 22, 62, 74, 96, 20, 55, 68, 90, 50 };

        for (int i : arr) {
            set.add(i);
        }

        System.out.println("size: " + set.size());

        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }

        });

    }

    public static void test2() {
        Set<Integer> set = new TreeSet<>();
        int[] arr = new int[] { 70, 56, 87, 22, 62, 74, 96, 20, 55, 68, 90, 50};

        for (int i : arr) {
            set.add(i);
        }

        System.out.println("size: " + set.size());

        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }

        });

    }

}
