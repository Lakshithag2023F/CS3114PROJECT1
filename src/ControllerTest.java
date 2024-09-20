import student.TestCase;
import java.io.*;

// -------------------------------------------------------------------------
/**
 * this class tests the controller.java class
 * 
 * @author laksh
 * @version Sep 19, 2024
 */
public class ControllerTest extends TestCase {
    /*
     * initializes COntroller controller
     */
    private Controller controller;
    /*
     * sets up ByteArrayOutputStream
     */
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    /**
     * called before each test method
     * initializes a new controller of size 10 and prints stream
     */
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        controller = new Controller(10);
    }


    /**
     * Testing insert song and artist
     */
    public void testInsertions() {
        controller.insert("Artist1", "Song1");
        controller.insert("Artist2", "Song2");
        controller.insert("Artist3", "Song3");

        String expectedOutput = "|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.\n"
            + "|Artist2| is added to the Artist database.\n"
            + "|Song2| is added to the Song database.\n"
            + "|Artist3| is added to the Artist database.\n"
            + "|Song3| is added to the Song database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Testing duplicating insert method
     */
    public void testDuplicateInsertion() {
        controller.insert("Artist1", "Song1");
        controller.insert("Artist1", "Song1"); // Duplicate

        String expectedOutput = "|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.\n"
            + "|Artist1<SEP>Song1| duplicates"
            + " a record already in the database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Testing insert an existing artist with a new song
     */
    public void testInsertExistingArtistNewSong() {
        controller.insert("Artist1", "Song1");
        controller.insert("Artist1", "Song4"); // New song with existing artist

        String expectedOutput = "|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.\n"
            + "|Song4| is added to the Song database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Testing insert new artist with an existing song
     */
    public void testInsertNewArtistExistingSong() {
        controller.insert("Artist1", "Song1");
        controller.insert("Artist4", "Song1"); // New artist with existing song

        String expectedOutput = "|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.\n"
            + "|Artist4| is added to the Artist database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Testing remove artist
     */
    public void testRemoveArtist() {
        controller.insert("Artist2", "Song2");
        controller.removeArtist("Artist2");
        controller.removeArtist("Artist2"); // Attempt to remove again

        String expectedOutput = "|Artist2| is added to the Artist database.\n"
            + "|Song2| is added to the Song database.\n"
            + "|Artist2| is removed from the Artist database.\n"
            + "|Artist2| does not exist in the Artist database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Testing remove song
     */
    public void testRemoveSong() {
        controller.insert("Artist3", "Song3");
        controller.removeSong("Song3");
        controller.removeSong("Song3"); // Attempt to remove again

        String expectedOutput = "|Artist3| is added to the Artist database.\n"
            + "|Song3| is added to the Song database.\n"
            + "|Song3| is removed from the Song database.\n"
            + "|Song3| does not exist in the Song database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Removing a non existent artist
     */
    public void testRemoveNonExistentArtist() {
        controller.removeArtist("NonExistentArtist");

        String expectedOutput =
            "|NonExistentArtist| does not exist in the Artist database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Removing a non existent song
     */
    public void testRemoveNonExistentSong() {
        controller.removeSong("NonExistentSong");

        String expectedOutput =
            "|NonExistentSong| does not exist in the Song database.\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    /**
     * Tests print artist
     */
    public void testPrintArtist() {
        controller.insert("Artist1", "Song1");
        controller.printArtist();

        String expectedOutput = "|Artist1| is added to the Artist database.\n"
            + "|Song1| is added to the Song database.\n" + "0: |Artist1|\n"
            + "total artists: 1\n";

        assertEquals(expectedOutput, outContent.toString());

    }

}
