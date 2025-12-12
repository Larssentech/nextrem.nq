package utilities.io;

// Copyright (c) 2001 NextremSoft, Nextrem Communications

import java.io.*;
import java.util.*;

public class ReadFromFile {
  public String[] readFromFile(String fileName) {
    try {
      if (new ValidateFile().validateFile(fileName)) {
        BufferedReader fromFile = new BufferedReader
            (new FileReader(fileName));
        Stack stack = new Stack();
        int lineNumber = 0;
        String line;
        while ( (line = fromFile.readLine()) != null) {
          stack.push(line);
          lineNumber++;
        }
        fromFile.close();
        String[] lineInFile = new String[lineNumber];
        lineNumber--; // To end backwards in index 0
        while (lineNumber >= 0) {
          lineInFile[lineNumber] = stack.pop().toString();
          lineNumber--;
        }
        if (lineInFile.length == 0) {
          lineInFile[0] = "";
        }
        return lineInFile;
      }
      else {
        String[] error = {
            "Error"};
        return error;
      }
    }
    catch (Exception e) {
      String[] except = {
          "Error: " + e.toString()};
      return except;
    }
  }
}
