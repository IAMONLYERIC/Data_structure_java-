package com.bjtu.Map;

import java.util.Objects;

@SuppressWarnings({ "unchecked", "unused" })
public class HashMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4; // 数组大小为2的幂次
    private int size = 0;
    private Node<K, V>[] table; // 数组中存放红黑树的根节点

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0)
            return;

        size = 0;
        for (int i = 0; i < table.length; i++) {
            // 将数组中所有存放的红黑树节点清空
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);

        Node<K, V> root = table[index];
        if (root == null) { // 如果index位置还没有红黑树
            root = new Node<>(key, value, null);
            table[index] = root;
            afterPut(root); // 修复红黑树
            size++;
            return null;
        }

        // index位置已经有红黑树节点 -- 已经哈希冲突
        // 添加的不是第一个节点
        // 找到父节点
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        int keyHashCode = key == null ? 0 : key.hashCode();
        do {
            cmp = compare(key, node.key, keyHashCode, node.hashCode);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等覆盖
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null;

    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return key != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        // TODO Auto-generated method stub

    }

    private V remove(Node<K,V>node){
        return null;
    }

    private int index(K key) {
        if (key == null)
            return 0; // null的key都放在0号篮子里面
        int hashCode = key.hashCode();
        // 为了确保万一，先把hashcode再次混合一遍
        // & 的目的和取模目的类似，使得到的索引不会大于数组的长度
        return (hashCode ^ (hashCode >>> 16)) & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        // 为了确保万一，先把hashcode再次混合一遍
        // & 的目的和取模目的类似，使得到的索引不会大于数组的长度
        return (node.hashCode ^ (node.hashCode >>> 16)) & (table.length - 1);
    }

    private int compare(K k1, K k2, int h1, int h2) {
        // 直接比较hashCode的大小
        int res = h1 - h2;
        if (res != 0)
            return res;

        // 如果res为0， 只能证明hashCode一样大，但是不能说明是同一个key，所以不能返回0去覆盖
        // 检查key是否一致
        if (Objects.equals(k1, k2))
            return 0; // 同时拦截了k1 和 k2 都为null的情况

        // hashcode一样，但是key不同
        // 此时再比较类名
        if (k1 != null && k2 != null) {
            String k1ClassName = k1.getClass().getName();
            String k2ClassName = k2.getClass().getName();
            res = k1ClassName.compareTo(k2ClassName);
            if (res != 0)
                return res;

            // 同一个类型
            // 在看看这个类型是否实现了可比较的接口
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // hashcode一样，但是key不同
        // 1.类名相同但是没有实现Comparable接口
        // 2.k1为null, k2 不为 null
        // 3.k1不为null, k2为null
        // 方法：使用内存地址算出的hashcode进行相减
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    private void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null)
            return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent))
            return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null)
            return node;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    // 根据给定的key 寻找存放该key的节点
    private Node<K, V> node(K key) {
        Node<K, V> node = table[index(key)];
        int h1 = key == null ? 0 : key.hashCode();
        while (node != null) {
            int cmp = compare(key, node.key, h1, node.hashCode);
            if (cmp == 0)
                return node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        }
        return null;
    }

    private static class Node<K, V> {
        K key;
        V value;
        int hashCode; // 由于hashCode经常用到，所以再此保存一个
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.hashCode = (key == null ? 0 : key.hashCode());
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }

}