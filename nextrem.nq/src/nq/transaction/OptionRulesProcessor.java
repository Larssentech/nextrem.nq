// (c) 2006-2007 NextremSoft

package nq.transaction;

import nq.catalogue.*;
import nq.sales.*;

public class OptionRulesProcessor {

  private TransactionManager manager;
  private ArtificialIntelligenceProcessor ai;
  private User user;

  public OptionRulesProcessor(TransactionManager manager, User user) {
    this.user = user;
    this.manager = manager;

  }

  public Option addMandatoryAndOneOptional(Option parent, boolean enforceAI, boolean enforceUniqueness) {
    this.ai = new ArtificialIntelligenceProcessor(this.manager.nq(), this.user);
    int mandatoryNum = 0;
    int optionalNum = 0;
    int perfectMatches = 0;
    int imPerfectMatches = 0;
    boolean optAdded = false;

    Option parentTemplate = this.manager.optionCatalog().findOptionByCode(parent.getCode());

    // Loop thru all parts adding mandatory
    for (int i = 0; i < parentTemplate.options().length; i++) {
      Option thisDaughter = parentTemplate.options()[i];
      if (thisDaughter.isMandatory()) {
        mandatoryNum++;
        manager.addOptionToOption2(thisDaughter.getCode(), parent.id(), enforceAI, enforceUniqueness);
      }
    }

    // Loop again and add the one that has zero incompatibility index. We want to check them all though
    // to record how many perfect matches we have
    for (int i = 0; i < parentTemplate.options().length; i++) {
      Option thisDaughter = parentTemplate.options()[i];
      if (!thisDaughter.isMandatory()) {
        if (ai.checkPartCompatibility(thisDaughter.getCode(), parent.id()) == 0) {
          perfectMatches++;
          if (!optAdded) {
            manager.addOptionToOption2(thisDaughter.getCode(), parent.id(), enforceAI, enforceUniqueness);
            optAdded = true;
          }
        }
      }
    }

    // Loop once more to add any non-incompatible if no zero index was found
    // Also warn the user
    for (int i = 0; i < parentTemplate.options().length; i++) {
      Option thisDaughter = parentTemplate.options()[i];
      if (!thisDaughter.isMandatory()) {
        optionalNum++; // Only here as otherwise it will be x2
        if (ai.checkPartCompatibility(thisDaughter.getCode(), parent.id()) > 0) {
          imPerfectMatches++;
          if (!optAdded) {
            manager.addOptionToOption2(thisDaughter.getCode(), parent.id(), enforceAI, enforceUniqueness);
            optAdded = true;
          }
        }
      }
    }

    // If no optional was added, we need to let the user know as this is not OK
    // Also, if more than one optional was found and more than 1 was compatible
    // this means there is no boolean criteria and that we have chosen the first
    // occurrence of these
    System.out.println();
    System.out.println("Compatibility Summary: " + parent.getName() + ", " + parent.getCode());
    System.out.println("Mandatory parts:\t" + mandatoryNum);
    System.out.println("Optional parts:\t\t" + optionalNum);
    System.out.println("Perfect Matches:\t" + perfectMatches);
    System.out.println("Imperfect Matches:\t" + imPerfectMatches);

    if (mandatoryNum == 0) {
      System.out.println("- Mandatory part(s) not found!");
    }
    if (!optAdded && optionalNum > 0) {
      System.out.println("- Optional part(s) found but none was added!");
    }
    if (optAdded && perfectMatches > 1) {
      System.out.println("- More than 1 perfect match optional part!");
    }
    if (optAdded && imPerfectMatches > 1) {
      System.out.println("- More than 1 (im)perfect match optional part!");

    }
    return parent;
  }

  public boolean optionCanBeAdded(String optionCodeToAdd, String parentId, boolean enforceUniqueness) {
    Option parentOption = manager.optionBasket().findOptionById(parentId);

    Option daughterTemplate = manager.optionCatalog().findOptionByCode(optionCodeToAdd);
    boolean parentDoesntHaveGroup = true;
    if (daughterTemplate.type().equals("OPTION") && !daughterTemplate.isMultipleAllowed()) {
      // If we are enforcing uniqueness for options
      // that is 1 option per group
      if (enforceUniqueness) {
        parentDoesntHaveGroup = !parentOption.hasGroupCode(daughterTemplate.getGroup());
        // If we are not enforcing 1 option per group
        // we allow > 1 options per group
      }
      else {
        System.out.println("Enforce uniqueness is set to: " + enforceUniqueness);
        parentDoesntHaveGroup = true;
      }
    }
    boolean parentDoesntHaveCode = true;
    if (daughterTemplate.type().equals("UNIT")) {
      parentDoesntHaveCode = true;
    }
    else {
      parentDoesntHaveCode = !parentOption.hasOptionCode(daughterTemplate.getCode());
    }
    return manager.optionBasket().hasOptionId(parentOption.id())
        && parentDoesntHaveCode
        && manager.optionCatalog().findOptionByCode(parentOption.getCode()).hasOptionCode(daughterTemplate.getCode())
        && parentDoesntHaveGroup;
  }

  public void setConditionedPrices(String parentId) {
    Option parent = this.manager.optionBasket().findOptionById(parentId);
    for (int i = 0; i < parent.options().length; i++) {
      Option daughter = parent.options()[i];
      boolean pairMatched = false;
      int j;
      for (j = 0; j < daughter.conditioners().length; j++) {
        String thisConditionerCode = daughter.conditioners()[j].getCode();
        if (parent.hasOptionCode(thisConditionerCode)) {
          daughter.listPrice(daughter.findConditionerByCode(thisConditionerCode).listPrice());
          pairMatched = true;
          System.out.println();
          System.out.println(" RULES CPU> Combo detected... OK, conditional price has been adjusted.");
        }
      }
      // if having conditioners, none is found in the basket, reset the price
      if (!pairMatched && j > 0) {
        daughter.listPrice(this.manager.optionCatalog().findOptionByCode(daughter.getCode()).listPrice());
        System.out.println();
        System.out.println(" RULES CPU> Strange... item " + daughter.getName() + " (" + daughter.type() + ")");
        System.out.println(" RULES CPU> has " + j + " possible combos, but no match is found in " + parent.getName() +
                           " (" + parent.type() + ")");
        System.out.println(" RULES CPU> Its price has been reset to original list price.");
      }
      this.setConditionedPrices(daughter.id());
    }
  }
}
