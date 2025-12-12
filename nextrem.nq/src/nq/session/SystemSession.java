// (c) 2006-2007 NextremSoft

package nq.session;

import java.util.*;

import nq.catalogue.*;
import nq.sales.*;

public class SystemSession {

  private Option optionCatalog;
  private SalesTerritory salesTerritory;
  private Hashtable requiredGroups, multiples;

  public SystemSession() {
  }

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
