package utilities.settings;

// (c) 2006 NextremSoft
import java.util.*;

import utilities.io.*;

public class Settings2HashtableConverter {
  public Settings2HashtableConverter() {
  }

  public Hashtable convertFile2Hashtable(String fileName) {
    Hashtable myHashtable = new Hashtable();
    String[] requiredDat = new ReadFromFile().readFromFile(fileName);
    for (int i = 1; i < requiredDat.length; i++) {
      String key = requiredDat[i].substring(0, requiredDat[i].indexOf("="));
      String value = requiredDat[i].substring(requiredDat[i].indexOf("=") + 1, requiredDat[i].length());
      myHashtable.put(key, value);
    }
    return myHashtable;
  }
}
