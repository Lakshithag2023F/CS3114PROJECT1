public class Graph {
    // ~ Fields ................................................................
    private DoubleLL<Node>[] vertex;

    private int[] array;
    private int numberOfNodes;
    private int maxSize;

    // ~ Constructors ..........................................................
    @SuppressWarnings("unchecked")
    public Graph(int maxSize) {
        this.numberOfNodes = 0;
        this.maxSize = maxSize;
        this.numberOfNodes = 0;
        this.vertex = new DoubleLL[maxSize];

        array = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            array[i] = -1;
        }

    }

    // ~Public Methods ........................................................


    /**
     * adds a new node to the graph
     * 
     * @param node
     */
    public void newNode(Node node) {
        if (numberOfNodes >= maxSize / 2) {
            expand();
        }
        DoubleLL<Node> currentList = new DoubleLL<>();
        currentList.insert(node);
        vertex[numberOfNodes] = currentList;
        numberOfNodes++;

    }


    /**
     * adds an edge between two nodes
     * 
     * @param start
     * @param end
     */
    public void addEdge(int artistNode, int songNode) {

        if (find(artistNode) != find(songNode)) {
            union(artistNode, songNode);
        }
    }


    /**
     * check if edge exists
     * 
     * @param start
     * @param end
     * @return true or false whether edge exists
     */
    public boolean hasEdge(Node start, Node end) {
        int index = start.getIndex();
        return vertex[index].contains(end);
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


    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            array[root1] = root2;
        }

    }


    public int find(int curr) {
        while (array[curr] != -1) {
            curr = array[curr];
        }

        return curr;

    }


    // Return the root of curr's tree with path compression
    public int FIND(int curr) {
        if (array[curr] == -1)
            return curr; // At root
        array[curr] = FIND(array[curr]);
        return array[curr];
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


    public void connectedComponent() {
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println();
        }

    }


    public int diameter() {
        return 0;
    }
}
