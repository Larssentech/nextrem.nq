package utilities.io;

// Copyright (c) 2001 NextremSoft, Nextrem Communications

import java.io.*;

public class SaveToFile {
  public SaveToFile() {
  }

  public boolean saveToFile(String fileName, String[] dataLine, boolean append) {
    try {
      PrintWriter toFile = new PrintWriter(new FileWriter(fileName, append));
      for (int i = 0; i < dataLine.length; i++) {
        toFile.println(dataLine[i]);
      }
      toFile.close();
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }
}
