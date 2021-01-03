package com.bjtu.Tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public RBTree() {
        this(null);
    }

    // 节点染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null)
            return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        // RB树中空节点一般认为是黑色的
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点，直接染色
        if(parent == null){
            black(node);
            return;
        }

        // 父节点是黑色的四种情况，不用处理
        if(isBlack(parent)){
            return;
        }

        // 叔父节点
        Node<E> uncle = parent.sibling();

        // 祖父节点
        Node<E> grand = parent.parent;

        // 上溢的四种情况：叔父节点是红色
        if(isRed(uncle)){ 
            black(parent);
            black(uncle);

            // 把grand当成新添加的节点进行处理
            afterAdd(red(grand));
            return;
        }


        // 叔父节点不是红色的四种情况
        if(parent.isLeftChild()){ // L
            if(node.isLeftChild()){ // LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else { // LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else { // R
            if(node.isLeftChild()){ // RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else { // RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }

        }


    }

    private static class RBNode<E> extends Node<E> {
        // 比起传统二叉树的节点，AVL维护height属性
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            Node<E> outNode = (RBNode<E>) this;
            String parentString = "null";
            if (outNode.parent != null) {
                parentString = outNode.parent.element.toString();
            }

            return outNode.element + "_p(" + parentString + ")";
        }
    }

}
