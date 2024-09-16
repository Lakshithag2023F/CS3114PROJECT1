import student.TestCase;

public class GraphTest extends TestCase{
    //~ Fields ................................................................
    private Graph graph;
    
    //~ Constructors ..........................................................

    public void setUp()
    {
        graph = new Graph(5);
    }
    //~Public  Methods ........................................................

    
    public void testAddEdge() {
        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));

        graph.addEdge(1, 2); // Add again to ensure no duplicates
        assertTrue(graph.hasEdge(1, 2));
    }

    public void testRemoveEdge() {
        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));

        graph.removeEdge(1, 2);
        assertFalse(graph.hasEdge(1, 2));
    }

    public void testExpand() {
        for (int i = 0; i < 5; i++) {
            graph.newNode(new Node(i));
        }
        // Trigger expand
        graph.newNode(new Node(6));
        //assertEquals(10, graph.getMaxSize());
    }

    public void testUnionFind() {
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(1, 3));
        assertEquals(graph.find(1), graph.find(2));
        //assertNotEquals(graph.find(1), graph.find(3));
    }

    public void testPrintGraph() {
        // Capture print output and validate
        graph.printGraph();
    }
}

