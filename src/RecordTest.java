
/*
 * imports for Junit testing
 */
import student.TestCase;

/**
 * class to test Record.java
 * 
 * @author laksh
 * @version Sep 12, 2024
 */
public class RecordTest extends TestCase {
    // ~ Fields ................................................................
    /*
     * initilizes Record record
     */
    private Record record;
    /*
     * initializes Node node
     */
    private Node node;
    /*
     * initializes another node node2
     */
    private Node node2;

    // ~Public Methods ........................................................
    /**
     * Sets up the testing methods
     */
    public void setUp() {
        node = new Node(1);
        node2 = new Node(2);
        record = new Record("kalsdjfla", node, "artist");
    }


    /**
     * Tests getKey
     */
    public void testGetKey() {
        assertEquals("kalsdjfla", record.getKey());
    }


    /**
     * Tests getNode
     */
    public void testGetNode() {
        assertEquals(node, record.getNode());
    }


    /**
     * Tests setKey
     */
    public void testSetKey() {
        record.setKey("wassssssup");
        assertEquals("wassssssup", record.getKey());
    }


    /**
     * Tests setNode
     */
    public void testSetNode() {
        record.setNode(node2);
        assertEquals(node2, record.getNode());
    }


    /**
     * Tests getType
     */
    public void testGetType() {
        assertEquals("artist", record.getType());
    }


    /**
     * Tests setType
     */
    public void testSetType() {
        record.setType("song");
        assertEquals("song", record.getType());
    }
}
