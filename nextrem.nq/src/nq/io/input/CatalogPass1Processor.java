// (c) 2006-2007 NextremSoft

package nq.io.input;

import java.util.*;

import nq.catalogue.*;
import utilities.string.*;

public class CatalogPass1Processor {
  public CatalogPass1Processor() {
  }

  // This pass will run through the cost data structure creating the catalog
  // with cost and currency
  public Option buildOptionCatalogForCodePass1(String code, Option optionCatalog, Hashtable requiredHashtable,
                                               Hashtable[] data) {
    System.out.println();
    Option newOption;
    Option newPart;
    int counter = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i].get("series_code").toString().equals(code)) { // ****
        counter++;
        System.out.print("p");
        if (counter == 75) {
          counter = 0;
          System.out.println();
          System.out.flush();
        }

        // Pass 1: Options
        newOption = new Option();
        newOption.type("OPTION");
        newOption.code(data[i].get("primary_option_code").toString());
        newOption.name(data[i].get("option_desc").toString());
        newOption.group(data[i].get("group_code").toString());
        newOption.groupDesc(data[i].get("group_desc").toString());
        newOption.listPrice(Double.parseDouble(data[i].get("price").toString()));
        newOption.currency(data[i].get("currency").toString());

        boolean groupRequired = requiredHashtable.get(code) != null ?
            new StringManipulationToolkit().createVectorFromArray( (String[]) requiredHashtable.get(code)).contains(
            newOption.getGroup()) : false;
        newOption.isGroupRequired(groupRequired);

        if (newOption.isGroupRequired()) {
          newOption.groupIndex(new StringManipulationToolkit().createVectorFromArray( (String[]) requiredHashtable.get(
              code)).indexOf(newOption.getGroup()));
        }
        newOption.multipleAllowed(false);
        newOption.parentCode(code);

        optionCatalog.findOptionByCode(code).addUniqueOption(newOption);

        // Pass 2: Parts
        newPart = new Option();
        newPart.type("PART");
        newPart.code(data[i].get("part_code").toString());
        newPart.name(data[i].get("part_desc").toString());
        newPart.mandatory(data[i].get("optional").toString().equals("N") ? true : false);

        newPart.cost(Double.parseDouble(data[i].get("cost").toString()));
        newPart.currency(data[i].get("currency").toString());
        newPart.group(newOption.getGroup());
        newPart.groupDesc(newOption.getGroupDesc());
        newPart.parentCode(data[i].get("primary_option_code").toString());
        optionCatalog.findOptionByCode(newPart.parentCode()).addUniqueOption(newPart);

        StringManipulationToolkit sMT = new StringManipulationToolkit();

        // Update if compulsory is Y
        if (data[i].get("compulsory") != null && data[i].get("compulsory").toString().equals("Y")) {
          newPart.mandatory(true);

          // MAST SUPERGROUP
        }
        if (data[i].get("MLT") != null && data[i].get("MLT").toString().length() > 1) {
          newPart.addAttribute("Mast Length", sMT.createArrayListFromCSV(data[i].get("MLT").toString()));
        }
        if (data[i].get("LTY") != null && data[i].get("LTY").toString().length() > 1) {
          newPart.addAttribute("Mast Lift Type", sMT.createArrayListFromCSV(data[i].get("LTY").toString()));
        }
        if (data[i].get("FCL") != null && data[i].get("FCL").toString().length() > 1) {
          newPart.addAttribute("Forks Class", sMT.createArrayListFromCSV(data[i].get("FCL").toString()));
        }
        if (data[i].get("STG") != null && data[i].get("STG").toString().length() > 1) {
          newPart.addAttribute("Mast Stages", sMT.createArrayListFromCSV(data[i].get("STG").toString()));

          // CARRIAGE SUPERGROUP
        }
        if (data[i].get("CHT") != null && data[i].get("CHT").toString().length() > 1) {
          newPart.addAttribute("Carriage Height", sMT.createArrayListFromCSV(data[i].get("CHT").toString()));
        }
        if (data[i].get("LTY") != null && data[i].get("LTY").toString().length() > 1) {
          newPart.addAttribute("Mast Lift Type", sMT.createArrayListFromCSV(data[i].get("LTY").toString()));
        }
        if (data[i].get("FCL") != null && data[i].get("FCL").toString().length() > 1) {
          newPart.addAttribute("Forks Class", sMT.createArrayListFromCSV(data[i].get("FCL").toString()));
        }
        if (data[i].get("STG") != null && data[i].get("STG").toString().length() > 1) {
          newPart.addAttribute("Mast Stages", sMT.createArrayListFromCSV(data[i].get("STG").toString()));

          // HYDRAULICS SUPERGROUP
        }
        if (data[i].get("CLA") != null && data[i].get("CLA").toString().length() > 1) {
          newPart.addAttribute("Clamping", sMT.createArrayListFromCSV(data[i].get("CLA").toString()));
        }
        if (data[i].get("FTN") != null && data[i].get("FTN").toString().length() > 1) {
          newPart.addAttribute("Functions", sMT.createArrayListFromCSV(data[i].get("FTN").toString()));
        }
        if (data[i].get("HYD") != null && data[i].get("HYD").toString().length() > 1) {
          newPart.addAttribute("Hydraulics", sMT.createArrayListFromCSV(data[i].get("HYD").toString()));
        }
        if (data[i].get("LEV") != null && data[i].get("LEV").toString().length() > 1) {
          newPart.addAttribute("Levers", sMT.createArrayListFromCSV(data[i].get("LEV").toString()));
        }
        if (data[i].get("VWY") != null && data[i].get("VWY").toString().length() > 1) {
          newPart.addAttribute("Valve Ways", sMT.createArrayListFromCSV(data[i].get("VWY").toString()));

          // FRAME SUPERGROUP
        }
        if (data[i].get("TRD") != null && data[i].get("TRD").toString().length() > 1) {
          newPart.addAttribute("Tread", sMT.createArrayListFromCSV(data[i].get("TRD").toString()));
        }
        if (data[i].get("WBS") != null && data[i].get("WBS").toString().length() > 1) {
          newPart.addAttribute("Wheel Base", sMT.createArrayListFromCSV(data[i].get("WBS").toString()));

        }
      }
    }
    return optionCatalog;
  }

}
