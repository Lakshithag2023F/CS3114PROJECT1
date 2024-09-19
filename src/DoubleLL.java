
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

    
    public E getNext(DLLNode node) 
    {
        if (node == null || node.next == null) {
            return null;  // No next element
        }
        return node.next.data;
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


    public int getSize() {
        return size;
    }


    public boolean remove(E data) {

        DLLNode current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                }
                else if (current == head) {
                    head = head.next;
//                    if (head != null) {
                      head.prev = null;
//                    }
                }
                else if (current == tail) {
                    tail = tail.prev;
//                    if (tail != null) {
                       tail.next = null;
//                    }
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }
    
    public E getHeadData() {
        return head != null ? head.data : null;
    }

    public E getTailData() {
        return tail != null ? tail.data : null;
    }



}
