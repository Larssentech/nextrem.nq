// (c) 2006-2007 NextremSoft

package nq.journal;

import java.util.*;

import jdbc.*;
import nq.catalogue.*;
import nq.sales.*;
import nq.transaction.*;
import utilities.string.*;

public class SynchronizationAgent
    extends Thread {
  TransactionManager transactionManager;
  JournalController jController;
  public SynchronizationAgent(TransactionManager transactionManager, JournalController jController) {
    this.transactionManager = transactionManager;
    this.jController = jController;
  }

  public void sync() {
    Hashtable journal = jController.getUnsyncedJournal();
    for (int i = 0; i < journal.size(); i++) {
      JournalRecord thisRecord = (JournalRecord) journal.values().toArray()[i];
      String id = thisRecord.id();
      Option newBasket = transactionManager.getBasketFromLibrary(thisRecord.libraryName());
      if (newBasket != null) {
        Vector dataToUpload = new OptionExplorer().convertQuoteToCSVExportFormat
            (new User(), newBasket, false, false);
        String[][] records = new StringManipulationToolkit().create2DArrayFromVector(dataToUpload);
        if (new DBConn().writeData("efmcognos_midm_sqlserver", records)) {
          jController.setSynced(id);
        }
      }
      else {
        System.out.println("Journal entry not found in filesystem. Corrupted journal. Setting entry as 'synced'");
        jController.setSynced(id);
      }
    }
    this.jController.saveJournal();
  }
}
