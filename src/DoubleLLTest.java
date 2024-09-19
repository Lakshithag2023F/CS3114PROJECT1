import student.TestCase;
/**
 *  this class tests the doubleLL.java class
 * 
 *  @author laksh
 *  @version Sep 13, 2024
 */
public class DoubleLLTest extends TestCase {
    //~ Fields ................................................................
    /*
     * initilizes a doubleLL of intergers named listOfNums
     */
    private DoubleLL<Integer> listOfNums;
    
    //~ Constructors ..........................................................
    /**
     * Sets up the test cases by initializing the DoubleLL instances.
     */
    public void setUp() {
        listOfNums = new DoubleLL<>();
    }

    //~ Public Methods ........................................................
    /**
     * test insert
     */
    public void testInsert() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);
        assertEquals(3, listOfNums.getSize());
        assertTrue(listOfNums.contains(1));
        assertTrue(listOfNums.contains(2));
        assertTrue(listOfNums.contains(3));
        assertFalse(listOfNums.contains(4)); 
    }

    /**
     * Test that contains() method works for an empty list.
     */
    public void testContainsEmptyList() {
        assertFalse(listOfNums.contains(1));  // List is empty, so it shouldn't contain anything
    }

    /**
     * Test the resetCurr() and getNext() methods.
     */
    public void testTraversal() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);

        listOfNums.resetCurr();  // Reset traversal to the head
        assertEquals(1, listOfNums.getNext());  // Should return the first element
        assertEquals(2, listOfNums.getNext());  // Should return the second element
        assertEquals(3, listOfNums.getNext());  // Should return the third element
        assertEquals(-1, listOfNums.getNext());  // Should return -1 (end of the list)
    }

    /**
     * Test inserting duplicate values into the list.
     */
    public void testInsertDuplicates() {
        listOfNums.insert(5);
        listOfNums.insert(5);
        assertTrue(listOfNums.contains(5));  // Should contain the value
        assertEquals(2, listOfNums.getSize());  // Both values should be counted
    }

    /**
     * Test removal of elements from the list.
     */
    public void testRemove() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);

        // Remove element 2 and check the list
        assertTrue(listOfNums.remove(2));  
        assertFalse(listOfNums.contains(2));
        assertEquals(2, listOfNums.getSize());

        // Remove element 1 (head) and check the list
        assertTrue(listOfNums.remove(1));  
        assertFalse(listOfNums.contains(1));
        assertEquals(1, listOfNums.getSize());

        // Remove element 3 (tail) and check the list
        assertTrue(listOfNums.remove(3));  
        assertFalse(listOfNums.contains(3));
        assertEquals(0, listOfNums.getSize());

        // Attempt to remove non-existent element
        assertFalse(listOfNums.remove(4));
        assertEquals(0, listOfNums.getSize());
    }

    /**
     * Test removing all elements, leaving the list empty.
     */
    public void testRemoveAll() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);

        // Remove all elements to empty the list
        assertTrue(listOfNums.remove(1));  
        assertTrue(listOfNums.remove(2));  
        assertTrue(listOfNums.remove(3));  

        // Ensure the list is empty
        assertEquals(0, listOfNums.getSize());
        assertFalse(listOfNums.contains(1));
        assertFalse(listOfNums.contains(2));
        assertFalse(listOfNums.contains(3));

        // Try removing a non-existent element
        assertFalse(listOfNums.remove(4));
    }

    /**
     * Test removing from an empty list.
     */
    public void testRemoveEmptyList() {
        assertFalse(listOfNums.remove(1));  // Should return false as the list is empty
        assertEquals(0, listOfNums.getSize());
    }

    /**
     * Test the getSize() method.
     */
    public void testGetSize() {
        assertEquals(0, listOfNums.getSize());  // Empty list, size should be 0
        listOfNums.insert(1);
        listOfNums.insert(2);
        assertEquals(2, listOfNums.getSize());  // After two insertions
    }

    /**
     * Test the getHeadData() method.
     */
    public void testGetHeadData() {
        assertEquals(-1, listOfNums.getHeadData());  // Empty list, should return -1
        listOfNums.insert(1);
        assertEquals(1, listOfNums.getHeadData());  // Head should be 1
        listOfNums.insert(2);
        assertEquals(1, listOfNums.getHeadData());  // Head should still be 1
    }

    /**
     * Test the getTailData() method.
     */
    public void testGetTailData() {
        assertEquals(-1, listOfNums.getTailData());  // Empty list, should return -1
        listOfNums.insert(1);
        assertEquals(1, listOfNums.getTailData());  // Tail should be 1
        listOfNums.insert(2);
        assertEquals(2, listOfNums.getTailData());  // Tail should be updated to 2
    }

//    /**
//     * Test edge case: removing the only element in the list.
//     */
//    public void testRemoveOnlyElement() {
//        listOfNums.insert(1);
//        assertTrue(listOfNums.remove(1));  // Remove the only element
//        assertEquals(0, listOfNums.getSize());  // Size should be 0 after removal
//        assertFalse(listOfNums.contains(1));  // Should no longer contain the element
//        assertEquals(-1, listOfNums.getHeadData());  // Head should be null (or -1)
//        assertEquals(-1, listOfNums.getTailData());  // Tail should be null (or -1)
//    }
//
//    /**
//     * Test edge case: traversing an empty list.
//     */
//    public void testTraversalEmptyList() {
//        listOfNums.resetCurr();
//        assertEquals(-1, listOfNums.getNext());  // Should return -1 as the list is empty
//    }
    
    
//    /**
//     * Test removing the head element.
//     */
//    public void testRemoveHead() {
//        listOfNums.insert(1);
//        listOfNums.insert(2);
//        listOfNums.insert(3);
//
//        assertTrue(listOfNums.remove(1));  // Remove head element
//        assertFalse(listOfNums.contains(1));  // Ensure 1 is removed
//        assertEquals(2, listOfNums.getHeadData());  // Head should now be 2
//        assertEquals(2, listOfNums.getSize());  // Size should be reduced
//    }

    
    /**
     * Test attempting to remove a non-existent element.
     */
    public void testRemoveNonExistent() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);

        assertFalse(listOfNums.remove(4));
        assertEquals(3, listOfNums.getSize());

    }

}