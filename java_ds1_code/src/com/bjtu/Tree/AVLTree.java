package com.bjtu.Tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class AVLTree<E> extends BBST<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
        this(null);
    }

    // 用于删除节点之后，见BST中remove函数里面. 与afterAdd函数只差一个break，因为删除之后可以所有的祖父节点全部失衡
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 当前节点平衡，只需要更新高度
                updateHeight(node);
            } else { // 节点不平衡，需要调整并且更新高度； 此时找到的节点为最高的不平衡的节点
                rebalance(node);
            }
        }
    }

    // 用于添加节点之后，见BST中add函数里面
    @Override
    protected void afterAdd(Node<E> node) {

        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 当前节点平衡，只需要更新高度
                updateHeight(node);
            } else { // 节点不平衡，需要调整并且更新高度； 此时找到的节点为最高的不平衡的节点
                rebalance(node);
                break; // 只要node恢复平衡，则整棵树都会恢复平衡
            }
        }

    }

    /**
     * 回复不平衡节点的平衡
     * 
     * @param grand
     */
    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) { // L

            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }

        } else { // R
            if (node.isLeftChild()) { // RL

                rotateRight(parent);
                rotateLeft(grand);

            } else { // RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复不平衡节点的平衡： 这种方法为统一旋转方式
     * 
     * @param grand
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) { // L

            if (node.isLeftChild()) { // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }

        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else { // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.left;

        super.rotateLeft(grand);
        // 更新 g,p高度,从低往高更新

        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotateRight(Node<E> grand) {

        Node<E> parent = grand.left;

        super.rotateRight(grand);
        // 更新 g,p高度,从低往高更新
        
        updateHeight(grand);
        updateHeight(parent);
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

        Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight)
                return left;
            if (leftHeight < rightHeight)
                return right;
            if (leftHeight == rightHeight) { // 返回与自己同向的节点 同为父节点的左子树或者右子树
                if (this.isLeftChild()) {
                    return left;
                } else {
                    return right;
                }

            }
            return null;
        }

        @Override
        public String toString() {
            Node<E> outNode = (AVLNode<E>) this;
            String parentString = "null";
            if (outNode.parent != null) {
                parentString = outNode.parent.element.toString();
            }

            return outNode.element + "_p(" + parentString + ")" + "_h(" + height + ")";
        }
    }

    // 该节点是否平衡
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

}
