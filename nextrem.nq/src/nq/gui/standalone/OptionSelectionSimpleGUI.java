// Copyright (c) 2001-2007 NextremSoft

package nq.gui.standalone;

import java.util.*;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;

public class OptionSelectionSimpleGUI extends JDialog {
  private NextremQuoteStandaloneGUI NextremQuoteSimpleGUI;
  private String selectedCode;
  private String[][] options;
  private JPanel panel1 = new JPanel();
  private List optionSelectList = new List();
  private JTextField selectedOptionField = new JTextField();
  private JButton closeOptionSelectorButton = new JButton();
  private JButton okButton = new JButton();
  private JLabel codeLabel = new JLabel();
  private JLabel nameLabel = new JLabel();
  private Choice optionGroupSelector = new Choice();
  private Vector optionGroupCodes;
  private JLabel jLabel1 = new JLabel();
  private JButton addButton = new JButton();
  JCheckBox multiplesCheckBox = new JCheckBox();

  public OptionSelectionSimpleGUI(NextremQuoteStandaloneGUI NextremQuoteSimpleGUI, String title, boolean modal) {
    super(NextremQuoteSimpleGUI, title, modal);
    this.NextremQuoteSimpleGUI = NextremQuoteSimpleGUI;
    this.selectedCode = "";
    this.setResizable(false);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    this.setLocation(200, 200);
    this.setSize(370, 465);
  }

  public void enableAddButton(boolean b) {
    this.addButton.setEnabled(b);
    this.okButton.setEnabled(!b);
  }

  public OptionSelectionSimpleGUI() {
    this(null, "", false);
  }

  private void doAddOption() {
    boolean optionAdded = this.NextremQuoteSimpleGUI.addOptionSilently(codeLabel.getText(), true,
        this.multiplesCheckBox.isSelected()).get("Transaction Completed").toString().equals("OK");
    if (!optionAdded) {
      this.doCancel();
    }
    else {
      this.optionGroupSelector.remove(this.optionGroupSelector.getSelectedIndex());
      if (this.optionGroupSelector.getItemCount() > 0) {
        this.doGroupSelection();
        this.selectFirstEntry();
      }
      else {
        this.addButton.setEnabled(false);
        this.optionSelectList.removeAll();
        this.selectedOptionField.setText("");
        this.codeLabel.setText("");
        this.nameLabel.setText("");
        if (this.optionGroupSelector.getItemCount() == 0) {
          this.dispose();
        }
      }
    }

  }

  private void doGroupSelection() {
    optionSelectList.removeAll();
    String thisGroupCode = this.optionGroupSelector.getSelectedItem();
    for (int i = 0; i < this.options.length; i++) {
      if (this.options[i][2].equals(thisGroupCode.substring(0, thisGroupCode.indexOf(" - ")))) {
        optionSelectList.add(options[i][1]);
      }
    }
  }

  private void doLoadSelection() {
    selectedOptionField.setText(optionSelectList.getSelectedItem());
    nameLabel.setText(optionSelectList.getSelectedItem());
    for (int i = 0; i < options.length; i++) {
      if (options[i][1].equals(optionSelectList.getSelectedItem())) {
        codeLabel.setText(selectedCode = options[i][0]);
      }
    }
  }

  private void doCancel() {
    this.codeLabel.setText("");
    this.nameLabel.setText("");
    this.selectedOptionField.setText("");
    this.selectedCode = "";
    this.dispose();
  }

  public String selectedCode() {
    return this.selectedCode;
  }

  /**
   * Will first create a list of the possible option Group codes. Then it will place in the
   * List only those options that start with the 3 digit code that defines the group.
   * @param elements String[][]
   */
  public void populateOptionList(String[][] elements) {
    // [0] = code
    // [1] = name
    // [2] = group
    // [3] = group desc
    this.options = elements;
    optionGroupCodes = new Vector();
    for (int i = 0; i < elements.length; i++) {
      if (!optionGroupCodes.contains(elements[i][2])) {
        optionGroupCodes.add(elements[i][2]);
        this.optionGroupSelector.add(elements[i][2] + " - " + elements[i][3]);
      }
    }
    optionSelectList.removeAll();
    String thisGroupCode = this.optionGroupSelector.getSelectedItem();
    for (int i = 0; i < this.options.length; i++) {
      if (this.options[i][2].equals(thisGroupCode.substring(0, thisGroupCode.indexOf(" - ")))) {
        optionSelectList.add(options[i][1]);
      }
    }
    this.selectFirstEntry();

  }

  public void options(String[][] options) {
    this.options = options;
  }

  private void selectFirstEntry() {
    if (optionSelectList.getItemCount() > 0) {
      optionSelectList.select(0);
    }
    this.doLoadSelection();
  }

  private void jbInit() throws Exception {
    jLabel1.setIcon(new ImageIcon(InfoSplashScreen.class.getResource("folder.png")));
    panel1.setLayout(null);
    this.getContentPane().setLayout(null);
    panel1.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
    panel1.setBounds(new Rectangle(2, 3, 362, 439));
    optionSelectList.setBounds(new Rectangle(10, 77, 343, 238));
    optionSelectList.addActionListener(new
                                       OptionSelectionSimpleGUI_optionSelectList_actionAdapter(this));
    optionSelectList.addItemListener(new
                                     OptionSelectionSimpleGUI_optionSelectList_itemAdapter(this));
    selectedOptionField.setBackground(Color.white);
    selectedOptionField.setEditable(false);
    selectedOptionField.setText("");
    selectedOptionField.setBounds(new Rectangle(10, 325, 343, 25));
    closeOptionSelectorButton.setBounds(new Rectangle(239, 366, 75, 25));
    closeOptionSelectorButton.setText("Close");
    closeOptionSelectorButton.addActionListener(new
                                                OptionSelectionSimpleGUI_closeOptionSelectorButton_actionAdapter(this));
    okButton.setBounds(new Rectangle(140, 366, 75, 25));
    okButton.setText("OK");
    okButton.addActionListener(new OptionSelectionSimpleGUI_okButton_actionAdapter(this));
    codeLabel.setBorder(BorderFactory.createLoweredBevelBorder());
    codeLabel.setText("");
    codeLabel.setBounds(new Rectangle(1, 406, 99, 28));
    nameLabel.setBorder(BorderFactory.createLoweredBevelBorder());
    nameLabel.setDebugGraphicsOptions(0);
    nameLabel.setBounds(new Rectangle(103, 406, 257, 28));
    optionGroupSelector.setBounds(new Rectangle(10, 15, 266, 25));
    optionGroupSelector.addItemListener(new
                                        OptionSelectionSimpleGUI_optionGroupSelector_itemAdapter(this));
    jLabel1.setBounds(new Rectangle(296, 2, 52, 61));
    addButton.setText("Add");
    addButton.addActionListener(new OptionSelectionSimpleGUI_addButton_actionAdapter(this));
    addButton.setBounds(new Rectangle(41, 366, 75, 25));
    multiplesCheckBox.setSelected(true);
    multiplesCheckBox.setText("1-Op-Per-Group");
    multiplesCheckBox.setBounds(new Rectangle(9, 45, 106, 23));
    panel1.add(optionSelectList, null);
    panel1.add(selectedOptionField, null);
    panel1.add(codeLabel, null);
    panel1.add(nameLabel, null);
    panel1.add(jLabel1, null);
    panel1.add(optionGroupSelector, null);
    panel1.add(closeOptionSelectorButton, null);
    panel1.add(okButton, null);
    panel1.add(addButton, null);
    panel1.add(multiplesCheckBox, null);
    this.getContentPane().add(panel1, null);
  }

  void closeOptionSelectorButton_actionPerformed(ActionEvent e) {
    this.doCancel();
  }

  void okButton_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void optionSelectList_itemStateChanged(ItemEvent e) {
    doLoadSelection();
  }

  void optionGroupSelector_itemStateChanged(ItemEvent e) {
    doGroupSelection();
    this.selectFirstEntry();
  }

  void optionSelectList_actionPerformed(ActionEvent e) {
    this.hide();
  }

  void addButton_actionPerformed(ActionEvent e) {
    this.doAddOption();
  }
}

class OptionSelectionSimpleGUI_closeOptionSelectorButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionSelectionSimpleGUI adaptee;
  OptionSelectionSimpleGUI_closeOptionSelectorButton_actionAdapter(OptionSelectionSimpleGUI
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.closeOptionSelectorButton_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI_okButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionSelectionSimpleGUI adaptee;

  OptionSelectionSimpleGUI_okButton_actionAdapter(OptionSelectionSimpleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.okButton_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI_optionSelectList_itemAdapter
    implements java.awt.event.ItemListener {
  OptionSelectionSimpleGUI adaptee;

  OptionSelectionSimpleGUI_optionSelectList_itemAdapter(OptionSelectionSimpleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.optionSelectList_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI_optionGroupSelector_itemAdapter
    implements java.awt.event.ItemListener {
  OptionSelectionSimpleGUI adaptee;

  OptionSelectionSimpleGUI_optionGroupSelector_itemAdapter(OptionSelectionSimpleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.optionGroupSelector_itemStateChanged(e);
  }

}

class OptionSelectionSimpleGUI_optionSelectList_actionAdapter
    implements java.awt.event.ActionListener {
  OptionSelectionSimpleGUI adaptee;

  OptionSelectionSimpleGUI_optionSelectList_actionAdapter(OptionSelectionSimpleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.optionSelectList_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI_addButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionSelectionSimpleGUI adaptee;

  OptionSelectionSimpleGUI_addButton_actionAdapter(OptionSelectionSimpleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addButton_actionPerformed(e);
  }
}
