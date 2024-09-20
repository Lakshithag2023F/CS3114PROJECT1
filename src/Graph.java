// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * /**
 * Graph class that represents the adjacency list.
 * 
 * @author shrut
 * @version Sep 19, 2024
 */
public class Graph {
    // ~ Fields ................................................................
    /*
     * makes a double linked list of nodes
     */
    private DoubleLL<Node>[] vertex;
    /*
     * makes a array of intergers
     */
    private int[] array;
    /*
     * tracks the number of nodes
     */
    private int numberOfNodes;
    /*
     * tracks the max size
     */
    private int maxSize;
    /*
     * number of the connected components
     */
    private int connectedComponents;
    /*
     * array of the weights
     */
    private int[] weights;

    // ~ Constructors ..........................................................
    /**
     * Create a new Graph object.
     * 
     * @param maxSize
     *            is the maximum size of the graph
     */
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
            weights[i] = 0;
        }

        this.connectedComponents = 0;

    }

    // ~Public Methods ........................................................


    /**
     * Gets the number of nodes
     * 
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }


    /**
     * Get method for connected components
     * 
     * @return the number of connected components
     */
    public int getConnectedComponents() {
        return connectedComponents;
    }


    /**
     * Set method for the number of nodes
     * 
     * @param numberOfNodes
     *            is the number of nodes
     */
    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }


    /**
     * Returns the list at a position of the vertex adjacency list
     * 
     * @return the list
     */
    public DoubleLL<Node>[] getVertex() {
        return vertex;
    }


    /**
     * Set a position with an array
     * 
     * @param vertex
     *            is what it is set to
     */
    public void setVertex(DoubleLL<Node>[] vertex) {
        this.vertex = vertex;
    }


    /**
     * adds a new node to the graph
     * 
     * @param node
     *            is the node to be added
     */
    public void newNode(Node node) {
        if (getNumberOfNodes() >= maxSize / 2) {
            expand();
        }
        DoubleLL<Node> currentList = new DoubleLL<>();

        getVertex()[node.getIndex()] = currentList; // node of the graph should
                                                    // be the same as the one in
                                                    // the adjacency list
        setNumberOfNodes(getNumberOfNodes() + 1);

    }


    /**
     * adds an edge between two nodes
     * 
     * @param artistNode
     *            is the artist node
     * @param songNode
     *            is the song node
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
     * @param artistNode
     *            is the artist node
     * @param songNode
     *            is the song node
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
     * 
     * @param src
     *            is the source node
     * @param dest
     *            is the destination node
     */
    public void removeEdge(int src, int dest) {

        if (vertex[src] != null && vertex[dest] != null) {
            vertex[src].remove(dest);
        }

    }


    /**
     * Removes a node from the graph and its edges
     * 
     * @param node
     *            is the node removed
     */
    public void removeNode(Node node) {
        int index = node.getIndex();
        if (index < 0 || index >= vertex.length || vertex[index] == null) {
            return;
        }

        if (vertex[index] != null) {
            vertex[index].resetCurr();
            for (int i = 0; i < vertex[index].getSize(); i++) {
                int toRemove = vertex[index].getNext();
                removeEdge(toRemove, index);

            }
        }
        vertex[index] = null;
        array[index] = -1;
        numberOfNodes--;
    }


    /**
     * Union method to represent two nodes connecting
     * 
     * @param a
     *            is one node
     * @param b
     *            is the other node
     */
    public void union(int a, int b) {
        int root1 = find(a); // Find root of node a
        int root2 = find(b); // Find root of node b
        if (root1 != root2) { // weighted union
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


    /**
     * Get weight at index
     * 
     * @param index
     *            is the index to find in weights array
     * @return the weight
     */
    public int getWeight(int index) {
        return weights[index];
    }


    /**
     * Get root at index
     * 
     * @param index
     *            is the index to find the root of
     * @return the root
     */
    public int getRoot(int index) {
        return array[index];
    }


    /**
     * Find method with path compression to find the root of a node
     * 
     * @param curr
     *            is the node to find the root for
     * @return the root
     */
    public int find(int curr) {
        if (array[curr] == -1)
            return curr; // At root
        array[curr] = find(array[curr]);
        return array[curr];
    }


    /**
     * this method doubles the graph
     */
    public void expand() {
        int newSize = maxSize * 2; // Double current maxSize
        @SuppressWarnings("unchecked")
        DoubleLL<Node>[] newAdjacencyList = new DoubleLL[newSize];

        // Copy adjacency list to new array
        for (int i = 0; i < maxSize; i++) {
            newAdjacencyList[i] = vertex[i];
        }

        setVertex(newAdjacencyList);

        int[] newArray = new int[newSize];
        int[] newWeights = new int[newSize];

        for (int i = maxSize; i < newSize; i++) {
            newArray[i] = -1;
            newWeights[i] = 1;
        }
        array = newArray;
        weights = newWeights;
        maxSize = newSize;
    }


    /**
     * Print the graph contents
     */
    public void printGraph() {
        unionConnectedNodes();
        int numberOfComponents = 0;
        int largestComponent = 0;

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null) {
                if (array[i] == -1) {
                    numberOfComponents++;
                }
            }
        }

        for (int i = 0; i < vertex.length; i++) {
            if (weights[i] > largestComponent) {
                largestComponent = weights[i];
            }
        }

        System.out.println("There are " + numberOfComponents
            + " connected components");
        System.out.println("The largest connected component has "
            + largestComponent + " elements");

        this.connectedComponents = numberOfComponents;
    }


    /**
     * Iterates through graph and unionizes connected nodes
     */
    public void unionConnectedNodes() {
        for (int i = 0; i < maxSize; i++) {
            array[i] = -1;
            if (vertex[i] == null) {
                weights[i] = 0;
            }
            else {
                weights[i] = 1;
            }
        }
        for (int i = 0; i < maxSize; i++) {
            if (vertex[i] != null) {
                vertex[i].resetCurr();
                for (int j = 0; j < vertex[i].getSize(); j++) {
                    int neighbor = vertex[i].getNext();
                    union(i, neighbor);
                }
            }
        }
    }

}
