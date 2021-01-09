package com.bjtu.Set;

import java.util.Comparator;

import com.bjtu.Tree.BinaryTree;
import com.bjtu.Tree.RBTree;

public class TreeSet<E> implements Set<E> {

    private RBTree<E> rbtree;


    public TreeSet(){
        this(null);
    }

    public TreeSet(Comparator<E> comparator){
        rbtree = new RBTree<>(comparator);
    }

    @Override
    public int size() {
        return rbtree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbtree.isEmpty();
    }

    @Override
    public void clear() {
        rbtree.clear();
    }

    @Override
    public boolean contains(E element) {
        return rbtree.contains(element);
    }

    @Override
    public void add(E element) {
        rbtree.add(element);
    }

    @Override
    public void remove(E element) {
        rbtree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        rbtree.inOrderTraverSal(new BinaryTree.Visitor<E>(){

			@Override
			public boolean visit(E element) {
                visitor.visit(element);
                return false;

			}
            
        });
    }
    
}
