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
public class DoubleLLTest extends TestCase {
    //~ Fields ................................................................
    private DoubleLL<Integer> numList;

    //~ Constructors ..........................................................
    /**
     * Sets up the test cases by initializing the DoubleLL instances.
     */
    public void setUp() {
        numList = new DoubleLL<>();
    }

    //~ Public Methods ........................................................

    /**
     * Test the insert() method for integer values.
     */
    public void testInsert() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertTrue(numList.contains(1));  // Check insertion
        assertTrue(numList.contains(2));
        assertTrue(numList.contains(3));
        assertFalse(numList.contains(4));  // Check non-existent element
        assertEquals(3, numList.getSize());  // Ensure size is correct
    }

    /**
     * Test that contains() method works for an empty list.
     */
    public void testContainsEmptyList() {
        assertFalse(numList.contains(1));  // List is empty, so it shouldn't contain anything
    }

    /**
     * Test the resetTraversal() and getNext() methods.
     */
    public void testTraversal() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        numList.resetTraversal();  // Reset traversal to the head
        assertEquals(1, numList.getNext());  // Should return the first element
        assertEquals(2, numList.getNext());  // Should return the second element
        assertEquals(3, numList.getNext());  // Should return the third element
        assertEquals(-1, numList.getNext());  // Should return -1 (end of the list)
    }

    /**
     * Test inserting duplicate values into the list.
     */
    public void testInsertDuplicates() {
        numList.insert(5);
        numList.insert(5);
        assertTrue(numList.contains(5));  // Should contain the value
        assertEquals(2, numList.getSize());  // Both values should be counted
    }

    /**
     * Test removal of elements from the list.
     */
    public void testRemove() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        // Remove element 2 and check the list
        assertTrue(numList.remove(2));  
        assertFalse(numList.contains(2));
        assertEquals(2, numList.getSize());

        // Remove element 1 (head) and check the list
        assertTrue(numList.remove(1));  
        assertFalse(numList.contains(1));
        assertEquals(1, numList.getSize());

        // Remove element 3 (tail) and check the list
        assertTrue(numList.remove(3));  
        assertFalse(numList.contains(3));
        assertEquals(0, numList.getSize());

        // Attempt to remove non-existent element
        assertFalse(numList.remove(4));
        assertEquals(0, numList.getSize());
    }

    /**
     * Test removing all elements, leaving the list empty.
     */
    public void testRemoveAll() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        // Remove all elements to empty the list
        assertTrue(numList.remove(1));  
        assertTrue(numList.remove(2));  
        assertTrue(numList.remove(3));  

        // Ensure the list is empty
        assertEquals(0, numList.getSize());
        assertFalse(numList.contains(1));
        assertFalse(numList.contains(2));
        assertFalse(numList.contains(3));

        // Try removing a non-existent element
        assertFalse(numList.remove(4));
    }

    /**
     * Test removing from an empty list.
     */
    public void testRemoveEmptyList() {
        assertFalse(numList.remove(1));  // Should return false as the list is empty
        assertEquals(0, numList.getSize());
    }

    /**
     * Test the getSize() method.
     */
    public void testGetSize() {
        assertEquals(0, numList.getSize());  // Empty list, size should be 0
        numList.insert(1);
        numList.insert(2);
        assertEquals(2, numList.getSize());  // After two insertions
    }

    /**
     * Test the getHeadData() method.
     */
    public void testGetHeadData() {
        assertEquals(-1, numList.getHeadData());  // Empty list, should return -1
        numList.insert(1);
        assertEquals(1, numList.getHeadData());  // Head should be 1
        numList.insert(2);
        assertEquals(1, numList.getHeadData());  // Head should still be 1
    }

    /**
     * Test the getTailData() method.
     */
    public void testGetTailData() {
        assertEquals(-1, numList.getTailData());  // Empty list, should return -1
        numList.insert(1);
        assertEquals(1, numList.getTailData());  // Tail should be 1
        numList.insert(2);
        assertEquals(2, numList.getTailData());  // Tail should be updated to 2
    }

    /**
     * Test edge case: removing the only element in the list.
     */
    public void testRemoveOnlyElement() {
        numList.insert(1);
        assertTrue(numList.remove(1));  // Remove the only element
        assertEquals(0, numList.getSize());  // Size should be 0 after removal
        assertFalse(numList.contains(1));  // Should no longer contain the element
        assertEquals(-1, numList.getHeadData());  // Head should be null (or -1)
        assertEquals(-1, numList.getTailData());  // Tail should be null (or -1)
    }

    /**
     * Test edge case: traversing an empty list.
     */
    public void testTraversalEmptyList() {
        numList.resetTraversal();
        assertEquals(-1, numList.getNext());  // Should return -1 as the list is empty
    }
    
    
    /**
     * Test removing the head element.
     */
    public void testRemoveHead() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertTrue(numList.remove(1));  // Remove head element
        assertFalse(numList.contains(1));  // Ensure 1 is removed
        assertEquals(2, numList.getHeadData());  // Head should now be 2
        assertEquals(2, numList.getSize());  // Size should be reduced
    }

    /**
     * Test removing the tail element.
     */
    public void testRemoveTail() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertTrue(numList.remove(3));  // Remove tail element
        assertFalse(numList.contains(3));  // Ensure 3 is removed
        assertEquals(2, numList.getTailData());  // Tail should now be 2
        assertEquals(2, numList.getSize());  // Size should be reduced
    }

    /**
     * Test removing an element from the middle of the list.
     */
    public void testRemoveMiddle() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertTrue(numList.remove(2));  // Remove middle element
        assertFalse(numList.contains(2));  // Ensure 2 is removed
        assertEquals(1, numList.getHeadData());  // Head should remain 1
        assertEquals(3, numList.getTailData());  // Tail should remain 3
        assertEquals(2, numList.getSize());  // Size should be reduced
    }

    /**
     * Test removing the only element in the list.
     */
    public void testRemoveOnlyElement3() {
        numList.insert(1);

        assertTrue(numList.remove(1));  // Remove the only element
        assertFalse(numList.contains(1));  // Ensure 1 is removed
        assertEquals(0, numList.getSize());  // Size should be 0
        assertEquals(-1, numList.getHeadData());  // Head should be null
        assertEquals(-1, numList.getTailData());  // Tail should be null
    }

    /**
     * Test removing from an empty list.
     */
    public void testRemoveEmptyList4() {
        assertFalse(numList.remove(1));  // Removing from an empty list should return false
        assertEquals(0, numList.getSize());  // Size should still be 0
    }

    /**
     * Test attempting to remove a non-existent element.
     */
    public void testRemoveNonExistent() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);

        assertFalse(numList.remove(4));  // Element 4 does not exist in the list
        assertEquals(3, numList.getSize());  // Size should remain the same
        assertEquals(1, numList.getHeadData());  // Head should still be 1
        assertEquals(3, numList.getTailData());  // Tail should still be 3
    }

    /**
     * Test removing multiple elements and checking list consistency.
     */
    public void testRemoveMultiple() {
        numList.insert(1);
        numList.insert(2);
        numList.insert(3);
        numList.insert(4);

        // Remove head and ensure correct updates
        assertTrue(numList.remove(1));
        assertEquals(2, numList.getHeadData());
        assertEquals(4, numList.getTailData());
        assertEquals(3, numList.getSize());

        // Remove tail and ensure correct updates
        assertTrue(numList.remove(4));
        assertEquals(2, numList.getHeadData());
        assertEquals(3, numList.getTailData());
        assertEquals(2, numList.getSize());

        // Remove the remaining elements
        assertTrue(numList.remove(2));
        assertTrue(numList.remove(3));
        assertEquals(0, numList.getSize());
        assertEquals(-1, numList.getHeadData());
        assertEquals(-1, numList.getTailData());
    }
}