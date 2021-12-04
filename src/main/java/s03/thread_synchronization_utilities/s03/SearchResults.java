package s03.thread_synchronization_utilities.s03;

class SearchResults {

    private final int[] results;

    SearchResults(int size) {
        results = new int[size];
    }

    void set(int rowNumber, int count) {
        results[rowNumber] = count;
    }

    int[] getData() {
        return results;
    }

}
