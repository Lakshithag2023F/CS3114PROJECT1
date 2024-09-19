/**
 * The DoubleLL class is a doubly linked list.
 * A doubly linked list is a data structure where each node contains data (the
 * value),
 * a reference to the previous node, and a reference to the next node, allowing
 * traversal in both directions.
 * 
 * @param <E>
 *            the type of elements in this list
 * @author shrut
 * @version Sep 11, 2024
 */
public class DoubleLL<E> {
    // ~ Fields ................................................................

    private class DLLNode {
        E data;
        DLLNode prev;
        DLLNode next;

        public DLLNode(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private DLLNode head;
    private DLLNode tail;
    private DLLNode current; // For traversal purposes
    private int size;

    // ~ Constructors ..........................................................

    // Empty constructor for DoubleLL
    public DoubleLL() {
        head = null;
        tail = null;
        current = null;
        size = 0;
    }

    // ~ Public Methods ........................................................


    /**
     * Inserts a new element at the end of the list.
     * 
     * @param data
     *            the data to be inserted into the list
     */
    public void insert(E data) {
        DLLNode newNode = new DLLNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }


    /**
     * Resets the current node to the start of the list (head) for traversal.
     */
    public void resetTraversal() {
        current = head;
    }


    /**
     * Moves the current node pointer to the next node and returns its data.
     * 
     * @return the data of the next node, or null if at the end of the list
     */
    public E getNext() {
        if (current == null) {
            return null; // No more elements or traversal not started
        }
        E data = current.data; // Store the current node's data
        current = current.next; // Move to the next node
        return data; // Return the stored data
    }


    /**
     * Checks if the list contains a specific data element.
     * 
     * @param data
     *            the data to check
     * @return true if the list contains the data, false otherwise
     */
    public boolean contains(E data) {
        DLLNode temp = head;
        while (temp != null) {
            if (temp.data.equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    /**
     * Gets the size of the list.
     * 
     * @return the number of elements in the list
     */
    public int getSize() {
        return size;
    }


    /**
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param data
     *            the data to remove
     * @return true if the element was removed, false if it was not found
     */
    public boolean remove(E data) {
        DLLNode temp = head;
        while (temp != null) {
            if (temp.data.equals(data)) {
                if (temp == head && temp == tail) {
                    head = null;
                    tail = null;
                }
                else if (temp == head) {
                    head = head.next;
                    head.prev = null;
                }
                else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }
                else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    /**
     * Gets the data at the head of the list.
     * 
     * @return the data at the head, or null if the list is empty
     */
    public E getHeadData() {
        return head != null ? head.data : null;
    }


    /**
     * Gets the data at the tail of the list.
     * 
     * @return the data at the tail, or null if the list is empty
     */
    public E getTailData() {
        return tail != null ? tail.data : null;
    }
}
