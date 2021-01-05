package com.bjtu.Set;

import com.bjtu.List;
import com.bjtu.LinkList_P.LinkedList;

public class ListSet<E> implements Set<E> {

    private LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // if (contains(element)) {
        // return;
        // }

        // 如果集合中存在element元素，则替换
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.set(index, element);
        } else {
            // 不存在，则添加
            list.add(element);
        }

    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
        return;
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null)
            return;

        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i)) == true)
                return;
        }
    }

}
