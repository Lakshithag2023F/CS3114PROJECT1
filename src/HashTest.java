import student.TestCase;

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

        record = new Record("hokies", node);
        record2 = new Record("wassup", node2);

    }


    public void testInsert() {
        Node node5 = new Node(2);
        Record record5 = new Record("hokiesssss", node5);

        hash.insert(record);
        hash.insert(record5);
        int index = hash.find("hokies");

        Record[] allRecords = hash.getAllRecords();
        assertEquals("hokies", allRecords[index].getKey());
        // doesnt have tombstone

    }


    public void testInsertTombstone() {
        hash.insert(record);
        hash.remove("hokies");
        hash.insert(record);
        // gets a tombstone
    }


    public void testInsertSameRecord() {
        Node node5 = new Node(4);
        Record record5 = new Record("testing", node5);

        Node node6 = new Node(4);
        Record record6 = new Record("hello", node6);

        Node node7 = new Node(4);
        Record record7 = new Record("yurr", node7);

        Node node8 = new Node(4);
        Record record8 = new Record("red", node8);

        Node node9 = new Node(4);
        Record record9 = new Record("red", node9);

        // Node node10 = new Node(4);
        // Record record10 = new Record("red", node10);

        hash.insert(record5);
        hash.insert(record6);
        hash.insert(record7);
        // hash.remove("yurr");
        hash.insert(record8);
        hash.insert(record9);

        int index = hash.find("testing");

        Record[] allRecords = hash.getAllRecords();
        assertEquals("testing", allRecords[index].getKey());

        // Using a for-each loop
// for (Record record : allRecords) {
// System.out.print(record + " ");
// }
// System.out.println();
    }


    public void testInsertProbing() {
        Node node5 = new Node(4);
        Record record5 = new Record("one", node5);

        Node node6 = new Node(4);
        Record record6 = new Record("two", node6);

        Node node7 = new Node(4);
        Record record7 = new Record("three", node7);

        Node node8 = new Node(4);
        Record record8 = new Record("four", node8);

        Node node9 = new Node(4);
        Record record9 = new Record("five", node9);

        Node node10 = new Node(4);
        Record record10 = new Record("six", node10);

        hash.insert(record5);
        hash.insert(record6);
        hash.insert(record7);
        hash.insert(record8);
        hash.insert(record9);
        hash.insert(record10);

        Record[] allRecords = hash.getAllRecords();

        for (Record record : allRecords) {
            System.out.print(record + " ");
        }
        System.out.println();

    }


    public void testRemove() {
        hash.insert(record);
        hash.remove("hokies");
        hash.remove("notThere");

    }


    public void testFind() {
        hash.insert(record);
        hash.remove("hokies");
        hash.find("hokies");
    }


    public void testPrint() {
        hash.insert(record);
        // String printOutput = hash.print();
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
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
}
