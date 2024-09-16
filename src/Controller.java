public class Controller {
    // ~ Fields ................................................................
    private Hash artist;
    private Hash song;
    private Graph fullGraph;
    private int nodeNumber;
    private boolean artistThere;
    private boolean songThere;
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
        // check for an artist node
        Node artistNode = null;
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) { // artist not in artist hash
            artistNode = new Node(calculateNode());
            artist.insert(new Record(artistName, artistNode));
            fullGraph.newNode(artistNode);
            artistName = artistName.trim();
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
        }
        else {
            artistNode = artist.getRecord(artistName).getNode();
        }

        // check for a song node
        Node songNode = null;
        int songIndex = song.find(songName);
        if (songIndex == -1) {
            songNode = new Node(calculateNode());
            song.insert(new Record(songName, songNode));
            fullGraph.newNode(songNode);
            System.out.println("|" + songName
                + "| is added to the Song database.");
        }
        else {
            songNode = song.getRecord(songName).getNode();
        }

        // check if an edge exists between the two
        if (!fullGraph.hasEdge(artistNode.getIndex(), songNode.getIndex())) {
            fullGraph.addEdge(artistNode.getIndex(), songNode.getIndex());
        }

        else {
            artistName = artistName.trim();
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");
        }

    }


    public void removeArtist(String artistName) {
        Node artistNode;
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) // artist isnt in there
        {
            System.out.println("|" + artistName.trim()
                + "| does not exist in the Artist database.");
        }
        else {
            artistNode = artist.getRecord(artistName).getNode();
            artist.remove(artistName);
            System.out.println("|" + artistName
                + " is removed from the Artist database.");
            fullGraph.removeNode(artistNode);
        }

    }


    public void removeSong(String songName) {
        Node songNode;
        int songIndex = song.find(songName);
        if (songIndex == -1) // song isnt in there
        {
            System.out.println("|" + songName
                + "| does not exist in the Song database.");
        }
        else {
            songNode = song.getRecord(songName).getNode();
            song.remove(songName);
            System.out.println("|" + songName
                + " is removed from the Song database.");
            fullGraph.removeNode(songNode);
        }

    }


    public void printArtist() {
        artist.print();
        System.out.println("total artists: " + artist.getNumberOfRecords());

    }


    public void printSong() {
        song.print();
        System.out.println("total songs: " + song.getNumberOfRecords());

    }


    public void printBlock() {
        fullGraph.printGraph();

    }

}
