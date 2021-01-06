package com.bjtu.Tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {

    /**
     * 比较大小的策略： 1. 如果用户自定义了比较器comparator,则在compare函数中优先使用该比较器 2.
     * 如果没有自定义比较器，则要求传入的类型必须实现了comparable接口，则强制转换该对象为 comparable接口实例，然后进行比较。 3.
     * 如果上述两项都没满足，则报错。
     */
    protected Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this.comparator = null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }
    // 创建该公共接口，供遍历时访问节点使用

    // 此方法是为了方便子类 AVL RB树等定义自己的节点
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node(element, parent);
    }

    public void add(E element) {
        elementNotNullCheck(element); // 不允许存放空节点
        size++;

        // 添加的是第一个节点
        if (root == null) {
            root = createNode(element, null);
            afterAdd(root);
            return;
        }

        // 如果添加的不是第一个节点
        /**
         * 进行比较，一直走到待插入节点的父节点位置，并将元素插入 如果发现某个节点与待插入元素相等，则直接覆盖
         */
        Node<E> node = root;
        while (true) {
            int compare_res = compare(element, node.element);
            if (compare_res > 0) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = createNode(element, node);
                    afterAdd(node.right);
                    break;
                }
            } else if (compare_res < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = createNode(element, node);
                    afterAdd(node.left);
                    break;
                }
            } else {
                node.element = element; // 如果相等则覆盖
                size--;  // 减去刚开始的++
                break;
            }
        }

    }

    protected void afterAdd(Node<E> node) {
    }

    protected void afterRemove(Node<E> node, Node<E> replacement) {
    }

    public E remove(E element) {
        return remove(node(element));
    }

    private E remove(Node<E> node) {
        if (node == null)
            return null;
        size--;

        E oldElement = node.element;

        // node是度为2的节点
        if (node.left != null && node.right != null) {
            // 找到后继节点并且用后继节点的元素进行替代
            Node<E> suss = succeser(node);
            node.element = suss.element;

            // 接下来将node指向后继节点，进行删除
            node = suss;
        }

        // 此时node必为度为1或0的节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // 度为1的节点

            if (node.parent == null) { // 有可能该节点没有父节点，即是根节点的情况。
                root = replacement;
                root.parent = null;
                afterRemove(node, replacement); // 删除节点之后的处理
                return oldElement;
            }

            if (node == node.parent.left) {
                node.parent.left = replacement;
                replacement.parent = node.parent;
            } else {
                node.parent.right = replacement;
                replacement.parent = node.parent;
            }
            afterRemove(node, replacement);

        } else {// 度为0的节点:

            if (node.parent == null) { // 有可能该叶子节点没有父节点，即只有一个根节点的情况。
                root = null;
                afterRemove(node, null); // 删除节点之后的处理
                return oldElement;
            }

            // 存在父节点的叶子节点，判断该节点属于父节点哪个分支
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node, null);
        }
        
        return oldElement;
    }

    private Node<E> node(E element) {
        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            }
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return null;
    }

    /**
     * 如果e1大于e2,返回正数 如果e1小于e2,返回负数 如果e1等于e2,返回 0
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    /**
     * 对二叉树进行前序树状打印 7 【L】4 【L】【L】2 【L】【L】【L】1 【L】【L】【R】3 【L】【R】5 【R】9 【R】【L】8
     * 【R】【R】11 【R】【R】【R】12
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");

        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {

        if (node == null)
            return;

        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "【L】");
        toString(node.right, sb, prefix + "【R】");
    }

}