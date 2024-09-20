// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
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
