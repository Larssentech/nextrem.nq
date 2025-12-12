package utilities.project;

import java.io.*;
import java.util.*;

import utilities.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class CodeVault {
  public static void main(String[] args) {
    new CodeVault();
  }

  public CodeVault() {
    String sep = System.getProperty("file.separator");
    Vector data = new Vector();
    String[] files = new File("D:" + sep + "nacco" + sep + "java" + sep + "Utilities" + sep + "src").list();
    for (int i = 0; i < files.length; i++) {
      data.add(new ReadFromFile().readFromFile("D:" + sep + "nacco" + sep + "java" + sep + "Utilities" + sep + "src" +
                                               sep + "" + files[i]));
      System.out.print(".");
    }
    for (int i = 0; i < data.size(); i++) {
      new SaveToFile().saveToFile("D:" + sep + "nacco" + sep + "java" + sep + "TruckBuilder" + sep + "src" + sep +
                                  "vault.dat", ( (String[]) data.elementAt(i)), true);
    }
  }

}
