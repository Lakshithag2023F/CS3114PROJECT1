
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test class to make test Node.java
 * 
 *  @author laksh
 *  @version Sep 12, 2024
 */
public class NodeTest extends TestCase{
    //~ Fields ................................................................
    private Node node;
    //~ Constructors ..........................................................
    
    /**
     * sets up the node 
     */
    public void setUp() {
        node = new Node(1);
    }
    //~Public  Methods ........................................................
    
    
    // ----------------------------------------------------------
    /**
     * makes sure that getIndex is working
     */
    public void testGetIndex()
    {
        assertEquals(node.getIndex(), 1);
    }
    
    // ----------------------------------------------------------
    /**
     * makes sure theat set index is working. 
     */
    public void testSetIndex()
    {
        
        node.setIndex(5);
        assertEquals(node.getIndex(), 5);

    }
}
