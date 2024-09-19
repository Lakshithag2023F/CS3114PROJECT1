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
        assertEquals(3, numList.getSize());

    }



    public void testContains() {
        assertFalse(numList.contains(1));
    }

      public void testInsertDuplicates() {
        numList.insert(5);
        numList.insert(5);
        assertTrue(numList.contains(5));
    }
      
      public void testRemove3() {
          numList.insert(1);
          numList.insert(2);
          numList.insert(3);

          assertTrue(numList.remove(2));
          assertFalse(numList.contains(2));
          assertEquals(2, numList.getSize());

          assertTrue(numList.remove(1));
          assertFalse(numList.contains(1));
          assertEquals(1, numList.getSize());

          assertTrue(numList.remove(3));
          assertFalse(numList.contains(3));
          assertEquals(0, numList.getSize());

          assertFalse(numList.remove(4));
          assertEquals(0, numList.getSize());

          strLL.insert("what's");
          strLL.insert("up");
          strLL.insert("hokies");

          assertTrue(strLL.remove("up"));
          assertFalse(strLL.contains("up"));
          assertEquals(2, strLL.getSize());

          assertTrue(strLL.remove("what's"));
          assertFalse(strLL.contains("what's"));
          assertEquals(1, strLL.getSize());

          assertTrue(strLL.remove("hokies"));
          assertFalse(strLL.contains("hokies"));
          assertEquals(0, strLL.getSize());

          assertFalse(strLL.remove("not there"));
          assertEquals(0, strLL.getSize());
      }

      
      public void testRemoveNull() {
          numList.insert(1);
          numList.insert(2);
          numList.insert(3);

          // Remove elements to ensure that current is the tail and head is null
          assertTrue(numList.remove(1));  // Removes head, now 2 is the new head
          assertTrue(numList.remove(2));  // Removes the new head, now 3 is both head and tail

          assertTrue(numList.remove(3));  // Now, current is the tail
          assertFalse(numList.contains(3));
          assertEquals(0, numList.getSize());

          assertFalse(numList.remove(4)); // Attempt to remove a non-existent element

          strLL.insert("what's");
          strLL.insert("up");
          strLL.insert("hokies");

          // Remove middle element
          assertTrue(strLL.remove("up"));
          assertFalse(strLL.contains("up"));
          assertEquals(2, strLL.getSize());

          // Make 'what's' the tail by removing 'hokies'
          assertTrue(strLL.remove("hokies")); // Now, "what's" is both head and tail
          assertFalse(strLL.contains("hokies"));
          assertEquals(1, strLL.getSize());

          // Set up scenario to make head null and current as tail
          assertTrue(strLL.remove("what's")); // Now the list is empty, and head is null
          assertFalse(strLL.contains("what's"));
          assertEquals(0, strLL.getSize());

          assertFalse(strLL.remove("not there")); // Attempt to remove a non-existent element
          assertEquals(0, strLL.getSize());
      }

      public void testRemove() {
          // Insert elements to set up the list
          numList.insert(1);
          numList.insert(2);
          numList.insert(3);

          // Scenario 1: Remove head element and ensure the new head is correctly updated
          assertTrue(numList.remove(1)); // Remove the head (1)
          assertFalse(numList.contains(1)); // Verify 1 is removed
          assertTrue(Integer.valueOf(2) == numList.getHeadData()); // New head should be 2
          assertTrue(Integer.valueOf(3) == numList.getTailData()); // Tail should still be 3
          assertTrue(Integer.valueOf(2) == numList.getSize()); // Ensure size is of type Integer

          // Scenario 2: Remove tail element and ensure the new tail is correctly updated
          assertTrue(numList.remove(3)); // Remove the tail (3)
          assertFalse(numList.contains(3)); // Verify 3 is removed
          assertTrue(Integer.valueOf(1) == numList.getSize()); // Verify size is now 1
          assertTrue(Integer.valueOf(2) == numList.getHeadData()); // Head should still be 2
          assertTrue(Integer.valueOf(2) == numList.getTailData()); // Tail should now be 2 (same as head)

          // Scenario 3: Attempt to remove a non-existent element
          assertFalse(numList.remove(4)); // Attempt to remove 4, which is not in the list
          assertTrue(Integer.valueOf(1) == numList.getSize()); // Verify size remains 1
          assertTrue(Integer.valueOf(2) == numList.getHeadData()); // Head should still be 2
          assertTrue(Integer.valueOf(2) == numList.getTailData()); // Tail should still be 2

          // Scenario 4: Remove the last remaining element (head == tail)
          assertTrue(numList.remove(2)); // Remove the last element
          assertFalse(numList.contains(2)); // Verify 2 is removed
          assertTrue(Integer.valueOf(0) == numList.getSize()); // Verify list is now empty
          assertNull(numList.getHeadData()); // Head should be null
          assertNull(numList.getTailData()); // Tail should be null

          // String list tests
          strLL.insert("what's");
          strLL.insert("up");
          strLL.insert("hokies");

          // Scenario 5: Remove a middle element and check the list's integrity
          assertTrue(strLL.remove("up")); // Remove "up"
          assertFalse(strLL.contains("up")); // Verify "up" is removed
          assertTrue(Integer.valueOf(2) == strLL.getSize()); // Verify size is now 2
          assertTrue("what's" == strLL.getHeadData()); // Head should still be "what's"
          assertTrue("hokies" == strLL.getTailData()); // Tail should still be "hokies"

          // Scenario 6: Remove head element and ensure the new head is correctly updated
          assertTrue(strLL.remove("what's")); // Remove the head ("what's")
          assertFalse(strLL.contains("what's")); // Verify "what's" is removed
          assertTrue(Integer.valueOf(1) == strLL.getSize()); // Verify size is now 1
          assertTrue("hokies" == strLL.getHeadData()); // New head should be "hokies"
          assertTrue("hokies" == strLL.getTailData()); // Tail should also be "hokies"

          // Scenario 7: Remove the last remaining element (head == tail)
          assertTrue(strLL.remove("hokies")); // Remove the last element
          assertFalse(strLL.contains("hokies")); // Verify "hokies" is removed
          assertTrue(Integer.valueOf(0) == strLL.getSize()); // Verify list is now empty
          assertNull(strLL.getHeadData()); // Head should be null
          assertNull(strLL.getTailData()); // Tail should be null

          // Scenario 8: Attempt to remove a non-existent element
          assertFalse(strLL.remove("not there")); // Attempt to remove a non-existent element
          assertTrue(Integer.valueOf(0) == strLL.getSize()); // Verify size remains 0
      }
}