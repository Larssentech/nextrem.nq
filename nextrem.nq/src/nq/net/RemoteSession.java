// (c) 2006-2007 NextremSoft

package nq.net;

import java.util.*;

import nq.catalogue.*;
import nq.sales.*;
import nq.session.*;
import nq.transaction.*;

import utilities.string.*;

public class RemoteSession extends LocalSession implements SessionInterface {

  private boolean changesMade;

  public HashMap lastTransactionSummary;

  private String currentUnitId = "Empty";
  private String selectedItemId = "Empty";
  private String selectedItemCode = "Empty";
  private String parentCode = "Empty";

  private final StringManipulationToolkit sMT = new StringManipulationToolkit();
  private final String spacer = "                                                                              ";

  protected RemoteNextremQuote remoteNQ;
  protected User user;

  public RemoteSession(){
  }

  public RemoteSession(RemoteNextremQuote webClient, User user) {
    this.remoteNQ = webClient;
    this.user = user;

    System.out.print("\nClient Session Class\tOK\n");

    this.changesMade = false;
  }

  public void createNewUserSession(){
    remoteNQ.createNewUserSession(this.user);
  }

  public void printUserSessions(){
    //webClient.printUserSessions();
  }
  /**
   *
   * @param fileName String
   * @return ArrayList
   */
  public ArrayList importFromCSV( String fileName) {
    return remoteNQ.importFromCSV(this.user, fileName);
  }

  //private SystemSession serverSession() {
    //return webClient.getSystemSession();
  //}

  //public void saveJournal() {
  //  webClient.saveJournal(this.user);
  //}

//  public String libraryPath() {
//    return remoteNQ.getUserSessionForUser(this.user).getLibraryPath();
//  }

  public void renameBasket( String name) {
    // User made changes

    remoteNQ.renameBasket(this.user, name);
  }

  public void sync() {
    // User made changes

    //webClient.sync(this.user);
  }

  public void registerDB() {
    //webClient.registerDB();
  }

  public boolean saveQuoteToCSV() {
    return remoteNQ.saveQuoteToCSV(this.user);
  }

  //public boolean uploadBasket() {
    // User made changes

   // return webClient.uploadBasket(this.user);
  //}

  public boolean exportBasketToCSV() {
    return remoteNQ.exportBasketToCSV(this.user);
  }

//  public String internalVersion() {
//    return remoteNQ.internalVersion();
//  }

  public String[] getBasketItems() {
    return remoteNQ.getBasketItems(this.user);
  }

  public int getBasketQuantities(){

    return 0;
  }

//  public String getOptionNameByCode(String code){
//    return this.getOptionByCode(code).getName();
//  }

  public void generateOptionCatalogForCode(String code) {
    remoteNQ.generateOptionCatalogForCode(code);
  }

  // Units will always have 1-op-per-group enforced as this does not apply to them
  // It only applies to Options
  public String addUnitToBasket(String seriesCode, boolean enforceAI) {
    // User made a change


    this.currentUnitId = remoteNQ.addUnitToBasket(this.user, seriesCode, enforceAI);
    return this.currentUnitId;
  }


  public HashMap addOptionToUnit(String optionCodeToAdd,
                                 String parentId,
                                 boolean enforceAI,
                                 boolean enforceUniqueness) {
    // User made a change


    TransactionPack tPack =
        remoteNQ.addOptionToUnit(this.user,
                          optionCodeToAdd,
                          parentId,
                          enforceAI,
                          enforceUniqueness);

    HashMap transactionSummary = tPack.toHashMap();

    // Print the HashMap just to see what there is inside
    Iterator ite = transactionSummary.keySet().iterator();
    while(ite.hasNext()) {
      String thisKey = ite.next().toString();
      System.out.println(thisKey + ": " + transactionSummary.get(thisKey).toString());
    }

    // Store the summary in case user wants to investigate further
    this.lastTransactionSummary = transactionSummary;
    return transactionSummary;
  }

  // Parts will always have 1-op-per-group enforced as this does not apply to them
  // It only applies to Options
  public String addPartToOption(String optionCodeToAdd, String optionIdToUpdate, boolean enforceAI) {
    // User made a change


    return remoteNQ.addPartToOption(this.user, optionCodeToAdd, optionIdToUpdate, enforceAI);
  }

  public void resetDiscounts() {
    // User made changes

    remoteNQ.resetDiscounts(this.user);
  }

  public boolean clearOptionBasket() {
    return remoteNQ.clearOptionBasket(this.user);
  }

  public boolean openQuoteFromFile(String libraryFileName) {
    return remoteNQ.openQuoteFromFile(this.user, libraryFileName);
  }

  public boolean removeOptionFromOption(String optionToRemoveId) {
    // User made changes

    return remoteNQ.removeOptionFromOption(this.user, optionToRemoveId);
  }

  public String saveBasketToFile( String libraryName) {
    // User saved changes
    this.setChangesMade(false);
    return remoteNQ.saveBasketToFile(this.user, libraryName);
  }

  public String saveBasketToFileAs(String libraryName) {
    // User saved changes
    this.setChangesMade(false);
    return remoteNQ.saveBasketToFileAs(this.user, libraryName);
  }

//  public void printJournal() {
//    remoteNQ.printJournal(this.user);
//  }

  public boolean hideParts() {
    return remoteNQ.hideParts(this.user);
  }

  public void hideParts(boolean hideParts) {
    remoteNQ.hideParts(this.user, hideParts);
  }

//  public String version() {
//    return remoteNQ.version();
//  }

  public String importPath() {
    return remoteNQ.importPath(this.user);
  }


  public void doSelectOption(){

  }

  // Methods to obtain the basket for our user or arbitrary catalog
  // options to extract attributes from. Will only be used internally
  // so they are private.
  private Option getBasket(){
    return remoteNQ.getBasketForUser(this.user);
  }

  private Option getCatalogOptionByCode(String code){
    return this.remoteNQ.getCatalogOptionByCode(code);
  }

  public String doFindMissingGroups(){
    String missingGroupsString = "";

    // Get the option that is currently selected
    Option thisOption = this.remoteNQ.getBasketForUser(user).findOptionById(this.currentUnitId);

    // Which must be a unit
    if (thisOption.type().equals("UNIT")) {

      // And get the missing groups for it
      String[] missingGroups = remoteNQ.getMissingGroups(user, this.currentUnitId);

      for (int i = 0; i < missingGroups.length; i++) {

        missingGroupsString +=
            remoteNQ.getCatalogOptionByCode(thisOption.getCode()).
            getGroupDescriptionForGroup(missingGroups[i])
            + " (" + missingGroups[i] + ")\n";
      }

    }
    return missingGroupsString;
  }
  public String getCurrentUnitId() {
    return currentUnitId;
  }
  public void setCurrentUnitId(String currentUnitId) {
    // User made changes

    this.currentUnitId = currentUnitId;
  }
  public String getSelectedItemCode() {
    return selectedItemCode;
  }
  public String getSelectedItemId() {
    return selectedItemId;
  }
  public void setSelectedItemId(String selectedItemId) {
    // User made changes

    this.selectedItemId = selectedItemId;
  }
  public void setSelectedItemCode(String selectedItemCode) {
    // User made changes

    this.selectedItemCode = selectedItemCode;
  }
  public String getParentCode() {
    return parentCode;
  }
  public void setParentCode(String parentCode) {
    // User made changes

    this.parentCode = parentCode;
  }

  public boolean libraryPathExists(){
    return remoteNQ.libraryPathExists();
  }

  public void selectOption(String optionId){
    // Set the "selected item" code
    Option selectedOption = this.getBasket().findOptionById(optionId);
    setSelectedItemId(optionId);
    setSelectedItemCode(selectedOption.getCode());
    if (selectedOption.type().equals("OPTCTLG")) setCurrentUnitId("");
    if (selectedOption.type().equals("UNIT")) setCurrentUnitId(getSelectedItemId());
    else if (selectedOption.type().equals("OPTION")) setCurrentUnitId(selectedOption.parentId());
    else if (selectedOption.type().equals("PART"))
      setCurrentUnitId(this.getBasket().findOptionById(selectedOption.parentId()).parentId());
    setParentCode(this.getBasket().findOptionById(getSelectedItemId()).parentCode());

  }

  public boolean checkMissingOptions(){
    boolean incompleteUnitFound = false;
    Option basket = this.getBasket();
    for (int i = 0; i < basket.options().length; i++) {
      if (basket.options()[i].type().equals("UNIT")) {
        if (remoteNQ.getMissingGroups(this.user, basket.options()[i].id()).length > 0) {
          incompleteUnitFound = true;
          break;
        }
      }
    }
    return incompleteUnitFound;
  }

  public boolean checkExcessiveDiscount(){
    // Check current unit for discount problems
    boolean discountProblem = false;
    Option thisUnit = this.getBasket().findOptionById(currentUnitId);
    if (thisUnit.discountP100() > thisUnit.maxDiscP100()) return true;
    else{ // Check and warn if discount > Max in any unit in the basket (semaphore)
      for (int i = 0; i < this.getBasket().options().length; i++) {
        Option thisTempUnit = this.getBasket().options()[i];
        if (thisTempUnit.discountP100() > thisTempUnit.maxDiscP100()) {
          discountProblem = true;
          break;
        }
        else {
          discountProblem = false;
        }
      }
    }
    return discountProblem;
  }

  public void setMarkup(String markup){
    // User made changes

    new OptionAttributeModifier().saveMarkup(this.getBasket(), markup);
  }

  public ArrayList getOptionsForGroupAndSeries(String group, String parentId, boolean hideIncompatible) {
    ArrayList myOptions = new ArrayList();
    //ArtificialIntelligenceProcessor aiProcessor = new ArtificialIntelligenceProcessor(this.webClient,  this.user);

     Option basketParentOption = this.getBasket().findOptionById(parentId);
     Option catalogParentUnit = this.remoteNQ.getCatalogOptionByCode(basketParentOption.getCode());
     Option[] options = catalogParentUnit.findOptionsByGroup(group);
     for (int i = 0; i < options.length; i++) {

       // Here we now check for compatibility. If an opiton is compatible, then we add it otherwise not
       // when the user chooses 'show compatible only', otherwise, we show all
       if (hideIncompatible) {
         //if (aiProcessor.checkOptionCompatibility(options[i].getCode(), parentId)) {
           myOptions.add(options[i].getName() + spacer + "(" + options[i].getCode() + ")");
         //}
       }
       //else {
       //  myOptions.add(options[i].getName() + spacer + "(" + options[i].getCode() + ")");
       //}
     }
     return myOptions;
   }


   public ArrayList doLoadGroupsForSeries1(String parentId,
                                            boolean hidePresent,
                                            boolean hideUnrequired) {
     ArrayList myGroups = new ArrayList();
     String spacer = "                                                                              ";
     //ArtificialIntelligenceProcessor aiProcessor =
     //    new ArtificialIntelligenceProcessor(this.webClient, this.user);
     Option basketParentOption = this.getBasket().findOptionById(parentId);
     Option catalogParentUnit = this.remoteNQ.getCatalogOptionByCode(basketParentOption.getCode());

     // Get all catalog groups for this series
     this.remoteNQ.generateOptionCatalogForCode(catalogParentUnit.getCode());
     String[] groups = catalogParentUnit.getGroups(); // Unique group codes

     // Set up an ArrayList to store the strings temporarily to sort
     java.util.ArrayList unsortedGroups = new java.util.ArrayList();
     for (int i = 0; i < groups.length; i++) {
       Option[] optionsInGroup = catalogParentUnit.findOptionsByGroup(groups[i]);

       // Create a sorting key to order the gfroup descriptions by it
       String groupDescription =
           catalogParentUnit.getGroupDescriptionForGroup(groups[i]) +
           spacer + "(" + groups[i] + ") ";

       String orderKey = optionsInGroup[0].getGroupIndex() < 10 ? "0" + optionsInGroup[0].getGroupIndex() :
           "" + optionsInGroup[0].getGroupIndex();

       // If user has chosen to hide groups already in
       if (hidePresent) {
         if (!basketParentOption.hasGroupCode(groups[i])) {

           // If user has also chosen to only show 'required' groups
           if (hideUnrequired) {
             if (optionsInGroup[0].isGroupRequired()) {
               unsortedGroups.add(new String[] {groupDescription, orderKey});
             }
           }

           // If user wants to see all goups regardless of whether they are required
           else {
             unsortedGroups.add(new String[] {groupDescription, orderKey});
           }
         }
       }

       // If user wants to see all groups regardless of whether they are in the basket already
       else {

         // If user has also chosen to only show 'required' groups
         if (hideUnrequired) {
           if (optionsInGroup[0].isGroupRequired()) {
             unsortedGroups.add(new String[] {groupDescription, orderKey});
           }
         }

         // If user wants to see all goups regardless of whether they are required
         else {
           unsortedGroups.add(new String[] {groupDescription, orderKey});
         }
       }
     }

     // Sort the ArrayList
     String[][] mySortingArray = new String[unsortedGroups.size()][];
     java.util.Iterator it = unsortedGroups.iterator();
     for (int i = 0; it.hasNext(); i++) {
       mySortingArray[i] = (String[]) it.next();
     }
     mySortingArray = new StringSorter().sortMatrix(mySortingArray, 1);

     for (int i = 0; i < mySortingArray.length; i++) {
       myGroups.add(mySortingArray[i][0]);
     }
     return myGroups;
   }

   public ArrayList doSelectOption(String parentId,
                                String optionCode,
                                boolean hideIncompatible) {
     ArrayList myParts = new ArrayList();
     String spacer = "                                                                              ";

     //ArtificialIntelligenceProcessor aiProcessor =
     //    new ArtificialIntelligenceProcessor(this.webClient, this.user);
     StringManipulationToolkit sMT = new StringManipulationToolkit();
     Option selectedOption =
         remoteNQ.getCatalogOptionByCode(optionCode);

     for (int i = 0; i < selectedOption.options().length; i++) {

       if (hideIncompatible) {
         //if (aiProcessor.checkPartCompatibility(selectedOption.options()[i].getCode(), parentId) >= 0) {
         //  myParts.add(selectedOption.options()[i].getName() +
         //              spacer + "(" + selectedOption.options()[i].getCode() + ")");
         //}
      }
      else { // Even if -1 arrives, incompatibility
        myParts.add(selectedOption.options()[i].getName() + spacer + "(" + selectedOption.options()[i].getCode() + ")");
      }
    }
    return myParts;
  }



  public String getBasketCustomerName(){
    return this.getBasket().customer().getName();
  }

  public String setBasketCustomerName(String customerName){
    // User made changes

    this.getBasket().customer().setName(sMT.getAlphaNumericString(customerName));
    return this.getBasket().customer().getName();
  }


//  public String[][] getCatalogMatrixForCode(String parentCode,
//                                            int optionsArrayToUse){
//    return remoteNQ.getUserSessionForUser(this.user).
//        getCatalogMatrixForCode(parentCode, this.selectedItemId, optionsArrayToUse);
//
//  }

  public String[] getCountryNamesForUserName(){

    // Create reference for the countries array
    Country[] countries = new Country[0];

    // All RMs are TMs as well
    TerritoryManager tm = remoteNQ.getTMByName(this.user.getName());

    // If he is a TM, get  his countries
    if (tm != null)  countries = tm.countries();
    else System.exit(1);

    String[] countryNames = new String[countries.length];
    for(int i=0;i <countryNames.length; i++) countryNames[i] = countries[i].getName();
    return countryNames;
  }

  public ArrayList getDealersForTmAndCountry(String tmName, String countryName) {
    ArrayList myDealers = new ArrayList();

    // Get the TM class for the user
    if (this.getBasket() != null) {
      TerritoryManager tm = remoteNQ.getTMByName(this.user.getName());

      // If the TM is the person who is logged onto NextremQuote...
      if (tm != null) {
        // Get the country the basket refers to
        Country country = tm.findCountryByName(this.getBasket().country().getName());

        // Then get the dealers for this country
        if (country != null) {
          Dealer[] dealers = country.dealers();

          // We got TM and Country, does he have dealers?
          if (dealers != null) {
            for (int i = 0; i < dealers.length; i++) {
              myDealers.add(dealers[i].getCode() + " - " + dealers[i].getName());
            }
          }
        }
        // If user is TM but he has an basket that is not his: exit
        else System.exit(1);
      }
      // If user is not TM: exit
      else System.exit(1);
    }
    return myDealers;
  }


  public String getUserName(){
    return this.user.getName();
  }

  // What on Earth is this...?
  public String setDiscountP100(String id, String value){
    // User made changes

    // Store hte new discount in the selected option
    new OptionAttributeModifier().storeDiscountP100(id, this.getBasket(), Double.parseDouble(value));

    // Select parent, recalculate discount and then select this option again
    // This is a way to make the system aware of this option's discount before the user
    // selects the parent
    String previousId = id;
    id = this.getBasket().findOptionById(id).parentId();
    id = previousId;
    return this.getOptionDiscP100(id);
  }

//  public String setQuantity(String id, String value){
//    // User made changes
//
//    this.remoteNQ.getUserSessionForUser(this.user).
//        storeOptionQuantity(id,
//                            (int) Double.parseDouble
//                            (new StringManipulationToolkit().
//                             removeNumberFormatting(value)));
//    return this.getOptionQuantity(id);
//  }

//  public void setBasketCountry(String countryName){
//    // User made changes
//
//    this.remoteNQ.setBasketCountry(countryName);
//  }
//
//  public void setBasketDealer(String dealerCode){
//    // User made changes
//
//    this.remoteNQ.setBasketDealer(dealerCode);
//  }

  public String getBasketCountryName(){
    return this.getBasket().country().getName();
  }

  public String getBasketDealerName(){
    return this.getBasket().dealer().getName();
  }

  public String getBasketDealerCode(){
    return this.getBasket().dealer().getCode();
  }

  public String getOptionName(String id){
    return this.getOption(id).getName();
  }
  public String getOptionGroup(String id){
    return this.getOption(id).getGroup();
  }

  public String getOptionGroupDesc(String id){
    return this.getOption(id).getGroupDesc();
  }

  public String getOptionCode(String id){
    return this.getOption(id).getCode();
  }

  public String getBasketCurrency(){
    return this.getBasket().currency();
  }

  public String[] getOptionDaughtersNames(String id){
    Option[] daughters = this.getBasket().findOptionById(id).options();
    String[] daughterNames = new String[daughters.length];
    for (int i = 0; i < daughters.length; i++) daughterNames[i] = "- " + daughters[i].getName();
    return daughterNames;
  }

  public String[] getOptionConditionerNames(String id){
    Option[] conditioners =  this.getBasket().findOptionById(id).conditioners();
    String[] conditionerNames = new String[conditioners.length];
    for (int i = 0; i < conditioners.length; i++)
      conditionerNames[i] = "- " + conditioners[i].getName() + ": " + conditioners[i].listPrice() + "\n";
    return conditionerNames;
  }

  /**
   * Look up the option in the catalogue and find its attributes
   * @param id String
   * @return String[]
   */
  public String[] getOptionAttributeNames(String id) {
    Option basketOption = this.getBasket().findOptionById(id);
    Option catalogOption = this.remoteNQ.getCatalogOptionByCode(basketOption.getCode());
    String[] attributes = new String[catalogOption.attributes().size()];
    for (int i = 0; i < catalogOption.attributeKeys().length; i++) {
      String key = catalogOption.attributeKeys()[i];
      attributes[i] = "- " + key + ": " +
          catalogOption.attributes().get(key).toString() + "\n";
    }
    return attributes;
  }

  private Option getOption(String id) {
    return this.getBasket().findOptionById(id);
  }

  public String getOptionType(String id){
    return this.getOption(id).type();
  }

  public String getOptionGroupCode(String id) {
    return this.getOption(id).getGroup();
  }

  public String getGroupDesc(String id) {
    return this.getOption(id).getGroupDesc();
  }

  public String getOptionGroupIndex(String id) {
    return "" + this.getOption(id).getGroupIndex();
  }

  public boolean isOptionGroupRequired(String id) {
    return this.getOption(id).isGroupRequired();
  }

  public boolean isOptionMultipleAllowed(String id) {
    return this.getOption(id).isMultipleAllowed();
  }

  public boolean isOptionGroupMandatory(String id){
    return this.getOption(id).isMandatory();
  }

  public String getOptionListPrice(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    return sMT.insertThousandSeparator( (int) Math.round(listPrice) + "");
  }
  public String getOptionNetPrice(String id){
    double netPrice = this.getOption(id).rollUpNetPrice();
    return sMT.insertThousandSeparator( (int) Math.round(netPrice) + "");
  }
  public String getOptionCost(String id){
    double cost = this.getOption(id).rollUpCost();
    return sMT.insertThousandSeparator( (int) Math.round(cost) + "");
  }

  public String getOptionDiscount(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    double netPrice = this.getOption(id).rollUpNetPrice();
    double discount = listPrice - netPrice;
    return sMT.insertThousandSeparator( (int) Math.round(discount) + "");
  }

  public String getOptionDiscP100(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    double netPrice = this.getOption(id).rollUpNetPrice();
    double discount = listPrice - netPrice;
    double discountP100;
    if (listPrice > 0) {
      discountP100 = 100 * discount / listPrice;
    }
    else {
      discountP100 = 0.0;
    }
    // If the option is a unit then set the discount to calc
    // a weighted average
    if (this.getOption(id).type().equals("UNIT")) this.getOption(id).discountP100(discountP100);

    return sMT.processP100(discountP100 + "");
  }


  public String getOptionMargin(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    double cost = this.getOption(id).rollUpCost();
    double netPrice = this.getOption(id).rollUpNetPrice();
    double discount = listPrice - netPrice;
    int margin =
        (int) Math.round(listPrice) -
        (int) Math.round(cost) -
        (int) Math.round(discount);
    return sMT.insertThousandSeparator( (int) Math.round(margin) + "");
  }
  public String getOptionMarginP100(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    double cost = this.getOption(id).rollUpCost();
    double netPrice = this.getOption(id).rollUpNetPrice();
    double discount = listPrice - netPrice;
    int margin =
        (int) Math.round(listPrice) -
        (int) Math.round(cost) -
        (int) Math.round(discount);

    double marginP100 = 0;
    if (listPrice > 0) {
      marginP100 = 100 * margin / listPrice;
    }
    return sMT.processP100(marginP100 + "");
  }

  public String getOptionBtd(String id){
    return "" + this.getOption(id).standardDiscP100();
  }

  public String getOptionAut(String id){
    return "" + this.getOption(id).maxDiscP100();
  }

  public String getOptionQuantity(String id){
    int quantity = this.getOption(id).quantity();
    return sMT.insertThousandSeparator("" + quantity);
  }

  public boolean isOptionBtdAboveAut(String id){
    double listPrice = this.getOption(id).rollUpListPrice();
    double netPrice = this.getOption(id).rollUpNetPrice();
    double discount = listPrice - netPrice;
    double discountP100;
    if (listPrice > 0) {
      discountP100 = 100 * discount / listPrice;
    }
    else {
      discountP100 = 0.0;
    }

    double aut = this.getOption(id).maxDiscP100();
    return discountP100 > aut;
  }



  public String getGroupCodeByCode(String code) {
    return this.remoteNQ.getCatalogOptionGroupByCode(code);
  }

  public String getGroupDescByCode(String code){
    return this.remoteNQ.getCatalogOptionGroupDescByCode(code);
  }

  public String getGroupIndexByCode(String code) {
    return "" + this.remoteNQ.getCatalogOptionGroupIndexByCode(code);
  }

  public boolean isGroupRequiredByCode(String code) {
    return this.remoteNQ.getCatalogOptionIsGroupRequiredByCode(code);
  }

  public boolean isMultipleAllowedByCode(String code) {
    return this.remoteNQ.getCatalogOptionIsMultipleAllowedByCode(code);
  }

  public boolean isGroupMandatoryByCode(String code){
    return this.remoteNQ.getCatalogOptionIsGroupMandatoryByCode(code);
  }

//  public String getListPriceByCode(String code){
//    double listPrice = this.getOptionByCode(code).rollUpListPrice();
//    return sMT.insertThousandSeparator( (int) Math.round(listPrice) + "");
//  }
//
//  public String getNetPriceByCode(String code){
//    double netPrice = this.getOptionByCode(code).rollUpNetPrice();
//    return sMT.insertThousandSeparator( (int) Math.round(netPrice) + "");
//  }
//
//  public String getCostByCode(String code){
//    double cost = this.getOptionByCode(code).rollUpCost();
//    return sMT.insertThousandSeparator( (int) Math.round(cost) + "");
//  }
//
//  public String getBtdByCode(String code){
//    return "" + this.getOptionByCode(code).standardDiscP100();
//  }
//
//  public String getAutByCode(String code){
//    return "" + this.getOptionByCode(code).maxDiscP100();
//  }
//
//  public String[] getOptionAttributeNamesByCode(String code) {
//    Option catalogOption = remoteNQ.getSystemSession().optionCatalog().findOptionByCode(code);
//    String[] attributes = new String[catalogOption.attributes().size()];
//    for (int i = 0; i < catalogOption.attributeKeys().length; i++) {
//      String key = catalogOption.attributeKeys()[i];
//      attributes[i] = "- " + key + ": " +
//          catalogOption.attributes().get(key).toString() + "\n";
//    }
//    return attributes;
//  }

  public HashMap getLastTransactionSummary(){
    return this.lastTransactionSummary;
  }
  public boolean isChangesMade() {
    return changesMade;
  }

  public void setChangesMade(boolean changesMade) {
    this.changesMade = changesMade;
    if(changesMade) System.out.println("Changes made...");
    else System.out.println("Changes saved...");
  }

  public String getOptionParentId(String id){
    return this.getOption(id).parentId();
  }

  public double getBasketRollUpListPrice(){
    return this.getBasket().rollUpListPrice();
  }

  public double getBasketRollUpNetPrice(){
    return this.getBasket().rollUpNetPrice();
  }

  public double getBasketRollUpCost(){
    return this.getBasket().rollUpCost();
  }

  public double getBasketMarkup(){
    return this.getBasket().markup();
  }
  public int getBasketDaughterQuantity(){
    int units = 0;
    for (int i = 0; i < this.getBasket().options().length; i++) {
      units += this.getBasket().options()[i].quantity();
    }
    return units;
  }
}
