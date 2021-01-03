package com.bjtu.Queue;

import com.bjtu.LinkList_P.Double_LinkedList;

@SuppressWarnings("unchecked")
public class CircleDeque<E> {
    private int size;
    private Object[] elements;
    private int front; // 存储队列的前端元素下标

    private final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        elements[(front + size) % elements.length] = element;
        size++;
    }

    public E deQueueFront() {
        E oldElement = (E) elements[front];

        elements[front] = null;
        front = (front + 1) % elements.length;

        size--;
        return oldElement;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        // 如果索引时负数，则加上数组长度变换到对应位置
        int index = (front - 1) < 0 ? front - 1 + elements.length : front - 1;
        elements[index] = element;
        size++;
        front = index; // 更改头元素位置
    }

    public E deQueueRear() {
        E oldElement = (E) elements[(front + size - 1) % elements.length];
        elements[(front + size - 1) % elements.length] = null;
        size--;

        return oldElement;
    }

    public E front() {

        return (E) elements[front];
    }

    public E rear() {

        return (E) elements[(front + size - 1) % elements.length];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("capacity = ").append(elements.length).append("  size= ").append(size).append(", front= ").append(elements[front]).append(" , [");
        for (int i = 0; i < elements.length - 1; i++) {
            str.append(elements[i]).append(",");
        }
        if (elements.length - 1 >= 0)
            str.append(elements[elements.length - 1]);
        str.append("]");

        return str.toString();
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity < capacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(front + i) % oldCapacity];
            }
            elements = newElements;
            front = 0;
            System.out.println(oldCapacity + "  扩容为： " + newCapacity);
        }
    }

}