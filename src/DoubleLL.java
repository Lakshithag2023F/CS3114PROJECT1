/**
 * This class implements the methods for the doublly linked list
 * 
 * @param <E>
 *            type E of the entries in the list
 * @author shrut
 * @version Sep 11, 2024
 */
public class DoubleLL<E> {
    // ~ Fields ................................................................
    /*
     * the node that is the head
     */
    private Node head;
    /*
     * the node that is the tail
     */
    private Node tail;
    /*
     * the node that is the curr node
     */
    private Node current;
    /*
     * the size of the list
     */
    private int size;

    // ~ Constructors ..........................................................
    /**
     * Create a new DoubleLL object.
     */
    public DoubleLL() {
        head = null;
        tail = null;
        current = null;
        size = 0;
    }


    // ~ Public Methods ........................................................
    /**
     * Inserts at end of the list
     * 
     * @param index
     *            the index in node
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
     * sets the curr to head
     */
    public void resetCurr() {
        current = head;
    }


    /**
     * get Next node and returns index
     * 
     * @return -1 if curr is null, index if not
     */
    public int getNext() {
        if (current == null) {
            return -1;
        }
        int index = current.getIndex();
        current = current.getNext();
        return index;
    }


    /**
     * Loops through the list to find the data
     * 
     * @param index
     *            the index to check
     * @return true data is there, false if not
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
     * Gets size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * removes the node data at the index pos
     * 
     * @param pos
     *            what to remove
     * @return true if removed and false if not
     */
    public boolean remove(int pos) {
        if (head == null) {
            return false;
        }
        if (head.getIndex() == pos) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }
        Node previous = head;
        @SuppressWarnings("hiding")
        Node current = head.getNext();
        // removes the rest
        while (current != null) {
            if (current.getIndex() == pos) {
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
        return false;
    }


    /**
     * Gets the data/index of the head node
     * 
     * @return -1 is tail is null and index if head is not null
     */
    public int getHeadData() {
        if (head == null) {
            return -1;
        }
        return head.getIndex();
    }


    /**
     * Gets the data/index of the tail node
     * 
     * @return -1 is tail is null and index if tail is not null
     */
    public int getTailData() {
        if (tail == null) {
            return -1;
        }
        return tail.getIndex();
    }
    
    
    /**
     * getter for head 
     * @return head
     */
    public Node getHead()
    {
        return head;
        
    }
    
    /**
     * getter for tail 
     * @return tail
     */
    public Node getTail()
    {
        return tail;
        
    }
    
    /**
     * Setter for head
     * @param head new head
     */
    public void setHead(Node head) {
        this.head = head;
    }
    
    /**
     * Setter for tail
     * @param tail new tail
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * getter for current
     * @return current
     */
    public Node getCurrent() {
        return current;
    }

    /**
     * setter for current
     * @param current new current 
     */
    public void setCurrent(Node current) {
        this.current = current;
    }

    /**
     * setter for size
     * @param size the new size
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    
}
