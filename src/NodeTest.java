
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
