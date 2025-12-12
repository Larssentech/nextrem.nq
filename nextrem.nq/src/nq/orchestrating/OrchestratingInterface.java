package nq.orchestrating;

import nq.transaction.TransactionPack;
import nq.sales.User;
import nq.io.input.CatalogBuilder;
import nq.catalogue.Option;
import nq.sales.*;
import nq.transaction.TransactionManager;

import java.util.ArrayList;

public interface OrchestratingInterface {

  public Option getCatalogOptionByCode(String code);
  public TerritoryManager getTMByName(String name);

  // DO WE DELETE THESE?
  public Option getBasketForUser(User user);

  public TransactionPack addOptionToUnit(User user, String optionCodeToAdd,
                                       String parentId, boolean enforceAI,
                                       boolean enforceUniqueness);

  // IO methods
  public boolean exportBasketToCSV(User user);
  public boolean openQuoteFromFile(User user, String libraryFileName);
  public String saveBasketToFile(User user, String libraryName);
  public String saveBasketToFileAs(User user, String libraryName);
  public boolean saveQuoteToCSV(User user);
  public ArrayList importFromCSV(User user, String fileName);

  // Methods to obtain information about the domain model
  public String importPath(User user);
  public String getLibraryPath(User user);
  public boolean libraryPathExists();

  // Methods to request changes to the domain model
  public boolean removeOptionFromOption(User user, String optionToRemoveId);
  public void renameBasket(User user, String name);
  public void resetDiscounts(User user);
  public boolean hideParts(User user);
  public void hideParts(User user, boolean hideParts);
  public boolean clearOptionBasket(User user);
  public void createNewUserSession(User user);
  public void generateOptionCatalogForCode(String code);
  public String addPartToOption(User user, String optionCodeToAdd, String optionIdToUpdate, boolean enforceAI);
  public String addUnitToBasket(User user, String seriesCode, boolean enforceAI);

  // Methods to obtain information about the basket and its options
  public String[] getBasketItems(User user);
  public String[] getMissingGroups(User user, String id);

  // Methods to get catalog option attributes
  public String getCatalogOptionGroupByCode(String code);
  public String getCatalogOptionGroupDescByCode(String code);
  public String getCatalogOptionGroupIndexByCode(String code);
  public boolean getCatalogOptionIsGroupRequiredByCode(String code);
  public boolean getCatalogOptionIsMultipleAllowedByCode(String code);
  public boolean getCatalogOptionIsGroupMandatoryByCode(String code);
}
