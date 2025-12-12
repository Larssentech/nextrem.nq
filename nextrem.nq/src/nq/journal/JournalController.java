// (c) 2006-2007 NextremSoft

package nq.journal;

import java.util.*;

import utilities.io.*;
import utilities.parsers.*;

public class JournalController {
  private Hashtable journal = new Hashtable();

  public JournalController() {
    String[] thisJournal = new ReadFromFile().readFromFile("config" + System.getProperty("file.separator") +
        "journal.dat");
    for (int i = 0; i < thisJournal.length; i++) {
      String[] thisJournalRecordLine = new CSVExtractor2().extractValuesFrom(thisJournal[i]);
      if (thisJournalRecordLine.length > 2) {
        JournalRecord thisRecord = new JournalRecord();
        thisRecord.id(thisJournalRecordLine[0]);
        thisRecord.libraryName(thisJournalRecordLine[1]);
        thisRecord.synced(Boolean.valueOf(thisJournalRecordLine[2]).booleanValue());
        this.insertRecord(thisRecord);
      }
    }
  }

  public boolean insertRecord(JournalRecord record) {
    if (!this.journal.containsKey(record.id())) {
      this.journal.put(record.id(), record);
      this.saveJournal();
      return true;
    }
    else {
      return false;
    }
  }

  public Hashtable getJournal() {
    return (Hashtable)this.journal.clone();
  }

  public Hashtable getUnsyncedJournal() {
    Hashtable unsyncedJournal = new Hashtable();
    for (int i = 0; i < journal.size(); i++) {
      JournalRecord thisRecord = (JournalRecord) journal.values().toArray()[i];
      String key = thisRecord.id();
      if (!thisRecord.synced()) {
        unsyncedJournal.put(key, thisRecord);
      }
    }
    return unsyncedJournal;
  }

  public void setSynced(String key) {
    ( (JournalRecord)this.journal.get(key)).synced(true);
  }

  public void printJournal() {
    System.out.println("---------------------------------------------------------------------");
    for (int i = 0; i < this.journal.size(); i++) {
      JournalRecord thisRecord = (JournalRecord)this.journal.values().toArray()[i];
      System.out.println(" Journal: " + i + " - " + thisRecord.id() + " - " + thisRecord.libraryName() + "; Synced: " +
                         thisRecord.synced());
    }
    System.out.println("---------------------------------------------------------------------");
  }

  public void saveJournal() {
    String[] journalArray = new String[this.journal.size()];
    for (int i = 0; i < journal.size(); i++) {
      JournalRecord thisRecord = (JournalRecord) journal.values().toArray()[i];
      journalArray[i] = thisRecord.id() + ",";
      journalArray[i] += thisRecord.libraryName() + ",";
      journalArray[i] += thisRecord.synced();
    }
    new SaveToFile().saveToFile("config" + System.getProperty("file.separator") + "journal.dat", journalArray, false);

  }
}
