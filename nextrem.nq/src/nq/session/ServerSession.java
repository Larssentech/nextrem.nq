// (c) 2006-2007 NextremSoft

package nq.session;

import java.util.*;

import nq.catalogue.*;
import nq.sales.*;
import utilities.string.*;

public class ServerSession {
  //private Option optionBasket;
  private Option optionCatalog;
  private SalesTerritory salesTerritory;
  private Hashtable requiredGroups, multiples;

  public ServerSession() {
  }

  //public Option optionBasket() {
  //  return this.optionBasket;
  //}

  //public void optionBasket(Option optionBasket) {
  //  this.optionBasket = optionBasket;
  //}

  public Option optionCatalog() {
    return this.optionCatalog;
  }

  public void optionCatalog(Option optionCatalog) {
    this.optionCatalog = optionCatalog;
  }



  public SalesTerritory salesTerritory() {
    return salesTerritory;
  }

  public void salesTerritory(SalesTerritory salesTerritory) {
    this.salesTerritory = salesTerritory;
  }

  public Hashtable requiredGroupsHashtable() {
    return requiredGroups;
  }

  public void requiredGroupsHashtable(Hashtable requiredGroups) {
    this.requiredGroups = requiredGroups;
  }

  public Hashtable multiplesHashtable() {
    return multiples;
  }

}
