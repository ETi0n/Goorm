package goorm;
import java.util.*;

class LinkedList<T>{
    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    // LinkedList의 마지막 노드에 data 추가
    public void add(T data){
        Node<T> newNode = new Node<>(data);

        if(head == null){
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("노드가 생성되었습니다.");
    }

    // LinkedList의 i번째 노드의 data를 return
    public T get(int n){
        if(head == null){
            System.out.println("생성된 LinkedList가 없습니다.");
            return null;
        } else {
            Node<T> current = head;
            for(int i = 1; i < n; i++){
                if(current.next == null){
                    System.out.println(i + "번째 노드가 마지막 노드입니다.");
                    return null;
                }
                current = current.next;
            }
            System.out.println(n + "번째 노드에 저장된 데이터는 " + current.data + "입니다.");
            return current.data;
        }
    }

    // LinkedList의 i번째 노드의 데이터를 삭제
    public void delete(int n){
        if(head == null){
            System.out.println("생성된 LinkedList가 없습니다.");
        } else {
            Node<T> parent = head;
            Node<T> current = head;
            for(int i = 1; i < n; i++){
                if(current.next == null){
                    System.out.println(i + "번째 노드가 마지막 노드입니다.");
                }
                parent = current;
                current = current.next;
            }

            if(current == null){
                System.out.println("해당하는 노드가 존재하지 않습니다.");
            } else{
                if(parent == head){
                    head = current.next;
                } else{
                    parent.next = current.next;
                }
                current.next = null;
                System.out.println("해당 노드가 삭제되었습니다.");
            }
        }
    }
}


public class MyLinkedList{
    public static void main(String args[]){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        linkedList.get(3);
        linkedList.delete(3);
        linkedList.get(3);
    }
}
