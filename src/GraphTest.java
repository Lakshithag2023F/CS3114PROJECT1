import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GraphTest extends TestCase {
    // ~ Fields ................................................................
    private Graph graph;

    // ~ Constructors ..........................................................

    public void setUp() {
        graph = new Graph(5);
    }
    // ~Public Methods ........................................................


    /**
     * Tests adding edge between two valid nodes
     */
    public void testAddEdgeValid() {
        Graph graph = new Graph(10);
        graph.newNode(new Node(0));
        graph.newNode(new Node(1));

        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 0));

    }


    /**
     * Tests adding edge between non existent nodes
     */
    public void testAddEdgeNonExistent() {
        graph.addEdge(1, 2);

        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2, 1));
        assertNotNull(graph.getVertex()[1]);
        assertNotNull(graph.getVertex()[2]);

    }


    /**
     * Tests adding edge between two nodes that have edge already
     */
    public void testAddEdgeExisting() {

        graph.newNode(new Node(1));
        graph.newNode(new Node(2));

        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));

        graph.addEdge(1, 2); // Add again to ensure no duplicates
        assertTrue(graph.hasEdge(1, 2));
    }


    /**
     * Tests adding edge between the same node
     */
    public void testAddEdgeSame() {
        graph.newNode(new Node(1));
        graph.addEdge(1, 1);
        assertTrue(graph.hasEdge(1, 1));
    }


    /**
     * Tests adding edge after expanding
     */
    public void testAddEdgeExpanding() {
        Graph testGraph = new Graph(1);
        testGraph.newNode(new Node(0));
        testGraph.newNode(new Node(1));
        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));
        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));
    }


    /**
     * Tests adding edge with out of bound index
     */
    public void testAddEdgeOutOfBounds() {
        try {
            graph.addEdge(-1, 3);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Out of bounds");
        }
    }


    /**
     * Tests removing edge between two valid nodes that have edge
     */
    public void testRemoveEdgeValid() {

        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2, 1));

        graph.removeEdge(1, 2);
        assertFalse(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(2, 1));

    }


    /**
     * Tests removing edge that isn't there
     */
    public void testRemoveEdgeNotThere() {
        graph.newNode(new Node(0));
        graph.newNode(new Node(1));
        assertFalse(graph.hasEdge(0, 1));

        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
    }


    /**
     * Tests remove edge from null node
     */
    public void testRemoveEdgeNullNode() {
        graph.newNode(new Node(0));
        assertNull(graph.getVertex()[1]);
        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
    }


    /**
     * Tests remove edge from empty graph
     */
    public void testRemoveEdgeEmptyGraph() {
        assertNull(graph.getVertex()[2]);
        assertNull(graph.getVertex()[3]);
        graph.removeEdge(2, 3);
        assertFalse(graph.hasEdge(2, 3));
    }


    public void testExpand() {
        for (int i = 0; i < 5; i++) {
            graph.newNode(new Node(i));
        }
        // Trigger expand
        graph.newNode(new Node(6));
        // assertEquals(10, graph.getMaxSize());
    }


    /**
     * Tests the union and find method after an edge is added between two valid
     * nodes
     */
    public void testUnionFind() {
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        assertTrue(graph.hasEdge(1, 2)); // Both are connected
        assertFalse(graph.hasEdge(1, 3)); // Not connected

        assertTrue(graph.FIND(1) == graph.FIND(2));
        assertFalse(graph.FIND(1) == graph.FIND(3));
    }


    public void testPrintGraph() {
        graph.addEdge(0, 1); // First component
        graph.addEdge(1, 2);
        graph.addEdge(3, 4); // Second component

        // Capture the output of the printGraph() method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method to print the graph details
        graph.printGraph();

        // Reset the standard output
        System.setOut(System.out);

        // Convert the captured output to a String and trim it
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = "There are 2 connected components\n"
            + "The largest connected component has 3 elements";

        // Assert that the captured output matches the expected output
        // assertEquals(expectedOutput, output);
    }


    public void testNewNode() {
        // Setup: Initialize the graph structure
        int initialMaxSize = 4; // Example initial size
        Graph graph = new Graph(initialMaxSize);

        // Insert nodes to trigger expansion
        Node node1 = new Node(0);
        graph.newNode(node1);
        assertNotNull(graph.getVertex()[node1.getIndex()]); // Check that the
                                                            // adjacency list is
                                                            // initialized
        assertEquals(0, graph.getVertex()[node1.getIndex()].getSize()); // Adjacency
                                                                        // list
                                                                        // should
                                                                        // be
                                                                        // empty
        assertEquals(1, graph.getNumberOfNodes()); // Verify number of nodes
                                                   // increment

        // Continue inserting nodes
        Node node2 = new Node(1);
        graph.newNode(node2);
        assertNotNull(graph.getVertex()[node2.getIndex()]);
        assertEquals(0, graph.getVertex()[node2.getIndex()].getSize());
        assertEquals(2, graph.getNumberOfNodes());

        Node node3 = new Node(2);
        graph.newNode(node3);
        assertNotNull(graph.getVertex()[node3.getIndex()]);
        assertEquals(0, graph.getVertex()[node3.getIndex()].getSize());
        assertEquals(3, graph.getNumberOfNodes());

        // Trigger expansion by inserting another node
        Node node4 = new Node(3);
        graph.newNode(node4);

        // Assert that expansion has occurred
        assertEquals(8, graph.getVertex().length); // Check that the vertex
                                                   // array size has doubled
                                                   // (initially 4, now 8)
        assertEquals(4, graph.getNumberOfNodes()); // Verify number of nodes
                                                   // after expansion

        // Insert another node post-expansion
        Node node5 = new Node(4);
        graph.newNode(node5);
        assertNotNull(graph.getVertex()[node5.getIndex()]);
        assertEquals(0, graph.getVertex()[node5.getIndex()].getSize());
        assertEquals(5, graph.getNumberOfNodes());
    }


    public void testPrintGraph2() {
        // Initialize the graph with 6 nodes
        int initialSize = 6;
        Graph graph = new Graph(initialSize);

        // Add nodes and edges to form connected components
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        Node node6 = new Node(5);

        graph.newNode(node1);
        graph.newNode(node2);
        graph.newNode(node3);
        graph.newNode(node4);
        graph.newNode(node5);
        graph.newNode(node6);

        // Add edges to form two connected components:
        // Component 1: 0-1-2
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        // Component 2: 3-4
        graph.addEdge(3, 4);

        // Capture the output of printGraph()
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printGraph();

        // Verify the output
        String expectedOutput = "There are 3 connected components\n"
            + "The largest connected component has 3 elements\n";

        assertEquals(expectedOutput, outContent.toString());

        // Reset the output stream
        System.setOut(System.out);
    }


    /**
     * Tests removing a node with edges
     */
    public void testRemoveNode() {
        // Add nodes and edges
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        graph.newNode(node1);
        graph.newNode(node2);
        graph.newNode(node3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2, 3));

        assertNotNull(graph.getVertex()[1]);
        assertNotNull(graph.getVertex()[2]);
        assertNotNull(graph.getVertex()[3]);

        assertEquals(graph.getVertex()[1].getSize(), 1);

        graph.removeNode(node2);

        assertNull(graph.getVertex()[2]);

        assertFalse(graph.getVertex()[1].contains(2));
        assertFalse(graph.getVertex()[3].contains(2));

        assertFalse(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(2, 3));

    }


    /**
     * Tests removing node without edges
     */
    public void testRemoveNodeNoEdge() {
        graph.newNode(new Node(0));
        assertNotNull(graph.getVertex()[0]);
        graph.removeNode(new Node(0));
        assertNull(graph.getVertex()[(new Node(0)).getIndex()]);

    }


    /**
     * Tests removing node that doesn't exist
     */
    public void testRemoveNonExistingNode() {

        Node testNode = new Node(0);
        graph.newNode(testNode);

        Node nonExistingNode = new Node(4);
        graph.removeNode(nonExistingNode);

        assertNotNull(graph.getVertex()[testNode.getIndex()]);
        assertNull(graph.getVertex()[nonExistingNode.getIndex()]);

    }


    /**
     * Tests removing node with out of bounds index
     */
    public void testRemoveOutOfBounds() {
        Node negative = new Node(-1);
        Node greater = new Node(6);

        graph.removeNode(negative);
        graph.removeNode(greater);

        assertNull(graph.getVertex()[0]);
        assertNull(graph.getVertex()[1]);
        assertNull(graph.getVertex()[2]);
        assertNull(graph.getVertex()[3]);
        assertNull(graph.getVertex()[4]);

    }


    /**
     * Tests removing a null node
     */
    public void testRemoveNodeNull() {
        try {
            graph.removeNode(null);
        }
        catch (NullPointerException e) {
            System.out.println("Null point exception");
        }
        assertNull(graph.getVertex()[0]);
        assertNull(graph.getVertex()[1]);
        assertNull(graph.getVertex()[2]);
        assertNull(graph.getVertex()[3]);
        assertNull(graph.getVertex()[4]);
        assertEquals(graph.getNumberOfNodes(), 0);

        graph.newNode(new Node(1));
        assertNotNull(graph.getVertex()[1]);

    }


    /**
     * Tests advanced remove
     */
    public void testAdvancedRemove() {
        Node artistA = new Node(0);
        Node songA = new Node(1);
        Node songC = new Node(2);
        Node artistB = new Node(3);
        Node songB = new Node(4);
        Node songD = new Node(5);

        graph.newNode(artistA);
        graph.newNode(songA);
        graph.addEdge(0, 1);

        graph.newNode(songC);
        graph.addEdge(0, 2);

        graph.newNode(artistB);
        graph.addEdge(3, 2);

        graph.newNode(songB);
        graph.addEdge(3, 4);

        graph.newNode(songD);
        graph.addEdge(3, 5);

        graph.removeNode(artistB);
        graph.printGraph();
        assertEquals(graph.getConnectedComponents(), 3);

    }

}
