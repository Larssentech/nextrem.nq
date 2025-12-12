package utilities.settings;

// Copyright (c) 2001-2006 NextremSoft

import java.io.*;

import utilities.io.*;

public class SettingsUpdater {
  public SettingsUpdater() {
  }

  public boolean updateLine(String fileName, String field, String value) {
    if (! (new File(fileName)).exists()) {
      (new SaveToFile()).saveToFile(fileName, new String[] {
                                    "[NEXTREM_SETTINGS_DO_NOT_EDIT]"}
                                    , false);
    }
    String settings[] = (new ReadFromFile()).readFromFile(fileName);
    for (int i = 0; i < settings.length; i++) {
      if (settings[i].indexOf(field) == 0) {
        String oldValue = settings[i].substring(field.length() + 1);
        settings[i] = String.valueOf( (new StringBuffer(String.valueOf(field))).append("=").append(value));
        return (new SaveToFile()).saveToFile(fileName, settings, false);
      }
    }

    return (new SaveToFile()).saveToFile(fileName,
                                         new String[] {String.valueOf( (new StringBuffer(String.valueOf(field))).
        append("=").append(value))}
                                         , true);
  }
}
