package com.bjtu.Heap;

import java.util.Comparator;

import com.bjtu.Tree.Printer.BinaryTreeInfo;

/**
 * 二叉堆（大顶堆） 堆是一个完全二叉树
 */

@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements  BinaryTreeInfo {

    public Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
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
        return (E)elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        // 先让数组最后一个位置的元素覆盖堆顶元素，然后删除最后一个位置。并从堆顶开始下溢
        E root = (E) elements[0];
        elements[0] = elements[size - 1];
        elements[size -1] = null;
        size--;
        siftDown(0);
        return root;
    }

    // 用remove和add实现，但是效率不高, 2*log(n)
    // @Override
    // public E replace(E element) {
    //     E root = remove();
    //     add(element);
    //     return root;
    // }

    @Override
    public E replace(E element) {
        ElementNotNullCheck(element);
        E root = null;
        if(size == 0){
            // 第一个元素
            elements[0] = element;
            size++;
        }else{
            // 新添加的元素覆盖堆顶，然后开始下溢
            root = (E)elements[0];
            elements[0] = element;
            siftDown(0);
            
        }
        return root;
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
                break;
            // 将pindex位置元素放到index位置
            elements[index] = p;
            // 重新复制index
            index = pindex;
        }
        elements[index] = e;
    }

    private void siftDown(int index){
        E element = (E)elements[index];
        int half = size >> 1;
        // index要小于第一个叶子节点索引(非叶子节点的数量) 完全二叉树性质
        while(index < half){
            // 1. 只有左子节点
            // 2. 同时有左右子节点

            // 默认为左子节点的索引, 用左子节点与index节点进行比较
            int childIndex = (index << 1) + 1;
            E child = (E)elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            if(rightIndex < size && compare((E)elements[rightIndex], child) > 0){
                childIndex = rightIndex;
                child = (E) elements[rightIndex];
            }

            // 如果要下溢的元素大于等于最大子节点，满足二叉堆性质
            if(compare(element, child) >= 0) break;

            // 不满足，先把子节点放上去。并继续寻找位置
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;

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
