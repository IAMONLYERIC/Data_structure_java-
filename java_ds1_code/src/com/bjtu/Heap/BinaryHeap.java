package com.bjtu.Heap;

import java.util.Comparator;

import com.bjtu.Tree.Printer.BinaryTreeInfo;

/**
 * 二叉堆（大顶堆） 堆是一个完全二叉树
 */

@SuppressWarnings({ "unchecked", "unused" })
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    public Object[] elements;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.size = 0;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
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
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        ElementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);

    }

    @Override
    public E get() {
        emptyCheck();
        return (E) elements[0];
    }

    @Override
    public E remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E replace(E element) {
        // TODO Auto-generated method stub
        return null;
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void ElementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must not be null");
        }
    }

    // 直接使用动态数组的扩容代码
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity < capacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    // 从某个位置开始上滤，以保持二叉堆的性质
    private void siftUp(int index) {
        E e = (E) elements[index];
        while (index > 0) {
            int pindex = (index - 1) >> 1;
            E p = (E) elements[pindex];
            if (compare(e, p) <= 0)
                return;

            // 交换p,e
            E tmp = (E) elements[index];
            elements[index] = elements[pindex];
            elements[pindex] = tmp;

            // 重新复制index
            index = pindex;
        }
    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;

        return index >= size ? null : index;

    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;

        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(Integer)node];
    }

}
