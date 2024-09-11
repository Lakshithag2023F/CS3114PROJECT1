public class Record {
    // ~ Fields ................................................................
    private String key;
    private Node node;
    
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    
    public Record(String key, Node node)
    {
        this.key = key;
        this.node = node;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public Node getNode()
    {
        return node;
    }
}
