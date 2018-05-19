package com.jiahaoliuliu.bowlingscorekeeper.collection;

public class LinkedList<T extends Node> {
    private T head;
    private int listCount;

    public LinkedList() {
        head = null;
        listCount = 0;
    }

    public boolean add(T newNode) {
        // Precondition
        if (newNode == null) {
            return false;
        }

        // If this is not the first node
        if (head != null) {
            head.setNextNode(newNode);
            newNode.setPreviousNode(head);
        }

        // Advance the reference
        head = newNode;
        listCount++;
        return true;
    }

    public int size() {
        return listCount;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public T getHead() {
        return head;
    }
}
