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
     // private int size;
   
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
    
    // empty DoubleLL
    public DoubleLL() {
        head = null;
        tail = null;
    }


    // ~Public Methods ........................................................
    private void init() {

        size = 0;
    }


    /**
     * Sets the node before this one
     *
     * @param n
     *            the node before this one
     */
    public void setPrevious(Node<E> n) {
        previous = n;
    }

}
