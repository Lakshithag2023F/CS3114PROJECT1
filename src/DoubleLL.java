
/**
 * // -------------------------------------------------------------------------
 * /**
 * the doublell class is a doubly linked list
 * doubly linked list is data structure where each node contains data (the
 * value), previous, and next, allowing traversal in both directions
 * 
 * @author shrut
 * @version Sep 11, 2024
 */
public class DoubleLL<E> {
    // ~ Fields ................................................................

    private int size;

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

    // ~ Constructors ..........................................................

    // empty DoubleLL constructor
    public DoubleLL() {
        head = null;
        tail = null;
    }


    // ~Public Methods ........................................................
    /**
     * Inserts a new element at the end of the list
     * 
     * @param data
     *            the data to be inserted into list
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
     * Checks if list contains data
     * 
     * @param data
     * @return true or false
     */
    public boolean contains(E data) {
        DLLNode current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;

    }

}
