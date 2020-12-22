package com.bjtu.Tree;

import java.util.Comparator;

import com.bjtu.Tree.Printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    private Node<E> root;

    /**
     * 比较大小的策略： 1. 如果用户自定义了比较器comparator,则在compare函数中优先使用该比较器 2.
     * 如果没有自定义比较器，则要求传入的类型必须实现了comparable接口，则强制转换该对象为 comparable接口实例，然后进行比较。 3.
     * 如果上述两项都没满足，则报错。
     */
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this.comparator = null;
    }

    private class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        elementNotNullCheck(element); // 不允许存放空节点

        // 添加的是第一个节点
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }

        // 如果添加的不是第一个节点
        /**
         * 进行比较，一直走到待插入节点的父节点位置，并将元素插入 如果发现某个节点与待插入元素相等，则直接覆盖
         */
        Node<E> node = root;
        while (true) {
            int compare_res = compare(element, node.element);
            if (compare_res > 0) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new Node<E>(element, node);
                    break;
                }
            } else if (compare_res < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new Node<E>(element, node);
                    break;
                }
            } else {
                node.element = element; // 如果相等则覆盖
                break;
            }
        }

    }

    public boolean contains(E element) {
        return false;
    }

    public void clear() {

    }

    public E remove(E element) {
        return null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 如果e1大于e2,返回正数 如果e1小于e2,返回负数 如果e1等于e2,返回 0
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    public void preOrderTraverSal(){
        preOrderTraverSal(root);
    }

    private void preOrderTraverSal(Node<E> node){
        if(node == null) return;

        System.out.print(node.element + " ");
        preOrderTraverSal(node.left);
        preOrderTraverSal(node.right);
    }

    public void inOrderTraverSal(){
        inOrderTraverSal(root);
    }

    private void inOrderTraverSal(Node<E> node){
        if(node == null) return;
        inOrderTraverSal(node.left);
        System.out.print(node.element + " ");
        inOrderTraverSal(node.right);
    }

    public void postOrderTraverSal(){
        postOrderTraverSal(root);
    }

    private void postOrderTraverSal(Node<E> node){
        if(node == null) return;
        
        postOrderTraverSal(node.left);
        postOrderTraverSal(node.right);
        System.out.print(node.element + " ");
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {

        Node<E> outNode = (Node<E>) node;
        String parentString  = "null";
        if(outNode.parent != null){
            parentString = outNode.parent.element.toString();
        }

        return outNode.element + "_p(" + parentString + ")";
    }

}