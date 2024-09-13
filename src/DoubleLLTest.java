// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author laksh
 *  @version Sep 13, 2024
 */
import student.TestCase;
public class DoubleLLTest extends TestCase{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................


    private DoubleLL<Integer> numList;
    private DoubleLL<String> strLL;

    /**
     * Sets up 
     */
    
    public void setUp() {
        // Initialize lists for testing
        numList = new DoubleLL<>();
        strLL = new DoubleLL<>();
    }

    /**
     * Test the insert() method for integer values.
     */
    public void testInsert() {
        // Insert integers into the list
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertTrue(numList.contains(1));
        assertTrue(numList.contains(2));
        assertTrue(numList.contains(3));

        assertFalse(numList.contains(4));
    }



    public void testContains() {
        assertFalse(numList.contains(1));
    }

      public void testInsertDuplicates() {
        numList.insert(5);
        numList.insert(5);
        assertTrue(numList.contains(5));
    }
    
    
    
    
    
    
    
    
    
    
    
}
