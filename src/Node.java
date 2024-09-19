/**
 * // -------------------------------------------------------------------------
 * /**
 * Node class represents artist or track in the graph, it stores index to
 * identify
 * 
 * 
 * @author shrut
 * @version Sep 11, 2024
 */
public class Node {

    // ~ Fields ................................................................
    /*
     * 
     */
    private int index;
    
    private Node next;
    
    // ~ Constructors ..........................................................
    /**
     * Constructor that initializes node with an index
     * 
     * @param index
     */
    public Node(int index) 
    {
        this.index = index; 
        this.next = null;
    }

    // ~Public Methods ........................................................
    /**
     * getter for index
     * 
     * @return index
     */
    public int getIndex() 
    {
        return index;            
    }


    /**
     * setter for index
     * 
     * @param index
     */
    public void setIndex(int index) 
    {
        this.index = index;                
    }
    
    
    /**
     * Getter for next node
     * 
     * @return next node
     */
    public Node getNext() 
    {
        return next;
    }

    /**
     * Setter for next node
     * 
     * @param next The next node in the list
     */
    public void setNext(Node next) 
    {
        this.next = next;
    }
}