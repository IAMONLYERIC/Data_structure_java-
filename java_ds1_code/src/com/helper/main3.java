package com.helper;

import com.bjtu.List;
import com.bjtu.LinkList_P.*;

public class main3 {
    public static void main3(String[] args) {
        // LinkedList<Integer> slist = new LinkedList<>();
        josephus();

    }


    public static void josephus(){
        CycleDouble_LinkedList<Integer> arr = new CycleDouble_LinkedList<>();

        for(int i = 1; i <= 10; i++){
            arr.add(i);
        }
        arr.reset();
        while(arr.size() != 1){
            arr.next();arr.next();
           System.out.println("remove: " + arr.remove()) ; 
        }
        System.out.println("幸存者为:" + arr.get(0));

    }
}
