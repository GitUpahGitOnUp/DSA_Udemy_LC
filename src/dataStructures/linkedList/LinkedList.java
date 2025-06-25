package dataStructures.linkedList;

public class LinkedList {
        private Node head;
        private Node tail;
        private int length;

        // inner class, aka nested class
        class Node {
            int value;
            Node next; // of type 'node', and it can point to a node

            // constructor for node creation
            Node(int value) {
                // 'this.value' refers to 'int value' from the var. in the class declaration just under class Node
                // 'value' after the equal sign is the same 'value' as the 'int value' from the argument in Node(int value)
                this.value = value;
            }
        }
        // constructor for LL
        public LinkedList(int value) {
            //  = 'new' keyword runs the constructor for the Node Class and creates node
            // newNode is a var that is of type node which can point to a node
            Node newNode = new Node(value);
            // head is pointing at the same thing as newNode
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
            // create new node, last item of list will point to it, and tail will point to new node
            // if LL is empty, have head and tail point to the new node
            Node newNode = new Node(value);
            if (length == 0) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            length++;
        }

        // remove last item from LL, including an empty list
        // remove last node that points to null, point tail to previous node, and now it points to null
        // return removed node
        public Node removeLast() {
            // return type node
            if (length == 0) {
                return null;
            }

            Node temp = head;
            Node pre = head;
            // start at head and iterate through pointers to arrive at penultimate pointer
            while (temp.next != null) {
                pre = temp;
                temp = temp.next;
            }
            tail = pre;
            tail.next = null;
            length--;
            if (length == 0) {
                head = null;
                tail = null;
            }
            return temp;
        }

        public void prepend(int value) {
            Node newNode = new Node(value);
            // for empty list
            if (length == 0) {
                head = newNode;
                tail = newNode;
            }
            else {
                newNode.next = head;
                head = newNode;
            }
            length++;
        }

        public Node removeFirst() {
            if (length == 0) {
                return null;
            }
            Node temp = head;
            head = head.next;
            temp.next = null;
            length--;
            if (length == 0) {
                tail = null;
            }
            // temp is the pointer of the removed node
            return temp;
        }

        public Node get(int index) {
            // if logic ensures that the index is in range
            if (index < 0 || index >= length) {
                return null;
            }
            Node temp = head;

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public boolean set(int index, int value){
            // temp var used to point at node that gets a value change
            Node temp = get(index);
            // if node is not null, the current value will be changed to the value input in args
            // if the node is empty, it will skip the if condition and return false
            if (temp != null) {
                temp.value = value;
                return true;
            }
            return false;
        }

        public boolean insert(int index, int value){
            // if index is out of range
            if (index < 0 || index > length) {
                return false;
            }
            // if insertion is at index 0
            if (index == 0){
                prepend(value);
                return true;
            }
            // if index is the end of ll
            if (index == length) {
                append(value);
                return true;
            }
            // if index is 'inside' list
            Node newNode = new Node(value); // creates newNode for insertion
            Node temp = get(index - 1); // temp var that is set to the index of the node in front of the desired index (if inserting @ index 3, temp is @ index 2)
            newNode.next = temp.next; // the newNode's pointer will now point at the desired index (temp is 1 behind desired index)
            temp.next = newNode; // temp.next (1 behind desired index) is pointing to the newNode
            length ++;
            return true;
        }

        public Node remove(int index) {
            if (index < 0 || index >= length) return null;
            if (index == 0)  return removeFirst(); // return type node, remove also has type node, so it works for this return type
            if (index == length - 1)  return removeLast();

            // var points at previous node, so it's pointer can point at the correct node after removal of desired node
            Node prev = get(index - 1);
            // var points at node to be removed
            Node temp = prev.next;

            prev.next = temp.next;

            temp.next  = null; // removes from ll
            length--;
            return temp;
        }

        public void reverse() {
            Node temp = head;
            head = tail;
            tail = temp;

            Node after = temp.next;
            Node before = null;

            for (int i = 0; i < length; i++ ) {
                // at the first iteration, before = null, temp = head, and after is the node to the right of temp
                //                            null -> head -> after
                after = temp.next; // sets after to temp's next node
                temp.next = before; // flips temp's pointer from pointing at after to pointing at before
                before = temp; // before is now pointing at the temp node
                temp = after; //temp's pointer is now pointing at after
            }
        }
        // method to find the middle node when not able to use a length attribute and not able to count length
        public Node findMiddleNode(){
            Node fast = head; // both fast and slow point to head of the list
            Node slow = head;

            while(fast != null && fast.next != null){
                slow = slow.next; // slow is incremented by 1 by using .next to point to the neighboring node
                fast = fast.next.next; // fast is incremented by 2 by using .next.next

            }
            return slow; //once fast and fast+1 are both pointing to null, the while loop is exited and the slow node is returned
        }
}

