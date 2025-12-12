package utilities.settings;

// Copyright (c) 2001-2006 NextremSoft, Nextrem Communications
import utilities.io.*;

public class SettingsExtractor {

  public SettingsExtractor() {
  }

  public String extractThis(String fileName, String field) {
    String settings[] = (new ReadFromFile()).readFromFile(fileName);
    for (int i = 0; i < settings.length; i++) {
      if (settings[i].indexOf(field) == 0) {
        String value = settings[i].substring(settings[i].lastIndexOf("=") + 1);
        return value;
      }
    }
    return "Error";
  }
}
