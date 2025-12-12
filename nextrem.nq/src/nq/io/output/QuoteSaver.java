// (c) 2006-2007 NextremSoft

package nq.io.output;

import java.io.*;
import java.util.*;

import nq.catalogue.*;
import utilities.io.*;
import utilities.string.*;

public class QuoteSaver {

  private boolean libraryPathExists = false;
  private final String sep = System.getProperty("file.separator");
  private String previousSavePath = "", previousSaveFileName = "";

  public QuoteSaver() {
  }

  public void previousSavePath(String s) {
    this.previousSavePath = s;
  }

  public void previousSaveFileName(String s) {
    this.previousSaveFileName = s;
  }

  public boolean libraryPathExists() {
    return libraryPathExists;
  }

  public void libraryPathExists(boolean b) {
    this.libraryPathExists = b;
  }

  /**
   * This will save all options in the unit to a file, and each file will represent only one option.
   * Each file will also contain information about the options it has
   * @return boolean
   */
  public String saveQuoteToFile(Option optionBasket, String libraryPath, String libraryName, String version) {
    String savePath = previousSavePath;
    if (!this.previousSaveFileName.equals(libraryName)) {
      savePath = this.processDirectory4(optionBasket, libraryPath, libraryName);
      this.previousSaveFileName = libraryName;
      this.previousSavePath = savePath;
    }
    libraryPathExists = new SaveToFile().saveToFile(savePath + sep + libraryName + ".quote",
        new String[] {"Created=" + new Date()}
        , true);
    this.saveOptionToLibrary(optionBasket, savePath, version);
    return new File(savePath + sep + libraryName + ".quote").toString();
  }

  private void saveOptionToLibrary(Option quoteToSave, String savePath, String version) {
    this.saveOption(quoteToSave, savePath, version);
    for (int i = 0; i < quoteToSave.options().length; i++) {
      // make all daughter options inherit the customer, country and dealer of its parent
      this.saveOptionToLibrary(quoteToSave.options()[i], savePath, version);
    }
  }

  private boolean saveOption(Option optionToSave, String libraryPath, String version) {
    boolean optionSaved = false;
    String fileName = libraryPath + sep + optionToSave.id();
    // Prepare the daughters string as CSV with no delimiter
    String options = "";
    for (int i = 0; i < optionToSave.options().length - 1; i++) {
      options += (optionToSave.options()[i].id() + ",");
    }
    if (optionToSave.options().length > 0) {
      options += optionToSave.options()[optionToSave.options().length - 1].id();
      // Prepare the dependents string as CSV with no delimiter
    }
    String dependencies = "";
    for (int i = 0; i < optionToSave.requiredOptions().length - 1; i++) {
      dependencies += (optionToSave.requiredOptions()[i].getCode() + ",");
    }
    if (optionToSave.requiredOptions().length > 0) {
      dependencies += optionToSave.requiredOptions()[optionToSave.requiredOptions().length - 1].getCode();

    }
    String[] dataLines = new String[29];
    dataLines[0] = "app_info=[DO NOT EDIT]";
    dataLines[1] = "app_name=NextremSoft NextremQuote";
    dataLines[2] = "app_version=" + version;
    dataLines[3] = "app_copy=(c) 2001-2007 NextremSoft";
    dataLines[4] = "op_name=" + optionToSave.getName();
    dataLines[5] = "op_type=" + optionToSave.type();
    dataLines[6] = "op_code=" + optionToSave.getCode();
    dataLines[7] = "op_group=" + optionToSave.getGroup();
    dataLines[8] = "op_group_desc=" + optionToSave.getGroupDesc();
    dataLines[9] = "op_ID=" + optionToSave.id();
    dataLines[10] = "op_parent_ID=" + optionToSave.parentId();
    dataLines[11] = "op_cost=" + optionToSave.cost();
    dataLines[12] = "op_list_price=" + optionToSave.listPrice();
    dataLines[13] = "op_discount_p100=" + optionToSave.discountP100();
    dataLines[14] = "op_parent_code=" + optionToSave.parentCode();
    dataLines[15] = "op_mult_allowed=" + optionToSave.isMultipleAllowed();
    dataLines[16] = "op_group_is_required=" + optionToSave.isGroupRequired();
    dataLines[17] = dataLines[3] = "op_quantity=" + optionToSave.quantity();
    dataLines[18] = "op_dependencies=" + dependencies;
    dataLines[19] = "op_options=" + options;
    dataLines[20] = "op_currency=" + optionToSave.currency();
    dataLines[21] = "op_markup=" + optionToSave.markup();
    dataLines[22] = "user_login=" + optionToSave.user().getLogin();
    dataLines[23] = "customer_name=" + optionToSave.customer().getName();
    dataLines[24] = "country_name=" + optionToSave.country().getName();
    dataLines[25] = "dealer_code=" + optionToSave.dealer().getCode();
    dataLines[26] = "user_name=" + System.getProperty("user.name");
    dataLines[27] = "op_standardized_name=" + optionToSave.standardizedName();
    dataLines[28] = "part_mandatory=" + optionToSave.isMandatory();
    new SaveToFile().saveToFile(fileName, dataLines, false);
    return optionSaved;
  }

  private String processDirectory3(String libraryPath) {
    String sep = System.getProperty("file.separator");
    // Create time hierarchy Strings for directory names
    String myDate = new Date().toString();
    String thisYear = myDate.substring(myDate.length() - 4);
    String thisMonth = myDate.substring(4, 7);
    String today = myDate.substring(0, 10) + " " + myDate.substring(myDate.length() - 4);
    String now = new StringManipulationToolkit().replaceAll(myDate.substring(11, myDate.length() - 9), ":", ".");
    // Create the folder hierarchy
    new File(libraryPath + sep + thisYear).mkdir();
    new File(libraryPath + sep + thisYear + sep + thisMonth).mkdir();
    new File(libraryPath + sep + thisYear + sep + thisMonth + sep + today).mkdir();
    new File(libraryPath + sep + thisYear + sep + thisMonth + sep + today + sep + now).mkdir();
    return libraryPath + sep + thisYear + sep + thisMonth + sep + today + sep + now;
  }

  private String processDirectory2(String libraryPath, String libraryName) {
    String sep = System.getProperty("file.separator");
    // Create time hierarchy Strings for directory names
    String myDate = new Date().toString();
    String thisYear = myDate.substring(myDate.length() - 4);
    String thisMonth = myDate.substring(4, 7);
    String today = myDate.substring(0, 10) + " " + myDate.substring(myDate.length() - 4);
    String now = new StringManipulationToolkit().replaceAll(myDate.substring(11, myDate.length() - 9), ":", ".");
    // Create the folder hierarchy
    new File(libraryPath + sep + thisYear).mkdir();
    new File(libraryPath + sep + thisYear + sep + thisMonth).mkdir();
    new File(libraryPath + sep + thisYear + sep + thisMonth + sep + libraryName).mkdir();
    return libraryPath + sep + thisYear + sep + thisMonth + sep + libraryName;
  }

  private String processDirectory4(Option optionBasket, String libraryPath, String libraryName) {
    String sep = System.getProperty("file.separator");
    // Create string for territory names
    String thiscountryName = optionBasket.country().getName();
    String thisDealerName = optionBasket.dealer().getName();

    // Create time hierarchy Strings for directory names
    String myDate = new Date().toString();
    String thisYear = myDate.substring(myDate.length() - 4);
    String thisMonth = myDate.substring(4, 7);
    String today = myDate.substring(0, 10) + " " + myDate.substring(myDate.length() - 4);
    String now = new StringManipulationToolkit().replaceAll(myDate.substring(11, myDate.length() - 9), ":", ".");

    // Create the folder hierarchy
    new File(libraryPath + sep + thiscountryName).mkdir();
    new File(libraryPath + sep + thiscountryName + sep + thisDealerName).mkdir();
    new File(libraryPath + sep + thiscountryName + sep + thisDealerName + sep + thisYear + "-" + thisMonth).mkdir();
    if (new File(libraryPath + sep + thiscountryName + sep + thisDealerName + sep + thisYear + "-" + thisMonth + sep +
                 libraryName).exists()) {
      libraryName += " " + today + " " + now;
    }
    new File(libraryPath + sep + thiscountryName + sep + thisDealerName + sep + thisYear + "-" + thisMonth + sep +
             libraryName).mkdir();
    return libraryPath + sep + thiscountryName + sep + thisDealerName + sep + thisYear + "-" + thisMonth + sep +
        libraryName;
  }

}
