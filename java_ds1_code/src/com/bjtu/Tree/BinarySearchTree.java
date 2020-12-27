package com.bjtu.Tree;

import java.util.Comparator;
import java.util.LinkedList;

import com.bjtu.Tree.Printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    private Node<E> root;

    /**
     * 比较大小的策略： 1. 如果用户自定义了比较器comparator,则在compare函数中优先使用该比较器 2.
     * 如果没有自定义比较器，则要求传入的类型必须实现了comparable接口，则强制转换该对象为 comparable接口实例，然后进行比较。 3.
     * 如果上述两项都没满足，则报错。
     */
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this.comparator = null;
    }

    private class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    // 创建该公共接口，供遍历时访问节点使用
    public static interface Visitor<E> {
        void visit(E element);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        elementNotNullCheck(element); // 不允许存放空节点
        size++;

        // 添加的是第一个节点
        if (root == null) {
            root = new Node<E>(element, null);
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
                    node.right = new Node<E>(element, node);
                    break;
                }
            } else if (compare_res < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new Node<E>(element, node);
                    break;
                }
            } else {
                node.element = element; // 如果相等则覆盖
                break;
            }
        }

    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public E remove(E element) {
        return remove(node(element));
    }

    private E remove(Node<E> node) {
        if (node == null)
            return null;
        size --;

        E  oldElement = node.element;

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
        if(replacement != null){ // 度为1的节点

            if(node.parent == null){ // 有可能该节点没有父节点，即是根节点的情况。
                root = replacement;
                root.parent = null;
                return oldElement;
            }

            if(node == node.parent.left) {
                node.parent.left = replacement;
                replacement.parent = node.parent;
            }else{
                node.parent.right = replacement;
                replacement.parent = node.parent;
            }

        }else {// 度为0的节点: 

            if(node.parent == null){ // 有可能该叶子节点没有父节点，即只有一个根节点的情况。
                root = null;
                return oldElement;
            }

            // 存在父节点的叶子节点，判断该节点属于父节点哪个分支
            if(node == node.parent.left) {
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }

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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
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

    public void preOrderTraverSal(Visitor<E> visitor) {
        preOrderTraverSal(root, visitor);
    }

    private void preOrderTraverSal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null)
            return;

        visitor.visit(node.element);
        preOrderTraverSal(node.left, visitor);
        preOrderTraverSal(node.right, visitor);
    }

    public void inOrderTraverSal(Visitor<E> visitor) {
        inOrderTraverSal(root, visitor);
    }

    private void inOrderTraverSal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null)
            return;

        inOrderTraverSal(node.left, visitor);
        visitor.visit(node.element);
        inOrderTraverSal(node.right, visitor);
    }

    public void postOrderTraverSal(Visitor<E> visitor) {
        postOrderTraverSal(root, visitor);
    }

    private void postOrderTraverSal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null)
            return;

        postOrderTraverSal(node.left, visitor);
        postOrderTraverSal(node.right, visitor);
        visitor.visit(node.element);
    }

    public void levelOrderTraverSal(Visitor<E> visitor) {
        levelOrderTraverSal(root, visitor);
    }

    private void levelOrderTraverSal(Node<E> node, Visitor<E> visitor) {
        /**
         * 1. 将根节点入队 2. 只要队列不为空，先访问栈顶元素 3. 依次将左右节点入队（只要不为空）
         */

        if (node == null)
            return;
        // 此处做为队列使用，因为java中的链表实现了队列的接口
        LinkedList<Node<E>> queue = new LinkedList<>();

        queue.offer(node);

        Node<E> topNode;
        while (!queue.isEmpty()) {
            topNode = queue.poll();
            visitor.visit(topNode.element);

            if (topNode.left != null) {
                queue.offer(topNode.left);
            }
            if (topNode.right != null) {
                queue.offer(topNode.right);
            }

        }

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

    // 获取二叉树的高度
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {

        // 方法 1 递归
        // if(node.left == null && node.right == null ) return 1;
        // if(node.left != null && node.right != null) return
        // Math.max(height(node.left), height(node.right)) + 1;
        // if(node.left != null) return height(node.left) + 1;
        // if(node.right != null) return height(node.right) + 1;
        // return 0;

        // 方法 2 递归
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;

    }

    // 方法 3 迭代方式求树的高度
    public int height_loop() {

        if (root == null)
            return 0;
        // 此处做为队列使用，因为java中的链表实现了队列的接口
        LinkedList<Node<E>> queue = new LinkedList<>();

        queue.offer(root);
        int treeHeight = 0;
        int levelSize = 1;

        Node<E> topNode;
        while (!queue.isEmpty()) {
            topNode = queue.poll();
            levelSize--;

            if (topNode.left != null) {
                queue.offer(topNode.left);
            }
            if (topNode.right != null) {
                queue.offer(topNode.right);
            }

            if (levelSize == 0) {
                treeHeight++;
                levelSize = queue.size(); // 当本层的所有节点都pop时，此时队列中的元素个数即为下一层的节点数量
            }

        }

        return treeHeight;

    }

    /**
     * 判断是否为完全二叉树
     */
    public boolean isCompleteBT() {
        if (root == null)
            return false;

        // 此处做为队列使用，因为java中的链表实现了队列的接口
        LinkedList<Node<E>> queue = new LinkedList<>();

        queue.offer(root);
        Node<E> topNode;

        boolean endFlag = false; // 用来指示 后面的节点时候必须为叶子节点的标记
        while (!queue.isEmpty()) {
            topNode = queue.poll();

            // 如果已经开启了叶子节点标记，则要求节点不能有孩子节点，否则不是完全二叉树
            if (endFlag) {
                if (topNode.left != null || topNode.right != null)
                    return false;
            }

            // 度为2的节点，没问题，直接入队
            if (topNode.left != null && topNode.right != null) {
                queue.offer(topNode.left);
                queue.offer(topNode.right);
            }

            // 有右节点没有左节点，不满足完全二叉树 ，false
            if (topNode.left == null && topNode.right != null)
                return false;

            // 有左节点没有右节点，说明后面的节点必须都是叶子节点才能使完全二叉树，所以在此开启标记位
            if (topNode.left != null && topNode.right == null) {
                queue.offer(topNode.left);
                endFlag = true;
            }

            // 没有孩子节点，说明后面的节点必须都是叶子节点才能使完全二叉树，所以在此开启标记位
            if (topNode.left == null && topNode.right == null) {
                endFlag = true;
            }

        }

        // 上述条件满足，为完全二叉树
        return true;
    }

    public void invertBT() {
        // invertBTNode(root);
        // invertBTNode_inOrder(root);
        invertBTNode_levelOrder(root);
    }

    private Node<E> invertBTNode(Node<E> node) {
        if (node == null)
            return null;
        // if(node.left == null && node.right == null) return node;

        Node<E> tmpNode = node.left;
        node.left = node.right;
        node.right = tmpNode;

        invertBTNode(node.left);
        invertBTNode(node.right);

        return node;
    }

    private Node<E> invertBTNode_inOrder(Node<E> node) {
        if (node == null)
            return null;
        // if(node.left == null && node.right == null) return node;

        invertBTNode(node.left);

        Node<E> tmpNode = node.left;
        node.left = node.right;
        node.right = tmpNode;

        invertBTNode(node.left); // 此时原来的右子树，实际成为交换后的左子树

        return node;
    }

    private Node<E> invertBTNode_levelOrder(Node<E> node) {
        if (node == null)
            return null;
        // if(node.left == null && node.right == null) return node;

        // 此处做为队列使用，因为java中的链表实现了队列的接口
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        Node<E> topNode;
        while (!queue.isEmpty()) {
            topNode = queue.poll();

            Node<E> tmpNode = topNode.left;
            topNode.left = topNode.right;
            topNode.right = tmpNode;

            if (topNode.left != null) {
                queue.offer(topNode.left);
            }
            if (topNode.right != null) {
                queue.offer(topNode.right);
            }

        }

        return node;
    }

    // 寻找中序遍历下的前一个节点： 前驱结点
    private Node<E> predesessor(Node<E> node) {
        if (node == null)
            return null;

        // 存在左孩子： 顺着左孩子的右节点一路找下去，左孩子的最后一个节点必然就是自己的前驱节点
        if (node.left != null) {
            node = node.left;
            while (node.right != null)
                node = node.right;
            return node;
        }

        // 来到此处，肯定不存在左孩子，则去找父节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // 父节点为空，或者找到了一个节点为该父节点的右孩子
        return node.parent;

        // // 不存在左孩子，但是存在父节点：
        // if (node.left == null && node.parent != null) {

        // // 顺着父节点向前找，一直找到属于右孩子分支的父节点
        // while(node.parent != null){
        // if(node == node.parent.right) return node.parent;
        // node = node.parent;
        // }

        // // 如果没找到，则没有前驱，比如第一个节点
        // return null;
        // }

        // // 即不存在左孩子，也不存在父节点，没有前驱
        // if (node.left == null && node.parent == null) {
        // return null;
        // }

        // return null;
    }

    // 寻找中序遍历下的前一个节点： 前驱结点
    private Node<E> succeser(Node<E> node) {
        if (node == null)
            return null;

        // 存在右孩子： 顺着右孩子的左节点一路找下去，右孩子的第一个节点必然就是自己的后继节点
        if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }

        // 来到此处，肯定不存在右孩子，则去找父节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        // 父节点为空，或者找到了一个节点为该父节点的左孩子
        return node.parent;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {

        Node<E> outNode = (Node<E>) node;
        String parentString = "null";
        if (outNode.parent != null) {
            parentString = outNode.parent.element.toString();
        }

        return outNode.element + "_p(" + parentString + ")";
    }

}