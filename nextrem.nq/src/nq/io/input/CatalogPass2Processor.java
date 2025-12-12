// (c) 2006-2007 NextremSoft

package nq.io.input;

import java.util.*;

import nq.catalogue.*;
import utilities.string.*;

public class CatalogPass2Processor {
  public CatalogPass2Processor() {
  }

  //Pass 2 will read thru the price file to replace option names with better option names and will
  // also read compatibility attributes and other
  public Option buildOptionCatalogForCodePass22(String code, Option optionCatalog, Hashtable requiredHashtable,
                                                Hashtable[] data) {
    System.out.println();
    System.out.println();
    int counter = 0;

    // First we look at the primary options
    for (int i = 0; i < data.length; i++) { //Skip titles

      // If the entry corresponds to the series we are interested in
      if (data[i].get("series_code").toString().equals(code)) {

        counter++;
        System.out.print("o");
        if (counter == 75) {
          counter = 0;
          System.out.println();
        }
        System.out.flush();

        // Scan all primary options to update their prices from the price book
        // If the primary option is known to the catalog (so it was in the cost book)
        if (optionCatalog.hasOptionCode(data[i].get("primary_option_code").toString())) {
          Option thisOption = optionCatalog.findOptionByCode(data[i].get("primary_option_code").toString());
          // Last occurrence will be the one adopted, we will look at secondary options later
          thisOption.listPrice(Double.parseDouble(data[i].get("price").toString()));
          // Update name as cost book has broken names
          thisOption.name(data[i].get("option_desc").toString());
          thisOption.standardizedName(data[i].get("standard_option_desc").toString());

          // Process Option attributes
          thisOption = new OptionAttributeProcessor().processRecord(data[i], thisOption);

        }
        // If the primary option is NOT known to the catalog (so it was NOT in the cost book)
        else {
          // Create the new option
          Option thisOption = new Option();
          thisOption.type("OPTION");
          thisOption.code(data[i].get("primary_option_code").toString());
          thisOption.name(data[i].get("option_desc").toString());
          thisOption.standardizedName(data[i].get("standard_option_desc").toString()); // ****

          // Process Option attributes
          thisOption = new OptionAttributeProcessor().processRecord(data[i], thisOption);

          thisOption.group(data[i].get("group_code").toString());
          thisOption.groupDesc(data[i].get("group_desc").toString());
          thisOption.listPrice(Double.parseDouble(data[i].get("price").toString()));
          thisOption.currency(data[i].get("currency").toString());

          boolean groupRequired = requiredHashtable.get(code) != null ?
              new StringManipulationToolkit().createVectorFromArray( (String[]) requiredHashtable.get(code)).contains(
              thisOption.getGroup()) : false;
          thisOption.isGroupRequired(groupRequired);

          if (thisOption.isGroupRequired()) {
            thisOption.groupIndex(new StringManipulationToolkit().createVectorFromArray( (String[]) requiredHashtable.
                get(code)).indexOf(thisOption.getGroup()));
          }

          thisOption.multipleAllowed(false);
          thisOption.parentCode(code);
          optionCatalog.findOptionByCode(code).addUniqueOption(thisOption);

          // Create the part that makes this option with a code that will be the option code + the option code
          Option unknownPartInPrimaryOption = new Option();
          unknownPartInPrimaryOption.type("PART");
          unknownPartInPrimaryOption.code(data[i].get("primary_option_code").toString() +
                                          data[i].get("primary_option_code").toString());
          unknownPartInPrimaryOption.name(data[i].get("option_desc").toString() + " (Part)");
          unknownPartInPrimaryOption.standardizedName(data[i].get("standard_option_desc").toString() + " (Part)");
          unknownPartInPrimaryOption.mandatory(true);
          unknownPartInPrimaryOption.cost(0.0);
          thisOption.currency(data[i].get("currency").toString());
          unknownPartInPrimaryOption.group(data[i].get("group_code").toString());
          unknownPartInPrimaryOption.groupDesc(data[i].get("group_desc").toString());
          unknownPartInPrimaryOption.parentCode(data[i].get("primary_option_code").toString());
          optionCatalog.findOptionByCode(unknownPartInPrimaryOption.parentCode()).addUniqueOption(
              unknownPartInPrimaryOption);
        }
      }
    }
    // Now we look at secondary options
    for (int i = 0; i < data.length; i++) {
      // If the entry corresponds to the series we are interested in
      if (data[i].get("series_code").toString().equals(code)) {
        // Scan all secondary options
        // If the secondary option is known to the catalog (so it was in the cost book)
        if (optionCatalog.hasOptionCode(data[i].get("primary_option_code").toString()) &&
            optionCatalog.hasOptionCode(data[i].get("secondary_option_code").toString())) {
          Option thisPrimary = optionCatalog.findOptionByCode(data[i].get("primary_option_code").toString());
          Option thisConditioner = optionCatalog.findOptionByCode(data[i].get("secondary_option_code").toString()).
              cloneSelf(null);
          // Give it the conditional price for the primary it will condition
          thisConditioner.listPrice(Double.parseDouble(data[i].get("price").toString()));
          // Add secondary as conditioner for primary
          thisPrimary.addConditioner(thisConditioner);
        }
        // If the secondary option is NOT known to the catalog (so it was NOT in the cost book)
        else if (data[i].get("secondary_option_code").toString().length() > 0) {
          // Print out a warning
          System.out.print("x");
        }
      }
    }
    return optionCatalog;
  }

}
