package utilities.io;

// Copyright (c) 2001 NextremSoft, Nextrem Communications

import java.io.*;

public class ValidateFile {
  public ValidateFile() {}

  public boolean validateFile(String fileName) {
    if (new File(fileName).exists() && new File(fileName).isFile()) {
      return true;
    }
    else {
      return false;
    }
  }
}
