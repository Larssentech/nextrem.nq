// (c) 2006-2007 NextremSoft

package nq.net;

import java.util.*;

import nq.xml.*;
import nq.catalogue.*;
import nq.orchestrating.*;
import nq.io.output.*;
import nq.net.*;
import nq.sales.*;
import nq.transaction.*;

import utilities.string.*;

public class RemoteNextremQuote implements OrchestratingInterface {

  private NextremQuoteWebClient webClient;

  private final String sep = System.getProperty("file.separator");

  public RemoteNextremQuote(User user) {

    // Create an instance of the class responsible for the comms
    // with the server
    this.webClient = new NextremQuoteWebClient();
    this.webClient.initComms("localhost", 34001);

    System.out.println("\nRemote NextremQuote Orchestrating class...\tOK");
  }

  public void createNewUserSession(User user){
    this.webClient.sendCommand("createNewUserSession(" + user.getLogin() + ")");
  }

  /**
   * Will allow for client side CSV import (dynamic quote import)
   * @param user User
   * @param fileName String
   * @return ArrayList
   */
  public ArrayList importFromCSV(User user, String fileName) {
    //return new CSVImporter(this, user).importFromFile(fileName);
    ArrayList a = new ArrayList();
    a.add("Not Available");
    return a;
  }

  public Option getCatalogOptionByCode(String code){
    String[] XMLOptionData = this.webClient.sendCommand("getCatalogOptionByCode(" + code + ")");
    Option thisOption = new XMLObjectConstructor().constructOptionFrom(XMLOptionData);
    return thisOption;
  }

  public TerritoryManager getTMByName(String name){
    String[] XMLTMData = this.webClient.sendCommand("getTMByName(" + name + ")");
    TerritoryManager tm = new XMLObjectConstructor().constructTMFrom(XMLTMData);
    return tm;
  }

  /**
   * Method to get the right basket for a given user. We should save it
   * on memory to avoid traffic !!!
   * @param user User
   * @return Option
   */
  public Option getBasketForUser(User user) {
    String[] XMLOptionData = this.webClient.sendCommand("getBasketForUser(" + user + ")");
    Option thisOption = new XMLObjectConstructor().constructOptionFrom(XMLOptionData);
    return thisOption;
  }

  public TransactionPack addOptionToUnit(User user,
                                         String optionCodeToAdd,
                                         String parentId,
                                         boolean enforceAI,
                                         boolean enforceUniqueness) {
    String[] XMLTPackData =
        this.webClient.sendCommand("addOptionToUnit(" +
                                   user + ", " +
                                   optionCodeToAdd + ", " +
                                   parentId + ", " +
                                   enforceAI + ", " +
                                   enforceUniqueness + ")");

    TransactionPack tPack = new XMLObjectConstructor().constructTransactionPackFrom(XMLTPackData);
    return tPack;
  }


  public String getCatalogOptionGroupByCode(String code){
    return this.webClient.sendCommand("getCatalogOptionGroupByCode(" + code + ")")[0];
  }

  public String getCatalogOptionGroupDescByCode(String code){
    return this.webClient.sendCommand("getCatalogOptionGroupDescByCode(" + code + ")")[0];
  }

  public String getCatalogOptionGroupIndexByCode(String code){
    return this.webClient.sendCommand("getCatalogOptionGroupIndexByCode(" + code + ")")[0];
  }

  public boolean getCatalogOptionIsGroupRequiredByCode(String code){
    boolean b = Boolean.getBoolean(this.webClient.sendCommand("getCatalogOptionIsGroupRequiredByCode(" + code + ")")[0]);
    return b;
  }

  public boolean getCatalogOptionIsMultipleAllowedByCode(String code){
    boolean b = Boolean.getBoolean(this.webClient.sendCommand("getCatalogOptionIsMultipleAllowedByCode(" + code + ")")[0]);
    return b;
  }

  public boolean getCatalogOptionIsGroupMandatoryByCode(String code){
    boolean b = Boolean.getBoolean(this.webClient.sendCommand("getCatalogOptionIsGroupMandatoryByCode(" + code + ")")[0]);
    return b;
  }

  public String getLibraryPath(User user) {
    return this.webClient.sendCommand("getLibraryPath(" + user.getLogin() + ")")[0];
  }

  public void renameBasket(User user, String name) {
     this.webClient.sendCommand("renameBasket(" + user.getLogin() + ", " + name + ")");
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

  public boolean exportBasketToCSV(User user) {
    return false;
  }

  public String[] getBasketItems(User user) {
    return this.webClient.sendCommand("getBasketItems(" + user + ")");
  }

  public void generateOptionCatalogForCode(String code) {
    this.webClient.sendCommand("generateOptionCatalogForCode(" + code + ")");
  }

  public String addUnitToBasket(User user, String seriesCode, boolean enforceAI) {
    return this.webClient.sendCommand("addUnitToBasket(" + user + ", " + seriesCode + ", " + enforceAI + ")")[0];
  }

  // Parts will always have 1-op-per-group enforced as this does not apply to them
  // It only applies to Options
  public String addPartToOption(User user, String optionCodeToAdd, String optionIdToUpdate, boolean enforceAI) {
    return this.webClient.sendCommand
        ("addPartToOption(" + user + ", " + optionCodeToAdd + ", " + optionIdToUpdate + ", " + enforceAI + ")")[0];
  }

  public void resetDiscounts(User user) {
    this.webClient.sendCommand("resetDiscounts(" + user + ")");
  }

  public boolean clearOptionBasket(User user) {
    return Boolean.getBoolean
        (this.webClient.sendCommand
         ("clearOptionBasket(" + user + ")")[0]);

  }

  public boolean openQuoteFromFile(User user, String libraryFileName) {
    return false;
  }

  public boolean removeOptionFromOption(User user, String optionToRemoveId) {
    return Boolean.getBoolean
        (this.webClient.sendCommand
         ("removeOptionFromOption(" + user + ", " + optionToRemoveId + ")")[0]);
  }

  public String saveBasketToFile(User user, String libraryName) {
    return "Not Available";
  }

  public String saveBasketToFileAs(User user, String libraryName) {
    return "Not Available";
  }

  public String[] getMissingGroups(User user, String id) {
    return this.webClient.sendCommand("getMissingGroups(" + user + ", " + id + ")");
  }

  public boolean hideParts(User user) {
    return  Boolean.getBoolean(this.webClient.sendCommand("hideParts(" + user + ")")[0]);
  }

  public void hideParts(User user, boolean hideParts) {
    this.webClient.sendCommand("hideParts(" + user + ", " + hideParts + ")");
  }

  public boolean libraryPathExists() {
    return  Boolean.getBoolean(this.webClient.sendCommand("libraryPathExists()")[0]);
  }

  public String importPath(User user) {
    return  this.webClient.sendCommand("importPath()")[0];
  }



}
