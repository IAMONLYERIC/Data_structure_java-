package com.helper;

import com.bjtu.Tree.BinarySearchTree;
import com.bjtu.Tree.RBTree;
import com.bjtu.Tree.Printer.BinaryTrees;

public class Main_for_rbTree {
    
    public static void main(String[] args) {

        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        RBTree<Integer> bst = new RBTree<>();

        bst.add(1);

        // for (int i : arr) {
        //     bst.add(i);
        // }

        // BinaryTrees.println(bst);

    }

}
