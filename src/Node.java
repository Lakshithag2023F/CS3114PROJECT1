/**
 * This is the node class. It has a node constructor and has getters and setters
 * for both fields.
 * 
 * @author shrut
 * @version Sep 11, 2024
 */
public class Node {

    // ~ Fields ................................................................
    /*
     * node index
     */
    private int index;

    /*
     * the next Node
     */
    private Node next;

    // ~ Constructors ..........................................................
    /**
     * Constructor that initializes node with an index and next node
     * 
     * @param index
     *            the index of the graph
     */
    public Node(int index) {
        this.index = index;
        this.next = null;
    }


    // ~Public Methods ........................................................
    /**
     * getter for index
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }


    /**
     * setter for index
     * 
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * Getter for next node
     * 
     * @return next node
     */
    public Node getNext() {
        return next;
    }


    /**
     * Setter for next node
     * 
     * @param next
     *            node
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
