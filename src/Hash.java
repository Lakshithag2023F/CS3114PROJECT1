/**
 * Hash table class
 * 
 * this class manages hash table data structure. hash table retrieves data using
 * key-value pair system.
 * manages how data is inserted, retrieved and removed from this table
 *
 * @author <Put Something Here>
 * @version <Put Something Here>
 */

public class Hash {
    private Record[] allRecords; // array which stores records
    private boolean whichTable; // artist or song table
    private int numberOfRecords; // number of records
    private Record tombstone; // deleted records

    /**
     * Constructor
     * 
     * @param size
     *            is the initial size of the hash table
     */
    public Hash(int size) {
        allRecords = new Record[size];
        numberOfRecords = 0;
        tombstone = new Record(null, null);
    }


    // public methods
    /**
     * Inserts new record into hash table
     * 
     * @param key
     * @param value
     */
    public void insert(String key, Node value) {
        // implement
    }


    /**
     * Removes record
     * 
     * @param key
     */
    public void remove(String key) {
        // implement
    }


    /**
     * Find record index with its key
     * 
     * @param key
     * @return integer value
     */
    public int find(String key) {
        // implement

        return 0;
    }


    /**
     * Prints hash table contents
     */
    public void print() {
        // implement
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
