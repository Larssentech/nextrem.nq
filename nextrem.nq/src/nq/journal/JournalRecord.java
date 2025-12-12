// (c) 2006-2007 NextremSoft

package nq.journal;

public class JournalRecord {
  private String libraryName;
  private String id;
  private boolean synced;

  public JournalRecord() {
  }

  public String libraryName() {
    return libraryName;
  }

  public void libraryName(String libraryName) {
    this.libraryName = libraryName;
  }

  public String id() {
    return id;
  }

  public void id(String id) {
    this.id = id;
  }

  public boolean synced() {
    return synced;
  }

  public void synced(boolean synced) {
    this.synced = synced;
  }
}
