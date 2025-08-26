package dataStructures.linkedList;

public class Main {
    public static void main(String[] args) {
        // myLinkedList is the var. of type dataStructures.linkedList.LinkedList
        // = new(keyword) runs the constructor
        // this creates a LL with head and tail both pointing at the value 4, and 4 pointing to 'null', length of 1
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.append(5);
        //myLinkedList.append(2);
        //myLinkedList.append(5);



        //System.out.println(myLinkedList.get(2).value + "\n");
        myLinkedList.printList();
        myLinkedList.swapPairs();
        System.out.println("----");
        myLinkedList.printList();


    }
}