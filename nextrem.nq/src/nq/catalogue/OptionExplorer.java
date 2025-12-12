// (c) 2006-2007 NextremSoft

package nq.catalogue;

import java.util.*;

import nq.sales.*;
import utilities.string.*;

public class OptionExplorer {
  private String empty = "                                                               ";
  private Country country;
  private Dealer dealer;
  private Customer customer;

  public OptionExplorer() {
  }

  public String[] checkRequiredGroupsPresent(Option optionToExplore, Hashtable requiredHashtable) {
    if (optionToExplore.type().equals("UNIT")) {
      String[] requiredGroups = (String[]) requiredHashtable.get(optionToExplore.getCode());

      // If required groups can be found for the series
      if (requiredGroups != null) {
        Vector missingGroups = new Vector();
        for (int i = 0; i < requiredGroups.length; i++) {

          // Exclude empty cells
          if (requiredGroups[i].length() > 0) {
            if (!optionToExplore.hasGroupCode(requiredGroups[i])) {
              missingGroups.add(requiredGroups[i]);
            }
          }
        }
        return new StringManipulationToolkit().createArrayFromVector(missingGroups);
      }

      // If no required groups are found return a 0 length String[]
      else {
        return new String[0];
      }
    }
    else {
      return new String[0];
    }
  }

  public String exploreOption(Option optionToExplore) {
    String thisName = optionToExplore.getName() + "\n";
    for (int i = 0; i < optionToExplore.options().length; i++) {
      Option thisOption = optionToExplore.options()[i];
      thisName += new OptionExplorer().exploreOption(thisOption);
    }
    return thisName;
  }

  public String[] exploreOptionsNamesAndIds(Option optionToExplore, boolean hideParts) {
    Vector v = this.exploreOptionNamesAndIds2(optionToExplore, new Vector(), hideParts);
    String[] namesAndIdsArray = new String[v.size()];
    for (int i = 0; i < v.size(); i++) {
      namesAndIdsArray[i] = (String) v.elementAt(i);
    }
    return namesAndIdsArray;
  }

  private Vector exploreOptionNamesAndIds2(Option optionToExplore, Vector branchStore, boolean hideParts) {
    String itemToAdd = "";
    String mark = optionToExplore.marked() ? " X   " : "      ";
    if (optionToExplore.type().equals("OPTCTLG")) {
      itemToAdd = mark + "[+]  " + optionToExplore.getName() + " (" + optionToExplore.user().getLogin() + ") " + empty +
          empty + empty + "id=" + optionToExplore.id();
    }
    if (optionToExplore.type().equals("UNIT")) {
      itemToAdd = mark + "       [+]  " + optionToExplore.getName() + empty + empty + empty + "id=" + optionToExplore.id();
    }
    if (optionToExplore.type().equals("OPTION")) {
      itemToAdd = mark + "             [+]  " + optionToExplore.getName() + empty + empty + empty + "id=" +
          optionToExplore.id();
    }
    if (optionToExplore.type().equals("PART") && !hideParts) {
      itemToAdd = mark + "                      |_  " + optionToExplore.getName() + empty + empty + empty + "id=" +
          optionToExplore.id();
    }
    if (itemToAdd.length() > 0) {
      branchStore.add(itemToAdd);
    }
    for (int i = 0; i < optionToExplore.options().length; i++) {
      this.exploreOptionNamesAndIds2(optionToExplore.options()[i], branchStore, hideParts);
    }
    return branchStore;
  }

  private Vector myTSVVector = new Vector();

  public Vector convertQuoteToCSVExportFormat(User user, Option basket, boolean hideParts, boolean hideCosts) {
    String[] thisOptionData = new String[17];
    thisOptionData[0] = "User";
    thisOptionData[1] = "Basket ID";
    thisOptionData[2] = "Timestamp";
    thisOptionData[3] = "Name";
    thisOptionData[4] = "Type";
    thisOptionData[5] = "Code";
    thisOptionData[6] = "Group";
    thisOptionData[7] = "Quantity";
    thisOptionData[8] = "Gross Value";
    thisOptionData[9] = "Discount";
    thisOptionData[10] = "Net Value";
    thisOptionData[11] = "Markup%";
    thisOptionData[12] = "Street Value";
    thisOptionData[13] = "Country";
    thisOptionData[14] = "Dealer";
    thisOptionData[15] = "Customer";
    thisOptionData[16] = "Cost";

    myTSVVector.add(thisOptionData);
    this.country = basket.country();
    this.dealer = basket.dealer();
    this.customer = basket.customer();
    return this.convertOptionToCSV(user, basket, new java.util.Date().getTime() + "", hideParts, hideCosts);
  }

  private Vector convertOptionToCSV(User user, Option option, String uniqueId, boolean hideParts, boolean hideCosts) {
    String[] thisOptionData = new String[17];
    thisOptionData[0] = user.getLogin();
    thisOptionData[1] = new java.util.Date() + "";
    thisOptionData[2] = uniqueId;
    thisOptionData[3] = option.getName();
    thisOptionData[4] = option.type();
    thisOptionData[5] = option.getCode();
    thisOptionData[6] = option.getGroup();
    thisOptionData[7] = option.quantity() + "";
    thisOptionData[8] = option.rollUpListPrice() + "";
    thisOptionData[9] = option.discountP100() * option.rollUpListPrice() / 100F + "";
    thisOptionData[10] = option.rollUpNetPrice() + "";
    thisOptionData[11] = option.markup() + "";
    thisOptionData[12] = option.markup() * option.rollUpNetPrice() / 100 + option.rollUpNetPrice() + "";
    thisOptionData[13] = this.country.getName();
    thisOptionData[14] = this.dealer.getCode();
    thisOptionData[15] = this.customer.getName();
    if (!hideCosts) {
      thisOptionData[16] = option.rollUpCost() + "";

    }
    myTSVVector.add(thisOptionData);
    for (int i = 0; i < option.options().length; i++) {
      if (option.options()[i].type().equals("PART")) {
        if (!hideParts) {
          this.convertOptionToCSV(user, option.options()[i], uniqueId, hideParts, hideCosts);
        }
      }
      else {
        this.convertOptionToCSV(user, option.options()[i], uniqueId, hideParts, hideCosts);
      }
    }
    return myTSVVector;
  }

  public Vector convertQuoteToCSVSaveFormat(User user, Option basket) {
    // Reuse code: get the whole basket with all columns
    Vector temp = this.convertQuoteToCSVExportFormat(user, basket, true, true);
    Vector returnVector = new Vector();
    String unitCode = "";

    // Strip out what we do not need:
    // Header row
    String[] thisOption = new String[3];
    thisOption[0] = "Product Code";
    thisOption[1] = "Option Code";
    thisOption[2] = "Option Name";
    returnVector.add(thisOption);

    for (int i = 0; i < temp.size(); i++) {

      // Flag to know what to store
      boolean found = false;

      // Create new for every option
      thisOption = new String[3];

      // If we find a unit, take the code out
      if ( ( (String[]) temp.elementAt(i))[4].equals("UNIT")) {
        unitCode = ( (String[]) temp.elementAt(i))[5];

        // For all options, take them
      }
      if ( ( (String[]) temp.elementAt(i))[4].equals("OPTION")) {
        thisOption[0] = unitCode;
        thisOption[1] = ( (String[]) temp.elementAt(i))[5];
        thisOption[2] = ( (String[]) temp.elementAt(i))[3];
        found = true;
      }

      // And store them if we found something to store
      if (found) {
        returnVector.add(thisOption);
      }
    }
    return returnVector;
  }
}
