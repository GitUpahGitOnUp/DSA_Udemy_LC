package dataStructures.linkedList;

public class Main {
    public static void main(String[] args) {




        DoublyLinkedList myDLL = new DoublyLinkedList(0);
        myDLL.append(1);
        myDLL.append(2);

        myDLL.printList();
        myDLL.remove(1);
        myDLL.printList();

    }
}