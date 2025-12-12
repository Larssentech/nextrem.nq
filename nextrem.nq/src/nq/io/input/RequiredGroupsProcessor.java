// (c) 2006-2007 NextremSoft

package nq.io.input;

import java.util.*;

import utilities.io.*;
import utilities.parsers.*;

public class RequiredGroupsProcessor {
  public RequiredGroupsProcessor() {
  }

  public Hashtable processRequiredGroups(String requiredFileName) {

    String[][] required = new ArrayBuilder().makeArrayFromTSVExcludeLines(requiredFileName, 1);

    Hashtable requiredHashtable = new Hashtable();

    for (int i = 0; i < required.length; i++) {
      String[] thisArray = required[i];
      String[] thisRequired = new String[thisArray.length - 1];
      String[] sortedRequiredArray = new String[thisArray.length - 1];

      for (int j = 0; j < thisRequired.length; j++) {
        thisRequired[j] = thisArray[j + 1];

        // If there are commas take the value right after the comma and use it to sort the
        // required groups array into a temp array
        if (thisRequired[j].indexOf(",") != -1) {
          String[] thisGroupsData = new CSVExtractor2().extractValuesFrom(thisRequired[j]);
          thisRequired[j] = thisGroupsData[0];
          int thisGroupsIndex = 0;
          try {
            thisGroupsIndex = Integer.parseInt(thisGroupsData[1]) - 1;
          }
          // If some have the index and some don't, quit, corrupted required.dat
          catch (Exception e) {
            System.out.println("Error parsing index of required groups. Look at source file 'required.dat': " + e);
            System.exit( -1);
          }
          sortedRequiredArray[thisGroupsIndex] = thisRequired[j];
        }

        // If nothing comes after the group, ignore (as it will be an empty string

      }

      // Pad the nulls in the sorted array
      for (int k = 0; k < sortedRequiredArray.length; k++) {
        if (sortedRequiredArray[k] == null) {
          sortedRequiredArray[k] = "";

          // Finally put in hashtable
        }
      }
      requiredHashtable.put(required[i][0], sortedRequiredArray);
    }
    return requiredHashtable;
  }
}
