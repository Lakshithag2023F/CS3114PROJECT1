public class Controller {
    // ~ Fields ................................................................
    private Hash artist;
    private Hash song;
    private Graph fullGraph;
    private int nodeNumber;
    // ~ Constructors ..........................................................

    public Controller(int hashSize) {
        artist = new Hash(hashSize);
        song = new Hash(hashSize);
        fullGraph = new Graph(hashSize);
        nodeNumber = -1;
    }
    // ~Public Methods ........................................................


    private int calculateNode() {
        return nodeNumber++;
    }


    public void insert(String artistName, String songName) {
        Node artistNode = null;
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) { // artist not in artist hash
            artistNode = new Node(calculateNode());
            artist.insert(new Record(artistName, artistNode));
           // fullGraph.newNode(artistNode);
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
        }
        Node songNode = null;
        int songIndex = song.find(songName);
        if (songIndex == -1) {
            songNode = new Node(calculateNode());
            song.insert(new Record(songName, songNode));
           // fullGraph.newNode(songNode);
            // fullGraph.addEdge(artistNode, songNode);
            System.out.println("|" + songName
                + "| is added to the Song database.");
        }
        else {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");
        }

    }


    public void removeArtist(String key) {
        artist.remove(key);
    }


    public void removeSong(String key) {
        song.remove(key);
    }


    public void printArtist() {
        artist.print();
    }


    public void printSong() {
        song.print();
    }


    public void printBlock() {

    }


    public void printGraph() {

    }
}
