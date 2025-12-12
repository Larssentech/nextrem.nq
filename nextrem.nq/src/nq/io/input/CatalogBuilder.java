// (c) 2006-2007 NextremSoft

package nq.io.input;

import java.util.*;

import nq.catalogue.*;
import utilities.io.*;
import utilities.string.*;

public class CatalogBuilder {

  private Hashtable[] partsHashtablesArray;

  public CatalogBuilder(String partsFileName) {
    System.out.println();
    System.out.print("Creating hashtables");
    partsHashtablesArray = new StringManipulationToolkit().createHashtablesFrom2DArray(new ArrayBuilder().
        makeArrayFromTSVExcludeLines(partsFileName, 0));
    System.out.println("\tOK");
  }

  public Hashtable processRequiredGroups(String requiredFileName) {
    return new RequiredGroupsProcessor().processRequiredGroups(requiredFileName);
  }

  // For efficiency, we first only load the units into the catalog
  // We will load every units details on demand later on
  public Option buildUnitCatalogPass1() {
    Option optionCatalog = new Option();
    Option newUnit;

    // For the catalog
    optionCatalog.type("OPTCTLG");
    optionCatalog.name("OPTCTLG");
    optionCatalog.code("OPTCTLG");
    optionCatalog.id("c" + Math.random() + "-" + new Date().getTime());
    optionCatalog.currency(partsHashtablesArray[1].get("currency").toString());

    // Now process all units only
    for (int i = 0; i < partsHashtablesArray.length; i++) {
      // Pass 0: Units
      newUnit = new Option();
      newUnit.type("UNIT");
      newUnit.multipleAllowed(true);
      newUnit.group("UNIT");
      newUnit.parentCode("OPTCTLG");
      newUnit.groupDesc("Full Unit");
      newUnit.code(partsHashtablesArray[i].get("series_code").toString());
      newUnit.name(partsHashtablesArray[i].get("series_desc").toString());
      newUnit.currency(partsHashtablesArray[i].get("currency").toString());
      optionCatalog.addUniqueOption(newUnit);
    }
    return optionCatalog;
  }

  public Option buildOptionCatalogForCodePass1(String code, Option optionCatalog, Hashtable requiredHashtable,
                                               Hashtable multiplesHashtable) {
    return new CatalogPass1Processor().buildOptionCatalogForCodePass1
        (code, optionCatalog, requiredHashtable, partsHashtablesArray);
  }

  // Pass 2 will read thru the price file to replace option names with better option names and will
  // also read compatibility attributes and other
  public Option buildOptionCatalogForCodePass2(String code, Option optionCatalog, Hashtable requiredHashtable,
                                               Hashtable multiplesHashtable, String optionsFileName) {

    return new CatalogPass2Processor().buildOptionCatalogForCodePass22
        (code, optionCatalog, requiredHashtable, new StringManipulationToolkit().createHashtablesFrom2DArray
         (new ArrayBuilder().makeArrayFromTSVFilterBy(optionsFileName, code, 2)));
  }
}
