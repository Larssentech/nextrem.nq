// (c) 2006-2007 NextremSoft

package nq.catalogue;

import java.util.*;

public class UniqueIdGenerator {

  public UniqueIdGenerator() {
  }

  public String createId(String thisOptionType) {
    String id = "";
    if (thisOptionType.equals("OPTCTLG")) {
      id = "b";
    }
    if (thisOptionType.equals("PART")) {
      id = "p";
    }
    if (thisOptionType.equals("OPTION")) {
      id = "o";
    }
    if (thisOptionType.equals("UNIT")) {
      id = "t";
    }
    id = id + Math.random() + "-" + new Date().getTime();
    return id;
  }

}
