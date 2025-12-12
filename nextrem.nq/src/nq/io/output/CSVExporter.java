package nq.io.output;

import nq.catalogue.*;
import nq.sales.*;
import utilities.io.*;
import utilities.string.*;

public class CSVExporter {
  public CSVExporter() {
  }

  /** This method will export all details of an option basket to a CSV
   *  file. The option basket will be sent on behalf of a specific
   * user that we need to use to pass the name to the Option Explorer
   */
  public boolean exportBasketToCSV(User user,
                                   Option optionBasket,
                                   boolean hideParts,
                                   boolean hideCosts,
                                   String fileName) {
    return new ArraySaver().saveArrayToCSVFile
        (new StringManipulationToolkit().create2DArrayFromVector
         (new OptionExplorer().convertQuoteToCSVExportFormat
          (user, optionBasket, hideParts, hideCosts)), fileName);
  }

  /** This method will export only the unit codes and option codes of an option
   * basket to a CSV file in the format the CSVImporter class will need them.
   * This means that we can save a basket skeleton and rebuild it in the
   * future with the most recent costs and prices (as opposed to saving
   * this information in the export file).
   * The option basket will be sent on behalf of a specific user that we need
   * to use to pass the name to the Option Explorer
   */
  public boolean saveQuoteToCSV(User user, Option optionBasket, String fileName) {
    return new ArraySaver().saveArrayToCSVFile
        (new StringManipulationToolkit().create2DArrayFromVector
         (new OptionExplorer().convertQuoteToCSVSaveFormat
          (user, optionBasket)), fileName);
  }
}
