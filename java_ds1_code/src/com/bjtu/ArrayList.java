package com.bjtu;

public class ArrayList<E> {

    /**
     * size是数组元素的数量 element 为内部的数组
     */
    private int size;
    private Object[] element;
    private static final int DEFAULT_CAPACILY = 2;
    private static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 构造函数
     * 
     * @param capacity 传入的初始容量
     */
    public ArrayList(int capacity) {
        element = new Object[capacity < DEFAULT_CAPACILY ? DEFAULT_CAPACILY : capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACILY);
    }

    public int size() {
        return size;
    } // 元素的数量

    public boolean isEmpty() {
        return size == 0;
    } // 是否为空

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    } // 是否包含某个元素

    public void add(E element) {
        ensureCapacity(size + 1);
        this.element[size++] = element;
    } // 添加元素到最后面

    private void check_bound(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("数组越界: index: " + index + " size: " + size);
    }

    private void check_bound(int index, int bound) {
        if (index < 0 || index >= bound)
            throw new IndexOutOfBoundsException("数组越界: index: " + index + " bound: " + bound);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = element.length;
        if (oldCapacity < capacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = element[i];
            }
            element = newElements;
            System.out.println(oldCapacity + "  扩容为： " + newCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        check_bound(index);
        return (E) element[index];
    } // 返回index位置对应的元素

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        check_bound(index);
        E old = (E) this.element[index];
        this.element[index] = element;
        return old;
    } // 设置index位置的元素

    public void add(int index, E element) {
        check_bound(index, size + 1);
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            this.element[i + 1] = this.element[i];
        }
        this.element[index] = element;
        size++;
    }; // 往index位置添加元素

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        check_bound(index);
        E old = (E) element[index];
        for (int i = index; i < size - 1; i++) {
            element[i] = element[i + 1];
        }
        size--;

        // 后面向前移动的时候，最后一个元素需要清空。
        this.element[size] = null;

        return old;
    } // 删除index位置对应的元素

    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (this.element[i]==null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                    if (element.equals(this.element[i]))
                        return i;
            }
        }

        
        return ELEMENT_NOT_FOUND;
    } // 查看元素的位置

    public void clear() {

        // 清空对象引用，方便垃圾回收
        for (int i = 0; i < size; i++)
            this.element[i] = null;
        size = 0;
        // element = new Object[DEFAULT_CAPACILY];
    } // 清除所有元素

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("size=").append(size).append(", [");
        for (int i = 0; i < size - 1; i++) {
            str.append(element[i]).append(",");
        }
        if (size - 1 >= 0)
            str.append(element[size - 1]);
        str.append("]");

        return str.toString();
    }

}