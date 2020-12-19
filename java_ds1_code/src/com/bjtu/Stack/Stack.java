package com.bjtu.Stack;

import com.bjtu.ArrayList;

// 使用继承的方法，虽然可以实现功能，但会导致外界也能访问父类的公开接口
/** 
public class Stack<E> extends ArrayList<E> {
    public void push(E element) {
        add(element);
    }

    public E pop() {
        return remove(size - 1);
    }

    public E top() {
        return get(size - 1);
    }
}
*/

// 采用复合方式 即内部蕴含一个私有的数组对象，此方法可以防止数组对象公共接口暴露
public class Stack<E>{

    private ArrayList<E> array;

    public Stack() {
        array = new ArrayList<E>();
    }

    public void push(E element) {
        array.add(element);
    }

    public E pop() {
        return array.remove(array.size() - 1);
    }

    public E top() {
        return array.get(array.size() - 1);
    }

    public int size(){
        return array.size();
    }

    public boolean isEmpty(){
        return array.isEmpty();
    }
}