package dataStructures.linkedList;

public class Main {
    public static void main(String[] args) {




        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(4);
        myDLL.append(5);

        myDLL.printList();
        myDLL.reverse();
        myDLL.printList();

    }
}