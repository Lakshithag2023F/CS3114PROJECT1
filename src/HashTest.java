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

  
    
    
    public void testFindWithSingleCollision() {
        // Insert two records that will cause a collision
        Record record1 = new Record("key1", new Node(1), "artist");
        Record record2 = new Record("key1Collide", new Node(2), "artist");

        // Insert records into the hash table
        hash.insert(record1);
        hash.insert(record2);

        // Find both records
        int index1 = hash.find("key1");
        int index2 = hash.find("key1Collide");

        // Ensure both are found
        assertTrue(index1 >= 0); // Ensure "key1" is found
        assertTrue(index2 >= 0); // Ensure "key1Collide" is found

        // Ensure they are not in the same slot
        assertTrue(index1 != index2); // Different indices due to collision resolution
        assertEquals(record1, hash.getRecord("key1")); 
        assertEquals(record2, hash.getRecord("key1Collide"));
    }

    public void testFindWithMultipleCollisions() {
        // Insert records that will cause multiple collisions
        hash.insert(new Record("collision1", new Node(1), "song"));
        hash.insert(new Record("collision2", new Node(2), "song"));
        hash.insert(new Record("collision3", new Node(3), "song"));

        // Ensure that all records are found and are not in the same index
        int index1 = hash.find("collision1");
        int index2 = hash.find("collision2");
        int index3 = hash.find("collision3");

        assertTrue(index1 >= 0);
        assertTrue(index2 >= 0);
        assertTrue(index3 >= 0);

        assertFalse(index1 == index2); // Ensure different slots
        assertFalse(index1 == index3); 
        assertFalse(index2 == index3); 
    }

    public void testQuadraticProbingWrapAround() {
        // Fill part of the table to create conditions for a wrap-around
        for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
            hash.insert(new Record("key" + i, new Node(i), "artist"));
        }

        // Insert a record that will wrap around due to quadratic probing
        hash.insert(new Record("wrapAroundKey", new Node(100), "artist"));

        // Ensure that the record is still found despite the wrap-around
        int index = hash.find("wrapAroundKey");
        assertTrue(index >= 0); // Ensure it is found
        assertEquals("wrapAroundKey", hash.getRecord("wrapAroundKey").getKey());
    }

    
    public void testFindWithFullTableProbing() {
        // Fill the table to a safe threshold, leaving some empty slots
        for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
            hash.insert(new Record("key" + i, new Node(i), "song"));
        }

        // Insert a key that will require probing but won't cause timeout
        int index = hash.find("key" + (hash.getAllRecords().length / 2 - 2));
        assertTrue(index >= 0); // Ensure it finds the key

        // Check that the probing loop handled the search correctly
        Record result = hash.getRecord("key" + (hash.getAllRecords().length / 2 - 2));
        assertNotNull(result);
        assertEquals("key" + (hash.getAllRecords().length / 2 - 2), result.getKey());
    }

    public void testFindWithTombstones() {
        // Insert and then remove records to create tombstones
        hash.insert(new Record("toBeRemoved1", new Node(1), "artist"));
        hash.insert(new Record("toBeRemoved2", new Node(2), "artist"));
        hash.remove("toBeRemoved1");
        hash.remove("toBeRemoved2");

        // Insert a new record that will require probing past tombstones
        hash.insert(new Record("newKeyAfterTombstones", new Node(3), "artist"));

        // Ensure the key is found correctly
        int index = hash.find("newKeyAfterTombstones");
        assertTrue(index >= 0);
        assertEquals("newKeyAfterTombstones", hash.getRecord("newKeyAfterTombstones").getKey());
    }

    

    public void testRehashSongType() {
        // Create a stream to capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Fill the hash table to trigger rehash
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("song" + i, new Node(i), "song"));
            }

            // Reset the output stream
            outputStream.reset();
            
            // Insert a song record that will trigger rehash
            hash.insert(new Record("testSong", new Node(1), "song"));

            // Capture the output
            String output = outputStream.toString().trim();

            // Print the output to see what was captured (for debugging)
            System.out.println("Captured output: " + output);

            // Assert that the expected message is in the output
            assertTrue(output.contains("Song hash table size doubled."));
        } finally {
            // Reset the System.out to its original stream
            System.setOut(originalOut);
        }
    }

    public void testRehashArtistType() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Fill the hash table to trigger rehash
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("artist" + i, new Node(i), "artist"));
            }

            // Reset the output stream
            outputStream.reset();
            
            // Insert an artist record that will trigger rehash
            hash.insert(new Record("testArtist", new Node(2), "artist"));

            String output = outputStream.toString().trim();
            System.out.println("Captured output: " + output); // Debugging output

            assertTrue(output.contains("Artist hash table size doubled."));
        } finally {
            System.setOut(originalOut);
        }
    }

    public void testRehashUnknownType() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Fill the hash table to trigger rehash
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("unknown" + i, new Node(i), "unknown"));
            }

            // Reset the output stream
            outputStream.reset();
            
            // Insert an unknown record type that will trigger rehash
            hash.insert(new Record("testUnknown", new Node(3), "unknown"));

            String output = outputStream.toString().trim();
            System.out.println("Captured output: " + output); // Debugging output

            assertTrue(output.contains("Unknown type hash table size doubled."));
        } finally {
            System.setOut(originalOut);
        }
    }

    
    
    
    public void testPrintEmptyTables() {
        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Print empty song table
            hash.print();
            String expectedOutput = "";
            assertEquals(expectedOutput, outputStream.toString().trim());

            // Assuming similar method for artist hash, you would test it similarly
        } finally {
            System.setOut(originalOut);
        }
    }

    public void testInsertArtistSongPairs() {
        // Insert "Blind Lemon Jefferson" with "Long Lonesome Blues"
        Record artistRecord = new Record("Blind Lemon Jefferson", new Node(0), "artist");
        Record songRecord = new Record("Long Lonesome Blues", new Node(1), "song");
        hash.insert(artistRecord);
        hash.insert(songRecord);

        // Attempt to insert duplicate
        hash.insert(artistRecord);
        hash.insert(songRecord);

        // Insert variations
        Record songRecord2 = new Record("Long   Lonesome Blues", new Node(2), "song");
        Record songRecord3 = new Record("long Lonesome Blues", new Node(3), "song");
        hash.insert(songRecord2);
        hash.insert(songRecord3);

        // Verify records are inserted
        assertNotNull(hash.getRecord("Blind Lemon Jefferson"));
        assertNotNull(hash.getRecord("Long Lonesome Blues"));
        assertNotNull(hash.getRecord("Long   Lonesome Blues"));
        assertNotNull(hash.getRecord("long Lonesome Blues"));

        // Check for duplicates
        int artistIndex = hash.find("Blind Lemon Jefferson");
        hash.insert(artistRecord); // Should not change the number of records
        assertEquals(artistIndex, hash.find("Blind Lemon Jefferson"));
        assertEquals(4, hash.getNumberOfRecords()); // Two artists and two songs
    }

    public void testRehashing() {
        // Fill the hash table to trigger rehashing
        for (int i = 0; i < 6; i++) {
            Record record = new Record("Artist" + i, new Node(i), "artist");
            hash.insert(record);
        }

        // Hash table should have rehashed
        assertTrue(hash.getAllRecords().length > 10); // Initial size was 10

        // Verify all records are still accessible
        for (int i = 0; i < 6; i++) {
            assertNotNull(hash.getRecord("Artist" + i));
        }
    }

    public void testRemoveArtistCaseSensitivity() {
        // Insert artist "Ma Rainey"
        Record artistRecord = new Record("Ma Rainey", new Node(0), "artist");
        hash.insert(artistRecord);

        // Attempt to remove "ma rainey" (lowercase)
        hash.remove("ma rainey");

        // Artist should still exist
        assertNotNull(hash.getRecord("Ma Rainey"));

        // Now remove with correct case
        hash.remove("Ma Rainey");

        // Artist should be removed
        assertNull(hash.getRecord("Ma Rainey"));
    }

    public void testPrintAfterRemovals() {
        // Insert artist
        Record artistRecord = new Record("Ma Rainey", new Node(0), "artist");
        hash.insert(artistRecord);

        // Remove artist
        hash.remove("Ma Rainey");

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Print artist table
            hash.print();

            // Expected output should show TOMBSTONE at the index where "Ma Rainey" was
            String expectedOutput = "";
            for (int i = 0; i < hash.getAllRecords().length; i++) {
                if (hash.getAllRecords()[i] == hash.getTombstone()) {
                    expectedOutput += i + ": TOMBSTONE\n";
                }
            }
            assertEquals(expectedOutput.trim(), outputStream.toString().trim());
        } finally {
            System.setOut(originalOut);
        }
    }


    public void testCollisionHandling() {
        // Insert records that will collide
        Record record1 = new Record("Key1", new Node(1), "song");
        Record record2 = new Record("Key1Collision", new Node(2), "song");

        // Manually set hash values to cause collision
        int hash1 = Hash.h("Key1", hash.getAllRecords().length);
        int hash2 = hash1; // Force collision

        // Insert records
        hash.insert(record1);
        hash.insert(record2);

        // Verify both records are in the table
        assertNotNull(hash.getRecord("Key1"));
        assertNotNull(hash.getRecord("Key1Collision"));

        // Ensure they are at different positions
        //assertNotEquals(hash.find("Key1"), hash.find("Key1Collision"));
    }

}
