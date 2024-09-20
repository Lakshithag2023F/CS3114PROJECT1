
// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
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
        hash = new Hash(10);

        node = new Node(2);
        node2 = new Node(3);

        record = new Record("hokies", node, "artist");
        record2 = new Record("wassup", node2, "artist");
    }


    /**
     * Tests insert method
     */
    public void testInsert() {
        Node node5 = new Node(2);
        Record record5 = new Record("hokiesssss", node5, "artist");

        hash.insert(record);
        hash.insert(record5);
        int index = hash.find("hokies");

        Record[] allRecords = hash.getAllRecords();
        assertEquals("hokies", allRecords[index].getKey());

        Node node6 = new Node(4);
        Record record6 = new Record("hello", node6, "artist");
        hash.insert(record6);
    }


    /**
     * Tests inserting the same record again
     */
    public void testInsertSame() {
        Node node5 = new Node(4);
        Record record5 = new Record("testing", node5, "artist");

        Node node6 = new Node(4);
        Record record6 = new Record("hello", node6, "artist");

        hash.insert(record5);
        hash.insert(record6);
        int index = hash.find("testing");

        hash.insert(record5);
        assertEquals(index, hash.find("testing"));
    }


    /**
     * Test the rehash method to see if the hash size doubles
     */
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

        Record record10 = new Record("six", node9, "artist");

        Node node11 = new Node(7);
        Record record11 = new Record("seven", node11, "artist");

        hash.insert(record5);
        hash.insert(record6);
        hash.insert(record7);
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


    /**
     * Tests the remove method
     */
    public void testRemove() {
        hash.insert(record);
        hash.remove("hokies");

        assertEquals(-1, hash.find("hokies"));
        assertNull(hash.getRecord("hokies"));
        assertEquals(0, hash.getNumberOfRecords());

        hash.insert(record);
        hash.insert(record2);
        assertEquals(2, hash.getNumberOfRecords());
    }


    /**
     * Tests find method
     */
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


    /**
     * Tests get records
     */
    public void testGetRecords() {
        hash.insert(record);
        Record getIndex = hash.getRecord("hokies");
        assertEquals(record, getIndex);
        assertNull(hash.getRecord("notThere"));
    }


    /**
     * Tests print method
     */
    public void testPrint() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            hash.print();
            String expectedOutput = "";
            assertEquals(expectedOutput, outputStream.toString().trim());

            hash.insert(record);
            outputStream.reset();
            hash.print();
            expectedOutput = "3: |hokies|";
            assertEquals(expectedOutput, outputStream.toString().trim());

            hash.remove("hokies");
            outputStream.reset();
            hash.print();
            expectedOutput = "3: TOMBSTONE";
            assertEquals(expectedOutput, outputStream.toString().trim());

            record2 = new Record("test", new Node(2), "artist");
            hash.insert(record2);
            outputStream.reset();
            hash.print();
            expectedOutput = "3: TOMBSTONE\n8: |test|";

            assertEquals(expectedOutput, outputStream.toString().trim());
        }
        finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests to see if rehash is working as it should
     */
    public void testRehashing0() {
        for (int i = 0; i < 6; i++) {
            node = new Node(i);
            record = new Record("key" + i, node, "artist");
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
        assertNotNull(hash.getRecord("keyExtra"));
    }


    /**
     * Tests Sfold
     * 
     * @throws Exception
     */
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


    /**
     * Tests find with a collision to trigger quadratic probing
     */
    public void testCollision() {
        Record record1 = new Record("key1", new Node(1), "artist");
        record2 = new Record("key1Collide", new Node(2), "artist");

        hash.insert(record1);
        hash.insert(record2);

        int index1 = hash.find("key1");
        int index2 = hash.find("key1Collide");

        assertTrue(index1 >= 0);
        assertTrue(index2 >= 0);

        assertTrue(index1 != index2);
        assertEquals(record1, hash.getRecord("key1"));
        assertEquals(record2, hash.getRecord("key1Collide"));
    }


    /**
     * Tests find with many collisions
     */
    public void testFindCollisions() {
        hash.insert(new Record("collision1", new Node(1), "song"));
        hash.insert(new Record("collision2", new Node(2), "song"));
        hash.insert(new Record("collision3", new Node(3), "song"));

        int index1 = hash.find("collision1");
        int index2 = hash.find("collision2");
        int index3 = hash.find("collision3");

        assertTrue(index1 >= 0);
        assertTrue(index2 >= 0);
        assertTrue(index3 >= 0);

        assertFalse(index1 == index2);
        assertFalse(index1 == index3);
        assertFalse(index2 == index3);
    }


    /**
     * Tests quadratic probing
     */
    public void testQuadProbing() {
        for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
            hash.insert(new Record("key" + i, new Node(i), "artist"));
        }
        hash.insert(new Record("wrapAroundKey", new Node(100), "artist"));

        int index = hash.find("wrapAroundKey");
        assertTrue(index >= 0);
        assertEquals("wrapAroundKey", hash.getRecord("wrapAroundKey").getKey());
    }


    /**
     * Tests find with full table
     */
    public void testFindWithFullTable() {
        for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
            hash.insert(new Record("key" + i, new Node(i), "song"));
        }

        int index = hash.find("key" + (hash.getAllRecords().length / 2 - 2));
        assertTrue(index >= 0);

        Record result = hash.getRecord("key" + (hash.getAllRecords().length / 2
            - 2));
        assertNotNull(result);
        assertEquals("key" + (hash.getAllRecords().length / 2 - 2), result
            .getKey());
    }


    /**
     * Tests find with a tombstone
     */
    public void testTombstones0() {
        hash.insert(new Record("toBeRemoved1", new Node(1), "artist"));
        hash.insert(new Record("toBeRemoved2", new Node(2), "artist"));
        hash.remove("toBeRemoved1");
        hash.remove("toBeRemoved2");

        hash.insert(new Record("newKeyAfterTombstones", new Node(3), "artist"));

        int index = hash.find("newKeyAfterTombstones");
        assertTrue(index >= 0);
        assertEquals("newKeyAfterTombstones", hash.getRecord(
            "newKeyAfterTombstones").getKey());
    }


    /**
     * Tests rehash song type
     */
    public void testRehashSong() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("song" + i, new Node(i), "song"));
            }

            outputStream.reset();

            hash.insert(new Record("testSong", new Node(1), "song"));

            String output = outputStream.toString().trim();
            assertTrue(output.contains("Song hash table size doubled."));
        }
        finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests rehash artist type
     */
    public void testRehashArtist() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("artist" + i, new Node(i), "artist"));
            }

            outputStream.reset();

            hash.insert(new Record("testArtist", new Node(2), "artist"));

            String output = outputStream.toString().trim();
            System.out.println("Captured output: " + output);

            assertTrue(output.contains("Artist hash table size doubled."));
        }
        finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests rehash unknown type
     */
    public void testRehashUnknown() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            for (int i = 0; i < hash.getAllRecords().length / 2; i++) {
                hash.insert(new Record("unknown" + i, new Node(i), "unknown"));
            }
            outputStream.reset();
            hash.insert(new Record("testUnknown", new Node(3), "unknown"));

            String output = outputStream.toString().trim();
            System.out.println("Captured output: " + output);

            assertTrue(output.contains(
                "Unknown type hash table size doubled."));
        }
        finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests printing empty tables
     */
    public void testPrintEmptyTables() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            hash.print();

            String expectedOutput = "";
            assertEquals(expectedOutput, outputStream.toString().trim());
        }
        finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests insert artist pair
     */
    public void testInsertPairs() {
        Record artistRecord = new Record("Blind Lemon Jefferson", new Node(0),
            "artist");
        Record songRecord = new Record("Long Lonesome Blues", new Node(1),
            "song");
        hash.insert(artistRecord);
        hash.insert(songRecord);

        hash.insert(artistRecord);
        hash.insert(songRecord);

        Record songRecord2 = new Record("Long   Lonesome Blues", new Node(2),
            "song");
        Record songRecord3 = new Record("long Lonesome Blues", new Node(3),
            "song");
        hash.insert(songRecord2);
        hash.insert(songRecord3);

        assertNotNull(hash.getRecord("Blind Lemon Jefferson"));
        assertNotNull(hash.getRecord("Long Lonesome Blues"));
        assertNotNull(hash.getRecord("Long   Lonesome Blues"));
        assertNotNull(hash.getRecord("long Lonesome Blues"));

        int artistIndex = hash.find("Blind Lemon Jefferson");
        hash.insert(artistRecord);
        assertEquals(artistIndex, hash.find("Blind Lemon Jefferson"));
        assertEquals(4, hash.getNumberOfRecords());
    }


    /**
     * Tests rehash
     */
    public void testRehashing() {
        for (int i = 0; i < 6; i++) {
            record = new Record("Artist" + i, new Node(i), "artist");
            hash.insert(record);
        }
        assertTrue(hash.getAllRecords().length > 10);
        for (int i = 0; i < 6; i++) {
            assertNotNull(hash.getRecord("Artist" + i));
        }
    }


    /**
     * Tests remove artist
     */
    public void testRemoveArtist() {
        Record artistRecord = new Record("Ma Rainey", new Node(0), "artist");
        hash.insert(artistRecord);
        hash.remove("ma rainey");
        assertNotNull(hash.getRecord("Ma Rainey"));
        hash.remove("Ma Rainey");
        assertNull(hash.getRecord("Ma Rainey"));
    }


    /**
     * Tests collision
     */
    public void testCollision0() {
        Record record1 = new Record("Key1", new Node(1), "song");
        record2 = new Record("Key1Collision", new Node(2), "song");
        hash.insert(record1);
        hash.insert(record2);
        assertNotNull(hash.getRecord("Key1"));
        assertNotNull(hash.getRecord("Key1Collision"));
    }


    /**
     * Tests remove method
     */
    public void testRemoval0() {
        hash = new Hash(10);
        hash.insert(new Record("key1", new Node(1), "artist"));
        assertNotNull(hash.getRecord("key1"));
        hash.remove("key1");
        assertNull(hash.getRecord("key1"));
    }


    /**
     * Test removal when there is collision
     */
    public void testRemovalCollision() {
        hash = new Hash(10);
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.insert(new Record("key2", new Node(2), "artist"));
        hash.insert(new Record("key3", new Node(3), "artist"));
        hash.remove("key2");
        assertNull(hash.getRecord("key2"));
        assertNotNull(hash.getRecord("key1"));
        assertNotNull(hash.getRecord("key3"));
    }


    /**
     * Testing remove after quad probings
     */
    public void testRemoval() {
        hash = new Hash(10);
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.insert(new Record("keyA", new Node(2), "artist"));
        hash.insert(new Record("keyB", new Node(3), "artist"));
        hash.remove("keyB");
        assertNull(hash.getRecord("keyB"));
        assertNotNull(hash.getRecord("key1"));
        assertNotNull(hash.getRecord("keyA"));
    }


    /**
     * Tests removal
     */
    public void testRemoval2() {
        hash = new Hash(5);
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.insert(new Record("key2", new Node(2), "artist"));
        hash.remove("key2");
        assertNull(hash.getRecord("key2"));
        assertNotNull(hash.getRecord("key1"));
    }


    /**
     * Testing removing a non existent key
     */
    public void testRemovingKeyNonExistent() {
        hash = new Hash(10);
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.remove("nonexistent");
        assertNotNull(hash.getRecord("key1"));
    }


    /**
     * Testing tombstones
     */
    public void testTombstones() {
        hash = new Hash(10);
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.remove("key1");
        assertNull(hash.getRecord("key1"));
        hash.insert(new Record("key2", new Node(2), "artist"));
        assertNotNull(hash.getRecord("key2"));
    }


    /**
     * Tests quadratic probing again
     */
    public void testQuadraticProbing2() {
        hash.insert(new Record("key1", new Node(1), "artist"));
        hash.insert(new Record("key2", new Node(2), "artist"));
        int index1 = hash.find("key1");
        int index2 = hash.find("key2");

        assertNotSame(index1, index2);
        assertEquals("key1", hash.getRecord("key1").getKey());
        assertEquals("key2", hash.getRecord("key2").getKey());
    }

}
