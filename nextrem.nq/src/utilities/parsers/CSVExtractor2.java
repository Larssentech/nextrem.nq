package utilities.parsers;

// Copyright (c) 2003 NextremSoft, Nextrem Communications

import java.util.*;

import utilities.string.*;

public class CSVExtractor2 {

  public static void main(String[] args) {
    new CSVExtractor2().extractValuesFrom("\"String\",String,\"St,ring\",String");
  }

  public CSVExtractor2() {}

  public String[] extractValuesFrom(String line) {
    line = new StringManipulationToolkit().replaceAll(line, "\"\"", "'");
    String[] fieldName;
    try {
      Stack stack = new Stack();
      int counter = 0, end = 0, start = 0;
      while (end < line.length()) {

        if (line.indexOf("\"", start) == start) {
          end = line.indexOf("\"", start + 1) + 1; // After the closing " which is either before a , or EO line
          stack.push(line.substring(start + 1, end - 1));
        }
        else {
          // EO line...
          if ( (end = line.indexOf(",", start)) == -1) {
            stack.push(line.substring(start, line.length()));
            counter++;
            break;
          }
          stack.push(line.substring(start, end));
        }
        start = end + 1;
        counter++;
      }
      fieldName = new String[counter];
      counter = counter - 1; // To be able to reach String[0]
      while (counter >= 0) {
        fieldName[counter] = stack.pop().toString();
        counter--;
      }
      return fieldName;
    }
    catch (Exception e) {
      fieldName = new String[1];
      fieldName[0] = "Error. Process was aborted.";
      System.out.println(e);
      return fieldName;
    }
  }
}
