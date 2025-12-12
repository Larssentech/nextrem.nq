// (c) 2006-2007 NextremSoft

package nq.catalogue;

/**
 * <p>Title: Option Class </p>
 *
 * <p>Description: Options are 2 things at the same time. First they are a logical component
 * of a forklift unit, ranging from a whole unit to a small part to a smaller component.
 * Second, they are containers or wrappers for other options, like a unit contains
 * options and an option contains parts and a part contains components.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: NextremSoft</p>
 * @author Jeffrey J Cerasuolo
 * @version 27.0
 */
import java.util.*;

import nq.sales.*;
import utilities.string.*;

public class Option {
  private int rank;
  private Hashtable attributes;
  private boolean skipCompatibilityCheck;
  private User user;
  private Country country;
  private Dealer dealer;
  private Customer customer;
  private String name;
  private String standardizedName;
  private String type;
  private String code;
  private String parentCode;
  private String group;
  private int getGroupIndex;
  private String getGroupDesc;
  private String id;
  private String parentId;
  private boolean isMandatory;
  private double cost;
  private String currency;
  private int quantity;
  private double listPrice;
  private double netPrice;
  private double discountP100;
  private double standardDiscP100;
  private double maxDiscP100;
  private double markup;
  private Vector options;
  private Vector requiredOptions;
  private Vector conditioners;
  private boolean marked;
  private boolean isGroupRequired;
  private boolean isMultipleAllowed;

  public Option() {
    this.attributes = new Hashtable();
    this.skipCompatibilityCheck = false;
    this.options = new Vector();
    this.requiredOptions = new Vector();
    this.conditioners = new Vector();
    this.name = "Empty";
    this.standardizedName = "Empty";
    this.type = "Empty";
    this.group = "Empty";
    this.getGroupIndex = 777777;
    this.getGroupDesc = "Empty";
    this.code = "Empty";
    this.parentCode = "Empty";
    this.id = "Empty";
    this.parentId = "Empty";
    this.isMandatory = false;
    this.listPrice = 0.0;
    this.netPrice = 0.0;
    this.cost = 0;
    this.currency = "Empty";
    this.quantity = 1;
    this.discountP100 = 0.0;
    this.markup = 0.0;
    this.marked = false;
    this.isGroupRequired = false;
    this.isMultipleAllowed = false;
    this.user = new User();
    this.customer = new Customer();
    this.country = new Country("");
    this.dealer = new Dealer();
  }

  public int getGroupIndex() {
    return this.getGroupIndex;
  }

  public void groupIndex(int i) {
    this.getGroupIndex = i;
  }

  /**
   * Searches for a specific option code. It looks at itself and its daughters recursively
   * If it finds the code, returns the option with the given code. If not, returns a blank (empty) Option
   * @param objectCode String
   * @return Option
   */
  public Option findOptionByCode(String objectCode) {
    if (objectCode == null) {
      return new Option();
    }
    else {
      if (this.code.equals(objectCode)) {
        return this;
      }
      else {
        Option tempOption = new Option();
        for (int i = 0; i < this.options.size(); i++) {
          tempOption = ( (Option)this.options.elementAt(i)).findOptionByCode(objectCode);
          if (tempOption.getCode().equals(objectCode)) {
            break;
          }
        }
        return tempOption;
      }
    }
  }

  public Option findConditionerByCode(String objectCode) {
    Option tempOption = new Option();
    if (objectCode == null) {
      return tempOption;
    }
    else {
      for (int i = 0; i < this.conditioners.size(); i++) {
        if ( ( (Option)this.conditioners.elementAt(i)).getCode().equals(objectCode)) {
          tempOption = (Option)this.conditioners.elementAt(i);
          break;
        }
      }
      return tempOption;
    }
  }

  /**
   * Searches for a specific option code. It looks at itself and its daughters recursively
   * If it finds the code, it returns true. If not, false
   * @param objectCode String
   * @return boolean
   */
  public boolean hasOptionCode(String objectCode) {
    if (objectCode == null) {
      return false;
    }
    else {
      if (this.code.equals(objectCode)) {
        return true;
      }
      else {
        boolean found = false;
        for (int i = 0; i < this.options.size(); i++) {
          if (found = ( (Option)this.options.elementAt(i)).hasOptionCode(objectCode)) {
            break;
          }
        }
        return found;
      }
    }
  }

  // Looks only into first level daughters, not recursively
  public boolean hasConditionerOptionCode(String objectCode) {
    if (objectCode == null) {
      return false;
    }
    else {
      if (this.code.equals(objectCode)) {
        return true;
      }
      else {
        boolean found = false;
        for (int i = 0; i < this.conditioners.size(); i++) {
          if (found = ( (Option)this.conditioners.elementAt(i)).getCode().equals(objectCode)) {
            break;
          }
        }
        return found;
      }
    }
  }

  /**
    Searches for a specific Group code. It looks at itself and its daughters recursively
   * If it finds the code, returns true. If not, false
   * @param groupCode String
   * @return boolean
   */
  public boolean hasGroupCode(String groupCode) {
    if (groupCode == null) {
      return false;
    }
    else {
      if (this.group.equals(groupCode)) {
        return true;
      }
      else {
        boolean found = false;
        for (int i = 0; i < this.options.size(); i++) {
          if (found = ( (Option)this.options.elementAt(i)).hasGroupCode(groupCode)) {
            break;
          }
        }
        return found;
      }
    }
  }

  /**
   * Creates a clone of this Option. Returns the newly created clone option
   * @param newId String
   * @return Option
   */
  public Option cloneSelf(String newId) {
    Option clonedOption = new Option();
    clonedOption.name = this.name;
    clonedOption.standardizedName = this.standardizedName;
    clonedOption.code = this.code;
    clonedOption.parentCode = this.parentCode;
    clonedOption.cost = this.cost;
    clonedOption.type = this.type;
    clonedOption.group = this.group;
    clonedOption.getGroupIndex = this.getGroupIndex;
    clonedOption.getGroupDesc = this.getGroupDesc;
    clonedOption.quantity = this.quantity;
    clonedOption.listPrice = this.listPrice;
    clonedOption.markup = this.markup;
    clonedOption.netPrice = this.netPrice;
    clonedOption.isMandatory = this.isMandatory;
    clonedOption.discountP100 = this.discountP100;
    clonedOption.isGroupRequired = this.isGroupRequired;
    clonedOption.isMultipleAllowed = this.isMultipleAllowed;
    clonedOption.requiredOptions = this.requiredOptions;
    clonedOption.conditioners = this.conditioners;
    clonedOption.id(newId);
    clonedOption.parentId("");
    clonedOption.currency(this.currency);
    clonedOption.options = new Vector();
    clonedOption.attributes = this.attributes;
    return clonedOption;
  }

  /**
   * Searches for a specific option ID. It looks at itself and its daughters recursively
   * If it finds the ID, returns the option with the given ID. If not, returns a blank (empty) Option
   * @param idToFind String
   * @return Option
   */
  public Option findOptionById(String idToFind) {
    if (idToFind == null) {
      return new Option();
    }
    else {
      if (this.id.equals(idToFind)) {
        return this;
      }
      else {
        Option tempOption = new Option();
        for (int i = 0; i < this.options.size(); i++) {
          // tempOption is now the daughter that we could find, the daughter of (Option)this.options.elementAt(i)
          // and NOT (Option)this.options.elementAt(i) itself
          tempOption = ( (Option)this.options.elementAt(i)).findOptionById(idToFind);
          if (tempOption.id().equals(idToFind)) {
            break;
          }
        }
        return tempOption;
      }
    }
  }

  /**
   * Searches for a specific Option ID. It looks at itself and its daughters recursively
   * If it finds the ID, returns true. If not, false
   * @param objectId String
   * @return boolean
   */
  public boolean hasOptionId(String objectId) {
    if (objectId == null) {
      return false;
    }
    else {
      if (this.id.equals(objectId)) {
        return true;
      }
      else {
        boolean optionIdFound = false;
        for (int i = 0; i < this.options.size(); i++) {
          if (optionIdFound = ( (Option)this.options.elementAt(i)).hasOptionId(objectId)) {
            break;
          }
        }
        // This (Option)this.options.elementAt(i).id() will never equal the objectId since it is the daughter
        // of (Option)this.options.elementAt(i) that will euqal it. Thus, this will not break, so when 2 units in
        // basket the next one will be checked and if it does not have the id, and it will not have it if it is in
        // unit 1, this returns false when it should return true F U U U C K !!!
        //if ( ( (Option)this.options.elementAt(i)).id().equals(objectId)) {
        //  optionIdFound = true;
        //  break;
        //}
        return optionIdFound;
      }
    }
  }

  // When rolling up cost, net value and list value, apply the quantity of the parent item (this)
  // to the rolled up measures
  public double rollUpNetPrice() {
    double rolledUpNetPrice = this.netPrice * this.quantity;
    for (int i = 0; i < options.size(); i++) {
      rolledUpNetPrice += ( (Option)this.options.elementAt(i)).rollUpNetPrice() * this.quantity;
    }
    return rolledUpNetPrice;
  }

  public String[] getGroups() {
    Vector v = new Vector();
    for (int i = 0; i < this.options.size(); i++) {
      Option thisOption = this.options()[i];
      if (!v.contains(thisOption.getGroup())) {
        v.add(thisOption.getGroup());
      }
    }
    return new StringManipulationToolkit().createArrayFromVector(v);
  }

  public String getGroupDescriptionForGroup(String groupCode) {
    String desc = "Error";
    for (int i = 0; i < this.options.size(); i++) {
      if ( ( (Option)this.options.elementAt(i)).getGroup().equals(groupCode)) {
        desc = ( (Option)this.options.elementAt(i)).getGroupDesc();
        break;
      }
    }
    return desc;
  }

  public Option[] findOptionsByGroup(String groupCode) {
    Vector v = new Vector();
    for (int i = 0; i < this.options.size(); i++) {
      if ( ( (Option)this.options.elementAt(i)).getGroup().equals(groupCode)) {
        v.add(this.options.elementAt(i));
      }
    }
    Option[] o = new Option[v.size()];
    for (int i = 0; i < v.size(); i++) {
      o[i] = (Option) v.elementAt(i);
    }
    return o;
  }

  /**
   *
   * @param discP100 double
   * @return boolean
   */
  public boolean overRideStandardDiscP100(double discP100) {
    this.standardDiscP100(discP100);
    for (int i = 0; i < this.options.size(); i++) {
      ( (Option)this.options.elementAt(i)).overRideStandardDiscP100(discP100);
    }
    return true;
  }

  public boolean overRideDiscountP100(double discP100) {
    this.discountP100(discP100);
    for (int i = 0; i < this.options.size(); i++) {
      ( (Option)this.options.elementAt(i)).overRideDiscountP100(discP100);
    }
    return true;
  }

  public boolean overRideMaxDiscountP100(double discP100) {
    this.maxDiscP100(discP100);
    for (int i = 0; i < this.options.size(); i++) {
      ( (Option)this.options.elementAt(i)).overRideMaxDiscountP100(discP100);
    }
    return true;
  }

  /**
   * Rolls up the list price in a recursive manner for self and all daughter options
   * @return double
   */
  public double rollUpListPrice() {
    double rolledUpListPrice = this.listPrice * this.quantity;
    if (this.options.size() > 0) {
      for (int i = 0; i < options.size(); i++) {
        rolledUpListPrice += ( (Option)this.options.elementAt(i)).rollUpListPrice() * this.quantity;
      }
    }
    return rolledUpListPrice;
  }

  /**
   * Rolls up the cost in a recursive manner for self and all daughter options
   * @return double
   */
  public double rollUpCost() {
    double rolledUpCost = this.cost * this.quantity;
    if (this.options.size() > 0) {
      for (int i = 0; i < options.size(); i++) {
        rolledUpCost += ( (Option)this.options.elementAt(i)).rollUpCost() * this.quantity;
      }
    }
    return rolledUpCost;
  }

  /**
   * Method to return an Array with all required Options (dependencies)
   * @return Option[]
   */
  public Option[] requiredOptions() {
    Option[] requiredOptionsArray = new Option[this.requiredOptions.size()];
    for (int i = 0; i < requiredOptionsArray.length; i++) {
      requiredOptionsArray[i] = (Option)this.requiredOptions.elementAt(i);
    }
    return requiredOptionsArray;
  }

  /**
   * Method to add a given Option to the Vector of required Options for the current Option
   * @param optionToAdd Option
   * @return boolean
   */
  public boolean addRequiredOption(Option optionToAdd) {
    boolean requiredOptionAbsent = true;
    for (int i = 0; i < this.requiredOptions.size(); i++) {
      Option thisOption = (Option)this.requiredOptions.elementAt(i);
      if (thisOption.getCode().equals(optionToAdd.getCode())) {
        requiredOptionAbsent = false;
        break;
      }
    }
    if (requiredOptionAbsent && !this.code.equals(optionToAdd.code)) {
      requiredOptions.addElement(optionToAdd);
    }
    return requiredOptionAbsent;
  }

  /**
   * Method to return the daughter options array
   * @return Option[]
   */
  public Option[] options() {
    Option[] optionsArray = new Option[options.size()];
    for (int i = 0; i < options.size(); i++) {
      optionsArray[i] = (Option) options.elementAt(i);
    }
    return optionsArray;
  }

  /**
   * Adds an option to itself if the option code is not found already as a daughter option
   * @param option Option
   * @return boolean
   */
  public boolean addUniqueOption(Option option) {
    boolean optionAbsent = true;
    for (int i = 0; i < options.size(); i++) {
      Option thisOption = (Option) options.elementAt(i);
      if (thisOption.getCode().equals(option.getCode())) {
        optionAbsent = false;
        break;
      }
    }
    if (optionAbsent) {
      options.addElement(option);
    }
    option.parentId(this.id);
    rollUpListPrice();
    rollUpCost();
    return optionAbsent;
  }

  /**
   * Adds an option to itself and gives it its inheritable values
   * @param option Option
   * @return boolean
   */
  public boolean addOption(Option option) {
    options.addElement(option);
    option.parentId(this.id);
    rollUpListPrice();
    rollUpCost();
    return true;
  }

  /**
   * Searches for the specified option ID in a recursive manner and removes it if it finds it. As options
   * are nested within other options, removing an option will also remove all daughter options
   * to the option to remove. Returns true if successful, false otherwise
   * @param id String
   * @return boolean
   */
  public boolean removeOption(String id) {
    boolean deleted = false;
    for (int i = 0; i < this.options.size(); i++) {
      Option thisDaughterOption = (Option)this.options.elementAt(i);
      thisDaughterOption.removeOption(id);
      if (thisDaughterOption.id.equals(id)) {
        this.options.removeElementAt(i);
        deleted = true;
        break;
      }
    }
    return deleted;
  }

  /**
   * Removes all options that are daughters to self
   * @return boolean
   */
  public boolean removeAllOptions() {
    this.options = new Vector();
    return true;
  }

  public void listPrice(double listPrice) {
    this.listPrice = listPrice;
    this.netPrice = this.listPrice - (this.listPrice * discountP100 / 100);
  }

  public double listPrice() {
    return this.listPrice;
  }

  public double discountP100() {
    return this.discountP100;
  }

  public void discountP100(double discountP100) {
    this.discountP100 = discountP100;
    this.netPrice = this.listPrice - (this.listPrice * discountP100 / 100);
  }

  public double cost() {
    return this.cost;
  }

  public void cost(double cost) {
    this.cost = cost;
  }

  public int quantity() {
    return this.quantity;
  }

  public void quantity(int quantity) {
    if (this.isMultipleAllowed) {
      this.quantity = quantity;
    }
    else {
      this.quantity = 1;
    }
  }

  public void name(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void standardizedName(String standardizedName) {
    this.standardizedName = standardizedName;
  }

  public String standardizedName() {
    return this.standardizedName;
  }

  public void group(String group) {
    this.group = group;
  }

  public String getGroup() {
    return this.group;
  }

  public void groupDesc(String groupDesc) {
    this.getGroupDesc = groupDesc;
  }

  public String getGroupDesc() {
    return this.getGroupDesc;
  }

  public void id(String id) {
    this.id = id;
  }

  public String id() {
    return this.id;
  }

  public String parentId() {
    return this.parentId;
  }

  public void parentId(String parentId) {
    this.parentId = parentId;
  }

  public void code(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public void parentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public String parentCode() {
    return this.parentCode;
  }

  public void type(String type) {
    this.type = type;
  }

  public String type() {
    return this.type;
  }

  public boolean marked() {
    return this.marked;
  }

  public void mandatory(boolean mandatory) {
    this.isMandatory = mandatory;
  }

  public boolean isMandatory() {
    return this.isMandatory;
  }

  public void marked(boolean marked) {
    this.marked = marked;
  }

  public boolean isGroupRequired() {
    return this.isGroupRequired;
  }

  public void isGroupRequired(boolean isGroupRequired) {
    this.isGroupRequired = isGroupRequired;
  }

  public boolean isMultipleAllowed() {
    return this.isMultipleAllowed;
  }

  public void multipleAllowed(boolean multipleAllowed) {
    this.isMultipleAllowed = multipleAllowed;
  }

  public void currency(String currency) {
    this.currency = currency;
  }

  public String currency() {
    return currency;
  }

  public double markup() {
    return markup;
  }

  public void markup(double markup) {
    this.markup = markup;
  }

  public Option[] conditioners() {
    Option[] requiredOptionsFromArray = new Option[this.conditioners.size()];
    for (int i = 0; i < requiredOptionsFromArray.length; i++) {
      requiredOptionsFromArray[i] = (Option)this.conditioners.elementAt(i);
    }
    return requiredOptionsFromArray;
  }

  public void addConditioner(Option option) {
    boolean optionFound = false;
    for (int i = 0; i < conditioners.size(); i++) {
      if ( ( (Option) conditioners.elementAt(i)).getCode().equals(option.code)) {
        optionFound = true;
        break;
      }
    }
    if (!optionFound) {
      this.conditioners.addElement(option);
    }

  }

  public double conditionalPriceForCode(String code) {
    double conditionalListPrice = 0.0;
    for (int i = 0; i < conditioners.size(); i++) {
      if ( ( (Option)this.conditioners.elementAt(i)).getCode().equals(code)) {
        conditionalListPrice = ( (Option)this.conditioners.elementAt(i)).listPrice;
      }
    }
    return conditionalListPrice;
  }

  public User user() {
    return user;
  }

  public void user(User user) {
    this.user = user;
  }

  public double standardDiscP100() {
    return standardDiscP100;
  }

  public void standardDiscP100(double standardDiscP100) {
    this.standardDiscP100 = standardDiscP100;
  }

  public double maxDiscP100() {
    return maxDiscP100;
  }

  public void maxDiscP100(double maxDiscP100) {
    this.maxDiscP100 = maxDiscP100;
  }

  public Country country() {
    return country;
  }

  public void country(Country country) {
    this.country = country;
  }

  public Dealer dealer() {
    return dealer;
  }

  public void dealer(Dealer dealer) {
    this.dealer = dealer;
  }

  public Customer customer() {
    return customer;
  }

  public void customer(Customer customer) {
    this.customer = customer;
  }

  public Hashtable attributes() {
    return this.attributes;
  }

  public boolean updateAttribute(String name, String value) {
    if (this.attributes.get(name) != null) {
      this.attributes.remove(name);
      ArrayList values = new ArrayList();
      values.add(value);
      this.attributes.put(name, values);
      return true;
    }
    else {
      return false;
    }
  }

  public boolean addValueToAttribute(String name, String additionalValue) {
    if (this.attributes.get(name) != null) {
      ArrayList values = (ArrayList)this.attributes.get(name);
      this.attributes.remove(name);
      values.add(additionalValue);
      this.attributes.put(name, values);
      return true;
    }
    else {
      return false;
    }
  }

  public void addAttribute(String name, String value) {
    ArrayList values = new ArrayList();
    values.add(value);
    this.attributes.put(name, values);
  }

  public void addAttribute(String name, ArrayList values) {
    this.attributes.put(name, values);
  }

  public ArrayList getAttributeValuesByName(String name) {
    if (this.attributes.get(name) != null) {
      return (ArrayList)this.attributes.get(name);
    }
    else {
      return null;
    }
  }

  public String[] attributeKeys() {
    Object[] keys = this.attributes.keySet().toArray();
    String[] keysArray = new String[keys.length];
    for (int i = 0; i < keys.length; i++) {
      keysArray[i] = keys[i].toString();
    }
    return keysArray;
  }

  public boolean skipCompatibilityCheck() {
    return skipCompatibilityCheck;
  }

  public void skipCompatibilityCheck(boolean skipCompatibilityCheck) {
    this.skipCompatibilityCheck = skipCompatibilityCheck;
  }

  public int rank() {
    return rank;
  }

  public void rank(int rank) {
    this.rank = rank;
  }
}
