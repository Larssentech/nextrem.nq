package utilities.string;

// (c) 2006 NextremSoft

import java.util.*;

public class StringManipulationToolkit {
  public static void main(String[] args) {
    new StringManipulationToolkit().replaceAll("Nextrem-Soft- (c) -2006", "-", "");
  }

  public StringManipulationToolkit() {
  }

  public String processP100(String discount) {
    try {
      float discountFloat = Math.round(10 * Double.parseDouble(discount)) / 10F;
      if (discountFloat > 100) {
        discountFloat = 100;
      }
      discount = "" + discountFloat;
    }
    catch (Exception e) {
      discount = "0.0";
    }
    return discount;
  }

  public String processQuantity(String quantity) {
    try {
      int quantityInt = Integer.parseInt(quantity);
      if (quantityInt > 10000) {
        quantityInt = 10000;
      }
      quantity = "" + quantityInt;
    }
    catch (Exception e) {
      quantity = "1";
    }
    return quantity;
  }

  public String getAlphaNumericString(String text) {
    String finalString = "";
    if (text != null) {
      for (int i = 0; i < text.length(); i++) {
        if (text.substring(i, i + 1).equals(" ")) {
          finalString += text.charAt(i);
        }
        else if (Character.isLetterOrDigit(text.charAt(i))) {
          finalString += text.charAt(i);
        }
      }
    }
    return finalString;
  }

  public String insertThousandSeparator(String numberToFormat) {
    String formattedNumber = "";
    int counter = 0;
    for (int i = numberToFormat.length() - 1; i >= 0; i--) {
      counter++;
      formattedNumber += numberToFormat.charAt(i);
      if (counter % 3 == 0 && i > 0 && counter < new StringManipulationToolkit().replaceAll(numberToFormat, "-", "")
          .length()) {
        formattedNumber += ",";
      }
    }
    return this.reverseString(formattedNumber);
  }

  public String removeNumberFormatting(String numberToClean) {
    return new StringManipulationToolkit().replaceAll(numberToClean, ",", "");
  }

  private String reverseString(String string) {
    String reversedString = "";
    for (int i = string.length() - 1; i >= 0; i--) {
      reversedString += string.charAt(i);
    }
    return reversedString;
  }

  public String replaceAll(String originalString, String stringToReplace, String substituteString) {
    if (originalString != null && stringToReplace != null && substituteString != null) {
      String tempString = "";
      int start = 0;
      int charCount = 0;
      if (originalString.indexOf(stringToReplace) != -1) {
        while ( (charCount = originalString.indexOf(stringToReplace, start)) != -1) {
          tempString += originalString.substring(start, charCount);
          tempString += substituteString;
          start = charCount + stringToReplace.length();
        }
        tempString += originalString.substring(start, originalString.length());
        return tempString;
      }
      else {
        return originalString;
      }
    }
    else {
      return "";
    }
  }

  public String createCSVFromArrayList(ArrayList array) {
    String tempString = "";
    for (int i = 0; i < array.size() - 1; i++) {
      tempString += array.get(i) + ",";
    }
    if (array.size() > 0) {
      tempString += array.get(array.size() - 1);
    }
    return tempString;
  }

  public ArrayList createArrayListFromCSV(String csvString) {
    StringTokenizer st = new StringTokenizer(csvString, ",");
    ArrayList aList = new ArrayList();
    for (int i = 0; st.hasMoreTokens(); i++) {
      aList.add(i, st.nextToken());
    }
    return aList;
  }

  public String[] createArrayFromVector(Vector vector) {
    String[] array = new String[vector.size()];
    for (int i = 0; i < vector.size(); i++) {
      array[i] = vector.elementAt(i).toString();
    }
    return array;
  }

  public String[] createArrayFromArrayList(ArrayList arrayList) {
  String[] array = new String[arrayList.size()];
  for (int i = 0; i < arrayList.size(); i++) {
    array[i] = arrayList.get(i).toString();
  }
  return array;
}


  public Vector createVectorFromArray(String[] array) {
    Vector v = new Vector();
    for (int i = 0; i < array.length; i++) {
      v.addElement(array[i]);
    }
    return v;
  }

  public Hashtable[] createHashtablesFrom2DArray(String[][] a2DArray) {
    Hashtable[] myHashtableArray = new Hashtable[a2DArray.length - 1];
    for (int i = 1; i < a2DArray.length; i++) {
      myHashtableArray[i - 1] = new Hashtable();
      for (int j = 0; j < a2DArray[i].length; j++) {
        myHashtableArray[i - 1].put(a2DArray[0][j], a2DArray[i][j]);

      }
    }
    return myHashtableArray;
  }

  public String[][] create2DArrayFromArrayList(ArrayList arrayList) {
    String[][] finalArray = new String[arrayList.size()][];
    Iterator it = arrayList.iterator();
    for (int i = 0; i < finalArray.length; i++) {
      finalArray[i] = (String[]) it.next();
    }
    return finalArray;
  }

  public String[][] create2DArrayFromVector(Vector vector) {
    String[][] array = new String[vector.size()][];
    for (int i = 0; i < vector.size(); i++) {
      String[] thisLine = new String[ ( (String[]) vector.elementAt(i)).length];
      for (int j = 0; j < thisLine.length; j++) {
        thisLine[j] = ( (String[]) vector.elementAt(i))[j];
      }
      array[i] = thisLine;
    }
    return array;
  }

  public int[] createIntArrayFromArray(String[] array) {
    int[] intArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      int thisInt;
      try {
        thisInt = Integer.parseInt(array[i]);
      }
      catch (Exception e) {
        thisInt = -1;
      }
      intArray[i] = thisInt;
    }
    return intArray;
  }
}
