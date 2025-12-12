package utilities.parsers;

import java.util.*;

import utilities.string.*;

public class TSVExtractor {
  public TSVExtractor() {}

  public String[] extractFrom(String line) {
    if (line.length() > 0) {
      String[] fieldName;
      try {
        Stack stack = new Stack();
        String thisField;
        int a = 0, b = 0;
        while ( (a = line.indexOf("\t", b)) != -1) {
          // So field[i] will be either in this format: '"name"' or just 'name'
          // Remove all " occurrences
          if ( (thisField = line.substring(b, a)).indexOf("\"") != -1) {
            thisField = new StringManipulationToolkit().replaceAll(thisField, "\"", "");
          }
          stack.push(thisField);
          b = a + 1;
        }
        // For the last one
        thisField = line.substring(b, line.length());

        if (thisField.indexOf("\"") != -1) {
          thisField =
              new StringManipulationToolkit().replaceAll(thisField, "\"", "");
        }
        stack.push(thisField);

        // Dump all entries into a String[]
        fieldName = new String[stack.size()];
        while (stack.size() > 0) {
          fieldName[stack.size() - 1] = stack.pop().toString();
        }
        return fieldName;
      }
      catch (Exception e) {
        fieldName = new String[] {
            "Fatal error in TSV extraction: no TSV format?"};
        System.out.println(e);
        return fieldName;
      }
    }
    else {
      return new String[] {
          ""};
    }
  }

  public static void main(String[] args) {
    String[] fieldNames = new TSVExtractor().extractFrom(
        "Date	Name	Email	Job_title	Reference	Advertised_in	Advertised_on_date");
    for (int i = 0; i < fieldNames.length; i++) {
      System.out.println(fieldNames[i]);
    }
  }
}
