package com.bjtu.Tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
        this(null);
    }

    // 用于添加节点之后，见BST中add函数里面
    @Override
    protected void afterAdd(Node<E> node) {

        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 当前节点平衡，只需要更新高度

                updateHeight(node);

            } else { // 节点不平衡，需要调整并且更新高度

            }
        }

    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {

        return new AVLNode<E>(element, parent);
    }

    // 此处封装仅仅是为了把强制转换代码疯转起来
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        // 比起传统二叉树的节点，AVL维护height属性
        int height = 1;

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        // 获取节点的平衡因子
        private int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            return leftHeight - rightHeight;
        }

        private void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }
    }

    // 该节点是否平衡
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

}
