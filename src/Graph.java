public class Graph {
    // ~ Fields ................................................................
    private DoubleLL<Node>[] vertex;

    private int[] array;
    private int numberOfNodes;
    private int maxSize;
    private int connectedComponents;
    private int[] weights;

    // ~ Constructors ..........................................................
    @SuppressWarnings("unchecked")
    public Graph(int maxSize) {
        this.setNumberOfNodes(0);
        this.maxSize = maxSize;
        this.setNumberOfNodes(0);
        this.setVertex(new DoubleLL[maxSize]);

        array = new int[maxSize];
        weights = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            array[i] = -1;
            weights[i] = 1;
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
            return;
        }

        if (vertex[index] != null) {
            vertex[index].resetCurr();
            while (vertex[index].getSize() > 0) {
                int toRemove = vertex[index].getNext();
                removeEdge(index, toRemove);
             

            }
        }
        vertex[index] = null;
        array[index] = -1;
        numberOfNodes--;
    }


    public void UNION(int a, int b) {
        int root1 = FIND(a); // Find root of node a
        int root2 = FIND(b); // Find root of node b
        if (root1 != root2) { // Merge with weighted union
            if (weights[root2] > weights[root1]) {
                array[root1] = root2;
                weights[root2] += weights[root1];
            }
            else {
                array[root2] = root1;
                weights[root1] += weights[root2];
            }
        }
    }


    // Return the root of curr's tree with path compression
    public int FIND(int curr) {
        if (array[curr] == -1)
            return curr; // At root
        array[curr] = FIND(array[curr]);
        return array[curr];
    }
// // Return the root of curr's tree with path compression
// public int find(int curr) {
// if (array[curr] == -1 || array[curr] == curr) {
// return curr;
// }
// array[curr] = find(array[curr]);
// return array[curr];
//
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
        int[] newWeights = new int[newSize];
        // Copy old Union-Find parent array
//        for (int i = 0; i < maxSize; i++) {
//            newArray[i] = array[i];
//        }
        // Initialize the rest of the array
        for (int i = maxSize; i < newSize; i++) {
            newArray[i] = -1;
            newWeights[i] = 1;
        }
        array = newArray;
        weights = newWeights;

        maxSize = newSize;
    }


    public void printGraph() {
        unionConnectedNodes();
        int[] components = new int[getNumberOfNodes()];
        int numberOfComponents = 0;
        int largestComponent = 0;

        for (int i = 0; i < getNumberOfNodes(); i++) {
            if (vertex[i] != null) {
                int root = FIND(i);
                components[root]++;
            }
        }

        for (int i = 0; i < getNumberOfNodes(); i++) {
            if (components[i] > 0) {
                numberOfComponents++;
                if (components[i] > largestComponent) {
                    largestComponent = components[i];
                }
            }
        }

        int diameter = diameter();

        System.out.println("There are " + numberOfComponents
            + " connected components");
        System.out.println("The largest connected component has "
            + largestComponent + " elements");
        System.out.println("The diameter of the graph is " + diameter);

        this.connectedComponents = numberOfComponents;
    }


    public void unionConnectedNodes() {
        for (int i = 0; i < numberOfNodes; i++) {
            if (vertex[i] != null) {
                vertex[i].resetCurr();
                for (int j = 0; j < vertex[i].getSize(); j++) {
                    int neighbor = vertex[i].getNext();
                    UNION(i, neighbor);
                }
            }
        }
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


    public int diameter() {
        int diameter = 0;
        for (int i = 0; i < numberOfNodes; i++) {
            if (vertex[i] != null) {
                int size = vertex[i].getSize();
                if (size > diameter) {
                    diameter = size;
                }
            }
        }
        return diameter;
    }

}
