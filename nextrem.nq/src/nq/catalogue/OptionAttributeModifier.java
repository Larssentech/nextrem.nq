package nq.catalogue;

import nq.io.input.*;

public class OptionAttributeModifier {

  public OptionAttributeModifier() {
  }

  public void resetDiscounts(Option optionBasket, DiscountsGenerator discountsGenerator) {
    // For units
    for (int i = 0; i < optionBasket.options().length; i++) {
      Option parentUnit = optionBasket.options()[i];
      // And for all options
      for (int j = 0; j < optionBasket.options()[i].options().length; j++) { // So for Options, incl models which is what we are looking for
        Option thisOption = optionBasket.options()[i].options()[j];
        // But only for models as only models contain Disc and Max in our dicounts book
        if (thisOption.getGroupDesc().equals("MODEL")) {
          double btdDisc = discountsGenerator.findStandardDiscount(optionBasket.dealer().getCode(),
              optionBasket.country().getName(), thisOption.standardizedName(), 0);
          double autDisc = discountsGenerator.findStandardDiscount(optionBasket.dealer().getCode(),
              optionBasket.country().getName(), thisOption.standardizedName(), 1);
          // If we found a country-dealer-model combination in the Discbook then use it for the whole unit tree where this
          // combination belongs
          // Set discount as book discounts only if no discount is found: 0.0 in parent
          if (parentUnit.discountP100() == 0.0) {
            parentUnit.overRideDiscountP100(btdDisc);
          }
          parentUnit.overRideStandardDiscP100(btdDisc);
          parentUnit.overRideMaxDiscountP100(autDisc);
        }
      }
    }
  }

  public boolean storeDiscountP100(String optionId, Option optionBasket, double discP100) {
    if (optionBasket.findOptionById(optionId).type().equals("OPTION")) {
      optionBasket.findOptionById(optionId).overRideDiscountP100(discP100);
      return true;
    }
    else if (optionBasket.findOptionById(optionId).type().equals("UNIT")) {
      optionBasket.findOptionById(optionId).overRideDiscountP100(discP100);
      return true;
    }
    else {
      return false;
    }
  }

  public boolean saveMarkup(Option optionBasket, String markup) {
    optionBasket.markup(Double.parseDouble(markup));
    return true;
  }

}
