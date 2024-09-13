/**
 * // -------------------------------------------------------------------------
 * /**
 * Record class stores a key and the associated node.
 * 
 * @author shrut
 * @version Sep 11, 2024
 */
public class Record {
    // ~ Fields ................................................................
    private String key; // artist name or track
    private Node node; // associated node

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Constructor initialized record with key and node
     * 
     * @param key
     * @param node
     */
    public Record(String key, Node node) {
        this.key = key;
        this.node = node;
    }


    // Getter methods
    /**
     * getter for key
     * 
     * @return the key
     */
    public String getKey() {
        return key;
    }


    /**
     * getter for node
     * 
     * @return the node
     */
    public Node getNode() {
        return node;
    }


    // setter methods
    /**
     * setter for key
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * setter for node
     * 
     * @param node
     */
    public void setNode(Node node) {
        this.node = node;
    }
    
    public String toString() {
        return "Record[key=" + key + ", node=" + node + "]";
    }

}
