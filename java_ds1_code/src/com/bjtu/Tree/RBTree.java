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

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点 或者 上溢到了根节点，直接染色
        if (parent == null) {
            black(node);
            return;
        }

        // 父节点是黑色的四种情况，不用处理
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        Node<E> uncle = parent.sibling();

        // 祖父节点
        Node<E> grand = parent.parent;

        // 上溢的四种情况：叔父节点是红色
        if (isRed(uncle)) {
            black(parent);
            black(uncle);

            // 把grand当成新添加的节点进行处理
            afterAdd(red(grand));
            return;
        }

        // 叔父节点不是红色的四种情况
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
                red(grand);
                rotateRight(grand);
            } else { // LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }

        }

    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {

        // 删除红色节点，
        if (isRed(node)) {
            return;
        }

        // 拥有一个red子节点的black节点,此时判断条件为
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        // 如果删除的是根节点：只有一个黑色节点，且该节点是根节点
        if (parent == null) {
            return;
        }

        // 删除黑色节点
        // 判断被删除的node节点是左还是右, 由于传进来的node节点已经被父节点删除，所以此处不能用isLeftChild()判断  
        // 补充：添加 || node.isLeftChild()条件，为了应对删除黑色节点，且兄弟节点是黑色，兄弟子节点为空，父节点向下合并的递归情况
        boolean left = node.parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if(isRed(sibling)){ // 兄弟节点是红色, 先通过旋转红色兄弟变成黑色兄弟
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 来到此处兄弟节点sibling都是黑色的
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                // 兄弟子节点没有一个红色节点 黑兄弟为空的情况，此时需要父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent, null);
                }

            }else {
                // 兄弟子节点至少有一个红色节点
                // 为了统一处理，如果兄弟节点只有一个右红节点 LR情况，先对其进行处理
                if(isBlack(sibling.right)){
                    rotateRight(sibling);
                    // 因为旋转过，sibling不是之前的sibling，需要重新赋值
                    sibling = parent.right; 
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.right);
                
                rotateLeft(parent);

            }
        } else { // 被删除的的节点在右边，兄弟节点在左边
            if(isRed(sibling)){ // 兄弟节点是红色, 先通过旋转红色兄弟变成黑色兄弟
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 来到此处兄弟节点sibling都是黑色的
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                // 兄弟子节点没有一个红色节点 黑兄弟为空的情况，此时需要父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent, null);
                }

            }else {
                // 兄弟子节点至少有一个红色节点
                // 为了统一处理，如果兄弟节点只有一个右红节点 LR情况，先对其进行处理
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    // 因为旋转过，sibling不是之前的sibling，需要重新赋值
                    sibling = parent.left; 
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.left);

                rotateRight(parent);

            }
        }

    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
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

            return outNode.element + "_p(" + parentString + ")" + "_C(" + (color == RED ? "R" : "B") + ")";
        }
    }

}
