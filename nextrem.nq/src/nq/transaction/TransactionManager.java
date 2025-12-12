// (c) 2006-2007 NextremSoft

package nq.transaction;

import nq.catalogue.*;
import nq.orchestrating.*;
import nq.sales.*;
import nq.io.input.*;

public class TransactionManager {

  // The rules processor will handle specific rules (obviously)
  private OptionRulesProcessor optionRulesProcessor;
  private User user;

  // A bundle is a group of options that need to go in at the same time
  // since the price of one or more will depend on other options' presence
  private Option openBundle;

  // The orchestrating class in order to ask for the basket and catalog
  private NextremQuote nq;

  public TransactionManager(NextremQuote nq, User user) {
    this.user = user;
    this.nq = nq;
    this.optionRulesProcessor = new OptionRulesProcessor(this, this.user);
    this.openBundle = new Option();
  }

  // Other classes like the rules processor need to have access to catalog
  public Option optionCatalog() {
    return this.nq.getSystemSession().optionCatalog();
  }

  // and basket
  public Option optionBasket() {
    return this.nq.getBasketForUser(this.user);
  }

  // Will be used by the rules processor to create instances of the AI which
  // needs the NextremQuote orchestrating class
  public NextremQuote nq() {
    return nq;
  }

  // 1. This is the method called from the outside
  //    and never called from the inside. It is a method to add an item to
  //    the basket given the parent. Will return a transaction pack with
  //    info on what happened
  public TransactionPack addItem(String optionCodeToAdd, String parentId, boolean enforceAI, boolean enforceUniqueness) {

    // Needed objects
    TransactionPack transactionPack = new TransactionPack();
    Option optionToAddTemplate = this.optionCatalog().findOptionByCode(optionCodeToAdd);
    Option parent = this.optionBasket().findOptionById(parentId);

    // Does incoming option need conditioners? If not just add it to the parent
    if (optionToAddTemplate.conditioners().length == 0) {
      transactionPack = addItemToBasket(optionCodeToAdd, parentId, enforceAI, enforceUniqueness);

      // If it needs conditioners...
    }
    else {

      // ...does the parent already contain one of the conditioners?
      boolean conditionerInParent = false;
      for (int i = 0; i < optionToAddTemplate.conditioners().length; i++) {
        if (this.optionBasket().findOptionById(parentId).hasOptionCode(optionToAddTemplate.conditioners()[i].getCode())) {
          conditionerInParent = true;
          break;
        }
      }

      // If parent does not have any of the needed conditioners, add incoming option to the bundle
      if (!conditionerInParent) {
        this.openBundle.addOption(optionToAddTemplate);
        transactionPack.openBundle(this.openBundle);
        transactionPack.conditionerOptions(optionToAddTemplate.conditioners());
        transactionPack.optionToAdd(optionToAddTemplate);

        // Set the flag to false to inform other parts of the program there is an open bundle
        transactionPack.transactionCompleted(false);
      }

      // If parent already had one of the needed conditioners, just add the incoming option
      else {
        transactionPack = addItemToBasket(optionCodeToAdd, parentId, enforceAI, enforceUniqueness);
      }
    }

    // Done with the incoming option
    // After processing an incoming option we  always check that the bundle is complete to close it
    // Check that all options in the bundle have a match inside the bundle or already in the parent
    int matches = 0;
    // For each option in the bundle...
    for (int i = 0; i < this.openBundle.options().length; i++) {
      Option bundleOptionToCheck = this.openBundle.options()[i];

      // ...check every of its conditioners for presence in the bundle (matching pairs) or parent
      for (int j = 0; j < bundleOptionToCheck.conditioners().length; j++) {
        String thisConditionerCode = bundleOptionToCheck.conditioners()[j].getCode();
        if (this.openBundle.hasOptionCode(thisConditionerCode) || parent.hasOptionCode(thisConditionerCode)) {
          matches++;
          break;
        }
      }
    }

    // If each option in bundle either has a conditioner in the bundle or has a conditioner already in the parent
    if (matches == this.openBundle.options().length && matches > 0) {
      // Add all options in the bundle
      for (int i = 0; i < openBundle.options().length; i++) {
        transactionPack = this.addItemToBasket(openBundle.options()[i].getCode(), parentId, enforceAI, enforceUniqueness);
      }
      // Clear the bundle
      this.openBundle.removeAllOptions();
    }

    // If an option was added to the parent above and it was not part of the chain of options
    // linked to the bundle, so matches are less than required, the bundle needs to forget the chain
    // This only applies if the transaction was completed, so an independent option was added
    // to a parent none relating to the bundle
    else if (matches < this.openBundle.options().length && transactionPack.transactionCompleted()) {
      this.openBundle.removeAllOptions();
    }

    // If the transaction did not complete, this means we have an open bundle and the details
    // are now collected in the transaction pack. Do nothing else and return the pack. If successive
    // options needing conditioners arrive, place in the bundle and still do nothing until a separate option
    // breaks the chain or all options in the bundle have a match

    return transactionPack;
  }

  // Method to handle the addition of an item to the basket and the addition
  // of parts to the option just added and modify the pack if something went wrong with the parts
  // The preprocessing for bundle has taken place.
  // The method is called only internallyto this class
  // and will return a transaction pack
  private TransactionPack addItemToBasket(String optionCodeToAdd, String parentId, boolean enforceAI,
                                          boolean enforceUniqueness) {

    // Add the incoming option to the basket without 'further ado'
    TransactionPack tPack = this.addOptionToOption2(optionCodeToAdd, parentId, enforceAI, enforceUniqueness);

    // If successful (and no reason why not but we need to check)
    if (tPack.transactionCompleted()) {

      // Get the original daughter option from the transaction pack. We need to add
      // parts to it
      Option originalDaughter = tPack.optionToAdd();

      // Add the relevant parts to the option
      Option optionToAdd = optionRulesProcessor.addMandatoryAndOneOptional
          (originalDaughter,
          enforceAI,
          enforceUniqueness);
      if (optionToAdd != null && !optionToAdd.getName().equals("EMPTY") && optionToAdd.options().length > 0) {

        // ## Why do we do this? the pack already is set to added = true
        tPack.transactionCompleted(true);
      }

      // If no part was found adequate for the option just added, well then we have
      // a fucking big problem so we need to tell the user
      else {
        tPack.transactionCompleted(false);

        // The option to add, the original daughter, which will now be either
        // null or a blank EMPTY option goes in the pack to voerwrite the
        // real original daughter. Since it will be a corrupted option
        // and we have told the rest of the program, we leave the processing
        // to them
      }
      tPack.optionToAdd(optionToAdd);
    }
    return tPack;
  }

  // This method is the only one that calls the ADDOPTION method of a parent option
  // It is used to add units to baskets, options to units and parts to options
  // and checks that the item to add is compatible by calling the AISIGNOFF method
  public TransactionPack addOptionToOption2(String optionCodeToAdd, String parentId, boolean enforceAI,
                                            boolean enforceUniqueness) {

    // Create a blank transaction pack
    TransactionPack transactionPack = new TransactionPack();

    // Get AI sign off
    String AILog = this.AISignOff(optionCodeToAdd, parentId, enforceAI);

    // And store it in the pack
    transactionPack.transactionLog(AILog);

    // Only if an option can be added based on the rules of the rules processor
    if (optionRulesProcessor.optionCanBeAdded(optionCodeToAdd, parentId, enforceUniqueness)) {

      // And can be  added based on the AI compatibility logic
      if (AILog.equals("OK")) {
        // ## Why do we need this line?
        transactionPack.transactionLog("OK");

        // Define the parent and daughter...
        Option basketParent = optionBasket().findOptionById(parentId);
        Option catalogParent = optionCatalog().findOptionByCode(basketParent.getCode());
        Option optionToAdd = catalogParent.findOptionByCode(optionCodeToAdd);
        optionToAdd = optionToAdd.cloneSelf(this.createId(optionCodeToAdd));

        // Daughter option WILL ALLWAYS take the discount of its parent
        optionToAdd.discountP100(basketParent.discountP100());

        // Daughter will always be of the same group as the parent so it will inherit the index (order)
        optionToAdd.groupIndex(basketParent.getGroupIndex());

        // And finally add it
        basketParent.addOption(optionToAdd);

        // Put it in the pack
        transactionPack.optionToAdd(optionToAdd);

        // And inform the user
        transactionPack.transactionCompleted(true);
      }
      return transactionPack;
    }
    else {

      // If the option was not added  because of a violation of the rules
      // we need to tell the user
      transactionPack.transactionLog(AILog);
      return transactionPack;
    }
  }

  // Will validate the AI rules and return a text string that will be OK if all went well
  // or some message if not.
  // Since part compatibility follows a differnt logic depending on the mandatory flag
  // we will check that separately so we OK all parts here
  private String AISignOff(String optionCodeToAdd, String parentId, boolean enforceAI) {
    ArtificialIntelligenceProcessor
        myAIprocessor = new ArtificialIntelligenceProcessor(nq, this.user);
    if (enforceAI) {

      // For options compatibility
      if (this.optionCatalog().findOptionByCode(optionCodeToAdd).type().equals("OPTION")){
        myAIprocessor.checkOptionCompatibility(optionCodeToAdd, parentId);
      }

      // For part compatibility (we do nothing as this is handled in a separate method
      // based on a flag which is different from the option one
      else if (this.optionCatalog().findOptionByCode(optionCodeToAdd).type().equals("PART")) {
        // We return OK so that the log is OK
        return "OK";
      }

      // For unit compatibility
      else if (this.optionCatalog().findOptionByCode(optionCodeToAdd).type().equals("UNIT")){
        myAIprocessor.checkUnitCompatibility(optionCodeToAdd, parentId);
      }
    }

    // Return the AI log regardless
    return myAIprocessor.log();
  }

  // Method to delete unwanted options from a parent
  public boolean removeOptionFromOption(String id) {
    this.optionBasket().removeOption(id);
    return true;
  }

  // Method to retrieve an option tree (a basket) from file
  public Option getBasketFromLibrary(String libraryName) {
    Option newBasket = new QuoteLoader().loadQuoteFromLibrary(libraryName);
    return newBasket;
  }

  // Method to remove all options from a basket
  public boolean clearOptionBasket() {
    return this.optionBasket().removeAllOptions();
  }

  // Method to create a unique ID code. Calls a specialized class that
  // will create the ID depending on whether the option is a part or option or...
  public String createId(String code) {
    return new UniqueIdGenerator().createId(this.optionCatalog().findOptionByCode(code).type());
  }

  // Method for other parts of the program to get hold of the rules processor
  public OptionRulesProcessor optionRulesProcessor() {
    return optionRulesProcessor;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
