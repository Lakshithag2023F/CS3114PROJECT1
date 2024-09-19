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

    private Node head;
    private Node tail;
    private Node current; // For traversal purposes
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
    public void insert(int index) {
        Node newNode = new Node(index);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.setNext(newNode);
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
    public int getNext() {
        if (current == null) {
            return -1;
        }
        int data = current.getIndex(); // Store the current node's data
        current = current.getNext(); // Move to the next node
        return data; // Return the stored data
    }


    /**
     * Checks if the list contains a specific data element.
     * 
     * @param data
     *            the data to check
     * @return true if the list contains the data, false otherwise
     */
    public boolean contains(int index) {
        Node temp = head;
        while (temp != null) {
            if (temp.getIndex() == index) {
                return true;
            }
            temp = temp.getNext();
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
    public boolean remove(int index) {
        if (head == null) {
            return false;
        }
        if (head.getIndex() == index) {
            head = head.getNext();
            if (head == null) { // If the list becomes empty
                tail = null;
            }
            size--;
            return true;
        }
        Node previous = head;
        Node current = head.getNext();
        while (current != null) {
            if (current.getIndex() == index) {
                previous.setNext(current.getNext());
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false; // Node not found
    }


    /**
     * Gets the data at the head of the list.
     * 
     * @return the data at the head, or null if the list is empty
     */
    public int getHeadData() {
        return head != null ? head.getIndex() : -1;
    }


    /**
     * Gets the data at the tail of the list.
     * 
     * @return the data at the tail, or null if the list is empty
     */
    public int getTailData() {
        return tail != null ? tail.getIndex() : -1;
    }
}
