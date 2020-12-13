package com.bjtu;


// 此处实现了List接口，所以子类可以只用继承该抽象类即可
// 同时 此类将链表和数组的公共代码进行了抽取，比如size，和一些共有的函数
public abstract class AbstractList<E> implements List<E>{

    protected int size;

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
        // ensureCapacity(size + 1);
        // this.element[size++] = element;
        add(size,element);
    } // 添加元素到最后面

    protected void check_bound(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("数组越界: index: " + index + " size: " + size);
    }

    protected void check_bound(int index, int bound) {
        if (index < 0 || index >= bound)
            throw new IndexOutOfBoundsException("数组越界: index: " + index + " bound: " + bound);
    }

}
