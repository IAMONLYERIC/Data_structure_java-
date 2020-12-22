package com.helper;

import com.bjtu.Tree.BinarySearchTree;
import com.bjtu.Tree.Printer.BinaryTrees;
import com.bjtu.Tree.Printer.Printer;

public class main_for_bst {
    public static void main(String[] args) {
        
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();

        Integer []arr = new Integer[]{
            7,4,9,2,5,8,11,3,1,12
        };

        for (Integer integer : arr) {
            bst1.add(integer);
        }

        BinaryTrees.print(bst1);
        
    }
}
