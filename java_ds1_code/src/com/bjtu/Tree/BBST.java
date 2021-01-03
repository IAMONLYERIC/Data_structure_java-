package com.bjtu.Tree;

import java.util.Comparator;

public class BBST<E> extends BinarySearchTree<E> {
    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    public BBST() {
        this(null);
    }

    // 配合rebalance使用
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        

        // e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;


    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        grand.right = parent.left;
        parent.left = grand;

        // 更新parent节点的parent属性
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand.parent == null情况 即现在的parent是root节点
            root = parent;
        }

        // 更新grand.right的parent
        if (grand.right != null) {
            grand.right.parent = grand;
        }

        // 更新grand.parent属性
        grand.parent = parent;


    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        grand.left = parent.right;
        parent.right = grand;

        // 更新parent节点的parent属性
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand.parent == null情况 即现在的parent是root节点
            root = parent;
        }

        // 更新grand.left的parent
        if (grand.left != null) {
            grand.left.parent = grand;
        }

        // 更新grand.parent属性
        grand.parent = parent;
    }
    
}
