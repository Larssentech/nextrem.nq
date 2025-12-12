// (c) 2006-2007 NextremSoft

package nq.sales;

public class Country {
  private String name;
  private String isoCode;
  private Dealer[] dealers;
  public Country(String name) {
    this.name = name;
    this.dealers = new Dealer[0];
  }

  public String isoCode() {
    return isoCode;
  }

  public String getName() {
    return name;
  }

  public void isoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  public Dealer[] dealers() {
    return dealers;
  }

  public boolean addDealer(Dealer dealer) {
    boolean dealerFound = false;
    for (int i = 0; i < dealers.length; i++) {
      if (dealers[i].getCode().equals(dealer.getCode())) {
        dealerFound = true;
        break;
      }
    }
    if (!dealerFound) {
      Dealer[] biggerDealerArray = new Dealer[dealers.length + 1];
      for (int i = 0; i < dealers.length; i++) {
        biggerDealerArray[i] = dealers[i];
      }
      biggerDealerArray[biggerDealerArray.length - 1] = dealer;
      dealers = biggerDealerArray;
      return true;
    }
    else {
      return false;
    }
  }

  public Dealer findDealerByCode(String code) {
    Dealer thisDealer = null;
    for (int i = 0; i < dealers.length; i++) {
      if (dealers[i].getCode().equals(code)) {
        thisDealer = dealers[i];
        break;
      }
    }
    return thisDealer;
  }

}
