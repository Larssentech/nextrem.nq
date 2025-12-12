package utilities.io;

import java.io.*;

public class LineCounter {
  public LineCounter() {
  }

  public int countLinesFrom(String fileName) {
    try {
      BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
      int lines = 0;
      while (fromFile.readLine() != null) {
        lines++;
      }
      fromFile.close();
      return lines;
    }
    catch (IOException e) {
      return 0;
    }
  }
}
