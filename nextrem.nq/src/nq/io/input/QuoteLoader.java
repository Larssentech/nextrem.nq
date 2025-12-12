// (c) 2006-2007 NextremSoft

package nq.io.input;

import java.io.*;

import nq.catalogue.*;
import nq.sales.*;
import utilities.parsers.*;
import utilities.settings.*;

public class QuoteLoader {

  private final String sep = System.getProperty("file.separator");

  public QuoteLoader() {
  }

  public Option loadQuoteFromLibrary(String libraryFileName) {
    // Library will be the *.quote file and parent is the container folder
    // Both need to exist and more than 1 file need to be present
    if (new File(libraryFileName).exists() && new File(new File(libraryFileName).getParent()).list().length > 1) {
      String libraryPath = new File(libraryFileName).getParent();
      String[] fileList = new File(libraryPath).list();
      Option thisBasket = new Option();
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].indexOf("b") == 0 && !fileList[i].endsWith(".quote")) {
          thisBasket = this.loadOption(libraryPath + sep + fileList[i]);
        }
      }
      return thisBasket;
    }
    else {
      return null;
    }
  }

  private Option loadOption(String fileName) {
    Option thisOption = new Option();
    SettingsExtractor s = new SettingsExtractor();
    Customer customer = new Customer();
    customer.setName(s.extractThis(fileName, "customer_name"));
    thisOption.customer(customer);
    Dealer dealer = new Dealer();
    dealer.code(s.extractThis(fileName, "dealer_code"));
    thisOption.dealer(dealer);
    thisOption.country(new Country(s.extractThis(fileName, "country_name")));
    thisOption.user().setLogin(s.extractThis(fileName, "user_name"));
    thisOption.name(s.extractThis(fileName, "op_name"));
    thisOption.standardizedName(s.extractThis(fileName, "op_standardized_name"));
    thisOption.type(s.extractThis(fileName, "op_type"));
    thisOption.code(s.extractThis(fileName, "op_code"));
    thisOption.group(s.extractThis(fileName, "op_group"));
    thisOption.groupDesc(s.extractThis(fileName, "op_group_desc"));
    thisOption.id(s.extractThis(fileName, "op_ID"));
    thisOption.parentId(s.extractThis(fileName, "op_parent_ID"));
    thisOption.cost(extractDouble(s.extractThis(fileName, "op_cost")));
    thisOption.listPrice(extractDouble(s.extractThis(fileName, "op_list_price")));
    thisOption.discountP100(extractDouble(s.extractThis(fileName, "op_discount_p100")));
    thisOption.markup(extractDouble(s.extractThis(fileName, "op_markup")));
    thisOption.multipleAllowed(Boolean.valueOf(s.extractThis(fileName, "op_mult_allowed")).booleanValue());
    thisOption.isGroupRequired(Boolean.valueOf(s.extractThis(fileName, "op_group_is_required")).booleanValue());
    thisOption.mandatory(Boolean.valueOf(s.extractThis(fileName, "part_mandatory")).booleanValue());
    thisOption.currency(s.extractThis(fileName, "op_currency"));
    // Now quantity, which is dependent on the mult allowed value
    thisOption.quantity(Integer.parseInt(s.extractThis(fileName, "op_quantity")));
    thisOption.parentCode(s.extractThis(fileName, "op_parent_code"));
    String daughterOptions = s.extractThis(fileName, "op_options");
    if (!daughterOptions.equals("Error")) {
      File thisFile = new File(fileName);
      String[] fileNames = new CSVExtractor2().extractValuesFrom(daughterOptions);
      for (int i = 0; i < fileNames.length; i++) {
        fileNames[i] = thisFile.getParent() + sep + fileNames[i];
        thisOption.addOption(loadOption(fileNames[i]));
      }
    }
    return thisOption;
  }

  private double extractDouble(String s) {
    try {
      return Double.parseDouble(s);
    }
    catch (NumberFormatException nFE) {
      return 0.0;
    }
  }
}
