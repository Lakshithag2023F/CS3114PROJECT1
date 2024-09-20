// -------------------------------------------------------------------------
/**
 * This is the record class. It takes in a node and has the name of the
 * artist/song and if the record is a artist or song.
 * 
 * @author laksh
 * @version Sep 19, 2024
 */
public class Record {
    /*
     * key for the name of the song/artist
     */
    private String key;
    /*
     * node that is inputted with the index
     */
    private Node node;
    /*
     * to differentiate song or artist records
     */
    private String type;

    /**
     * Create a new Record object.
     * 
     * @param key
     *            name of artist or song
     * @param node
     *            contains index of the node
     * @param type
     *            differentiates song record and artist record
     */
    public Record(String key, Node node, String type) {
        this.key = key;
        this.node = node;
        this.type = type;
    }


    /**
     * gets the string key
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }


    /**
     * gets the node Node
     * 
     * @return node
     */
    public Node getNode() {
        return node;
    }


    /**
     * gets the String type
     * 
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * sets the key to the name
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * sets the node to the node
     * 
     * @param node
     */
    public void setNode(Node node) {
        this.node = node;
    }


    /**
     * sets the type to the string type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * sets all fields to null when record is a tombstone
     */
    public void setTombstone() {
        this.setKey(null);
        this.setNode(null);
        this.setType(null);

    }
}
