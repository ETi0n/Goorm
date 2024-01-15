package goorm;
import java.util.*;

class LinkedList<T> implements Iterable<T> {
    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // LinkedList가 비어있는지 확인
    public boolean isEmpty(){
        return head == null;
    }

    // LinkedList의 첫번째 노드에 데이터를 삽입
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if(isEmpty()){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // LinkedList의 n번째 노드에 데이터를 삽입
    public void add(int n, T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> parent = head;
        Node<T> current = head;

        if(isEmpty()){
            head = newNode;
            return;
        }

        if(n == 1){
            addFirst(data);
            return;
        }

        for(int i = 2; i < n; i++) {
            parent = current;
            current = current.next;
        }
        newNode.next = current;
        parent.next = newNode;
    }

    // LinkedList의 마지막 노드에 data 추가
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if(isEmpty()){
            head = newNode;
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // LinkedList의 n번째 노드의 data를 return
    public T get(int n) {
        if(isEmpty()) return null;

        Node<T> current = head;
        for (int i = 1; i < n; i++) {
            if (current == null) {
                System.out.println(--i + "번째 노드가 마지막 노드입니다.");
                return null;
            }
            current = current.next;
        }
        return current.data;
    }

    // LinkedList의 n번째 노드의 데이터를 삭제
    public void delete(int n) {
        Node<T> parent = head;
        Node<T> current = head;

        if(isEmpty()) return;

        for (int i = 1; i < n; i++) {
            if (current == null) {
                System.out.println(--i + "번째 노드가 마지막 노드입니다.");
                return;
            }
            parent = current;
            current = current.next;
        }

        if (current == head) {
            head = current.next;
        } else {
            parent.next = current.next;
        }
        current.next = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}

class MyQueue<T>{
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T item){
        list.addLast(item);
    }

    public T dequeue(){
        T data = list.get(1);
        list.delete(1);
        return data;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}

class MyStack<T>{
    private LinkedList<T> list = new LinkedList<>();

    public void push(T item){
        list.addFirst(item);
    }

    public T pop(){
        T data = list.get(1);
        list.delete(1);
        return data;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}

public class MyLinkedList {
    public static void main(String args[]) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.add(1,3);
        linkedList.add(1,4);
        linkedList.addLast(0);
        for (Integer value : linkedList) {
            System.out.print(value);
        }
        System.out.println();
        linkedList.delete(2);
        linkedList.delete(10);
        for (Integer value : linkedList) {
            System.out.print(value);
        }
        System.out.println();


        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        while (!queue.isEmpty()) {
            System.out.println("Dequeue: " + queue.dequeue());
        }

        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }
    }
}