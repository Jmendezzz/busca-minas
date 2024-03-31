package com.example.datastructures;

public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
      head = null;
      size = 0;
    }

    public void add(T data) {
      Node<T> newNode = new Node<>(data);
      if (head == null) {
        head = newNode;
      } else {
        Node<T> current = head;
        while (current.getNext() != null) {
          current = current.getNext();
        }
        current.setNext(newNode);
      }
      size++;
    }

    public void add(int index, T data) {
      if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("Index out");
      }
      Node<T> newNode = new Node<>(data);
      if (index == 0) {
        newNode.setNext(head);
        head = newNode;
      } else {
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
          current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
      }
      size++;
    }

    public T get(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
      }
      Node<T> current = head;
      for (int i = 0; i < index; i++) {
        current = current.getNext();
      }
      return current.getData();
    }
    public void set(int index, T data) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
      }
      Node<T> current = head;
      for (int i = 0; i < index; i++) {
        current = current.getNext();
      }
      current.setData(data);
    }

    public void remove(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
      }
      if (index == 0) {
        head = head.getNext();
      } else {
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
          current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
      }
      size--;
    }

    public void clear() {
      head = null;
      size = 0;
    }

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return size == 0;
    }

}
