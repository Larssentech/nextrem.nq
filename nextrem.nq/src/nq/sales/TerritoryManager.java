// (c) 2006-2007 NextremSoft

package nq.sales;

public class TerritoryManager extends Person {
  private Country[] countries;

  public TerritoryManager() {
    this.countries = new Country[0];
  }

  public Country[] countries() {
    return countries;
  }

  public void countries(Country[] countries) {
    this.countries = countries;
  }

  public boolean addCountry(Country country) {
    boolean countryFound = false;
    for (int i = 0; i < countries.length; i++) {
      if (countries[i].getName().equals(country.getName())) {
        countryFound = true;
        break;
      }
    }
    if (!countryFound) {
      Country[] biggerArray = new Country[countries.length + 1];
      for (int i = 0; i < countries.length; i++) {
        biggerArray[i] = countries[i];
      }
      biggerArray[biggerArray.length - 1] = country;
      countries = biggerArray;
      return true;
    }
    else {
      return false;
    }
  }

  public Country findCountryByName(String name) {
    Country thisCountry = null;
    for (int i = 0; i < countries.length; i++) {
      if (countries[i].getName().equals(name)) {
        thisCountry = countries[i];
        break;
      }
    }
    return thisCountry;
  }

}
