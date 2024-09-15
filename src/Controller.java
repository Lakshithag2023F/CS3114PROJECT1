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
        artistThere = false;
        songThere = false;
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
            artistName = artistName.trim();
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
            artistThere = true;
        }
        else {
            artistThere = true;
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
            songThere = true;
        }
        else {
            songThere = true;
        }
      
        if (artistThere && songThere) {
            if (fullGraph.hasEdge(artistNode, songNode)) {
                artistName = artistName.trim();
                System.out.println("|" + artistName + "<SEP>" + songName
                    + "| duplicates a record already in the database.");
            }
            else {
                return;
            }
        }

    }



    public void removeArtist(String artistName) {
        

        int artistIndex = artist.find(artistName);
        if (artistIndex == -1) // artist isnt in there 
        {
            System.out.println("|" + artistName.trim()
                + "| does not exist in the Artist database.");
            //print |ma rainey| does not exist in the Artist database.
        }
        else
        {
            artist.remove(artistName);
            System.out.println("|" + artistName
                + " is removed from the Artist database.");
            //|Ma Rainey| is removed from the Artist database.
        }
        

    }


    public void removeSong(String songName) {
        int songIndex = song.find(songName);
        songName = songName.trim();
        if (songIndex == -1) // artist isnt in there 
        {
            System.out.println("|" + songName
                + "| does not exist in the Song database.");
            //print |ma rainey| does not exist in the Artist database.
        }
        else
        {
            artist.remove(songName);
            System.out.println("|" + songName
                + " is removed from the Song database.");
            //|Ma Rainey| is removed from the Artist database.
        }
        
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
