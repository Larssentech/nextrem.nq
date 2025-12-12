// (c) 2006-2007 NextremSoft

package nq.orchestrating;

import java.io.*;
import java.util.*;

import jdbc.*;
import nq.catalogue.*;
import nq.io.input.*;
import nq.io.output.*;
import nq.journal.*;
import nq.sales.*;
import nq.session.*;
import nq.transaction.*;
import utilities.settings.*;
import utilities.string.*;

public class NextremQuote implements OrchestratingInterface {

  private final String internalVersion = "1.3.0.0 [Build 070701]";
  private String version = internalVersion.substring(0, 8);

  private SystemSession systemSession;
  private QuoteSaver quoteSaver = new QuoteSaver();

  private HashMap UserSessions;

  private final String sep = System.getProperty("file.separator");

  private CatalogBuilder catalogBuilder;
  private DiscountsGenerator discountsGenerator;

  private String partsFileName, optionsFilename, discountFileName, requiredFileName;

  public NextremQuote(User user) {


    // Buy some time here...
    System.out.println();
    System.out.print("Orchestrating class");
    System.out.println("\tOK");

    // Read client details
    this.version += " - " + new SettingsExtractor().extractThis("config" + sep + "nextremquote.ini", "client_name");
    this.partsFileName = new SettingsExtractor().extractThis("config" + sep + "nextremquote.ini", "client_op_config1");
    this.optionsFilename = new SettingsExtractor().extractThis("config" + sep + "nextremquote.ini", "client_op_config2");
    this.discountFileName = new SettingsExtractor().extractThis("config" + sep + "nextremquote.ini", "client_discount");
    this.requiredFileName = new SettingsExtractor().extractThis("config" + sep + "nextremquote.ini",
        "client_op_required");

    // Create Information Publishing interface
    this.systemSession = new SystemSession();

    // Create the catalog generator and get the initial catalog

    catalogBuilder = new CatalogBuilder("config" + sep + this.partsFileName);

    System.out.print(".");
    this.systemSession.optionCatalog(catalogBuilder.buildUnitCatalogPass1());
    System.out.print(".");

    // Process required groups
    this.systemSession.requiredGroupsHashtable(catalogBuilder.processRequiredGroups("config" + sep +
        this.requiredFileName));
    System.out.print("..");


    // Create the sales territory structure and pass it to the info publisher
    this.systemSession.salesTerritory(new SalesStructureGenerator().generateSalesStructureFromFile("config" + sep +
        this.discountFileName));

    if (this.systemSession.salesTerritory().findTmByName(user.getName()) == null) {
      System.out.println("USER NOT AUTHORIZED");
      System.exit(1);
    }
    else {
      System.out.println(".");
      System.out.print("Validating user...");
      System.out.println("\tOK");
      System.out.println();
    }

    // Create the Discount Generator so we can get Discs and Maxs dynamically
    this.discountsGenerator = new DiscountsGenerator("config" + sep + discountFileName);

    // Initialise the HashMap
    this.UserSessions = new HashMap();

    // Create the default Client Session
    this.createNewUserSession(user);
  }

  public void createNewUserSession(User user){
    // Create a new basket
    Option myBasket = this.createOptionBasket(user);

    // Create a transaction manager
    TransactionManager myManager = new TransactionManager(this, user);

    // Create Agents
    JournalController myJournalController = new JournalController();
    SynchronizationAgent mySyncAgent = new SynchronizationAgent(myManager, myJournalController);

    // Set paths
    String myLibraryPath = "quotes";
    String myImportPath = "import";
    String myExportPath = "export";


    // Create a new client session
    UserSession myUserSession = new UserSession();

    // Configure it
    myUserSession.setNextremQuote(this);
    myUserSession.setLibraryPath(myLibraryPath);
    myUserSession.setSaveToPath(myLibraryPath);
    myUserSession.setImportPath(myImportPath);
    myUserSession.setExportPath(myExportPath);

    myUserSession.setUser(user);
    myUserSession.setTransactionManager(myManager);

    // Add the basket to the session
    myUserSession.setOptionBasket(myBasket);
    myUserSession.setJournalController(myJournalController);
    myUserSession.setSyncAgent(mySyncAgent);

    // Add the session to the hashmap
    this.UserSessions.put(user, myUserSession);
  }

  public void printUserSessions(){
    Iterator ite = this.UserSessions.values().iterator();
    while(ite.hasNext()){
      UserSession thisUserSession = (UserSession)ite.next();
      System.out.println();
      System.out.println(thisUserSession.toString() + " - " +
                         (thisUserSession).getUser().toString());
      System.out.println();
    }
  }

  public ArrayList importFromCSV(User user, String fileName) {
    return new CSVImporter(this, user).importFromFile(fileName);
  }

  public SystemSession getSystemSession() {
    return this.systemSession;
  }

  public void saveJournal(User user) {
    this.getUserSessionForUser(user).getJournalController().saveJournal();
  }

  public String getLibraryPath(User user) {
    return this.getUserSessionForUser(user).getSaveToPath();
  }

  /**
   * Method to get the right basket for a given user
   * @param user User
   * @return Option
   */
  public Option getBasketForUser(User user){
    return ((UserSession)this.UserSessions.get(user)).getOptionBasket();
  }

  /**
   *
   * @param user User
   * @param basket Option
   */
  public void setBasketForUser(User user, Option basket){
    ((UserSession)this.UserSessions.get(user)).setOptionBasket(basket);
  }

  public
      UserSession getUserSessionForUser(User user){
    return (UserSession)this.UserSessions.get(user);
  }

  public void renameBasket(User user, String name) {
    this.getBasketForUser(user).name(name);
  }

  public void sync(User user) {
    this.getUserSessionForUser(user).getSyncAgent().sync();
  }

  public void registerDB() {
    try {
      Runtime.getRuntime().exec("ODBC.bat");
    }
    catch (Exception e) {
      System.out.println("Runtime error: " + e);
    }
  }

  public boolean saveQuoteToCSV(User user) {
    return
        new CSVExporter().saveQuoteToCSV
        (user, this.getBasketForUser(user),
         System.getProperty("user.home") + System.getProperty("file.separator") +
         "Desktop" + System.getProperty("file.separator") +
         "Unit Builder Export " +
         new StringManipulationToolkit().replaceAll(new Date().toString(), ":", ".") + ".csv");
  }

  public boolean uploadBasket(User user) {
    String[][] records = new StringManipulationToolkit().
        create2DArrayFromVector(new OptionExplorer().convertQuoteToCSVExportFormat(user,
        this.getBasketForUser(user), this.getUserSessionForUser(user).isHideParts(), !user.isSuperUser()));
    return new DBConn().writeData("efmcognos_midm_sqlserver", records);
  }

  public boolean exportBasketToCSV(User user) {
    return
        new CSVExporter().exportBasketToCSV
        (user, this.getBasketForUser(user), this.getUserSessionForUser(user).isHideParts(), !user.isSuperUser(),
         System.getProperty("user.home") + System.getProperty("file.separator") +
         "Desktop" + System.getProperty("file.separator") +
         "Unit Builder Export " +
         new StringManipulationToolkit().replaceAll(new Date().toString(), ":", ".") + ".csv");
  }

  private Option createOptionBasket(User user) {
    Option optionBasket = new Option();
    optionBasket.name("BASKET");
    optionBasket.type("OPTCTLG");
    optionBasket.code("OPTCTLG");
    optionBasket.group("N/A");
    optionBasket.groupDesc("N/A");
    optionBasket.cost(0.0);
    optionBasket.discountP100(0.0);
    optionBasket.listPrice(0.0);
    optionBasket.id(new UniqueIdGenerator().createId("OPTCTLG"));
    optionBasket.user(user);
    optionBasket.customer(new Customer());
    optionBasket.dealer(new Dealer());
    optionBasket.country(new Country("NO_COUNTRY"));
    optionBasket.currency(this.systemSession.optionCatalog().currency());

    return optionBasket;
  }

  public String internalVersion() {
    return this.internalVersion;
  }

  public String[] getBasketItems(User user) {
    return new OptionExplorer().exploreOptionsNamesAndIds
        (this.getBasketForUser(user),
         this.getUserSessionForUser(user).isHideParts());
  }

  public void generateOptionCatalogForCode(String code) {
    if (this.systemSession.optionCatalog().findOptionByCode(code).options().length == 0) {
      this.systemSession.optionCatalog(catalogBuilder.buildOptionCatalogForCodePass1(code,
          this.systemSession.optionCatalog(),
          this.systemSession.requiredGroupsHashtable(), this.systemSession.multiplesHashtable()));
      this.systemSession.optionCatalog
          (catalogBuilder.buildOptionCatalogForCodePass2(code, this.systemSession.optionCatalog(),
          this.systemSession.requiredGroupsHashtable(), this.systemSession.multiplesHashtable(),
          "config" + sep + this.optionsFilename));
    }
  }

  // Units will always have 1-op-per-group enforced as this does not apply to them
  // It only applies to Options
  public String addUnitToBasket(User user, String seriesCode, boolean enforceAI) {
    TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();
    TransactionPack tPack = thisTransactionManager.addOptionToOption2(seriesCode,
        this.getBasketForUser(user).id(), enforceAI, true);

    return tPack.optionToAdd().id();
  }

  // NEW -  Adds option to unit and returns a TransactionPack so other parts
  // of the program can assess what has happened
  public TransactionPack addOptionToUnit(User user, String optionCodeToAdd, String parentId, boolean enforceAI,
                                         boolean enforceUniqueness) {

    TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();

    TransactionPack transactionPack = thisTransactionManager.addItem(optionCodeToAdd, parentId, enforceAI, enforceUniqueness);

    if (transactionPack.transactionCompleted()) {
      thisTransactionManager.optionRulesProcessor().setConditionedPrices(parentId);
    }
    new OptionAttributeModifier().resetDiscounts(this.getBasketForUser(user), this.discountsGenerator);
    return transactionPack;
  }

  // Parts will always have 1-op-per-group enforced as this does not apply to them
  // It only applies to Options
  public String addPartToOption(User user, String optionCodeToAdd, String optionIdToUpdate, boolean enforceAI) {
    TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();
    return thisTransactionManager.addItem(optionCodeToAdd, optionIdToUpdate, enforceAI, true).optionToAdd().id();
  }

  public void resetDiscounts(User user) {
    new OptionAttributeModifier().resetDiscounts(this.getBasketForUser(user), this.discountsGenerator);
  }

  public boolean clearOptionBasket(User user) {
    TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();
    this.quoteSaver.libraryPathExists(false);
    thisTransactionManager.clearOptionBasket();
    this.getBasketForUser(user).id(thisTransactionManager.createId("OPTCTLG"));
    this.getBasketForUser(user).customer(new Customer());
    return true;
  }

  public boolean openQuoteFromFile(User user, String libraryFileName) {
    // Distribute the basket from file
    this.setBasketForUser(user, new QuoteLoader().loadQuoteFromLibrary(libraryFileName));

    // Inform the QuoteSaver about the path and name of the open quote
    this.quoteSaver.previousSavePath(new File(libraryFileName).getParent());
    this.quoteSaver.libraryPathExists(true);
    String basketName = new File(libraryFileName).getName();
    if (new File(libraryFileName).getName().endsWith(".quote")) {
      basketName = new File(libraryFileName).getName().substring(0,
          new File(libraryFileName).getName().lastIndexOf(".quote"));
    }
    this.quoteSaver.previousSaveFileName(basketName);

    // Load options for these units now for efficiency
    for (int i = 0; i < this.getBasketForUser(user).options().length; i++) {
      this.generateOptionCatalogForCode(this.getBasketForUser(user).options()[i].getCode());
    }

    // Ensure the country is one we recognize and same fror the dealer and customer
    this.getBasketForUser(user).country(this.systemSession.salesTerritory().findCountryByName
                                        (this.getBasketForUser(user).country().getName()));
    this.getBasketForUser(user).dealer(this.systemSession.salesTerritory().findDealerByCode
                                       (this.getBasketForUser(user).dealer().getCode()));

    // Reset discounts
    this.resetDiscounts(user);
    return true;
  }

  public boolean removeOptionFromOption(User user, String optionToRemoveId) {
    TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();

    boolean removed = thisTransactionManager.removeOptionFromOption(optionToRemoveId);
    thisTransactionManager.optionRulesProcessor().setConditionedPrices(this.getBasketForUser(user).id());
    return removed;
  }

  public String saveBasketToFile(User user, String libraryName) {
    String savedFile =
        quoteSaver.saveQuoteToFile(this.getBasketForUser(user),
                                   this.getUserSessionForUser(user).getSaveToPath(),
                                   libraryName,
                                   this.version);

    if (new File(savedFile).isFile()) {
      JournalRecord newRecord = new JournalRecord();
      newRecord.libraryName(savedFile);
      newRecord.id(this.getBasketForUser(user).id());
      this.getUserSessionForUser(user).getJournalController().insertRecord(newRecord);
    }
    return savedFile;
  }

  public String saveBasketToFileAs(User user, String libraryName) {
    // Give it a nu ID and nu Key so it gets synced
     TransactionManager thisTransactionManager = this.getUserSessionForUser(user).getTransactionManager();

    this.getBasketForUser(user).id(thisTransactionManager.createId("OPTCTLG"));

    String savedFile =
        quoteSaver.saveQuoteToFile(this.getBasketForUser(user),
                                   this.getUserSessionForUser(user).getSaveToPath(),
                                   libraryName,
                                   this.version);
    if (new File(savedFile).isFile()) {
      JournalRecord newRecord = new JournalRecord();
      newRecord.libraryName(savedFile);
      newRecord.id(this.getBasketForUser(user).id());
      this.getUserSessionForUser(user).getJournalController().insertRecord(newRecord);
    }
    return savedFile;
  }

  public String[] getMissingGroups(User user, String id) {
    return new OptionExplorer().checkRequiredGroupsPresent(this.getBasketForUser(user).findOptionById(id),
        this.systemSession.requiredGroupsHashtable());
  }

  public void printJournal(User user) {
    this.getUserSessionForUser(user).getJournalController().printJournal();
  }

  public boolean hideParts(User user) {
    return this.getUserSessionForUser(user).isHideParts();
  }

  public void hideParts(User user, boolean hideParts) {
    this.getUserSessionForUser(user).setHideParts(hideParts);
  }

  public String version() {
    return this.version;
  }

  public void version(String version) {
    this.version = version;
  }

  public boolean libraryPathExists() {
    return this.quoteSaver.libraryPathExists();
  }

  public String importPath(User user) {
    return this.getUserSessionForUser(user).getImportPath();
  }

  public TerritoryManager getTMByName(String name){
    return this.systemSession.salesTerritory().findTmByName(name);
  }

  public String getCatalogOptionGroupByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).getGroup();
  }

  public String getCatalogOptionGroupDescByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).getGroupDesc();
  }

  public String getCatalogOptionGroupIndexByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).getGroupIndex() + "";
  }

  public boolean getCatalogOptionIsGroupRequiredByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).isGroupRequired();
  }

  public boolean getCatalogOptionIsMultipleAllowedByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).isMultipleAllowed();
  }

  public boolean getCatalogOptionIsGroupMandatoryByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code).isMandatory();
  }

  public Option getCatalogOptionByCode(String code){
    return this.systemSession.optionCatalog().findOptionByCode(code);
  }


}
