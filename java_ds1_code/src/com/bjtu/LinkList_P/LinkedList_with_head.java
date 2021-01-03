package com.bjtu.LinkList_P;

import com.bjtu.AbstractList;

public class LinkedList_with_head<E> extends AbstractList<E> {

    private Node<E> first;

    public LinkedList_with_head(){
        this.first = new Node<>(null, null);
    }
    
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
        size = 0;
        first = new Node<>(null, null);
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        check_bound(index,size+1);

        // 虽有有哨兵节点，但是当index为0时候，还需要手动返回first
        Node<E> preNode = index==0 ? first : node(index-1);
        preNode.next = new Node<E>(element, preNode.next);

        size++;
    }

    @Override
    public E remove(int index) {
        check_bound(index);

        // 虽有有哨兵节点，但是当index为0时候，还需要手动返回first
        Node<E> preNode = index==0 ? first : node(index-1); 
        E old = preNode.next.element;
        preNode.next = preNode.next.next;

        size--;

        // 不用将index位置的节点next置为null，因为已经没有引用指向index位置的节点了，
        // 所以该节点已经被自动释放
        return old;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = first.next;

        // 此处是允许数组中存放null元素
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element==null)
                    return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element))
                    return i;
                node = node.next;    
            }
        }

        
        return ELEMENT_NOT_FOUND;
    }

    // 获取下标为index的节点,带有哨兵节点，多找一个
    private Node<E> node(int index){
        check_bound(index);
        Node<E> node = first;
        for(int i = 0; i < index + 1; i++){
            node = node.next;
        }
        return node;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        Node<E> node = first.next;

        str.append("size=").append(size).append(", [");
        for (int i = 0; i < size - 1; i++) {
            str.append(node.element).append(",");
            node = node.next;
        }

        if (size >= 1)
            str.append(node.element);
        str.append("]");
        return str.toString();
    }
}
