// (c) 2006-2007 NextremSoft

package nq.transaction;

import java.util.HashMap;

import nq.catalogue.*;

/**
 * <p>Title: TransactionPack</p>
 * <p>Description: This is the object that is returned when any option is added to another option. This class
 * contains information on:
 * a) Whether the transaction (option addition) was completed or not
 * b) An Option[] with options in the parent option that need to be removed before the transaction can be completed
 * c) An Option[] with options from which the user needs to choose one so that the combo can be added
 * d) The instance of the original option that was the subject of the transaction
 * e) The Bundle (an Option) that is opened waiting for the necessary secondary option ot be added so the combo
 *    is complete, both options get added and the bundle is emptied</p>
 * <p>Copyright: Copyright (c) 2006 NextremSoft</p>
 * <p>Company: NextremSoft</p>
 * @author Olufsen
 * @version 1.0
 */

public class TransactionPack {
  private boolean transactionCompleted;
  private Option[] incompatibleOptions;
  private Option[] conditionerOptions;
  private Option optionToAdd;
  private Option openBundle;
  private String transactionLog;
  public TransactionPack() {
    this.transactionCompleted = false;
    this.incompatibleOptions = new Option[0];
    this.conditionerOptions = new Option[0];
    this.optionToAdd = new Option();
    this.transactionLog = "";
  }

  public Option[] conditionerOptions() {
    return conditionerOptions;
  }

  public Option[] incompatibleOptions() {
    return incompatibleOptions;
  }

  public boolean transactionCompleted() {
    return transactionCompleted;
  }

  public void conditionerOptions(Option[] conditionerOptions) {
    this.conditionerOptions = conditionerOptions;
  }

  public void incompatibleOptions(Option[] incompatibleOptions) {
    this.incompatibleOptions = incompatibleOptions;
  }

  public void transactionCompleted(boolean transactionCompleted) {
    this.transactionCompleted = transactionCompleted;
  }

  public Option optionToAdd() {
    return optionToAdd;
  }

  public void optionToAdd(Option optionToAdd) {
    this.optionToAdd = optionToAdd;
  }

  public Option openBundle() {
    return openBundle;
  }

  public void openBundle(Option openBundle) {
    this.openBundle = openBundle;
  }

  public String transactionLog() {
    return transactionLog;
  }

  public void transactionLog(String transactionLog) {
    this.transactionLog = transactionLog;
  }

  public HashMap toHashMap(){
    // Create the return ArrayList from the transaction pack
    HashMap transactionSummary = new HashMap();
    transactionSummary.put("Transaction Completed", "" + this.transactionCompleted());
    transactionSummary.put("Transaction Log", this.transactionLog());
    transactionSummary.put("Incompatible Options", "" + this.incompatibleOptions().length);
    transactionSummary.put("Conditioners Available", "" + this.conditionerOptions().length);
    return transactionSummary;
  }

}
