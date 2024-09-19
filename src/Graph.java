public class Graph {
    // ~ Fields ................................................................
    private DoubleLL<Node>[] vertex;

    private int[] array;
    private int numberOfNodes;
    private int maxSize;
    private int connectedComponents;

    // ~ Constructors ..........................................................
    @SuppressWarnings("unchecked")
    public Graph(int maxSize) {
        this.setNumberOfNodes(0);
        this.maxSize = maxSize;
        this.setNumberOfNodes(0);
        this.setVertex(new DoubleLL[maxSize]);

        array = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            array[i] = -1;
        }

        this.connectedComponents = 0;

    }

    // ~Public Methods ........................................................


    /**
     * adds a new node to the graph
     * 
     * @param node
     */
    public void newNode(Node node) {
        if (getNumberOfNodes() >= maxSize / 2) {
            expand();
        }
        DoubleLL<Node> currentList = new DoubleLL<>();
        // currentList.insert(node);
        // getVertex()[getNumberOfNodes()] = currentList;
        getVertex()[node.getIndex()] = currentList; // node of the graph should
                                                    // be the same as the one in
                                                    // the adjacenty list

        setNumberOfNodes(getNumberOfNodes() + 1);

    }


    /**
     * adds an edge between two nodes
     * 
     * @param start
     * @param end
     */
    public void addEdge(int artistNode, int songNode) {
        if (vertex[artistNode] == null) {
            vertex[artistNode] = new DoubleLL<Node>();
        }
        if (vertex[songNode] == null) {
            vertex[songNode] = new DoubleLL<Node>();
        }
        if (!hasEdge(artistNode, songNode)) {
            vertex[artistNode].insert(songNode);
            vertex[songNode].insert(artistNode);
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
    public boolean hasEdge(int artistNode, int songNode) {
        if (vertex[artistNode] != null) {
            return vertex[artistNode].contains(songNode);
        }
        return false;
    }


    /**
     * remove node and edges
     */
    public void removeEdge(int artistNode, int songNode) {

        if (vertex[artistNode] != null && vertex[songNode] != null) {
            vertex[artistNode].remove(songNode);
            vertex[songNode].remove(artistNode);
        }

    }


    public void removeNode(Node node) {
        int index = node.getIndex();
        if (index < 0 || index >= vertex.length || vertex[index] == null) {
            return; // Exit early if node does not exist or index is invalid
        }

        if (vertex[index] != null) {
            vertex[index].resetTraversal();
            while (vertex[index].getSize() > 0) {
                int toRemove = vertex[index].getNext();
                removeEdge(index, toRemove);
                //array[toRemove] = toRemove;

            }
        }
        vertex[index] = null;
        array[index] = -1;
        numberOfNodes--;
    }


    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {

            array[root1] = root2;
        }

    }


    // Return the root of curr's tree with path compression
    public int find(int curr) {
        if (array[curr] == -1) {
            return curr; // This node is the root
        }

        array[curr] = find(array[curr]);
        return array[curr];
        // return curr;

    }


    public void expand() {
        int newSize = maxSize * 2; // Correctly double the current maxSize
        @SuppressWarnings("unchecked")
        DoubleLL<Node>[] newAdjacencyList = new DoubleLL[newSize];

        // Copy old adjacency lists to the new array
        for (int i = 0; i < maxSize; i++) { // Use maxSize instead of
                                            // getNumberOfNodes()
            newAdjacencyList[i] = vertex[i];
        }

        // No need to initialize the rest of the array; we'll handle it when
        // adding new nodes

        // Replace the old adjacency list with the new one
        setVertex(newAdjacencyList);

        // Expand the Union-Find array
        int[] newArray = new int[newSize];
        // Copy old Union-Find parent array
        for (int i = 0; i < maxSize; i++) {
            newArray[i] = array[i];
        }
        // Initialize the rest of the array
        for (int i = maxSize; i < newSize; i++) {
            newArray[i] = -1;
        }
        array = newArray;

        maxSize = newSize;
    }


    public void printGraph() {
        int[] components = new int[getNumberOfNodes()];
        int numberOfComponents = 0;
        int largestComponent = 0;

        for (int i = 0; i < getNumberOfNodes(); i++) {
            int root = find(i);
            components[root]++;
        }

        for (int i = 0; i < getNumberOfNodes(); i++) {
            if (components[i] > 0) {
                numberOfComponents++;
                if (components[i] > largestComponent) {
                    largestComponent = components[i];
                }
            }
        }

        System.out.println("There are " + numberOfComponents
            + " connected components");
        System.out.println("The largest connected component has "
            + largestComponent + " elements");

        this.connectedComponents = numberOfComponents;
    }


    public int getNumberOfNodes() {
        return numberOfNodes;
    }


    public int getConnectedComponents() {
        return connectedComponents;
    }


    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }


    public DoubleLL<Node>[] getVertex() {
        return vertex;
    }


    public void setVertex(DoubleLL<Node>[] vertex) {
        this.vertex = vertex;
    }

}
