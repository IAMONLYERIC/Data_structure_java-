package com.helper;

import java.util.Comparator;

import com.bjtu.Heap.BinaryHeap;
import com.bjtu.Tree.Printer.BinaryTrees;

public class Main_for_heap {

    public static void main_heap(String[] args) {
        // test1();
        // test2();
        // test3();
        test4();
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

    public static void test2(){
       
        Integer [] arr = new Integer []{16,85,96,25,35,62,42};

        BinaryHeap<Integer> heap = new BinaryHeap<>(arr);

        BinaryTrees.println(heap);

    }

    public static void test3(){
        
       
        Integer [] arr = new Integer []{16,85,96,25,35,62,42};

        // 通过比较器可以实现小顶堆的创建
        BinaryHeap<Integer> heap = new BinaryHeap<>(arr, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                
                return o2 - o1;
            }
            
        });

        BinaryTrees.println(heap);

    }

    public static void test4(){
        // TOP K 问题
        Integer [] data = new Integer[] {57, 97, 86, 45, 29, 38, 68, 8, 10, 4, 3, 96, 13, 91, 77, 85, 90, 47, 81, 25, 92, 78, 64, 22, 24, 74, 54, 27, 48, 20, 55, 95, 26, 53, 65, 14, 73, 42, 46, 1, 51, 63, 15, 71, 21, 75, 59, 58, 84};
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                
                return o2 - o1;
            }
            
        });

        int k = 5;
        for (int i = 0; i < data.length; i++) {
            if(heap.size() < k){
                heap.add(data[i]);
            } else {
                if(heap.get() < data[i]){
                    heap.replace(data[i]);
                }
            }
        }

        BinaryTrees.println(heap);

    }
    
    
}
