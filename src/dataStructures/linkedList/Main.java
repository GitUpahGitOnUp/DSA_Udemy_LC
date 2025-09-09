package dataStructures.linkedList;

public class Main {
    public static void main(String[] args) {




        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(7);
        myDLL.append(1);

        myDLL.printList();
        myDLL.isPalindrome();
        myDLL.printList();

    }
}