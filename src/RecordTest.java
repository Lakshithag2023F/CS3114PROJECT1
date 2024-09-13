import student.TestCase;
// -------------------------------------------------------------------------
/**
 *  test class to test record.java
 * 
 *  @author laksh
 *  @version Sep 12, 2024
 */
public class RecordTest extends TestCase{
    //~ Fields ................................................................
    private Record record;
    private Node node;
    private Node node2;
    //~ Constructors ..........................................................
    
    /**
     * sets up the node 
     */
    public void setUp()
    {
        record = new Record("six", node);
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * tests the getKey method
    */
    public void testGetKey()
    {
        assertEquals("six", record.getKey());
    }
    
    // ----------------------------------------------------------
    /**
     * tests the getNode method
     */
    public void testGetNode()
    {
        assertEquals(node, record.getNode());
    }
    
    // ----------------------------------------------------------
    /**
     * tests the setKey method
     */
    public void testSetKey()
    {
        record.setKey("five");
        assertEquals("five", record.getKey());

    }
    
    // ----------------------------------------------------------
    /**
     * tests the setNode method. 
     */
    public void testSetNode()
    {
        record.setNode(node2);
        assertEquals(node2, record.getNode());

    }
}
