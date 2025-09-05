package dataStructures.linkedList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    // inner / nested class
    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }
    // constructor
    public DoublyLinkedList(int value) {

        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void append(int value) {

        DoublyLinkedList.Node newNode = new DoublyLinkedList.Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // takes current tail's pointer and points at newNode
            newNode.prev = tail; // newNode.prev will point to the current location of tail, which is no longer at the end
            tail = newNode; // tail is updated to newNode
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null; // handles empty DLL case

        Node temp = tail;

        if (length == 1) { // handles case for DLL that *starts* with only 1 node which is then removed
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if(length ==0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        Node temp = head;
        if(length == 0) return null;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node temp = head;
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {

        if (index < 0 || index > length) {
            return false;
        }
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;

        newNode.prev = before;
        newNode.next = after;
        before.next = newNode;
        after.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {

        if (index < 0 || index >= length) return null; //handles out of range cases

        if (index == 0) return removeFirst(); // handles case for removing first node in DLL

        if (index == length - 1) return removeLast(); //handles removing last node in DLL

        Node temp = get(index);

        temp.next.prev = temp.prev; // temp.next points to next node - temp.next.prev = temp.prev points next node to node in front of temp
        temp.prev.next = temp.next; // temp.prev points to node in front of temp - temp.prev.next = temp.next points the node in front of temp to the node after temp
        temp.prev = null; // removes temp node from list
        temp.next = null; // removes temp node from list

        length--;
        return temp;
    }
}
