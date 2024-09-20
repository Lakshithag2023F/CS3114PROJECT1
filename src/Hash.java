/**
 * Hash table class
 * 
 * This class manages hash table data structure.
 * Hash table retrieves data using key-value pair system.
 * Manages how data is inserted, retrieved and removed from this table
 *
 * @author Shruti & Lakshitha
 * @version 9/19/24
 */

public class Hash {

    /**
     * private fields
     */
    private Record[] allRecords; // array which stores records
    private int numberOfRecords; // number of records
    private Record tombstone; // represents deleted record

    /**
     * Constructor method
     * 
     * @param size
     *            is the initial size of the hash table
     */
    public Hash(int size) {
        this.allRecords = new Record[size];
        this.numberOfRecords = 0;
        this.tombstone = (new Record(null, null, null));
    }


    /**
     * Get method for the number of records in tale
     * 
     * @return number of methods
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }


    /**
     * Get method for array that stores records
     * 
     * @return all records
     */
    public Record[] getAllRecords() {
        return allRecords;
    }


    /**
     * Get method to return the tombstone
     * 
     * @return the tombstone
     */
    public Record getTombstone() {
        return tombstone;
    }


    /**
     * Retrieves a record from hash based on its key
     * 
     * @param value
     * @return the record
     */
    public Record getRecord(String value) {
        int pos = find(value);
        if (pos == -1) { // record not found
            return null;
        }
        return allRecords[pos];
    }


    /**
     * Inserts a record into the hash
     * 
     * @param record
     *            is the record to be inserted
     */
    public void insert(Record record) {
        // Check if insertion greater than 50% capacity
        if (numberOfRecords + 1 > allRecords.length / 2) {
            rehash(); // Rehash if needed

            String type = record.getType(); // determines if song or artist
            if ("song".equals(type)) {
                System.out.println("Song hash table size doubled.");
            }
            else if ("artist".equals(type)) {
                System.out.println("Artist hash table size doubled.");
            }
            else {
                System.out.println("Unknown type hash table size doubled.");
            }
        }

        int home; // home position
        String key = record.getKey();
        int pos = home = h(key, allRecords.length); // initial hash position

        // Handles collisions
        for (int i = 0; allRecords[pos] != null
            && allRecords[pos] != tombstone; i++) {
            if (key.equals(allRecords[pos].getKey())) {
                return; // Record with the same key already exists so no
                        // insertion is needed
            }
            pos = (home + (i * i)) % allRecords.length; // Quadratic probing
        }

        allRecords[pos] = record; // record inserted
        numberOfRecords++;
    }


    /**
     * Doubles size of hash table and adds all records from old hash to new one
     */
    private void rehash() {
        Record[] oldRecords = allRecords;
        allRecords = new Record[oldRecords.length * 2]; // increase length
        numberOfRecords = 0;

        for (Record record : oldRecords) {
            if (record != null && record != tombstone) {
                insert(record); // Reinsert each existing record using the
                                // insert method
            }
        }
    }


    /**
     * Removes record from hash
     * 
     * @param key
     *            from record
     */
    public void remove(String key) {
        int home = h(key, allRecords.length);
        int pos = home;

        for (int i = 0; allRecords[pos] != null && i < allRecords.length; i++) {
            if (allRecords[pos] != tombstone && key.equals(allRecords[pos]
                .getKey())) {
                allRecords[pos] = tombstone; // deleted record
                numberOfRecords--;
                return;
            }
            pos = (home + (i * i)) % allRecords.length;
        }
    }


    /**
     * Finds record in hash table based on key
     * 
     * @param key
     *            is the record key
     * 
     * @return position or -1 if not found
     */
    public int find(String key) {
        int home = h(key, allRecords.length);
        int pos = home;

        for (int i = 0; allRecords[pos] != null && i < allRecords.length; i++) {
            if (allRecords[pos] != tombstone && key.equals(allRecords[pos]
                .getKey())) {
                return pos;
            }
            pos = (home + (i * i)) % allRecords.length; // Quadratic probing
        }
        return -1; // Key not found
    }


    /**
     * Prints hash table contents
     */
    public void print() {
        for (int i = 0; i < allRecords.length; i++) {
            if (allRecords[i] != null && allRecords[i] != tombstone) {
                System.out.println(i + ": |" + allRecords[i].getKey().trim()
                    + "|");
            }
            else if (allRecords[i] == tombstone) {
                System.out.println(i + ": TOMBSTONE");
            }
        }
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
