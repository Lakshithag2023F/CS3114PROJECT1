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
        setTombstone(new Record(null, null, null));
    }

    // public methods


    public int getNumberOfRecords() {
        return numberOfRecords;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return all records
     */
    public Record[] getAllRecords() {
        return allRecords;
    }



    public void insert(Record record) {
        // Check if insertion would exceed 50% capacity
        if (numberOfRecords + 1 > allRecords.length / 2) {
            rehash(); // Perform rehashing if needed

            // Determine the type of the record and print the appropriate
            // message
            String type = record.getType();
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

        int home; // Home position for e
        String key = record.getKey();
        int pos = home = h(key, allRecords.length); // Initial hash position

        // Handle collisions with quadratic probing
        for (int i = 0; allRecords[pos] != null
            && allRecords[pos] != tombstone; i++) {
            if (key.equals(allRecords[pos].getKey())) {
                return; // Record with the same key already exists, no insertion
                        // needed
            }
            pos = (home + (i * i)) % allRecords.length;
        }

        allRecords[pos] = record;
        numberOfRecords++;
    }



    private void rehash() {
        Record[] oldRecords = allRecords;

        allRecords = new Record[oldRecords.length * 2];
        numberOfRecords = 0;

        for (Record record : oldRecords) {
            if (record != null && record != getTombstone()) {
                insert(record); // Reinsert each existing record using the
                                // updated insert method

            }
        }

    }


    /**
     * Removes record
     * 
     * @param key
     */

    public void remove(String key) {
        int home = h(key, allRecords.length);
        int pos = home;

        for (int i = 0; allRecords[pos] != null && i < allRecords.length; i++) {
            if (allRecords[pos] != tombstone && key.equals(allRecords[pos]
                .getKey())) {
                allRecords[pos] = tombstone;
                numberOfRecords--;
                return;
            }
            pos = (home + (i * i)) % allRecords.length;
        }
        // Key not found; you may print a message or handle it as needed
    }



    public int find(String key) {
        int home = h(key, allRecords.length);
        int pos = home;

        for (int i = 0; allRecords[pos] != null && i < allRecords.length; i++) {
            if (allRecords[pos] != tombstone && key.equals(allRecords[pos]
                .getKey())) {
                return pos;
            }
            pos = (home + (i * i)) % allRecords.length;
        }
        return -1; // Key not found
    }




    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param value
     * @return record
     */
    public Record getRecord(String value) {
        int pos = find(value);
        if (pos == -1) {
            return null;
        }
        return allRecords[pos];
    }


    /**
     * Prints hash table contents
     */
    public void print() {
        for (int i = 0; i < allRecords.length; i++) {
            if (allRecords[i] != null && allRecords[i] != getTombstone()) {
                System.out.println(i + ": |" + allRecords[i].getKey().trim()
                    + "|");
            }
            else if (allRecords[i] == getTombstone()) {
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


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public Record getTombstone() {
        return tombstone;
    }


    public void setTombstone(Record tombstone) {
        this.tombstone = tombstone;
    }

}
