public class LinkedList {
        private Node head;
        private Node tail;
        private int length;

        // inner class, aka nested class
        class Node {
            int value;
            Node next;

            // constructor for node creation
            Node(int value) {
                // 'this.value' refers to 'int value' from the class declaration just under class Node
                // 'value' after the equal sign is the same 'value' as the 'int value' from the argument in Node(int value)
                this.value = value;
            }
        }

        public LinkedList(int value) {
            //  = 'new' keyword runs the constructor for the Node Class and creates node
            // newNode is a var that is of type node which can point to a node
            Node newNode = new Node(value);
            // head is pointing at the same thing as newNode
            head = newNode;
            tail = newNode;
            length = 1;
        }
}

