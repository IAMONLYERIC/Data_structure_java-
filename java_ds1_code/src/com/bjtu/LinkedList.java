package com.bjtu;

public class LinkedList<E> implements List<E> {

    private int size;
    private Node<E> first;
    

    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void add(E element) {
        // TODO Auto-generated method stub

    }

    @Override
    public E get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E set(int index, E element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub

    }

    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
