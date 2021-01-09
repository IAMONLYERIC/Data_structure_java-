package com.bjtu;

import com.bjtu.Map.HashMap;

public class Trie<V> {

    private int size;
    private Node<V> root = new Node<>();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root.getChildren().clear();

    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    public boolean contains(String key) {
        return node(key) != null;
    }

    public V add(String key, V value){
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> nextNode = node.getChildren().get(c);
            if(nextNode == null){
                nextNode = new Node<>();
                node.getChildren().put(c, nextNode);
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
        return null;
    }

    public boolean startsWith(String prefix){
        keyCheck(prefix);
        if(root == null) return false;

        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            node = node.getChildren().get(c);
            if(node == null) return false;
        }
        return true;
    }

    private Node<V> node(String key){
        if(root == null) return null;
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if(node == null) return null;
        }

        return node.word ? node : null;
    }
    

    private void keyCheck(String key){
        if(key == null || key.length() ==0){
            throw new IllegalArgumentException("key must not be empty");
        }
    }



    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;     // 放在word为true的节点中
        boolean word; // 判断是否为单词的结尾

        HashMap<Character, Node<V>> getChildren(){
            return children == null ? (children = new HashMap<>()) : children;
        }
    }

}
 