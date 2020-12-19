package com.helper;

import com.bjtu.List;
import com.bjtu.LinkList_P.*;

public class main3 {
    public static void main(String[] args) {
        // LinkedList<Integer> slist = new LinkedList<>();

        CycleDouble_LinkedList<Person> arr = new CycleDouble_LinkedList<>();

        arr.add(new Person(1,"Tom"));
        arr.add(new Person(2,"Jack"));

        arr.reset();
        System.out.println(arr);

        arr.remove();
        System.out.println(arr);

        System.out.println(arr.current);
        arr.remove();
        System.out.println(arr);

        System.out.println(arr.current);

    }
}
