// (c) 2006-2007 NextremSoft

package nq.sales;

public class Dealer
    extends Person {
  private Country country;
  private Customer[] customers;
  private String code;
  private TerritoryManager tmCB;
  private TerritoryManager tmWHE;
  private RegionalManager rm;

  public Dealer() {
    this.code = "";
    this.setName("");

  }

  public String getCode() {
    return code;
  }

  public void code(String code) {
    this.code = code;
  }

  public TerritoryManager tmCB() {
    return tmCB;
  }

  public void tmWHE(TerritoryManager tmWHE) {
    this.tmWHE = tmWHE;
  }

  public void tmCB(TerritoryManager tmCB) {
    this.tmCB = tmCB;
  }

  public TerritoryManager tmWHE() {
    return tmWHE;
  }

  public boolean addCustomer(Customer customer) {
    boolean found = false;
    for (int i = 0; i < customers.length; i++) {
      if (customers[i].getName().equals(customer.getName())) {
        found = true;
        break;
      }
    }
    if (!found) {
      Customer[] biggerArray = new Customer[customers.length + 1];
      for (int i = 0; i < customers.length; i++) {
        biggerArray[i] = customers[i];
      }
      biggerArray[biggerArray.length - 1] = customer;
      customers = biggerArray;
      return true;
    }
    else {
      return false;
    }
  }

  public Customer findCustomerByName(String name) {
    Customer thisCustomer = null;
    for (int i = 0; i < customers.length; i++) {
      if (customers[i].getName().equals(name)) {
        thisCustomer = customers[i];
        break;
      }
    }
    return thisCustomer;
  }

  public Country country() {
    return country;
  }

  public void country(Country country) {
    this.country = country;
  }

  public void rm(RegionalManager rm) {
    this.rm = rm;
  }

  public RegionalManager rm() {
    return rm;
  }

}
