package nq.transaction;

import java.util.*;

import nq.catalogue.*;
import nq.orchestrating.*;
import utilities.parsers.*;
import nq.sales.*;
public class ArtificialIntelligenceProcessor {

  private String log;
  private NextremQuote nq;
  private User user;

  public ArtificialIntelligenceProcessor(NextremQuote nq, User user) {
    this.user = user;
    this.nq = nq;
    this.log = "Empty Log";
  }

  public String log() {
    return this.log;
  }

  public Option optionCatalog() {
    return this.nq.getSystemSession().optionCatalog();
  }

  public Option optionBasket() {
    return this.nq.getBasketForUser(this.user);
  }

  public boolean checkUnitCompatibility(String optionCodeToAdd, String parentId) {
    if (this.optionCatalog().findOptionByCode(optionCodeToAdd).type().equals("UNIT")) {
      this.log = "OK";
      return true;
    }
    else {
      this.log = "AI: Not a unit";
      return false;
    }
  }

  public boolean checkOptionCompatibility(String optionCodeToAdd, String parentId) {
    boolean allCompatible = true;
    this.log = "OK";
    // Get the basket parent whose daughters we need to check against
    // the option to add (incoming option) which we get from the catalog
    Option parent = this.optionBasket().findOptionById(parentId);
    Option optionToAdd = this.optionCatalog().findOptionByCode(optionCodeToAdd);

    // Climb back up to the parent unit , compatibility is performed between options
    if (parent.type().equals("OPTCTLG")) {
      return true;
    }

    // Ensure we start from the unit level
    if (!parent.type().equals("UNIT")) {
      parent = this.optionBasket().findOptionById(parent.parentId());

      // If the incoming option has attributes, for each of them check against
      // all daughters in the unit
    }
    for (int i = 0; i < optionToAdd.attributeKeys().length; i++) {
      String attributeName = optionToAdd.attributeKeys()[i];

      // Check each of the options in the parent unit for compatible attributes
      for (int j = 0; j < parent.options().length; j++) {
        // We want to compare against the 'catalog' option not the basket option
        // as we want the most recent attributes not the saved ones (if saved, which
        // still does not happen)
        Option thisOption = this.optionCatalog().findOptionByCode(parent.options()[j].getCode());

        // If both options have the same attribute
        if (optionToAdd.getAttributeValuesByName(attributeName) != null &&
            thisOption.getAttributeValuesByName(attributeName) != null) {

          // Check 1-on-1 peer options
          int compatIndex = checkAttributeByName(attributeName, optionToAdd.getCode(), thisOption.getCode());
          // If 1 incompatibility is found we need look no further
          if (compatIndex == -1) {
            return false;
          }
        }
      }
    }
    return allCompatible;
  }

  // Compatibility return codes are:
  // 0 = Perfect match or mandatory part
  // 1 = Not compatible and not incompatible, indiferent
  // 2 = Incompatible
  public int checkPartCompatibility(String partCodeToAdd, String parentId) {
    Option partToAdd = this.optionCatalog().findOptionByCode(partCodeToAdd);

    // Mandatory parts are always perfectly compatible
    if (partToAdd.isMandatory()) {
      return 0;
    }

    // We will use this to store units of incompatibility
    int inCcompatibilityIndex = 0;

    this.log = "OK";
    Option parentOption = this.optionBasket().findOptionById(parentId);
    // Ensure we start from the unit level
    if (!parentOption.type().equals("UNIT")) {
      parentOption = this.optionBasket().findOptionById(parentOption.parentId());

      // If part to add has attributes
    }
    if (partToAdd.attributeKeys().length > 0) {

      // For each peer part in the unit
      Iterator ite = this.getAllPartsInUnit(parentOption).iterator();
      while (ite.hasNext()) {
        Option peerPart = ( (Option) ite.next());

        // Check this peer part for all incoming part attributes
        for (int i = 0; i < partToAdd.attributeKeys().length; i++) {
          String attributeName = partToAdd.attributeKeys()[i];

          // Check 1-on-1 peer parts
          int compatIndex = checkAttributeByName(attributeName, partToAdd.getCode(), peerPart.getCode());

          // If 1 incompatibility is found we need look no further
          if (compatIndex == -1) {
            return -1;
          }

          // Add a unit of "lackof perfect match" to the index. If all peer parts
          // are perfectly compatible, the index will be zero
          inCcompatibilityIndex += compatIndex;
        }
      }
    }

    // If part to add did not have attributes, it is compatible by default
    return inCcompatibilityIndex;
  }

  // Method to check 1 attribute at a time for 2 peer options
  // Returns: 0 if:
  //  - Both have the attribute and there is a value match
  //  - They do not have the attribute in common
  //
  // Returns: -1 if both have the attribute but it does not have a matching value
  private int checkAttributeByName(String attributeName, String optionCode, String peerCode) {

    // Incoming option and peer option both are fetched from the catalog
    Option optionToAdd = this.optionCatalog().findOptionByCode(optionCode);
    Option peerOption = this.optionCatalog().findOptionByCode(peerCode);

    // If both options have the same attribute
    if (optionToAdd.getAttributeValuesByName(attributeName) != null &&
        peerOption.getAttributeValuesByName(attributeName) != null) {

      // Check if there is a match in any of the combinations of values
      // We need to iterate thru both for all combinations
      ArrayList peerOptionsCurrentAttributeValues = peerOption.getAttributeValuesByName(attributeName);
      ArrayList incominOptionCurrentAttributeValues = optionToAdd.getAttributeValuesByName(attributeName);
      Iterator peerOptionValuesIterator = peerOptionsCurrentAttributeValues.iterator();
      Iterator incominOptionValuesIterator = incominOptionCurrentAttributeValues.iterator();

      // For each value
      while (peerOptionValuesIterator.hasNext()) {
        String i1Value = peerOptionValuesIterator.next().toString();

        // We check each of the incoming option same attribute values
        while (incominOptionValuesIterator.hasNext()) {
          String i2Value = incominOptionValuesIterator.next().toString();

          // As an array of attribute values can be stored as a CSV string
          // in 1 value, we need to extract the CSVs to compare them all
          String[] i1ValueArray = new CSVExtractor2().extractValuesFrom(i1Value);
          String[] i2ValueArray = new CSVExtractor2().extractValuesFrom(i2Value);

          // Check all possible combinations and return when 1 compat is found
          for (int i = 0; i < i1ValueArray.length; i++) {
            for (int j = 0; j < i2ValueArray.length; j++) {
              if (i1ValueArray[i].equals(i2ValueArray[j])) {
                return 0;
              }
            }
          }
        }
      }
      // If no match was found incoming option is incompatible and we need to
      // tell the user. Then return -1
      this.log = "Incompatibility: " + attributeName;
    }

    // If both options do not have the same attribute then they are compatible
    // by default
    else {
      return 0;
    }
    return -1;
  }

  private ArrayList getAllPartsInUnit(Option parentOption) {
    // We need dynamic indexed storage
    ArrayList parts = new ArrayList();

    // Check each of the options in the parent unit
    for (int j = 0; j < parentOption.options().length; j++) {
      Option thisOption = parentOption.options()[j];

      // Check each part in the option
      for (int k = 0; k < thisOption.options().length; k++) {
        Option peerPart = thisOption.options()[k];
        Option catalogPeerPart = this.nq.getSystemSession().optionCatalog().findOptionByCode(peerPart.getCode());
        if (catalogPeerPart.attributes().size() > 0) {
          parts.add(catalogPeerPart);
        }
      }
    }
    return parts;
  }
}
