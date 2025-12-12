package nq.io.input;

/**
 * <p>Title: UnitBuilder</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: NextremSoft</p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;

import nq.orchestrating.*;
import nq.transaction.*;
import utilities.io.*;
import nq.sales.*;

public class CSVImporter {

  private NextremQuote nq;
  private ArrayList rejectedCodes;
  private User user;

  // # We need to pass this class constructor the User that initiates the import
  // # from the GUI so that the Orchestrating class knows where to add the
  // # imported trucks
  public CSVImporter(NextremQuote nq, User user) {
    this.user = user;
    this.nq = nq;
    this.rejectedCodes = new ArrayList();

    System.out.println();
    System.out.print("Import module loading...");
  }

  public ArrayList importFromFile(String fileName) {
    System.out.println("\tOK");

    // Read the file codes into an array
    String[][] codes = new ArrayBuilder().makeArrayFromCSVExcludeLines(fileName, 1);

    // Request the catalog be built for this series
    nq.generateOptionCatalogForCode(codes[0][0]);

    // Add the unit to the basket from the series code (row 0, col 0)
    String parentId = nq.addUnitToBasket(this.user, codes[0][0], true);
    System.out.println("Import module added unit series: " + codes[0][0] + ", ID=" + parentId);

    // Add all the options to the basket, storing the rejected ones.
    // Option codes are always preceded by the series code
    for (int i = 0; i < codes.length; i++) {
      // Precede the unit code to the option code if not already there
      String fullCode = codes[i][1];
      if(codes[i][1].startsWith(codes[0][0])) fullCode = codes[i][1];
      TransactionPack t = nq.addOptionToUnit(this.user, fullCode, parentId, true, true);

      // What happened?
      System.out.print("TransactionPack log: " + t.transactionLog());

      // Store the rejects
      if (!t.transactionCompleted()) {
        this.rejectedCodes.add(codes[i][1]);
      }
    }

    // Return the rejected codes
    return this.rejectedCodes;
  }

}
