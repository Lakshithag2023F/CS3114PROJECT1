public class Controller {
    // ~ Fields ................................................................
    private Hash artist;
    private Hash song;
    private Graph fullGraph;
    // ~ Constructors ..........................................................

    public Controller(int hashSize) {
        artist = new Hash(hashSize);
        song = new Hash(hashSize);
        fullGraph = new Graph(100);
    }
    // ~Public Methods ........................................................


    public void insert(String artistName, String songName) {
        int aIndex = artist.find(artistName);
        Node artistNode;
    }
}
