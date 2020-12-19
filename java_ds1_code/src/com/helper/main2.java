package com.helper;

import com.bjtu.ArrayList;
import com.bjtu.LinkList_P.CycleDouble_LinkedList;
import com.bjtu.LinkList_P.CycleLinkedList;
import com.bjtu.LinkList_P.Double_LinkedList;
import com.bjtu.LinkList_P.LinkedList;
import com.bjtu.LinkList_P.LinkedList_with_head;
import com.bjtu.List;

public class main2 {
    public static void main2(String[] args) {
        // ArrayList<Person> arr = new ArrayList<>();
        // LinkedList<Person> arr = new LinkedList<>();

        // List<Person> arr = new ArrayList<>();
        // List<Person> arr = new LinkedList<>();
        // List<Person> arr = new LinkedList_with_head<>();
        // List<Person> arr = new CycleLinkedList<>();
        List<Person> arr = new CycleDouble_LinkedList<>();
        // Double_LinkedList<Person> arr = new Double_LinkedList<>();

        arr.add(new Person(1,"Tom"));
        arr.add(new Person(2,"Jack"));
        
        for(int i = 0; i < 10; i++)
        {
            arr.add(new Person(i, "name:" + i));
        }

        arr.add(null);
        arr.add(null);
        arr.add(null);

       

        for(int i = 0; i < 3; i++)
        {
            arr.remove(0);
        }

        arr.add(arr.size(),new Person(666, "name:666"));

        // 添加最后一个与删除最后一个
       
        arr.remove(arr.size()-1);
        
        // 添加到第一个
        arr.add(0,new Person(666, "name:666"));
        
        // 打印操作
        System.out.println(arr);

        // 查找操作
        System.out.println(arr.indexOf(new Person(6, "name:6")));
        System.out.println(arr.indexOf(new Person(3, "name:2")));
        System.out.println(arr.indexOf(null));

        java.util.LinkedList<Integer> arr2;

        

    }
}
