package com.helper;

import com.bjtu.Queue.CircleQueue;
import com.bjtu.Queue.Deque;
import com.bjtu.Queue.Queue;

public class main_for_Queue {
    public static void main(String[] args) {
        // Queue<Integer> queue = new Queue<>();

        CircleQueue<Integer> queue = new CircleQueue<>();

        for(int i = 0; i < 20; i ++) {
            queue.enQueue(i);
        }

        System.out.println(queue);

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        System.out.println(queue);

        for(int i = 0; i < 3; i ++) {
            queue.enQueue(i+10);
        }
        System.out.println(queue);

        System.out.println(queue.front());

        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }

        // Deque<Integer> deque = new Deque<>();

        // deque.enQueueRear(1);
        // deque.enQueueRear(2);
        // deque.enQueueFront(3);
        // deque.enQueueFront(4);

        // // rear 2  1 3 4  head
        
        // while(!deque.isEmpty()){
        //     System.out.println(deque.deQueueFront());
        // }
        



    }
    
}
