package utilities.project;

import java.io.*;

import utilities.io.*;

public class ProjectLineCounter {
  public static void main(String[] args) {
    String sep = System.getProperty("file.separator");
    int lines = 0;
    String path = "/home/jcerasuolo/nextremsoft/software/live/nextremQuote";
    ProjectLineCounter p = new ProjectLineCounter();
    String[] projectDirectoryFiles = new File(path).list();
    for (int i = 0; i < projectDirectoryFiles.length; i++) {
      if (new File(path + sep + projectDirectoryFiles[i]).isDirectory()) {
        String[] projectDirectoryFiles2 = new File(path + sep + projectDirectoryFiles[i]).list();
        for (int j = 0; j < projectDirectoryFiles2.length; j++) {
          lines += p.getLines(path + sep + projectDirectoryFiles[i] + sep + projectDirectoryFiles2[j], ".java");
          System.out.println("Project contains: " + lines + " lines.");
        }
      }
    }
  }

  public ProjectLineCounter() {
  }

  public int getLines(String fileName, String extension) {
    if (fileName.endsWith(extension)) {
      return new ReadFromFile().readFromFile(fileName).length;
    }
    else {
      return 0;
    }
  }
}
