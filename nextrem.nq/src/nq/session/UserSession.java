package nq.session;

import java.util.*;
import nq.catalogue.*;
import nq.sales.*;
import nq.journal.*;
import nq.orchestrating.*;
import nq.transaction.*;
import utilities.string.*;

public class UserSession {

  private NextremQuote nextremQuote;
  private Option optionBasket;
  private User user;
  private String saveToPath;
  private String exportPath;
  private String importPath;
  private String libraryPath;
  private JournalController journalController;
  private SynchronizationAgent syncAgent;
  private boolean hideParts = true;
  private TransactionManager transactionManager;


  public UserSession() {
  }
  public String getExportToPath() {
    return exportPath;
  }
  public Option getOptionBasket() {
    return optionBasket;
  }
  public JournalController getJournalController() {
    return journalController;
  }
  public String getSaveToPath() {
    return saveToPath;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public void setSaveToPath(String saveToPath) {
    this.saveToPath = saveToPath;
  }
  public void setOptionBasket(Option optionBasket) {
    this.optionBasket = optionBasket;
  }
  public void setJournalController(JournalController journalController) {
    this.journalController = journalController;
  }
  public void setExportToPath(String exportToPath) {
    this.exportPath = exportToPath;
  }


  public boolean storeOptionQuantity(String id, int quantity) {
    this.optionBasket.findOptionById(id).quantity(quantity);
    return true;
  }


  // Comment God Dammit !
  public String[][] getCatalogMatrixForCode(String parentCode, String parentId, int optionsArrayToUse) {
    Option catalogParent = this.nextremQuote.getSystemSession().optionCatalog().findOptionByCode(parentCode);
    Option basketParent = optionBasket.findOptionById(parentId);
    String[][] optionNames = new String[0][];
    if (catalogParent != null) {
      Option[] optionsArray = catalogParent.options();
      if (optionsArrayToUse == 1) {
        optionsArray = catalogParent.requiredOptions();
      }
      if (optionsArrayToUse == 2) {
        optionsArray = catalogParent.conditioners();
      }
      Vector optionNamesVector = new Vector();
      if (basketParent.type().equals("OPTCTLG")) {
        for (int i = 0; i < optionsArray.length; i++) {
          String[] temp = new String[4];
          temp[0] = optionsArray[i].getCode();
          temp[1] = optionsArray[i].getName();
          temp[2] = optionsArray[i].getGroup();
          temp[3] = optionsArray[i].getGroupDesc();
          optionNamesVector.add(temp);
        }
      }
      else {
        for (int i = 0; i < optionsArray.length; i++) {

          //if(!basketParent.hasOptionCode(optionsArray[i].getCode()) && !basketParent.hasGroupCode(optionsArray[i].getGroup())){
          if (!basketParent.hasOptionCode(optionsArray[i].getCode())) {
            String[] temp = new String[4];
            temp[0] = optionsArray[i].getCode();
            temp[1] = optionsArray[i].getName();
            temp[2] = optionsArray[i].getGroup();
            temp[3] = optionsArray[i].getGroupDesc();
            optionNamesVector.add(temp);
          }
        }
      }
      optionNames = new String[optionNamesVector.size()][];
      for (int i = 0; i < optionNamesVector.size(); i++) {
        optionNames[i] = (String[]) optionNamesVector.elementAt(i);
      }
      if (optionNames.length > 0) {
        optionNames = new StringSorter().sortMatrix(optionNames,
            this.nextremQuote.getSystemSession().optionCatalog().findOptionByCode(parentCode).type().equals("OPTCTLG") ? 1 : 0);
      }
    }
    return optionNames;
  }
  public NextremQuote getNextremQuote() {
    return nextremQuote;
  }
  public void setNextremQuote(NextremQuote nextremQuote) {
    this.nextremQuote = nextremQuote;
  }
  public SynchronizationAgent getSyncAgent() {
    return syncAgent;
  }
  public void setSyncAgent(SynchronizationAgent syncAgent) {
    this.syncAgent = syncAgent;
  }
  public boolean isHideParts() {
    return hideParts;
  }
  public void setHideParts(boolean hideParts) {
    this.hideParts = hideParts;
  }
  public TransactionManager getTransactionManager() {
    return transactionManager;
  }
  public void setTransactionManager(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }
  public String getImportPath() {
    return importPath;
  }
  public void setImportPath(String importPath) {
    this.importPath = importPath;
  }
  public String getLibraryPath() {
    return libraryPath;
  }
  public void setLibraryPath(String libraryPath) {
    this.libraryPath = libraryPath;
  }
  public String getExportPath() {
    return exportPath;
  }
  public void setExportPath(String exportPath) {
    this.exportPath = exportPath;
  }




}
