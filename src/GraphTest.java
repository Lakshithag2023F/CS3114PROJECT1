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


    public void testAddEdge1() {
        try {
            graph.addEdge(1, 2);
        }
        catch (NullPointerException e) {
            System.out.println("Null point error.");
        }
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        graph.addEdge(1, 2);
        assertTrue(graph.hasEdge(1, 2));

        graph.addEdge(1, 2); // Add again to ensure no duplicates
        assertTrue(graph.hasEdge(1, 2));
    }


    public void testRemoveEdge1() {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
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
        // assertEquals(10, graph.getMaxSize());
    }


    public void testUnionFind() {
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(1, 3));
        assertEquals(graph.find(1), graph.find(2));
        // assertNotEquals(graph.find(1), graph.find(3));
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


    public void testRemoveNode() {
        // Initialize the graph
        Graph graph = new Graph(10);

        // Add nodes and edges
        Node artistNode = new Node(0);
        Node songNode1 = new Node(1);
        Node songNode2 = new Node(2);

        graph.newNode(artistNode);
        graph.newNode(songNode1);
        graph.newNode(songNode2);

        // Add edges
        graph.addEdge(artistNode.getIndex(), songNode1.getIndex());
        graph.addEdge(artistNode.getIndex(), songNode2.getIndex());

        // Verify initial connections
        assertTrue(graph.hasEdge(artistNode.getIndex(), songNode1.getIndex()));
        assertTrue(graph.hasEdge(artistNode.getIndex(), songNode2.getIndex()));

        // Remove artist node
        graph.removeNode(artistNode);

        // Verify that the artist node's adjacency list is null
        assertNull(graph.getVertex()[artistNode.getIndex()]);

        // Verify that the artist node is removed from other nodes' adjacency
        // lists
// assertFalse(graph.getVertex()[songNode1.getIndex()].contains(
// artistNode));
// assertFalse(graph.getVertex()[songNode2.getIndex()].contains(
// artistNode));

        // Verify that edges are no longer present
        assertFalse(graph.hasEdge(artistNode.getIndex(), songNode1.getIndex()));
        assertFalse(graph.hasEdge(artistNode.getIndex(), songNode2.getIndex()));
    }


    public void testRemoveNonExistentNode() {
        Graph graph = new Graph(10);

        // Attempt to remove a node that doesn't exist
        Node nonExistentNode = new Node(5);
        graph.removeNode(nonExistentNode);

        // Since the node doesn't exist, the graph should remain unchanged
// assertEquals(0, graph.getNumberOfNodes());
// No exception should be thrown
    }


    public void testRemoveEdge2() {
        // Initialize the graph
        Graph graph = new Graph(10);

        // Add nodes and edges
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        graph.newNode(node1);
        graph.newNode(node2);
        graph.addEdge(node1.getIndex(), node2.getIndex());

        // Verify initial connection
        assertTrue(graph.hasEdge(node1.getIndex(), node2.getIndex()));

        // Remove the edge
        graph.removeEdge(node1.getIndex(), node2.getIndex());

        // Verify that the edge is removed
        assertFalse(graph.hasEdge(node1.getIndex(), node2.getIndex()));

        // Verify that nodes still exist
        assertNotNull(graph.getVertex()[node1.getIndex()]);
        assertNotNull(graph.getVertex()[node2.getIndex()]);
    }


    public void testAddEdge() {
        Graph graph = new Graph(10);

        graph.newNode(new Node(0));
        graph.newNode(new Node(1));

        graph.addEdge(0, 1);

        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 0));

        // Check Union-Find structure
        assertEquals(graph.find(0), graph.find(1));
    }


    public void testRemoveEdge() {
        Graph graph = new Graph(10);

        graph.newNode(new Node(0));
        graph.newNode(new Node(1));

        graph.addEdge(0, 1);
        // graph.removeEdge(0, 1);

// assertFalse(graph.hasEdge(0, 1));
// assertFalse(graph.hasEdge(1, 0));

        // Check Union-Find structure
        // assertNotEquals(graph.find(0), graph.find(1));
    }

}
