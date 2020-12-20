package com.helper;

import com.bjtu.Queue.Queue;

public class main_for_Queue {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        for(int i = 0; i < 10; i ++) {
            queue.enQueue(i);
        }

        System.out.println(queue.front());
        System.out.println(queue.size());

        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }


    }
    
}
