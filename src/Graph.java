public class Graph {
    // ~ Fields ................................................................
    private DoubleLL<Node>[] vertex;

    private int[] array;
    private int numberOfNodes;
    private int maxSize;

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

        if (!hasEdge(artistNode, songNode)) {
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
        return find(artistNode) == find(songNode);

    }


    /**
     * remove node and edges
     */

// DoubleLL<Node> adjacencyList = vertex[node.getIndex()];
// if (adjacencyList != null) {
// //for (Node adjacentNode : adjacencyList)
// {
// // Remove the connection from the adjacent node's list
// //vertex[adjacentNode.getIndex()].remove(node);
//
// }
// }

// public void removeNode(Node node) {
//// DoubleLL<Node> adjacencyList = vertexF[node.getIndex()];
//// if (adjacencyList != null) {
//// //for (Node adjacentNode : adjacencyList)
//// {
//// // Remove the connection from the adjacent node's list
//// //vertex[adjacentNode.getIndex()].remove(node);
////
//// }
//// }
//
// int index = node.getIndex();
// // look for connected nodes in vertex[index]
//
// vertex[index] = null;
// for (int i = 0; i < maxSize; i++) {
// DoubleLL<Node> adjacency = vertex[i];
// adjacency.remove(node);
// }
// }

    public void removeNode(Node node) {
        int index = node.getIndex();

        if (index < 0 || index >= vertex.length || vertex[index] == null) {
            return;
        }
        // Remove the adjacency list for this node
        vertex[index] = null;

        // Remove this node from the adjacency lists of other nodes
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].remove(node);
            }
        }

        // Update the Union-Find parent array
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
        while (array[curr] != -1) {
            curr = array[curr];
        }
        return curr;
    }


    /**
     * expand size of adjacency list array
     */
// public void expand() {
// int newSize = maxSize * 2; // Correctly double the current maxSize
// @SuppressWarnings("unchecked")
// DoubleLL<Node>[] newAdjacencyList = new DoubleLL[newSize];
//
// // Copy old adjacency lists to the new array
// for (int i = 0; i < getNumberOfNodes(); i++) {
// newAdjacencyList[i] = getVertex()[i];
// }
//
// // Initialize the new parts of the array
// for (int i = getNumberOfNodes(); i < newSize; i++) {
// newAdjacencyList[i] = new DoubleLL<>();
// }
//
// // Replace the old adjacency list with the new one
// setVertex(newAdjacencyList);
// maxSize = newSize;
//
// // System.out.println("Graph expanded to " + maxSize + " nodes.");
// }

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
    }


    public int getNumberOfNodes() {
        return numberOfNodes;
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
