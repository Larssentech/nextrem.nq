// (c) 2006-2007 NextremSoft

package nq.sales;

import java.util.*;

public class SalesTerritory {
  private Country[] countries;
  private Dealer[] dealers;
  private TerritoryManager[] territoryManagers;
  private RegionalManager[] regionalManagers;

  public SalesTerritory() {
    countries = new Country[0];
    dealers = new Dealer[0];
    territoryManagers = new TerritoryManager[0];
    regionalManagers = new RegionalManager[0];
  }

  public Country findCountryByName(String name) {
    Country returnCountry = null;
    for (int i = 0; i < countries.length; i++) {
      if (countries[i].getName().equals(name)) {
        returnCountry = countries[i];
        break;
      }
    }
    return returnCountry;
  }

  public Country addCountry(Country country) {
    if (this.countries.length == 0 || this.findCountryByName(country.getName()) == null) {
      Country[] biggerCountryArray = new Country[countries.length + 1];
      for (int i = 0; i < countries.length; i++) {
        biggerCountryArray[i] = countries[i];
      }
      biggerCountryArray[biggerCountryArray.length - 1] = country;
      countries = biggerCountryArray;
      return country;
    }
    else {
      return null;
    }
  }

  public Dealer findDealerByCode(String code) {
    Dealer returnDealer = null;
    for (int i = 0; i < dealers.length; i++) {
      if (dealers[i].getCode().equals(code)) {
        returnDealer = dealers[i];
        break;
      }
    }
    return returnDealer;
  }

  public Dealer addDealer(Dealer dealer) {
    if (this.dealers.length == 0 || this.findDealerByCode(dealer.getCode()) == null) {
      Dealer[] biggerDealerArray = new Dealer[dealers.length + 1];
      for (int i = 0; i < dealers.length; i++) {
        biggerDealerArray[i] = dealers[i];
      }
      biggerDealerArray[biggerDealerArray.length - 1] = dealer;
      dealers = biggerDealerArray;
      return dealer;
    }
    else {
      return null;
    }
  }

  public TerritoryManager findTmByName(String name) {
    TerritoryManager returnTm = null;
    for (int i = 0; i < this.territoryManagers.length; i++) {
      if (territoryManagers[i].getName().equals(name)) {
        returnTm = territoryManagers[i];
        break;
      }
    }
    return returnTm;
  }

  public RegionalManager findRmByName(String name) {
    RegionalManager returnRm = null;
    for (int i = 0; i < this.regionalManagers.length; i++) {
      if (regionalManagers[i].getName().equals(name)) {
        returnRm = regionalManagers[i];
        break;
      }
    }
    return returnRm;
  }

  public TerritoryManager addTm(TerritoryManager tm) {
    if (this.territoryManagers.length == 0 || this.findTmByName(tm.getName()) == null) {
      TerritoryManager[] biggerTmArray = new TerritoryManager[territoryManagers.length + 1];
      for (int i = 0; i < this.territoryManagers.length; i++) {
        biggerTmArray[i] = territoryManagers[i];
      }
      biggerTmArray[biggerTmArray.length - 1] = tm;
      territoryManagers = biggerTmArray;
      return tm;
    }
    else {
      return null;
    }
  }

  public RegionalManager addRm(RegionalManager rm) {
    if (this.regionalManagers.length == 0 || this.findRmByName(rm.getName()) == null) {
      RegionalManager[] biggerRmArray = new RegionalManager[regionalManagers.length + 1];
      for (int i = 0; i < this.regionalManagers.length; i++) {
        biggerRmArray[i] = regionalManagers[i];
      }
      biggerRmArray[biggerRmArray.length - 1] = rm;
      regionalManagers = biggerRmArray;
      return rm;
    }
    else {
      return null;
    }
  }

  public Dealer[] findDealersForTmName(String tmName) {
    Country[] countries = this.findTmByName(tmName).countries();
    Vector temp = new Vector();
    for (int i = 0; i < countries.length; i++) {
      for (int j = 0; j < countries[i].dealers().length; j++) {
        temp.add(countries[i].dealers()[j]);
      }
    }
    Dealer[] returnArray = new Dealer[temp.size()];
    for (int i = 0; i < temp.size(); i++) {
      returnArray[i] = (Dealer) temp.elementAt(i);
    }
    return returnArray;
  }

}
