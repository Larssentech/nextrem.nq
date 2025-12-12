package utilities.io;

import java.io.*;
import java.util.*;

import utilities.parsers.*;
import utilities.string.*;

public class ArrayBuilder {

  public ArrayBuilder() {}

  public String[][] makeArrayFromCSVExcludeLines(String fileName, int rowsToExclude) {
    CSVExtractor2 cEx = new CSVExtractor2();
    String[][] finalArray;
    try {
      BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
      finalArray = new String[new LineCounter().countLinesFrom(fileName) - rowsToExclude][];

      // Read away the undesired lines...
      for (int i = 0; i < rowsToExclude; i++) {
        fromFile.readLine();
        // Store the rest
      }
      for (int i = 0; i < finalArray.length; i++) {
        String st = fromFile.readLine();
        //System.out.println(st);
        finalArray[i] = cEx.extractValuesFrom(st);
      }

      fromFile.close();
      return finalArray;
    }
    catch (Exception e) {
      System.out.println("ArrayBuilder: " + e);
      return finalArray = new String[0][];
    }
  }

  public String[][] makeArrayFromTSVExcludeLines(String fileName, int rowsToExclude) {
    TSVExtractor tEx = new TSVExtractor();
    String[][] finalArray;
    try {
      BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
      finalArray = new String[new LineCounter().countLinesFrom(fileName) - rowsToExclude][];

      // Read away the undesired lines...
      for (int i = 0; i < rowsToExclude; i++) {
        fromFile.readLine();
        // Store the rest
      }
      for (int i = 0; i < finalArray.length; i++) {
        finalArray[i] = tEx.extractFrom(fromFile.readLine());
      }

      fromFile.close();
      return finalArray;
    }
    catch (Exception e) {
      System.out.println("ARRAY BUILDER: " + e);
      return finalArray = new String[0][];
    }

  }

  public String[][] makeArrayFromTSVFilterBy(String fileName, String filterString, int filterPosition) {
    TSVExtractor tEx = new TSVExtractor();
    ArrayList arrayList;

    try {
      BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
      arrayList = new ArrayList();

      // Store the first line
      String[] thisLine = tEx.extractFrom(fromFile.readLine());
      arrayList.add(thisLine);

      int lines = new LineCounter().countLinesFrom(fileName);

      for (int i = 1; i < lines; i++) {
        thisLine = tEx.extractFrom(fromFile.readLine());
        // ...only if the element [x] is the filter string
        if (thisLine[filterPosition].equals(filterString)) {
          arrayList.add(thisLine);
        }
      }

      fromFile.close();
      System.out.print(" > " + arrayList.size() + " filtered records");
      return new StringManipulationToolkit().create2DArrayFromArrayList(arrayList);
    }
    catch (Exception e) {
      System.out.println("ARRAY BUILDER: " + e);
      return new String[0][];
    }

  }

}
