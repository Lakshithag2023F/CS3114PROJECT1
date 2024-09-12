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

    // ----------------------------------------------------------

    // ~ Fields ................................................................
    private int index;
    // ~ Constructors ..........................................................

    /**
     * Constructor that initializes node with an index
     * 
     * @param index
     */
    public Node(int index) {
        this.index = index;
    }

    // ~Public Methods ........................................................


    // getter method
    /**
     * getter for index
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }


    // setter method
    /**
     * setter for index
     * 
     * @param index
     */
    public void setIndex(int index) {

        this.index = index;
    }

}

// you might have to add something to manage the edges/connections between nodes
// in graph
//
