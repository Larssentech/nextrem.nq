package utilities.string;

public class StringSorter {

  public StringSorter() {
  }

  public String[] sortNow(String[] entry) {

    int numOfEntries = entry.length;

    // This is my sorting algorithm
    String minEntry = "";
    String tempEntry = "";
    int counter = 0;
    int j = 0;
    while (counter < numOfEntries) {
      minEntry = entry[counter];
      while (j < numOfEntries) {
        if (minEntry.compareToIgnoreCase(entry[j]) > 0) {
          tempEntry = minEntry;
          minEntry = entry[j];
          entry[j] = tempEntry;
        }
        j++;
      }
      // Now we got this round's minimum
      entry[counter] = minEntry;
      counter++;
      j = counter;
    }

    // Now all addresses are sorted by domain.com
    return entry;
  }

  public String[][] sortMatrix(String[][] entry, int key) {

    int numOfEntries = entry.length;

    // This is my sorting algorithm
    String[] minEntry = new String[] {};
    String[] tempEntry = new String[] {};
    int counter = 0;
    int j = 0;
    while (counter < numOfEntries) {
      minEntry = entry[counter];
      while (j < numOfEntries) {
        if (minEntry[key].compareToIgnoreCase(entry[j][key]) > 0) {
          tempEntry = minEntry;
          minEntry = entry[j];
          entry[j] = tempEntry;
        }
        j++;
      }
      // Now we got this round's minimum
      entry[counter] = minEntry;
      counter++;
      j = counter;
    }

    // Now all addresses are sorted by domain.com
    return entry;
  }

}
