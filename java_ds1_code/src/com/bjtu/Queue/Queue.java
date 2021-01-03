package com.bjtu.Queue;

import com.bjtu.LinkList_P.Double_LinkedList;

public class Queue<E>{
    private Double_LinkedList<E> list = new Double_LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void enQueue(E element){
        list.add(element);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){

        return list.get(0);
    }

}