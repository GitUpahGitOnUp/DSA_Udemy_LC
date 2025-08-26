package dataStructures.linkedList;

public class LinkedList {
        private Node head;
        private Node tail;
        private int length;

        // inner class, aka nested class
        class Node {
            int value;
            Node next; // of type 'node', it can point to a node

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
            // creates a new node, the last item of the list will point to it, and the tail will point to new node
            // if the LL is empty, have head and tail point to the new node
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

        // removes last item from LL, and includes handling an empty list
        // removes last node that points to null, points tail to previous node, and now it points to null
        // returns removed node
        public Node removeLast() {
            // return type node
            if (length == 0) {
                return null;
            }

            Node temp = head;
            Node pre = head;
            // start at the head and iterate through pointers to arrive at the penultimate pointer
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
            // if index is the end of LL
            if (index == length) {
                append(value);
                return true;
            }
            // if index is 'inside' list
            Node newNode = new Node(value); // creates newNode for insertion
            Node temp = get(index - 1); // temp var that is set to the index of the node in front of the desired index (if inserting @ index 3, temp is pointing to index 2)
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

            temp.next  = null; // removes from LL
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

        public boolean hasLoop() {
            Node fast = head;
            Node slow = head;

            while (fast != null && fast.next!= null) {
                slow = slow.next;
                fast = fast.next.next;
                if(fast == slow){
                    return true;
                }
            }
            return false;
        }


    public Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // method to remove duplicates in a LL using nested loops (using a set would be more efficient)
    // LL for this problem does not have a tail

    public void removeDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                    length -= 1;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }
    // method that converts binary to decimal w/o the use of length or counting
    public int binaryToDecimal() {
            Node current = head;
            int total = 0;

            while(current != null) { // iterates through LL until reaching the end
                total = (total * 2) + current.value; // double the value of total to reflect its place value, then add 0 or 1 based on value in the node
                current = current.next; // points current to the next node in LL
            }
            return total;
    }

    // method to partition nodes in a LL based on their relative value to be input by user
    public void partitionList(int x) {
            Node dum1 = new Node(0); // 2 new dummy nodes are given a value of 0 and will be used to 'sort'
            Node dum2 = new Node(0); // ea. partition based on input value x
            Node prev1 = dum1; // two pointer nodes previous 1&2 will point to their respective dummy nodes
            Node prev2 = dum2;
            Node current = head; // current will point to nodes as we traverse the LL

            if(head == null) return; // handles empty LL case

            while (current != null) {
                if (current.value < x) {
                    prev1.next = current; // prev1.next will append cur. node's val to list built by prev1
                    prev1 = current;
                }
                else if (current.value >= x) {
                    prev2.next = current; // prev2.next will append cur. node's val to list built by prev2
                    prev2 = current;
                }
                current = current.next;
            }
            prev2.next = null; // sets next pointer of the last node to null
            prev1.next = dum2.next; // sets p1 pointer to the node after d2, re-linking the OG LL
            head = dum1.next; // sets head to d1's pointer, reestablishing the OG LL node as head

    }

    // method that reverses the nodes between two given values in a LL
    public void reverseBetween(int m, int n) { // ints m and n will be used to determine range and pseudo indices of LL
            Node dum = new Node(0); // create dummy node so prev.next can point to the 0th 'indexed' node
            dum.next = head; // dummy node is pointing to head
            Node prev = dum; // previous node begins pointing to the dummy node & will iterate through LL and
                             //  track the node right before the segment to be reversed


            int startIndex = m;
            int endIndex = n;

            if(head == null) return; // handles empty LL case

            for (int i = 0; i < startIndex; i++) { // use loop to move prev forward startIndex - 1 # of steps
                prev = prev.next;
            }
            Node cur = prev.next; // current node points to the node after previous & will be the 1st node reversed

                for (int i = 0; i < (endIndex - startIndex); i++ ) { // determines the number of times nodes will be reversed
                    Node toMove = cur.next; // node that must be moved is pointed to by current node
                    cur.next = toMove.next; // current's pointer now points to node *after* toMove
                    toMove.next = prev.next; // toMove's pointer is now pointing to previous's next pointer
                    prev.next = toMove; // previous pointer is now pointing at the reversed node toMove
                }
                head = dum.next; // the head node was moved in the reversal process and must be reassigned to just after dummy node


    }

    public void swapPairs() {

            if(head == null) return; // handles empty LL exception

            Node dum = new Node(0); // create dummy node and set to head
            dum.next = head;
            Node prev = dum; // prev pointer initially starts at dummy node and will eventually progress through LL
            Node first = head; // first pointer begins pointing at head

            while (first != null && first.next != null) { // condit. handles even + odd num of nodes
                Node second = first.next; // points to node after first
                // steps to perform swap
                prev.next = second;
                first.next = second.next;
                second.next = first;
                // moves pointers
                prev = first;
                first = first.next;
            }
            head = dum.next;
    }
}

