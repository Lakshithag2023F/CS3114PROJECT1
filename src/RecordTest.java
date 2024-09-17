import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class to test Record.java
 * 
 * @author laksh
 * @version Sep 12, 2024
 */
public class RecordTest extends TestCase {
    //~ Fields ................................................................
    private Record record;
    private Node node;
    private Node node2;

    //~ Constructors ..........................................................
    
    /**
     * Sets up the test case by initializing the objects.
     */
    public void setUp() {
        node = new Node(1); // Initialize node with a valid index
        node2 = new Node(2); // Initialize a second node for testing

        record = new Record("six", node, "artist"); // Create a record with a key, node, and type
    }

    //~Public Methods ........................................................
    
    // ----------------------------------------------------------
    /**
     * Tests the getKey method.
     */
    public void testGetKey() {
        assertEquals("six", record.getKey());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the getNode method.
     */
    public void testGetNode() {
        assertEquals(node, record.getNode());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the setKey method.
     */
    public void testSetKey() {
        record.setKey("five");
        assertEquals("five", record.getKey());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the setNode method. 
     */
    public void testSetNode() {
        record.setNode(node2);
        assertEquals(node2, record.getNode());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the getType method.
     */
    public void testGetType() {
        assertEquals("artist", record.getType());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the setType method.
     */
    public void testSetType() {
        record.setType("song");
        assertEquals("song", record.getType());
    }
}
