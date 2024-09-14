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


    public Record[] getAllRecords() {
        return allRecords;
    }


    // Insert e into hash table HT
    public void insert(Record record) {
        if (numberOfRecords >= allRecords.length / 2) {
            rehash(); // Perform rehashing if needed
        }
        int home; // Home position for e
        String key = record.getKey();
        int pos = home = h(key, allRecords.length); // Init probe sequence
        for (int i = 1; null != allRecords[pos]
            || tombstone == allRecords[pos]; i++) {
            if (key == allRecords[pos].getKey()) {
                return;
            }
            pos = (home + (i * i)) % allRecords.length; // probe
        }
        allRecords[pos] = record;
        numberOfRecords++;
    }


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
            if (record != null && record != tombstone) {
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
        int index = find(key);
        if (index != -1) {
            allRecords[index] = tombstone;
            numberOfRecords--;
        }
    }


    public int find(String key) {
        int home; // Home position for K
        int pos = home = h(key, allRecords.length); // Initial position is the
                                                    // home slot
        for (int i = 1; (key != allRecords[pos].getKey())
            && (null != allRecords[pos]); i++) {
            pos = (home + i * i) % allRecords.length; // Next on probe sequence
        }
        if (key == (allRecords[pos]).getKey()) { // Found it
            return pos;
        }
        else {
            return -1;
        } // K not in hash table
    }
    /**
     * Find record index with its key
     * 
     * @param key
     * @return integer value
     */
// public int find(String key) {
// int index = h(key, allRecords.length);
// while (allRecords[index] != null) {
// if (allRecords[index] != tombstone && allRecords[index].getKey()
// .equals(key)) {
// return index;
// }
// index = (index + 1) % allRecords.length;
//
// }
//
// return -1;
// }


    /**
     * Prints hash table contents
     */
    public void print() {
        for (int i = 0; i < allRecords.length; i++) {
            if (allRecords[i] != null && allRecords[i] != tombstone) {
                System.out.println("Index " + i + ":" + allRecords[i].getKey());
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
