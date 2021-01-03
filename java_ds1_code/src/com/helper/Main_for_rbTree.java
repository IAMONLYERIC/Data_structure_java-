package com.helper;

import com.bjtu.Tree.BBST;
import com.bjtu.Tree.BinarySearchTree;
import com.bjtu.Tree.RBTree;
import com.bjtu.Tree.Printer.BinaryTrees;

public class Main_for_rbTree {

    public static void main(String[] args) {

        int[] arr = new int[] { 70, 56, 87, 22, 62, 74, 96, 20, 55, 68, 90, 50 };

        BBST<Integer> bst = new RBTree<>();

        for (int i : arr) {
            bst.add(i);
        }

        BinaryTrees.println(bst);

    }

}
