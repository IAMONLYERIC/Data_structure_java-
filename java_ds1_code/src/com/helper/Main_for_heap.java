package com.helper;

import com.bjtu.Heap.BinaryHeap;
import com.bjtu.Tree.Printer.BinaryTrees;

public class Main_for_heap {

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(13);
        heap.add(78);
        heap.add(56);
        heap.add(5);
        heap.add(66);

        System.out.println(heap.size());

        BinaryTrees.println(heap);

        heap.remove();
        BinaryTrees.println(heap);

        heap.replace(55);
        BinaryTrees.println(heap);
    }
    
}
