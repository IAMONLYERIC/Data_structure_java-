package com.helper;

import com.bjtu.Tree.AVLTree;
import com.bjtu.Tree.BinarySearchTree;
import com.bjtu.Tree.BinaryTree;
import com.bjtu.Tree.Printer.BinaryTrees;

public class Main_for_avl {

    public static void main(String[] args) {

        int [] arr = new int[]{
            1,2,3,4,5,6,7,8,9,10
        };

        BinarySearchTree<Integer> bst = new AVLTree<>();

        for (int i : arr) {
            bst.add(i);
        }

        BinaryTrees.println(bst);
    }
    
}
