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
        if (aIndex == -1) {
            artistNode = new Node(artist.getNext());
            artist.insert(new Record(artistName, artistNode));
            fullGraph.newNode(artistNode);
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
        }
        else {
            artistNode = artist.getRecordAt(aIndex).getNode();
        }

        int sIndex = song.find(songName);
        if (sIndex == -1) {
            Node songNode = new Node(song.getNext());
            song.insert(new Record(songName, songNode));
            fullGraph.newNode(songNode);
            fullGraph.addEdge(artistNode, songNode);
            System.out.println("|" + songName
                + "| is added to the Song database.");
        }
        else {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the databse.");
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


    public void printGraph() {

    }
}
