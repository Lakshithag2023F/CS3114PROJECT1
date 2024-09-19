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


// public void insert(Record record) {
// if (numberOfRecords >= allRecords.length / 2) {
// rehash(); // Perform rehashing if needed
//
// // Determine the type of the record and print the appropriate message
// String type = record.getType();
// if ("song".equals(type)) {
// System.out.println("Song hash table size doubled.");
// } else if ("artist".equals(type)) {
// System.out.println("Artist hash table size doubled.");
// } else {
// System.out.println("Unknown type hash table size doubled."); // Fallback for
// unexpected types
// }
// }
//
// String key = record.getKey();
// int home = h(key, allRecords.length); // Initial hash position
// int pos = home; // Current position in the probe sequence
//
// System.out.println("Hash position for " + record.getType() + " '" + key + "':
// " + pos);
//
// // Handle collisions with quadratic probing
// for (int i = 1; allRecords[pos] != null && tombstone != allRecords[pos]; i++)
// {
// if (key.equals(allRecords[pos].getKey())) {
// System.out.println("Duplicate found for key '" + key + "' at position " +
// pos);
// return; // Record with the same key already exists, no insertion needed
// }
// pos = (home + (i * i)) % allRecords.length;
// System.out.println("Collision occurred, new position for '" + key + "': " +
// pos);
// }
//
// allRecords[pos] = record;
// numberOfRecords++;
// System.out.println("Inserted '" + key + "' at position " + pos);
// }
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

/*
 * public void insert(Record record) {
 * if (numberOfRecords >= allRecords.length / 2) {
 * rehash(); // Perform rehashing if needed
 * 
 * // Determine the type of the record and print the appropriate
 * // message
 * String type = record.getType();
 * if ("song".equals(type)) {
 * System.out.println("Song hash table size doubled.");
 * }
 * else if ("artist".equals(type)) {
 * System.out.println("Artist hash table size doubled.");
 * }
 * else {
 * System.out.println("Unknown type hash table size doubled."); // Fallback
 * // for
 * // unexpected
 * // types
 * }
 * }
 * 
 * int home; // Home position for e
 * String key = record.getKey();
 * int pos = home = h(key, allRecords.length); // Init probe sequence
 * 
 * // Handle collisions with quadratic probing
 * for (int i = 1; allRecords[pos] != null
 * || getTombstone() == allRecords[pos]; i++) {
 * if (key.equals(allRecords[pos].getKey())) {
 * return; // Record with the same key already exists, no insertion
 * // needed
 * }
 * pos = (home + (i * i)) % allRecords.length;
 * }
 * allRecords[pos] = record;
 * numberOfRecords++;
 * }
 */


    /**
     * Inserts new record into hash table
     * 
     * @param record
     */
// public void insert(Record record) {
//
// if (numberOfRecords>=(allRecords.length/2)) {
// rehash();
// }
//
// String key = record.getKey();
// Node node = record.getNode();
// int index = h(key, allRecords.length);
//// int hIndex = index;
// int i = 1;
//
// while (allRecords[index] != null && allRecords[index] != tombstone)
// {
// if (!allRecords[index].getKey().equals(key))
// {
// return;
// }
//
// // index = (index + 1) % allRecords.length;
// index = (index + (i * i)) % allRecords.length;
// i++;
// }
//
// //should it be this line
// //allRecords[index] = record;
// //or should it be this line
// allRecords[index]= new Record(key, node);
//
// numberOfRecords++;
//
// }

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

// private void rehash() {
// Record[] oldRecords = allRecords;
// allRecords = new Record[oldRecords.length * 2];
// numberOfRecords = 0; // Reset number of records
//
// for (Record record : oldRecords) {
// if (record != null && record != tombstone) {
// System.out.println("Rehashing record: " + record.getKey());
// insert(record); // Reinsert each existing record
// }
// }
// }


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

/*
 * public void remove(String key) {
 * 
 * int index = h(key, allRecords.length);
 * int temp = index;
 * 
 * for (int i = 1; !allRecords[temp].getKey().equals(key); i++)
 * {
 * temp = (index + (i * i)) % allRecords.length;
 * }
 * allRecords[temp] = getTombstone();
 * allRecords[temp].setKey("Tombstone");
 * 
 * numberOfRecords--;
 * 
 * }
 */
// public void remove(String key) {
//
// int index = h(key, allRecords.length);
// int temp = index;
//
// // Use quadratic probing to find the key
// for (int i = 1; allRecords[temp] != null && !allRecords[temp].getKey()
// .equals(key); i++) {
// temp = (index + (i * i)) % allRecords.length; // Update temp with
// // quadratic probing
// }
//
// // Check if the key is found and not null
// if (allRecords[temp] != null && allRecords[temp].getKey().equals(key)) {
// allRecords[temp].setKey("TOMBSTONE");
// allRecords[temp] = tombstone;
// numberOfRecords--;
// }
// }

    // int index = find(key);
    // if (int i = 1, index != -1)
// {
// allRecords[index] = tombstone;
// numberOfRecords--;
// }


// public int find(String key) {
// int home; // Home position for K
// int pos = home = h(key, allRecords.length); // Initial position is the
// // home slot
// for (int i = 1; (null != allRecords[pos]); i++) {
// if (key != allRecords[pos].getKey()) {
//
// pos = (home + i * i) % allRecords.length; // Next on probe
// // sequence
// }
// else {
// break;
// }
// }
// if (key == (allRecords[pos]).getKey()) { // Found it
// return pos;
// }
// else {
// return -1;
// } // K not in hash table
// }
    /**
     * Find record index with its key
     * 
     * @param key
     * @return integer value
     */
    /*
     * public int find(String key) {
     * int index = h(key, allRecords.length);
     * int temp = index;
     * 
     * // Improved for loop with added safety check to avoid infinite loops
     * for (int i = 1; allRecords[temp] != null
     * && allRecords[temp] != getTombstone() && i < allRecords.length; i++) {
     * if (allRecords[temp] != getTombstone() && allRecords[temp].getKey()
     * .equals(key)) {
     * return temp;
     * }
     * 
     * // Update temp using quadratic probing
     * temp = (index + (i * i)) % allRecords.length;
     * 
     * // Break if we've looped back to the starting index
     * if (temp == index) {
     * break;
     * }
     * }
     * // skip tombstone instead of ignoring
     * 
     * return -1;
     * }
     */

    public int find(String key) {
        int home = h(key, allRecords.length);
        int pos = home;

        for (int i = 0; allRecords[pos] != null && allRecords[pos] != tombstone
            && i < allRecords.length; i++) {
            if (allRecords[pos] != tombstone && key.equals(allRecords[pos]
                .getKey())) {
                return pos;
            }
            pos = (home + (i * i)) % allRecords.length;
        }
        return -1; // Key not found
    }

// public int find(String key) {
//
// int index = h(key, allRecords.length);
// int temp = index;
// int i = 1;
//
// while (allRecords[temp] != null && i < allRecords.length)
// {
// if (allRecords[temp] != tombstone && allRecords[temp].getKey()
// .equals(key)) {
// return temp;
// }
// // index = (index + 1) % allRecords.length;
//
// temp = (index + (i * i)) % allRecords.length;
// i++;
// }
//
// return -1;
// }


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
