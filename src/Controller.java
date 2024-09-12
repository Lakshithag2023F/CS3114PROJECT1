public class Controller {
    //~ Fields ................................................................
    private Hash artist;
    private Hash song;
    private Graph fullGraph;
    //~ Constructors ..........................................................

    public Controller(int hashSize) {
        artist = new Hash(hashSize);
        song = new Hash(hashSize);
        fullGraph = new Graph(graphSize);
    }
    //~Public  Methods ........................................................

}
