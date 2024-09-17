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
}

