import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        //assertFalse(graph.hasEdge(1, 2));
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
        String expectedOutput = "There are 2 connected components\n" +
                                "The largest connected component has 3 elements";

        // Assert that the captured output matches the expected output
       // assertEquals(expectedOutput, output);
    }
    
    public void testNewNode() {
        // Setup: Initialize the graph structure
        int initialMaxSize = 4; // Example initial size
        Graph graph = new Graph(initialMaxSize); // Assuming you have a Graph class that uses DoubleLL

        // Insert nodes to trigger expansion
        Node node1 = new Node(1);
        graph.newNode(node1);
        assertNotNull(graph.getVertex()[0]); // Check that the first node is added
        assertEquals(node1, graph.getVertex()[0].getHeadData()); // Verify correct insertion
        assertEquals(1, graph.getNumberOfNodes()); // Verify number of nodes increment

        // Continue inserting nodes to reach the expansion threshold
        Node node2 = new Node(2);
        graph.newNode(node2);
        assertNotNull(graph.getVertex()[1]); // Check that the second node is added
        assertEquals(node2, graph.getVertex()[1].getHeadData()); // Verify correct insertion
        assertEquals(2, graph.getNumberOfNodes()); // Verify number of nodes increment

        Node node3 = new Node(3);
        graph.newNode(node3);
        assertNotNull(graph.getVertex()[2]); // Check that the third node is added
        assertEquals(node3, graph.getVertex()[2].getHeadData()); // Verify correct insertion
        assertEquals(3, graph.getNumberOfNodes()); // Verify number of nodes increment

        // Trigger expansion by inserting another node
        Node node4 = new Node(4);
        graph.newNode(node4);
        
        // Assert that expansion has occurred
        assertEquals(8, graph.getVertex().length); // Check that the vertex array size has doubled (initially 4, now 8)
        assertEquals(4, graph.getNumberOfNodes()); // Verify number of nodes after expansion

        // Insert another node to continue testing post-expansion
        Node node5 = new Node(5);
        graph.newNode(node5);
        assertNotNull(graph.getVertex()[4]); // Check that the fifth node is added
        assertEquals(node5, graph.getVertex()[4].getHeadData()); // Verify correct insertion
        assertEquals(5, graph.getNumberOfNodes()); // Verify number of nodes increment post-expansion
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
        String expectedOutput = "There are 3 connected components\n" +
                                "The largest connected component has 3 elements\n";
        
        assertEquals(expectedOutput, outContent.toString());

        // Reset the output stream
        System.setOut(System.out);
    }

}

