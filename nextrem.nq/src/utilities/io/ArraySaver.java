package utilities.io;

import java.io.*;

public class ArraySaver {
  public ArraySaver() {}

  public boolean saveArrayToCSVFile(String[][] array, String fileName) {
    try {
      PrintWriter toFile = new PrintWriter(new FileWriter(fileName));
      for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length - 1; j++) {
          toFile.print("\"" + array[i][j] + "\"" + ",");
        }
        toFile.print("\"" + array[i][array[i].length - 1] + "\"" + "\n");
      }
      toFile.close();
      return true;
    }
    catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }
}
