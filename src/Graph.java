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
     * @param node
     */
    public void newNode(Node node) {
        int index = node.getIndex();
        if (index >= size) {
            expand();
        }
        
        if (vertex[index] == null) {
            vertex[index] = new DoubleLL<>();
            numberOfNodes++;
        }
    }


    /**
     * adds an edge between two nodes
     * 
     * @param start
     * @param end
     */
    public void addEdge(Node start, Node end) {
        
        newNode(start);
        newNode(end);
        
        vertex[start.getIndex()].insert(end);
        vertex[end.getIndex()].insert(start);
    }


    /**
     * check if edge exists
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


    public void UNION(int a, int b) {
        int root1 = FIND(a);     // Find root of node a
        int root2 = FIND(b);     // Find root of node b
        if (root1 != root2) {          // Merge with weighted union
          if (weights[root2] > weights[root1]) {
            array[root1] = root2;
            weights[root2] += weights[root1];
          } else {
            array[root2] = root1;
            weights[root1] += weights[root2];
          }
        }
      }
    
 // Return the root of curr's tree with path compression
    public int FIND(int curr) {
      if (array[curr] == -1) return curr; // At root
      array[curr] = FIND(array[curr]);
      return array[curr];
    }


    public void connectedComponent() {

    }


    public int diameter() {
        return 0;
    }
}
