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
    // fix advanced insert for hash before anything else, write additional test cases
    // issue is from when doubling size of hash table
    // ~Public Methods ........................................................


    private int calculateNode() {
        return ++nodeNumber;
    }

//    public void insert(String artistName, String songName) {
//        boolean artistFound = false;
//        boolean songFound = false;
//
//        // Check for an artist node
//        Node artistNode = null;
//        int artistIndex = artist.find(artistName);
//        if (artistIndex == -1) { // Artist not in the artist hash
//            artistNode = new Node(calculateNode());
//            
//            // Insert new artist record with type "artist"
//            artist.insert(new Record(artistName, artistNode, "artist"));
//            
//            fullGraph.newNode(artistNode);
//            artistName = artistName.trim();
//            System.out.println("|" + artistName + "| is added to the Artist database.");
//        } else {
//            artistNode = artist.getRecord(artistName).getNode();
//            artistFound = true;
//        }
//
//        // Check for a song node
//        Node songNode = null;
//        int songIndex = song.find(songName);
//        if (songIndex == -1) { // Song not in the song hash
//            songNode = new Node(calculateNode());
//            
//            // Insert new song record with type "song"
//            song.insert(new Record(songName, songNode, "song"));
//            
//            fullGraph.newNode(songNode);
//            System.out.println("|" + songName + "| is added to the Song database.");
//        } else {
//            songFound = true;
//            songNode = song.getRecord(songName).getNode();
//        }
//
//        // Check if an edge exists between the two
//        if (fullGraph.hasEdge(artistNode.getIndex(), songNode.getIndex())) {
//            artistName = artistName.trim();
//            System.out.println("|" + artistName + "<SEP>" + songName
//                + "| duplicates a record already in the database.");
//        } else {
//            fullGraph.addEdge(artistNode.getIndex(), songNode.getIndex());
//        }
//    }

    
    public void insert(String artistName, String songName) {
        boolean artistFound = false;
        boolean songFound = false;

        // Trim whitespace from artist and song names
        artistName = artistName.trim();
        songName = songName.trim(); 

        // Check for an artist node
        Node artistNode = null;
        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) { // Artist not in the artist hash
            artistNode = new Node(calculateNode());
            
            // Insert new artist record with type "artist"
            artist.insert(new Record(artistName, artistNode, "artist"));
            
            fullGraph.newNode(artistNode);
            System.out.println("|" + artistName + "| is added to the Artist database.");
        } else {
            artistNode = artist.getRecord(artistName).getNode();
        }  

        // Check for a song node
        Node songNode = null;
        int songIndex = song.find(songName);
        if (songIndex == -1) { // Song not in the song hash
            songNode = new Node(calculateNode());
            
            // Insert new song record with type "song"
            song.insert(new Record(songName, songNode, "song"));
            
            fullGraph.newNode(songNode);
            System.out.println("|" + songName + "| is added to the Song database.");
        } else {
            songNode = song.getRecord(songName).getNode();
        }

        // Check if an edge exists between the two
        if (fullGraph.hasEdge(artistNode.getIndex(), songNode.getIndex())) {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");
        } else {
            fullGraph.addEdge(artistNode.getIndex(), songNode.getIndex());
        }
    }



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
