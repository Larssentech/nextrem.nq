package nq.session;

import nq.catalogue.Option;

import java.util.HashMap;
import java.util.ArrayList;

public interface SessionInterface {

  // Methods to add items to other items
  public HashMap addOptionToUnit(String optionCodeToAdd, String parentId, boolean enforceAI, boolean enforceUniqueness);
  public String addPartToOption(String optionCodeToAdd, String optionIdToUpdate, boolean enforceAI);
  public String addUnitToBasket(String seriesCode, boolean enforceAI);

  // Methods to check for exceptional circumstances
  public boolean checkExcessiveDiscount();
  public boolean checkMissingOptions();
  public String doFindMissingGroups();

  public boolean clearOptionBasket();

  public void createNewUserSession();

  public ArrayList doLoadGroupsForSeries1(String parentId, boolean hidePresent, boolean hideUnrequired);

  public void doSelectOption();

  public ArrayList doSelectOption(String parentId, String optionCode, boolean hideIncompatible);

  public boolean exportBasketToCSV();

  public void generateOptionCatalogForCode(String code);

  // Getter for basket attributes
  // All will be Strings, String[], boolean or numbers
  public int getBasketDaughterQuantity();
  public String getBasketCountryName();
  public String getBasketCurrency();
  public String getBasketCustomerName();
  public String getBasketDealerCode();
  public String getBasketDealerName();
  public double getBasketRollUpListPrice();
  public double getBasketRollUpNetPrice();
  public double getBasketRollUpCost();
  public double getBasketMarkup();
  public String[] getBasketItems();
  public int getBasketQuantities();

  // Getters for options by id
  // All will be Strings, String[], boolean or numbers
  public String getOptionBtd(String id);
  public String getOptionAut(String id);
  public String getOptionCost(String id);
  public String getOptionDiscP100(String id);
  public String getOptionDiscount(String id);
  public String getOptionGroupCode(String id);
  public String getOptionGroupIndex(String id);
  public String getOptionListPrice(String id);
  public String getOptionGroup(String id);
  public String getOptionGroupDesc(String id);
  public String getOptionName(String id);
  public String getOptionMargin(String id);
  public String getOptionMarginP100(String id);
  public String getOptionNetPrice(String id);
  public String getOptionCode(String id);
  public String getOptionParentId(String id);
  public String getOptionQuantity(String id);
  public String getOptionType(String id);
  public boolean isOptionGroupMandatory(String id);
  public boolean isOptionBtdAboveAut(String id);
  public boolean isOptionGroupRequired(String id);
  public boolean isOptionMultipleAllowed(String id);
  public String[] getOptionAttributeNames(String id);
  public String[] getOptionConditionerNames(String id);
  public String[] getOptionDaughtersNames(String id);

  // Getters for options by code
  // All will be Strings, String[], boolean or numbers
  public String getBtdByCode(String code);
  public String getAutByCode(String code);
  public String getCostByCode(String code);
  public String getGroupCodeByCode(String code);
  public String getOptionNameByCode(String code);
  public String getGroupDescByCode(String code);
  public String getGroupIndexByCode(String code);
  public String getListPriceByCode(String code);
  public String getNetPriceByCode(String code);
  public String[] getOptionAttributeNamesByCode(String code);
  public boolean isGroupMandatoryByCode(String code);
  public boolean isGroupRequiredByCode(String code);
  public boolean isMultipleAllowedByCode(String code);



  public String[][] getCatalogMatrixForCode(String parentCode, int optionsArrayToUse);

  public String[] getCountryNamesForUserName();

  public String getCurrentUnitId();

  public ArrayList getDealersForTmAndCountry(String tmName, String countryName);

  public HashMap getLastTransactionSummary();

  public ArrayList getOptionsForGroupAndSeries(String group, String parentId, boolean hideIncompatible);

  public String getParentCode();

  public String getSelectedItemCode();

  public String getSelectedItemId();

  public String getUserName();

  public boolean hideParts();

  public void hideParts(boolean hideParts);

  public ArrayList importFromCSV(String fileName);

  public String importPath();

  public String internalVersion();

  public boolean isChangesMade();

  public String libraryPath();

  public boolean libraryPathExists();

  public boolean openQuoteFromFile(String libraryFileName);

  public void printJournal();

  public void printUserSessions();

  public void registerDB();

  public boolean removeOptionFromOption(String optionToRemoveId);

  public void renameBasket(String name);

  public void resetDiscounts();

  public String saveBasketToFile(String libraryName);

  public String saveBasketToFileAs(String libraryName);

  public void saveJournal();

  public boolean saveQuoteToCSV();

  public void selectOption(String optionId);

  public void setBasketCountry(String countryName);

  public void setBasketDealer(String dealerCode);

  public void setChangesMade(boolean changesMade);

  public void setCurrentUnitId(String currentUnitId);

  public String setBasketCustomerName(String customerName);

  public String setDiscountP100(String id, String value);

  public void setMarkup(String markup);

  public void setParentCode(String parentCode);

  public String setQuantity(String id, String value);

  public void setSelectedItemCode(String selectedItemCode);

  public void setSelectedItemId(String selectedItemId);

  public void sync();

  public boolean uploadBasket();

  public String version();
}
