public class Graph {
    // ~ Fields ................................................................
    private DoubleLL<Node>[] vertex;
    private int numberOfNodes;
    private int size;

    // ~ Constructors ..........................................................
    @SuppressWarnings("unchecked")
    public Graph(int startSize) {
        this.size = startSize;
        this.numberOfNodes = 0;
        this.vertex = new DoubleLL[startSize];
        for (int i = 0; i < startSize; i++) {
            vertex[i] = new DoubleLL<Node>();
        }
    }

    // ~Public Methods ........................................................


    /**
     * adds a new node to the graph
     */
    public void newNode(Node node) {
        // implement
    }


    /**
     * adds an edge between two nodes
     * 
     * @param start
     * @param end
     */
    public void addEdge(Node start, Node end) {
        // implement
    }


    /**
     * check if edge exists
     */
    public boolean hasEdge(Node start, Node end) {
        // implement
        return false;
    }


    /**
     * removes edge between nodes
     */
    public void removeEdge(Node start, Node end) {
        // implemetn
    }


    /**
     * remove node and edges
     */
    public void removeNode(Node node) {
        // implemenet
    }


    /**
     * expand size of adjacency list array
     */
    public void expand() {

    }


    /**
     * print the graph
     */
    public void print() {

    }


    public void union() {

    }


    public void connectedComponent() {

    }


    public int diameter() {
        return 0;
    }
}
