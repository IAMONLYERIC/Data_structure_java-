package com.bjtu;

import com.bjtu.Map.HashMap;

public class Trie<V> {

    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;

    }

    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value){
        keyCheck(key);

        if(root == null){
            // 用到根节点才去创建
            root = new Node<>(null);
        }

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;
            Node<V> nextNode = emptyChildren ? null : node.children.get(c);
            if(nextNode == null){
                nextNode = new Node<>(node);
                nextNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, nextNode);
            }
            node = nextNode;
        }

        if(!node.word){
            node.word = true;
            node.value = value;
            size++;
            return null;
        }

        // node.word为true，本来就是一个单词
        V oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    public V remove(String key){
        // 找到最后一个节点
        Node<V> node = node(key);

        // 如果不是单词结尾，不用做任何处理
        if(node == null || !node.word) return null;

        size--;
        V oldValue = node.value;

        // 要删除的单词是否是其他单词的前缀
        if(node.children != null && !node.children.isEmpty()){
            
            node.word = false;
            node.value = null;
            return oldValue;
        }

        // 没有子节点
        node.parent.children.remove(node.character);
        Node<V> parent = null;
        while((parent = node.parent) != null){
            parent.children.remove(node.character);
            if(parent.word || !(parent.children.isEmpty())) break;
            node = parent;
        }

        return oldValue;
    }

    public boolean startsWith(String prefix){
        return node(prefix) != null;
    }

    private Node<V> node(String key){
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if(node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i);
            node = node.children.get(c);
        }

        return node;
    }
    

    private void keyCheck(String key){
        if(key == null || key.length() ==0){
            throw new IllegalArgumentException("key must not be empty");
        }
    }



    private static class Node<V> {
        Character character;
        Node<V> parent;
        HashMap<Character, Node<V>> children;
        V value;     // 放在word为true的节点中
        boolean word; // 判断是否为单词的结尾

        public Node(Node<V> parent){
            this.parent = parent;
        }
    }

}
 