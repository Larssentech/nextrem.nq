package utilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import utilities.io.*;

public class SimpleNotepad
    extends JFrame {
  private String destinationFile;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea textArea = new JTextArea();
  JButton jButton1 = new JButton();
  Border border1;
  Border border2;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem saveMenuItem = new JMenuItem();

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      System.out.println("UI LnF failed");
    }

    new SimpleNotepad();
  }

  public SimpleNotepad() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.setTitle("NextremSoft");
    this.setLocation(50, 50);
    this.setSize(400, 430);
    this.setResizable(false);
  }

  public void createEditableNotepad(String destinationFile) {
    this.destinationFile = destinationFile;
    String[] savedDebugLines = new ReadFromFile().readFromFile(this.
        destinationFile);
    for (int i = 0; i < savedDebugLines.length; i++) {
      this.textArea.append(savedDebugLines[i]);
      if (i < savedDebugLines.length - 1) {
        this.textArea.append("\n");
      }
    }
    this.textArea.setCaretPosition(textArea.getText().length());
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.textArea.setEditable(true);
    this.setTitle("NextremSoft");
    this.setLocation(50, 50);
    this.setSize(400, 430);
    this.setResizable(false);
    this.show();
  }

  public void appendText(String textToDisplay) {
    this.textArea.append(textToDisplay + "\n");
    this.textArea.setCaretPosition(textArea.getText().length());
  }

  private void saveToFile(String fileName) {
    new SaveToFile().saveToFile(fileName, new String[] {this.textArea.getText()}
                                , false);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder();
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(103, 101, 98),
                                              new Color(148, 145, 140));
    this.getContentPane().setLayout(null);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.
                                            VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setBorder(border2);
    jScrollPane1.setBounds(new Rectangle(3, 3, 388, 309));
    textArea.setFont(new java.awt.Font("Tahoma", 0, 9));
    textArea.setBorder(border1);
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    jButton1.setBounds(new Rectangle(154, 331, 80, 27));
    jButton1.setText("OK");
    jButton1.addActionListener(new SimpleNotepad_jButton1_actionAdapter(this));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setJMenuBar(jMenuBar1);
    jMenuItem1.setText("Exit");
    jMenuItem1.addActionListener(new SimpleNotepad_jMenuItem1_actionAdapter(this));
    jMenu1.setText("File");
    saveMenuItem.setEnabled(false);
    saveMenuItem.setText("Save");
    saveMenuItem.addActionListener(new SimpleNotepad_saveMenuItem_actionAdapter(this));
    this.getContentPane().add(jScrollPane1, null);
    this.getContentPane().add(jButton1, null);
    jScrollPane1.getViewport().add(textArea, null);
    jMenuBar1.add(jMenu1);
    jMenu1.add(saveMenuItem);
    jMenu1.add(jMenuItem1);
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    if (this.destinationFile.length() > 0) {
      this.saveToFile(this.destinationFile);
    }
    else {
      JFileChooser jFc = new JFileChooser();
      if (jFc.showSaveDialog(this) == 0) {
        this.saveToFile(jFc.getSelectedFile().
                        getAbsolutePath());
      }
    }
    this.dispose();
  }

  void jButton1_actionPerformed(ActionEvent e) {

    this.dispose();
  }

  void saveMenuItem_actionPerformed(ActionEvent e) {
    if (this.destinationFile.length() > 0) {
      this.saveToFile(this.destinationFile);
    }
    else {
      JFileChooser jFc = new JFileChooser();
      if (jFc.showSaveDialog(this) == 0) {
        this.saveToFile(jFc.getSelectedFile().
                        getAbsolutePath());
      }
    }
  }
}

class SimpleNotepad_jMenuItem1_actionAdapter
    implements java.awt.event.ActionListener {
  SimpleNotepad adaptee;

  SimpleNotepad_jMenuItem1_actionAdapter(SimpleNotepad adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class SimpleNotepad_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  SimpleNotepad adaptee;

  SimpleNotepad_jButton1_actionAdapter(SimpleNotepad adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SimpleNotepad_saveMenuItem_actionAdapter
    implements java.awt.event.ActionListener {
  SimpleNotepad adaptee;

  SimpleNotepad_saveMenuItem_actionAdapter(SimpleNotepad adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.saveMenuItem_actionPerformed(e);
  }
}
