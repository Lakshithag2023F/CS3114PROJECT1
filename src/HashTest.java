import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author <Put something here>
 * @version <Put something here>
 */
public class HashTest extends TestCase {
    private Hash hash;
    private Record record;
    private Record record2;
    private Node node;
    private Node node2;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        hash = new Hash(10); // Initialize hash table with size 10

        node = new Node(2);
        node2 = new Node(3);

        // Records to be tested
        record = new Record("hokies", node, "artist"); // Specify type for record
        record2 = new Record("wassup", node2, "artist"); // Specify type for record
    }

    public void testInsert() {
        Node node5 = new Node(2);
        Record record5 = new Record("hokiesssss", node5, "artist");

        hash.insert(record);
        hash.insert(record5);
        int index = hash.find("hokies");
        //assertNotEquals(index, -1); // Ensure "hokies" is inserted

        Record[] allRecords = hash.getAllRecords();
        assertEquals("hokies", allRecords[index].getKey());

        Node node6 = new Node(4);
        Record record6 = new Record("hello", node6, "artist");
        hash.insert(record6);
        int index2 = hash.find("hello");
        //assertNotEquals(index2, -1); // Ensure "hello" is inserted
    }

    public void testInsertSameRecord() {
        Node node5 = new Node(4);
        Record record5 = new Record("testing", node5, "artist");

        Node node6 = new Node(4);
        Record record6 = new Record("hello", node6, "artist");

        hash.insert(record5);
        hash.insert(record6);
        int index = hash.find("testing");
        //assertNotEquals(index, -1); // Ensure "testing" is inserted

        hash.insert(record5); // Inserting "testing" again should not change state
        assertEquals(index, hash.find("testing")); // Should be the same index
    }

    public void testRehash() {
        Node node5 = new Node(4);
        Record record5 = new Record("one", node5, "artist");

        Node node6 = new Node(4);
        Record record6 = new Record("two", node6, "artist");

        Node node7 = new Node(4);
        Record record7 = new Record("three", node7, "artist");

        Node node8 = new Node(4);
        Record record8 = new Record("four", node8, "artist");

        Node node9 = new Node(4);
        Record record9 = new Record("five", node9, "artist");

        Node node10 = new Node(4);
        Record record10 = new Record("six", node9, "artist");

        Node node11 = new Node(7);
        Record record11 = new Record("seven", node11, "artist");

        hash.insert(record5);
        hash.insert(record6);
        hash.insert(record7);
       // hash.remove("three");
       // hash.insert(record7); // Reinsert after removal
        hash.insert(record8);
        hash.insert(record9);
        hash.insert(record10);
        hash.insert(record11);

        assertNotNull(hash.getRecord("one"));
        assertNotNull(hash.getRecord("two"));
        assertNotNull(hash.getRecord("three"));
        assertNotNull(hash.getRecord("four"));
        assertNotNull(hash.getRecord("five"));
        assertNotNull(hash.getRecord("six"));
        assertNotNull(hash.getRecord("seven"));
    }

    public void testRemove() {
        // Insert a record and then remove it
        hash.insert(record); // "hokies" should be inserted
        hash.remove("hokies"); // "hokies" should be removed

        // Check that "hokies" is no longer found
        assertEquals(-1, hash.find("hokies")); 
        assertNull(hash.getRecord("hokies")); 
        assertEquals(0, hash.getNumberOfRecords()); // Ensure number of records is updated correctly

        // Check removing a non-existing record does not cause errors
        hash.remove("notThere"); // Trying to remove a non-existing record
        assertEquals(-1, hash.find("notThere"));

        // Reinsert and test again
        hash.insert(record);
        hash.insert(record2);
        assertEquals(2, hash.getNumberOfRecords()); // Ensure both records are inserted
    }


    public void testFind() {
        hash.insert(record);
        hash.remove("hokies");
        int index = hash.find("hokies");
        assertEquals(index, -1);

        index = hash.find("notThere");
        assertEquals(-1, index);

        index = hash.find("anotherKey");
        assertEquals(-1, index);
    }

    public void testGetRecords() {
        hash.insert(record);
        Record getIndex = hash.getRecord("hokies");
        assertEquals(record, getIndex);
        assertNull(hash.getRecord("notThere"));
    }

    public void testPrint() {
        // Create a stream to hold the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Test case 1: Print when the hash table is empty
            hash.print();
            String expectedOutput = "";
            assertEquals(expectedOutput, outputStream.toString().trim());

            // Test case 2: Print after inserting a record
            hash.insert(record);
            outputStream.reset();  // Clear the previous output
            hash.print();
            expectedOutput = "3: |hokies|"; // Expected format based on insertion
            assertEquals(expectedOutput, outputStream.toString().trim());

            // Test case 3: Print after removing a record
            hash.remove("hokies");
            outputStream.reset();  // Clear the previous output
            hash.print();
            expectedOutput = "3: TOMBSTONE";  // Should print "TOMBSTONE"
            assertEquals(expectedOutput, outputStream.toString().trim());

            // Test case 4: Insert multiple records and print again
            Record record2 = new Record("test", new Node(2), "artist");
            hash.insert(record2);
            outputStream.reset();  // Clear the previous output
            hash.print();
            expectedOutput = "3: TOMBSTONE\n8: |test|";
            
            assertEquals(expectedOutput, outputStream.toString().trim());
        } finally {
            // Reset System.out back to the original
            System.setOut(originalOut);
        }
    }

    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }

    public void testRehashingWorks() {
        for (int i = 0; i < 6; i++) {
            Node node = new Node(i);
            Record record = new Record("key" + i, node, "artist");
            hash.insert(record);
        }

        for (int i = 0; i < 6; i++) {
            assertNotNull(hash.getRecord("key" + i));
        }

        Node nodeExtra = new Node(7);
        Record recordExtra = new Record("keyExtra", nodeExtra, "artist");
        hash.insert(recordExtra);

        for (int i = 0; i < 6; i++) {
            assertNotNull(hash.getRecord("key" + i));
        }
        assertNotNull(hash.getRecord("keyExtra")); // Ensure extra key is also inserted
    }
    
    
    
    

}
