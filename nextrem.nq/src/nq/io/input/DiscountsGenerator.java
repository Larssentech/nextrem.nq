// (c) 2006-2007 NextremSoft

package nq.io.input;

import utilities.io.*;

public class DiscountsGenerator {

  private String[][] btdMatrix;

  public DiscountsGenerator(String fileName) {
    btdMatrix = new ArrayBuilder().makeArrayFromTSVExcludeLines(fileName, 2);
  }

  public double findStandardDiscount(String dealerCode, String countryName, String model, int standard) {
    double standardDiscount = -1.0;
    for (int i = 0; i < btdMatrix.length; i++) {
      if (btdMatrix[i][1].equals(dealerCode)) {
        if (btdMatrix[i][16].equals(countryName)) {
          if (btdMatrix[i][21].equals(model)) {
            if (standard == 0) {
              standardDiscount = Double.parseDouble(btdMatrix[i][8]);
            }
            if (standard == 1) {
              standardDiscount = Double.parseDouble(btdMatrix[i][22]);
            }
            break;
          }
        }
      }
    }
    return standardDiscount;
  }
}
