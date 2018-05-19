package com.jiahaoliuliu.bowlingscorekeeper.collection;

/**
 * Node of a {@link LinkedList}. It has reference to the previous node and next node
 * @param <T>
 *     The data to be set and get
 */
public class Node<T> {
    Node<T> nextNode = null;
    Node<T> previousNode = null;
    T data;

    public Node(T data) {
        this.data = data;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public boolean hasNextNode() {
        return nextNode != null;
    }

    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public boolean hasPreviousNode() {
        return previousNode != null;
    }

    public T getData() {
        return data;
    }
}
