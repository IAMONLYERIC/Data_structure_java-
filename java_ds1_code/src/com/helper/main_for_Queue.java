package com.helper;

import com.bjtu.Queue.CircleDeque;
import com.bjtu.Queue.CircleQueue;
import com.bjtu.Queue.Deque;
import com.bjtu.Queue.Queue;

public class main_for_Queue {
    public static void main(String[] args) {
        // Queue<Integer> queue = new Queue<>();

        // CircleQueue<Integer> queue = new CircleQueue<>();

        // for(int i = 0; i < 20; i ++) {
        //     queue.enQueue(i);
        // }

        // System.out.println(queue);

        // queue.deQueue();
        // queue.deQueue();
        // queue.deQueue();

        // System.out.println(queue);

        // for(int i = 0; i < 3; i ++) {
        //     queue.enQueue(i+10);
        // }
        // System.out.println(queue);

        // System.out.println(queue.front());

        // while(!queue.isEmpty()){
        //     System.out.println(queue.deQueue());
        // }

        // Deque<Integer> deque = new Deque<>();
        CircleDeque<Integer> deque = new CircleDeque<>();
        
        for(int i = 0; i < 6; i++){
            deque.enQueueRear(i);
        }
        for(int i = 0; i < 6; i++){
            deque.enQueueFront(i+10);
        }


        System.out.println(deque);
        
        while(!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }
        



    }
    
}
