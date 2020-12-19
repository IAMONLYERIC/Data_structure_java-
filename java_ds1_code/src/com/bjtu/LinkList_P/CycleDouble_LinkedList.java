package com.bjtu.LinkList_P;

import com.bjtu.AbstractList;


public class CycleDouble_LinkedList<E> extends AbstractList<E> {

    public Node<E> first;
    public Node<E> last;
    public Node<E> current;

    public static class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        public E get(){
            return element;
        }
    }

    @Override
    public void clear() {
        size = 0;

        // 链表内节点虽然相互引用，但是非gc_root对象指向，仍会被释放
        first = null;
        last = null;
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
            
            Node<E> node = new Node<E>(last, element, first);

            // case 2: 如果当前链表里面没有元素，则第一次插入 需要把头尾都连接好
            if (size == 0) {
                first = node;
                last = first;
                node.next = node;
                node.prev = node;
            }else{// case 1: 针对首元素的插入
                first.prev = node;
                last.next = node;
                first = node;
            }


        } else {
            Node<E> preNode = node(index - 1);
            Node<E> nextNode = preNode.next;

            // case 3: 如果当前位置不是最后一个位置
            if (nextNode != first) {

                Node<E>nowNode = new Node<E>(preNode, element, nextNode);
                preNode.next = nowNode;
                nextNode.prev = nowNode;

            } else { // case 4: 如果插入的是最后一个位置，则需要给last赋值
                // last = last.next = new Node<E>(last, element, null);

                Node<E> nowNode = new Node<E>(last, element, last.next);
                last.next = nowNode;
                last = nowNode;
                first.prev = last;
            }

        }

        size++;

    }

    @Override
    public E remove(int index) {
        check_bound(index);

        return remove(node(index));
    }


    private E remove(Node<E> node){
        E old;
        if (node == first) {
            // 当index==1时候，在node函数中进行范围检查不能通过，所以要单独处理。
            old = first.element;

            if(size == 1){
                first = last = null;
            }else{
                // Node<E> node = first;
                first = first.next;

                // 将之前的连线清空  不必要
                // node.next = null;
                // node.prev = null;

                first.prev = last;
                last.next = first;
            }
            
        } else {

            // case： 删除最后一个元素
            if(node == last){
                old = last.element;
                last = last.prev;
                last.next = first;
            }else{
                // case : 删除非最后一个元素
                Node<E> preNode = node.prev;
                old = node.element;
                preNode.next = node.next;
                node.next.prev = preNode;
            }
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
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
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


    // 为解决约斯夫问题 增加的函数 
    public void reset(){
        current = first;
    }

    public E next(){
        if (current == null) return null;

        current = current.next;
        return current.element;
    }

    public E remove(){
        if (current == null) return null;
        Node<E> oldNode = current;
        remove(current);

        if(size > 0){
            current = oldNode.next;
        }else{
            current = null;
        }
        return  oldNode.element;

    }
}
