public class Record {
    // Fields
    private String key;  // artist name or track
    private Node node;   // associated node
    private String type; // "song" or "artist" to indicate the type of the record

    // Constructor with type
    public Record(String key, Node node, String type) {
        this.key = key;
        this.node = node;
        this.type = type; // Initialize the type field
    }

    // Getter methods
    public String getKey() {
        return key;
    }

    public Node getNode() {
        return node;
    }

    public String getType() {
        return type;
    }

    // Setter methods
    public void setKey(String key) {
        this.key = key;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setType(String type) {
        this.type = type;
    }
}
