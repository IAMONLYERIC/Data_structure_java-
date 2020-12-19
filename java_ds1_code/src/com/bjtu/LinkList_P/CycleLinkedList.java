package com.bjtu.LinkList_P;

import com.bjtu.AbstractList;

public class CycleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E> {
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
        first = null;
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
        check_bound(index, size + 1);

        if (index == 0) {
            // 当index==1时候，在node函数中进行范围检查不能通过，所以要单独处理。

            if(size == 0){
                first = new Node<E>(element, first);// 如果当前插入的是第一个元素，则自己指向自己
                first.next = first;
            }else{
                Node<E> last = node(size - 1); 
                first = new Node<E>(element, first);
                last.next = first;
            }

            
        } else {
            Node<E> preNode = node(index - 1);
            preNode.next = new Node<E>(element, preNode.next);
        }

        size++;

    }

    @Override
    public E remove(int index) {
        check_bound(index);

        E old;
        if (index == 0) {
            // 当index==1时候，在node函数中进行范围检查不能通过，所以要单独处理。
            old = first.element;

            if(size == 1){
                first = null;  // 只有一个节点时，循环链表头结点直接置空
            }else{
                Node<E> last = node(size - 1); // 一定要放在下一句的前面,否则first变了，这一句就会越界。
                first = first.next;
                last.next = first;
            }
            
        } else {
            Node<E> preNode = node(index - 1);
            old = preNode.next.element;
            preNode.next = preNode.next.next;
        }

        size--;

        // 不用将index位置的节点next置为null，因为已经没有引用指向index位置的节点了，
        // 所以该节点已经被自动释放

        return old;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = first;

        // 此处是允许数组中存放null元素
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null)
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

    // 获取下标为index的节点
    private Node<E> node(int index) {
        check_bound(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        Node<E> node = first;

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
