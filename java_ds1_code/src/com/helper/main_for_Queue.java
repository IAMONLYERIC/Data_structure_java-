package com.helper;

import com.bjtu.Queue.Deque;
import com.bjtu.Queue.Queue;

public class main_for_Queue {
    public static void main(String[] args) {
        // Queue<Integer> queue = new Queue<>();

        // for(int i = 0; i < 10; i ++) {
        //     queue.enQueue(i);
        // }

        // System.out.println(queue.front());
        // System.out.println(queue.size());

        // while(!queue.isEmpty()){
        //     System.out.println(queue.deQueue());
        // }

        Deque<Integer> deque = new Deque<>();

        deque.enQueueRear(1);
        deque.enQueueRear(2);
        deque.enQueueFront(3);
        deque.enQueueFront(4);

        // rear 2  1 3 4  head
        
        while(!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }
        



    }
    
}
