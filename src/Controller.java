// -------------------------------------------------------------------------
/**
 * This class contains the methods that are called in the command processor.
 * Insert adds song/artist. Remove removes then and print prints them.
 * 
 * @author laksh
 * @version Sep 19, 2024
 */
public class Controller {
    // ~ Fields ................................................................
    /*
     * the Hash that contains artists
     */
    private Hash artist;
    /*
     * the hash that contains the songs
     */
    private Hash song;
    /*
     * the graph fullGraph contains nodes
     */
    private Graph fullGraph;
    /*
     * nodeNumber tracks how many nodes are there
     */
    private int nodeNumber;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Create a new Controller object.
     * 
     * @param hashSize
     *            passes in a size to initialize the hash to.
     */
    public Controller(int hashSize) {
        artist = new Hash(hashSize);
        song = new Hash(hashSize);
        fullGraph = new Graph(hashSize);
        nodeNumber = -1;

    }


    // ~Public Methods ........................................................
    /*
     * handles the -1 index when a new artist/song is added.
     * 
     * @return
     * number of nodes
     */
    private int calculateNode() {
        return ++nodeNumber;
    }


    /**
     * Inserts the artist / song
     * 
     * @param artistName
     *            name of artist to be addded
     * @param songName
     *            name of the song to be added.
     */
    public void insert(String artistName, String songName) {

        // Trim whitespace from artist and song names
        artistName = artistName.trim();
        songName = songName.trim();

        // Check for an artist node
        Node artistNode = null;
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) { // Artist not in the artist hash
            artistNode = new Node(calculateNode());
            artist.insert(new Record(artistName, artistNode, "artist"));
            fullGraph.newNode(artistNode);
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
        }
        else {
            artistNode = artist.getRecord(artistName).getNode();
        }

        // Check for a song node
        Node songNode = null;
        int songIndex = song.find(songName);
        if (songIndex == -1) { // Song not in the song hash
            songNode = new Node(calculateNode());
            song.insert(new Record(songName, songNode, "song"));
            fullGraph.newNode(songNode);
            System.out.println("|" + songName
                + "| is added to the Song database.");
        }
        else {
            songNode = song.getRecord(songName).getNode();
        }

        // Check if an edge exists between the two
        if (fullGraph.hasEdge(artistNode.getIndex(), songNode.getIndex())) {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");
        }
        else {
            fullGraph.addEdge(artistNode.getIndex(), songNode.getIndex());
        }
    }


    /**
     * removes the artist from graph and hash
     * 
     * @param artistName
     *            name of artist to be removed
     */
    public void removeArtist(String artistName) {
        Node artistNode;
        artistName = artistName.trim();
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) // artist isnt in there
        {
            System.out.println("|" + artistName
                + "| does not exist in the Artist database.");
        }
        else {
            artistNode = artist.getRecord(artistName).getNode();
            artist.remove(artistName);
            System.out.println("|" + artistName
                + "| is removed from the Artist database.");
            fullGraph.removeNode(artistNode);
        }

    }


    /**
     * removes the song from graph and hash
     * 
     * @param songName
     *            name of the song to be removed
     */
    public void removeSong(String songName) {
        Node songNode;
        songName = songName.trim();
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
                + "| is removed from the Song database.");
            fullGraph.removeNode(songNode);
        }

    }


    /**
     * Prints the artist
     */
    public void printArtist() {
        artist.print();
        System.out.println("total artists: " + artist.getNumberOfRecords());

    }


    /**
     * Prints the songs
     */
    public void printSong() {
        song.print();
        System.out.println("total songs: " + song.getNumberOfRecords());

    }


    /**
     * Prints the graph
     */
    public void printBlock() {
        fullGraph.printGraph();

    }

}
