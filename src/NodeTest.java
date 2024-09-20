
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
/*
 * imports for Junit testing
 */
import student.TestCase;

/**
 * Test class to test Node.java
 * 
 * @author laksh
 * @version Sep 12, 2024
 */
public class NodeTest extends TestCase {
    // ~ Fields ................................................................
    /*
     * initializes Node node for testing
     */
    private Node node;

    // ~Public Methods ........................................................
    /**
     * sets up the node
     */
    public void setUp() {
        node = new Node(1);
    }


    /**
     * tests getIndex
     */
    public void testGetIndex() {
        assertEquals(node.getIndex(), 1);
    }


    /**
     * tests set index
     */
    public void testSetIndex() {
        node.setIndex(5);
        assertEquals(node.getIndex(), 5);
    }
}
