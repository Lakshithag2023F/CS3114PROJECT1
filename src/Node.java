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
     *            is what node is set to
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
