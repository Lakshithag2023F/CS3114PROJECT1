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
        node = new Node(3);
        node2 = new Node(3);

        record = new Record("hokies",node);
        record2 = new Record("wassup",node2);

    }


    public void testInsert()
    {
        Node node5 = new Node(2);
        Record record5 = new Record("hokies", node5);
        
        hash.insert(record);
        hash.insert(record5);
        int index= hash.find("hokies");
        
        Record[] allRecords = hash.getAllRecords();
        assertEquals("hokies", allRecords[index].getKey());
    }
    
    public void testInsertSame()
    {  
        hash.insert(record);
        hash.insert(record);

        int index = hash.find("hokies");
                
        Record[] allRecords = hash.getAllRecords();
        assertEquals("hokies", allRecords[index].getKey()); 
    }
    
    
    public void testInsertTombAndNull()
    {
        Node node5 = new Node(2);
        Record record5 = new Record("vt", node5);
        
        hash.insert(record);
        hash.insert(record5);
        
        int index1 = hash.find("hokies");
        int index2 = hash.find("vt");
        Record[] allRecords = hash.getAllRecords();
    }
    
    public void testInsertTombAndNotNull()
    {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void testRemove()
    {
        
    }
    
    public void testFind()
    {
        
    }
    
    public void testPrint()
    {
        hash.insert(record);
        //String printOutput = hash.print();
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
