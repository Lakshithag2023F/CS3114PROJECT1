import student.TestCase;

/**
 * this class tests the doubleLL.java class
 * 
 * @author laksh
 * @version Sep 13, 2024
 */
public class DoubleLLTest extends TestCase {
    // ~ Fields ................................................................
    /*
     * initilizes a doubleLL of intergers named listOfNums
     */
    private DoubleLL<Integer> listOfNums;

    // ~ Constructors ..........................................................
    /**
     * Sets up the test cases by initializing the DoubleLL instances.
     */
    public void setUp() {
        listOfNums = new DoubleLL<>();
    }


    // ~ Public Methods ........................................................
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

        listOfNums.insert(5);
        listOfNums.insert(5);
        assertTrue(listOfNums.contains(5));
        assertEquals(5, listOfNums.getSize());
    }


    /**
     * test contains
     */
    public void testContainsEmptyList() {
        assertFalse(listOfNums.contains(100));
    }


    /**
     * test resetCurr
     */
    public void testresetCurr() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);
        listOfNums.resetCurr();
        assertEquals(1, listOfNums.getNext());
        assertEquals(2, listOfNums.getNext());
        assertEquals(3, listOfNums.getNext());
        assertEquals(-1, listOfNums.getNext());
    }


    /**
     * testing remove method
     */
    public void testRemove() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);

        assertTrue(listOfNums.remove(2));
        assertFalse(listOfNums.contains(2));
        assertEquals(2, listOfNums.getSize());

        assertTrue(listOfNums.remove(1));
        assertFalse(listOfNums.contains(1));
        assertEquals(1, listOfNums.getSize());

        assertTrue(listOfNums.remove(3));
        assertFalse(listOfNums.contains(3));
        assertEquals(0, listOfNums.getSize());

        assertFalse(listOfNums.remove(4));
        assertEquals(0, listOfNums.getSize());
    }


    /**
     * Test remove when you get rid of all the elements in the array
     */
    public void testRemoveAll() {
        assertFalse(listOfNums.remove(1));
        assertEquals(0, listOfNums.getSize());
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);
        assertTrue(listOfNums.remove(1));
        assertTrue(listOfNums.remove(2));
        assertTrue(listOfNums.remove(3));
        assertEquals(0, listOfNums.getSize());
        assertFalse(listOfNums.contains(1));
        assertFalse(listOfNums.contains(2));
        assertFalse(listOfNums.contains(3));
        assertFalse(listOfNums.remove(4));
    }


    /**
     * test getSize
     */
    public void testGetSize() {
        assertEquals(0, listOfNums.getSize());
        listOfNums.insert(1);
        listOfNums.insert(2);
        assertEquals(2, listOfNums.getSize());
    }


    /**
     * test getHeadData
     */
    public void testGetHeadData() {
        assertEquals(-1, listOfNums.getHeadData());
        listOfNums.insert(1);
        assertEquals(1, listOfNums.getHeadData());
        listOfNums.insert(2);
        assertEquals(1, listOfNums.getHeadData());
    }


    /**
     * test getTailData
     */
    public void testGetTailData() {
        assertEquals(-1, listOfNums.getTailData());
        listOfNums.insert(1);
        assertEquals(1, listOfNums.getTailData());
        listOfNums.insert(2);
        assertEquals(2, listOfNums.getTailData());
    }


    /**
     * testing if you can remove somehting when its not in the list
     */
    public void testRemoveNonExistent() {
        listOfNums.insert(1);
        listOfNums.insert(2);
        listOfNums.insert(3);
        assertFalse(listOfNums.remove(4));
        assertEquals(3, listOfNums.getSize());
    }


    /**
     * Test if head is null
     */
    public void testRemoveHeadIsNull() {
        boolean result = listOfNums.remove(0);
        assertFalse(result);

    }


    /**
     * Test adding multiple elements and removing one
     */
    public void testRemoveOneFromMany() {
        listOfNums.insert(0);
        listOfNums.insert(1);
        listOfNums.insert(2);
        boolean result = listOfNums.remove(1);
        assertTrue(result);
        assertTrue(listOfNums.getTailData() == 2);

    }


    /**
     * Test removing tail
     */
    public void testRemoveTail() {
        listOfNums.insert(0);
        listOfNums.insert(1);
        listOfNums.insert(2);
        boolean result = listOfNums.remove(1);
        assertTrue(result);
        assertTrue(listOfNums.getTailData() == 2);

    }

}
