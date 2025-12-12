// (c) 2006-2007 NextremSoft

package nq.gui.standalone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.border.*;

import nq.session.LocalSession;

public class OptionNavigatorGUI extends JDialog {
  boolean keepAIOff = false;
  JPanel panel1 = new JPanel();
  List groupList1 = new List();
  List optionList1 = new List();
  JLabel statusBar = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  private LocalSession cs;
  JButton closeButton = new JButton();
  JTextField seriesFilterField = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JButton addButton = new JButton();
  JTextField codeField = new JTextField();
  JTextField listPriceField = new JTextField();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField btdField = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField netPriceField = new JTextField();
  JTextField discountField = new JTextField();
  private NextremQuoteStandaloneGUI nextremQuoteStandaloneGUI;
  JCheckBox toggleHideGroupsCheckBox = new JCheckBox();
  private String parentId;
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JTextField groupCodeField = new JTextField();
  JTextField groupDescField = new JTextField();
  JCheckBox multipleAllowedCheckBox = new JCheckBox();
  JCheckBox groupRequiredCheckBox = new JCheckBox();
  JTextField searchTextField = new JTextField();
  JLabel jLabel15 = new JLabel();
  JButton clearSearchButton = new JButton();
  JPanel jPanel2 = new JPanel();
  JCheckBox filterRequiredGroupsCheckBox = new JCheckBox();
  JTextArea attributeArea = new JTextArea();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JCheckBox aiCheckBox = new JCheckBox();
  JCheckBox hideIncompatibleCheckBox = new JCheckBox();
  JCheckBox multiplesCheckBox = new JCheckBox();
  JLabel jLabel18 = new JLabel();
  JTextField groupIndexField = new JTextField();
  JCheckBox addInOrderCheckBox = new JCheckBox();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel19 = new JLabel();
  List partsList = new List();
  JLabel jLabel20 = new JLabel();
  JLabel jLabel111 = new JLabel();
  JLabel jLabel112 = new JLabel();
  JCheckBox partMandCheckBox = new JCheckBox();
  JTextField partCodeField = new JTextField();
  JCheckBox showPartsCheckBox = new JCheckBox();
  JTextArea partAttributeArea = new JTextArea();
  TitledBorder titledBorder1;
  JPanel jPanel3 = new JPanel();
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  JTextField partCostField = new JTextField();
  JLabel jLabel113 = new JLabel();

  public OptionNavigatorGUI(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    this.setResizable(false);
    try {
      jbInit();
      pack();
      this.toFront();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public OptionNavigatorGUI() {
    this(null, "", false);
  }

  public void clientSession(LocalSession t) {
    this.cs = t;
  }

  public void nextremQuoteSimpleGUI(NextremQuoteStandaloneGUI nextremQuoteSimpleGUI) {
    this.nextremQuoteStandaloneGUI = nextremQuoteSimpleGUI;
  }

  public void parentId(String id) {
    this.parentId = id;
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)),
                                     "Option Details");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)),
                                     "Part Details");
    panel1.setLayout(null);
    groupList1.setEnabled(true);
    groupList1.setFont(new java.awt.Font("Tahoma", 0, 10));
    groupList1.setBounds(new Rectangle(13, 98, 237, 302));
    groupList1.addItemListener(new OptionSelectionSimpleGUI2_groupList1_itemAdapter(this));
    groupList1.addMouseListener(new OptionSelectionSimpleGUI2_groupList1_mouseAdapter(this));
    optionList1.setFont(new java.awt.Font("Tahoma", 0, 10));
    optionList1.setForeground(Color.black);
    optionList1.setBounds(new Rectangle(256, 98, 237, 302));
    optionList1.addItemListener(new OptionSelectionSimpleGUI2_optionList1_itemAdapter(this));
    optionList1.addMouseListener(new OptionSelectionSimpleGUI2_optionList1_mouseAdapter(this));
    optionList1.addActionListener(new OptionSelectionSimpleGUI2_optionList1_actionAdapter(this));
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar.setText("...");
    statusBar.setBounds(new Rectangle(2, 551, 734, 22));
    statusBar.addMouseListener(new OptionSelectionSimpleGUI2_statusBar_mouseAdapter(this));
    jLabel6.setBounds(new Rectangle(6, 13, 46, 14));
    jLabel6.setText("Series");
    jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel7.setBounds(new Rectangle(234, 81, 280, 14));
    jLabel7.setText("Options");
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setBounds(new Rectangle(13, 81, 214, 14));
    jLabel8.setText("Groups");
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    closeButton.setBounds(new Rectangle(673, 451, 63, 23));
    closeButton.setMargin(new Insets(2, 2, 2, 2));
    closeButton.setText("Close");
    closeButton.addActionListener(new OptionSelectionSimpleGUI2_closeButton_actionAdapter(this));
    seriesFilterField.setFont(new java.awt.Font("Tahoma", 0, 10));
    seriesFilterField.setEditable(false);
    seriesFilterField.setText("");
    seriesFilterField.setHorizontalAlignment(SwingConstants.LEADING);
    seriesFilterField.setBounds(new Rectangle(54, 8, 237, 24));
    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel9.setRequestFocusEnabled(true);
    jLabel9.setText("Code");
    jLabel9.setBounds(new Rectangle(14, 23, 44, 14));
    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel10.setToolTipText("");
    jLabel10.setText("List Price");
    jLabel10.setBounds(new Rectangle(14, 44, 44, 14));
    addButton.setBounds(new Rectangle(672, 418, 63, 23));
    addButton.setMargin(new Insets(2, 2, 2, 2));
    addButton.setText("Add");
    addButton.addActionListener(new OptionSelectionSimpleGUI2_addButton_actionAdapter(this));
    codeField.setBounds(new Rectangle(67, 23, 70, 14));
    codeField.setEditable(false);
    codeField.setBorder(null);
    codeField.setAlignmentY( (float) 0.5);
    codeField.setEnabled(true);
    listPriceField.setBackground(UIManager.getColor("Panel.background"));
    listPriceField.setEnabled(true);
    listPriceField.setAlignmentY( (float) 0.5);
    listPriceField.setBorder(null);
    listPriceField.setEditable(false);
    listPriceField.setBounds(new Rectangle(67, 44, 142, 14));
    jPanel1.setBorder(titledBorder1);
    jPanel1.setBounds(new Rectangle(14, 412, 477, 132));
    jPanel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel1.setText("Disc%");
    jLabel1.setBounds(new Rectangle(14, 63, 44, 14));
    btdField.setBounds(new Rectangle(67, 63, 29, 14));
    btdField.setEditable(false);
    btdField.setBorder(null);
    btdField.setAlignmentY( (float) 0.5);
    btdField.setEnabled(true);
    btdField.setBackground(UIManager.getColor("Panel.background"));
    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel4.setText("Net Price");
    jLabel4.setBounds(new Rectangle(14, 84, 44, 14));
    netPriceField.setBounds(new Rectangle(67, 84, 142, 14));
    netPriceField.setEditable(false);
    netPriceField.setBorder(null);
    netPriceField.setAlignmentY( (float) 0.5);
    netPriceField.setEnabled(true);
    netPriceField.setBackground(UIManager.getColor("Panel.background"));
    discountField.setBounds(new Rectangle(129, 63, 80, 14));
    discountField.setEditable(false);
    discountField.setBorder(null);
    discountField.setAlignmentY( (float) 0.5);
    discountField.setEnabled(true);
    discountField.setBackground(UIManager.getColor("Panel.background"));
    toggleHideGroupsCheckBox.setToolTipText("Tick to only show unused groups and their options");
    toggleHideGroupsCheckBox.setSelected(true);
    toggleHideGroupsCheckBox.setText("Hide Used Groups");
    toggleHideGroupsCheckBox.setBounds(new Rectangle(300, 44, 138, 23));
    toggleHideGroupsCheckBox.addItemListener(new OptionSelectionSimpleGUI2_toggleHideGroupsCheckBox_itemAdapter(this));
    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel12.setText("Group Desc");
    jLabel12.setBounds(new Rectangle(230, 21, 63, 14));
    jLabel13.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel13.setText("Grp Reqd?");
    jLabel13.setBounds(new Rectangle(14, 106, 52, 14));
    jLabel14.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel14.setText("Mult OK?");
    jLabel14.setBounds(new Rectangle(86, 106, 49, 14));
    groupCodeField.setBackground(UIManager.getColor("Panel.background"));
    groupCodeField.setEnabled(true);
    groupCodeField.setAlignmentY( (float) 0.5);
    groupCodeField.setBorder(null);
    groupCodeField.setEditable(false);
    groupCodeField.setBounds(new Rectangle(170, 23, 38, 14));
    groupDescField.setBounds(new Rectangle(303, 21, 152, 14));
    groupDescField.setEditable(false);
    groupDescField.setBorder(null);
    groupDescField.setAlignmentY( (float) 0.5);
    groupDescField.setEnabled(true);
    groupDescField.setBackground(UIManager.getColor("Panel.background"));
    multipleAllowedCheckBox.setBackground(UIManager.getColor("Panel.background"));
    multipleAllowedCheckBox.setEnabled(false);
    multipleAllowedCheckBox.setText("");
    multipleAllowedCheckBox.setBounds(new Rectangle(131, 106, 19, 14));
    groupRequiredCheckBox.setBounds(new Rectangle(64, 106, 19, 14));
    groupRequiredCheckBox.setBackground(UIManager.getColor("Panel.background"));
    groupRequiredCheckBox.setEnabled(false);
    groupRequiredCheckBox.setText("");
    searchTextField.setText("");
    searchTextField.setBounds(new Rectangle(54, 38, 178, 24));
    searchTextField.addKeyListener(new OptionSelectionSimpleGUI2_searchTextField_keyAdapter(this));
    jLabel15.setText("Search");
    jLabel15.setBounds(new Rectangle(6, 43, 46, 14));
    clearSearchButton.setBounds(new Rectangle(246, 39, 45, 23));
    clearSearchButton.setMargin(new Insets(2, 2, 2, 2));
    clearSearchButton.setText("Clear");
    clearSearchButton.addActionListener(new OptionSelectionSimpleGUI2_clearSearchButton_actionAdapter(this));
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setBounds(new Rectangle(13, 7, 712, 72));
    jPanel2.setLayout(null);
    filterRequiredGroupsCheckBox.setToolTipText("Tick to only show required options");
    filterRequiredGroupsCheckBox.setSelected(true);
    filterRequiredGroupsCheckBox.setText("Hide Unrequired");
    filterRequiredGroupsCheckBox.setBounds(new Rectangle(300, 24, 138, 23));
    filterRequiredGroupsCheckBox.addItemListener(new OptionSelectionSimpleGUI2_filterRequiredGroupsCheckBox_itemAdapter(this));
    attributeArea.setBackground(UIManager.getColor("Panel.background"));
    attributeArea.setFont(new java.awt.Font("Dialog", 0, 9));
    attributeArea.setBorder(null);
    attributeArea.setEditable(false);
    attributeArea.setText("");
    attributeArea.setBounds(new Rectangle(302, 43, 152, 72));
    jLabel16.setBounds(new Rectangle(230, 55, 63, 14));
    jLabel16.setText("Attributes:");
    jLabel16.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel17.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel17.setText("Compatibility");
    jLabel17.setBounds(new Rectangle(230, 40, 63, 14));
    //aiCheckBox.addItemListener(new OptionSelectionSimpleGUI2_aiCheckBox_itemAdapter(this));
    aiCheckBox.setBounds(new Rectangle(444, 4, 146, 23));
    aiCheckBox.addActionListener(new OptionSelectionSimpleGUI2_aiCheckBox_actionAdapter(this));
    aiCheckBox.setEnabled(true);
    aiCheckBox.setToolTipText("Tick to ensure all options are compatible with the basket");
    aiCheckBox.setActionCommand("Enforce Compatib.");
    aiCheckBox.setSelected(true);
    aiCheckBox.setText("Enforce Compatib.");
    hideIncompatibleCheckBox.setBounds(new Rectangle(300, 4, 138, 23));
    hideIncompatibleCheckBox.addActionListener(new OptionSelectionSimpleGUI2_hideIncompatibleCheckBox_actionAdapter(this));
    hideIncompatibleCheckBox.setEnabled(true);
    hideIncompatibleCheckBox.setToolTipText("Tick to only show options that are compatible with the basket");
    hideIncompatibleCheckBox.setSelected(true);
    hideIncompatibleCheckBox.setText("Hide Incompatible");
    multiplesCheckBox.setText("1-Op-Per-Group");
    multiplesCheckBox.setSelected(true);
    multiplesCheckBox.setEnabled(true);
    multiplesCheckBox.setToolTipText("Tick to only allow 1 option per group");
    multiplesCheckBox.setBounds(new Rectangle(444, 24, 146, 23));
    jLabel18.setBounds(new Rectangle(154, 106, 36, 14));
    jLabel18.setText("Order");
    jLabel18.setFont(new java.awt.Font("Tahoma", 0, 9));
    groupIndexField.setBounds(new Rectangle(183, 106, 26, 14));
    groupIndexField.setEditable(false);
    groupIndexField.setBorder(null);
    groupIndexField.setAlignmentY( (float) 0.5);
    groupIndexField.setEnabled(true);
    groupIndexField.setBackground(UIManager.getColor("Panel.background"));
    addInOrderCheckBox.setBounds(new Rectangle(444, 44, 146, 23));
    addInOrderCheckBox.addActionListener(new OptionSelectionSimpleGUI2_addInOrderCheckBox_actionAdapter(this));
    addInOrderCheckBox.setToolTipText("Tick to only allow 1 option per group");
    addInOrderCheckBox.setEnabled(true);
    addInOrderCheckBox.setSelected(true);
    addInOrderCheckBox.setText("Add In Order");
    jLabel11.setBounds(new Rectangle(141, 23, 31, 14));
    jLabel11.setText("Group");
    jLabel11.setRequestFocusEnabled(true);
    jLabel11.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel19.setBounds(new Rectangle(102, 63, 29, 14));
    jLabel19.setText("Disc");
    jLabel19.setRequestFocusEnabled(true);
    jLabel19.setFont(new java.awt.Font("Tahoma", 0, 9));

    partsList.setBounds(new Rectangle(498, 98, 237, 302));
    partsList.addItemListener(new OptionSelectionSimpleGUI2_partsList_itemAdapter(this));
    partsList.setForeground(Color.black);
    partsList.setFont(new java.awt.Font("Tahoma", 0, 10));
    jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel20.setText("Parts");
    jLabel20.setBounds(new Rectangle(527, 79, 207, 14));
    jLabel111.setBounds(new Rectangle(6, 21, 51, 14));
    jLabel111.setText("Part Code");
    jLabel111.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel112.setBounds(new Rectangle(6, 43, 36, 14));
    jLabel112.setText("Mand");
    jLabel112.setFont(new java.awt.Font("Tahoma", 0, 9));
    partMandCheckBox.setText("");
    partMandCheckBox.setEnabled(false);
    partMandCheckBox.setBackground(UIManager.getColor("Panel.background"));
    partMandCheckBox.setBounds(new Rectangle(38, 43, 19, 14));
    partCodeField.setBackground(UIManager.getColor("Panel.background"));
    partCodeField.setEnabled(true);
    partCodeField.setAlignmentY( (float) 0.5);
    partCodeField.setBorder(null);
    partCodeField.setEditable(false);
    partCodeField.setBounds(new Rectangle(62, 21, 89, 14));
    showPartsCheckBox.setText("Show Parts");
    showPartsCheckBox.setSelected(true);
    showPartsCheckBox.setToolTipText("Tick to ensure all options are compatible with the basket");
    showPartsCheckBox.setEnabled(false);
    showPartsCheckBox.addActionListener(new OptionSelectionSimpleGUI2_showPartsCheckBox_actionAdapter(this));
    showPartsCheckBox.setBounds(new Rectangle(595, 6, 105, 23));
    showPartsCheckBox.addActionListener(new OptionSelectionSimpleGUI2_showPartsCheckBox_actionAdapter(this));
    partAttributeArea.setBounds(new Rectangle(14, 63, 135, 56));
    partAttributeArea.setText("");
    partAttributeArea.setEditable(false);
    partAttributeArea.setBorder(null);
    partAttributeArea.setFont(new java.awt.Font("Dialog", 0, 9));
    partAttributeArea.setBackground(UIManager.getColor("Panel.background"));
    jPanel3.setBorder(titledBorder3);
    jPanel3.setBounds(new Rectangle(504, 412, 162, 132));
    jPanel3.setLayout(null);
    partCostField.setBounds(new Rectangle(91, 43, 60, 14));
    partCostField.setEditable(false);
    partCostField.setBorder(null);
    partCostField.setAlignmentY( (float) 0.5);
    partCostField.setEnabled(true);
    partCostField.setBackground(UIManager.getColor("Panel.background"));
    jLabel113.setFont(new java.awt.Font("Tahoma", 0, 9));
    jLabel113.setText("Cost");
    jLabel113.setBounds(new Rectangle(60, 43, 36, 14));
    getContentPane().add(panel1);
    panel1.add(statusBar, null);
    panel1.add(groupList1, null);
    jPanel1.add(netPriceField, null);
    jPanel1.add(jLabel9, null);
    jPanel1.add(codeField, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(groupCodeField, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(listPriceField, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(btdField, null);
    jPanel1.add(jLabel19, null);
    jPanel1.add(discountField, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jLabel13, null);
    jPanel1.add(groupRequiredCheckBox, null);
    jPanel1.add(jLabel14, null);
    jPanel1.add(multipleAllowedCheckBox, null);
    jPanel1.add(jLabel18, null);
    jPanel1.add(groupIndexField, null);
    jPanel1.add(attributeArea, null);
    jPanel1.add(jLabel12, null);
    jPanel1.add(groupDescField, null);
    jPanel1.add(jLabel17, null);
    jPanel1.add(jLabel16, null);
    panel1.add(addButton, null);
    panel1.add(closeButton, null);
    panel1.add(jPanel3, null);
    jPanel3.add(jLabel111, null);
    jPanel3.add(partCodeField, null);
    jPanel3.add(jLabel112, null);
    jPanel3.add(partAttributeArea, null);
    jPanel3.add(partCostField, null);
    jPanel3.add(partMandCheckBox, null);
    panel1.add(optionList1, null);
    panel1.add(partsList, null);
    panel1.add(jLabel20, null);
    panel1.add(jLabel7, null);
    panel1.add(jLabel8, null);
    panel1.add(jPanel2, null);
    jPanel2.add(jLabel15, null);
    jPanel2.add(jLabel6, null);
    jPanel2.add(searchTextField, null);
    jPanel2.add(seriesFilterField, null);
    jPanel2.add(clearSearchButton, null);
    jPanel2.add(addInOrderCheckBox, null);
    jPanel2.add(aiCheckBox, null);
    jPanel2.add(multiplesCheckBox, null);
    jPanel2.add(showPartsCheckBox, null);
    jPanel2.add(toggleHideGroupsCheckBox, null);
    jPanel2.add(hideIncompatibleCheckBox, null);
    jPanel2.add(filterRequiredGroupsCheckBox, null);
    panel1.add(jPanel1, null);
    jPanel3.add(jLabel113, null);
  }

  private void doSearchOptions() {
    //this.searchTextField.setText(this.searchTextField.getText().replaceAll("\n", ""));
    //this.searchTextField.setText(this.searchTextField.getText().trim());
    //this.groupList1.removeAll();
    //this.optionList1.removeAll();
    //Option basketParentOption = t.getBasketForUser().findOptionById(this.parentId);
    //Option catalogParentUnit = this.t.serverSession().optionCatalog().findOptionByCode(basketParentOption.getCode());
    //for (int i = 0; i < catalogParentUnit.options().length; i++) {
    //  Option thisOption = catalogParentUnit.options()[i];
    //  if (thisOption.getName().toLowerCase().indexOf(this.searchTextField.getText().toLowerCase()) != -1 ||
    //      thisOption.groupDesc().toLowerCase().indexOf(this.searchTextField.getText().toLowerCase()) != -1 ||
    //      thisOption.getGroup().toLowerCase().indexOf(this.searchTextField.getText().toLowerCase()) != -1 ||
    //      thisOption.getCode().toLowerCase().indexOf(this.searchTextField.getText().toLowerCase()) != -1) {
    //    if (!new StringManipulationToolkit().createVectorFromArray(this.groupList1.getItems()).contains
    //        (thisOption.groupDesc() + spacer + "(" + thisOption.getGroup() + ")")) {
    //      this.groupList1.add(thisOption.groupDesc() + spacer + "(" + thisOption.getGroup() + ")");
    //    }
    //    this.optionList1.add(thisOption.getName() + spacer + "(" + thisOption.getCode() + ")");
    //  }
    //}
  }

  private void doExplainTransaction(){
    String incompatibleOptions = "0";
    if(cs.getLastTransactionSummary().get("Incompatible Options") != null){
      incompatibleOptions = cs.getLastTransactionSummary().get("Incompatible Options").toString();
    }
    String message = "Incompatible options: " + incompatibleOptions;

    this.statusBar.setText(message);
  }


  private void doLoadGroupsForSeries1() {
    ArrayList myGroups = cs.doLoadGroupsForSeries1(this.parentId,
                             this.toggleHideGroupsCheckBox.isSelected(),
                             this.filterRequiredGroupsCheckBox.isSelected());

    this.groupList1.removeAll();
    this.optionList1.removeAll();

    // Get all catalog groups for this series

    java.util.Iterator ite = myGroups.iterator();
    while(ite.hasNext()) this.groupList1.add(ite.next().toString());

    if (this.groupList1.getItemCount() > 0) {
      this.groupList1.select(0);
      doLoadOptionsForGroupAndSeries1();
    }

  }

  private void doLoadOptionsForGroupAndSeries1() {

    if ((this.groupList1.getSelectedIndex() == 0 &&
         this.addInOrderCheckBox.isSelected()) ||
        !this.addInOrderCheckBox.isSelected()) {
      this.optionList1.setEnabled(true);
    }
    else this.optionList1.setEnabled(false);

    this.optionList1.removeAll();

    ArrayList options = cs.getOptionsForGroupAndSeries(this.extractCode(this.groupList1.getSelectedItem()),
        this.parentId,
        this.hideIncompatibleCheckBox.isSelected());
    java.util.Iterator ite = options.iterator();
    while(ite.hasNext()) this.optionList1.add(ite.next().toString());

    if (this.optionList1.getItemCount() > 0) {
      this.optionList1.select(0);
      this.doSelectOption();
    }
  }

  private void doSelectOption() {
    String selectedCode = this.extractCode(this.optionList1.getSelectedItem());
    ArrayList myParts = cs.doSelectOption(this.parentId,
                     this.extractCode(this.optionList1.getSelectedItem()),
                     this.hideIncompatibleCheckBox.isSelected());


    this.codeField.setText(this.extractCode(this.optionList1.getSelectedItem()));

    this.partsList.removeAll();

    java.util.Iterator ite = myParts.iterator();
    while(ite.hasNext()) this.partsList.add(ite.next().toString());


    this.groupIndexField.setText(cs.getGroupIndexByCode(selectedCode));
    this.listPriceField.setText(cs.getListPriceByCode(selectedCode));

    this.btdField.setText(cs.getBtdByCode(selectedCode));
    this.discountField.setText(cs.getBtdByCode(selectedCode));
    this.netPriceField.setText(cs.getNetPriceByCode(selectedCode));
    this.groupCodeField.setText(cs.getGroupCodeByCode(selectedCode));
    this.groupDescField.setText(cs.getGroupDescByCode(selectedCode));
    this.groupRequiredCheckBox.setSelected(cs.isGroupRequiredByCode(selectedCode));
    this.multipleAllowedCheckBox.setSelected(cs.isMultipleAllowedByCode(selectedCode));
    this.attributeArea.setText("");
    for (int i = 0; i < cs.getOptionAttributeNamesByCode(selectedCode).length; i++) {
      this.attributeArea.append(cs.getOptionAttributeNamesByCode(selectedCode)[i]);
    }
    if (this.partsList.getItemCount() > 0) {
      this.partsList.select(0);
      doLoadPartInfo();
    }
  }

  private void doLoadPartInfo() {
    //if (this.partsList.getItemCount() > 0) {
    //  Option selectedPart = this.optionCatalog.findOptionByCode(this.extractCode(this.partsList.getSelectedItem()));
    //  this.partCodeField.setText(selectedPart.getCode());
    //  this.partMandCheckBox.setSelected(selectedPart.mandatory());
    //  String userName = System.getProperty("user.name");
    //  if (userName.equals("efnceras") || userName.equals("jcerasuolo") || userName.equals("efnmanzo")) {
    //    this.partCostField.setVisible(true);
     // }
    //  else {
    //    this.partCostField.setVisible(false);
    //  }
    //  this.partCostField.setText("" + Math.round(selectedPart.cost()));

      // Part attributes
     // this.partAttributeArea.setText("");
     // for (int i = 0; i < selectedPart.attributeKeys().length; i++) {
      //  String key = selectedPart.attributeKeys()[i];
     //   this.partAttributeArea.append(key + ": " +
      //                                selectedPart.attributes().get(key).toString() + "\n");
      //}
   // }
  }

  private void addOptionToUnit(int groupIndex) {
    // If user has chosen to add in order
    if ( (this.addInOrderCheckBox.isSelected() && groupIndex == 0) ||
         !this.addInOrderCheckBox.isSelected()) {

      HashMap tPack = this.nextremQuoteStandaloneGUI.addOptionSilently
          (this.codeField.getText(),
           this.aiCheckBox.isSelected(),
           this.multiplesCheckBox.isSelected());

      // Switch the 1-op-per-group rule back on
      this.multiplesCheckBox.setSelected(true);

      // If conditioners are needed the main GUI will manage what happens
      // next so simply close
      if (!tPack.get("Conditioners Available").toString().equals("0")) this.dispose();

      // If the transaction was completed
      if (tPack.get("Transaction Completed").toString().equals("true")) {
        this.doLoadGroupsForSeries1();
        this.statusBar.setForeground(Color.black);

        // IF no compatibility was checked
        if (!this.aiCheckBox.isSelected()) {
          this.statusBar.setForeground(Color.red);
          this.statusBar.setText("Transaction completed. Compatibility not verified.");
        }

        // If compatibility was checked
        else {
          this.statusBar.setForeground(Color.black);
          this.statusBar.setText("Transaction completed.");
        }
      }

      // If the transaction log indicates transaction failed
      else {
        this.statusBar.setForeground(Color.red);
        this.statusBar.setText("Transaction NOT completed: " + tPack.get("Transaction Log"));
      }
      this.toFront();
    }
    else {
      JOptionPane.showMessageDialog(null, "You need to add options from the available groups in the right order.\n" +
                                    "Please select options from the first group in the list or untick the 'add in order' box.",
                                    "NextremQuote", 0);
    }
  }



  private String extractCode(String nameAndCode) {
    if (nameAndCode != null && nameAndCode.indexOf("(") > 0 && nameAndCode.indexOf(")") > 0) {
      return nameAndCode.substring(nameAndCode.lastIndexOf("(") + 1, nameAndCode.lastIndexOf(")"));
    }
    else {
      return "";
    }
  }

  void optionList1_actionPerformed(ActionEvent e) {
    this.searchTextField.setText("");
    this.addOptionToUnit(this.groupList1.getSelectedIndex());
  }

  void closeButton_actionPerformed(ActionEvent e) {
    this.hide();
  }

  public void setSeriesFilter(String series) {
    this.seriesFilterField.setText(series);
    this.doLoadGroupsForSeries1();

  }

  void optionList1_mouseClicked(MouseEvent e) {
    //this.doSelectOption1();
  }

  void optionList1_itemStateChanged(ItemEvent e) {
    this.doSelectOption();
  }

  void addButton_actionPerformed(ActionEvent e) {
    this.searchTextField.setText("");
    this.addOptionToUnit(this.groupList1.getSelectedIndex());
  }

  void toggleHideGroupsCheckBox_itemStateChanged(ItemEvent e) {
    this.searchTextField.setText("");
    this.doLoadGroupsForSeries1();
  }

  void groupList1_itemStateChanged(ItemEvent e) {
    doLoadOptionsForGroupAndSeries1();
  }

  void searchTextField_keyReleased(KeyEvent e) {
    doSearchOptions();
  }

  void clearSearchButton_actionPerformed(ActionEvent e) {
    this.searchTextField.setText("");
    this.doLoadGroupsForSeries1();
  }

  void filterRequiredGroupsCheckBox_itemStateChanged(ItemEvent e) {
    this.doLoadGroupsForSeries1();
  }

  void aiCheckBox_actionPerformed(ActionEvent e) {

  }

  void addInOrderCheckBox_actionPerformed(ActionEvent e) {
    if (!this.addInOrderCheckBox.isSelected()) {
      this.optionList1.setEnabled(true);
    }
    else if (this.groupList1.getSelectedIndex() != 0) {
      this.optionList1.setEnabled(false);
    }
  }

  void hideIncompatibleCheckBox_actionPerformed(ActionEvent e) {
    int selectedGroup = this.groupList1.getSelectedIndex();
    this.doLoadGroupsForSeries1();
    this.groupList1.select(selectedGroup);
    this.doLoadOptionsForGroupAndSeries1();
  }

  void partsList_itemStateChanged(ItemEvent e) {
    this.doLoadPartInfo();
  }

  void showPartsCheckBox_actionPerformed(ActionEvent e) {
    this.partsList.setVisible(this.showPartsCheckBox.isSelected());
  }

  void perfectMatchCheckBox_actionPerformed(ActionEvent e) {
    int selectedGroup = this.groupList1.getSelectedIndex();
    this.doLoadGroupsForSeries1();
    this.groupList1.select(selectedGroup);
    this.doLoadOptionsForGroupAndSeries1();
  }

  void statusBar_mouseClicked(MouseEvent e) {
    this.doExplainTransaction();
  }

}

class OptionSelectionSimpleGUI2_optionList1_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_optionList1_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.optionList1_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_closeButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_closeButton_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.closeButton_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_groupList1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_groupList1_mouseAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }
}

class OptionSelectionSimpleGUI2_optionList1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_optionList1_mouseAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.optionList1_mouseClicked(e);
  }
}

class OptionSelectionSimpleGUI2_optionList1_itemAdapter
    implements java.awt.event.ItemListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_optionList1_itemAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.optionList1_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI2_addButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_addButton_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addButton_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_toggleHideGroupsCheckBox_itemAdapter
    implements java.awt.event.ItemListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_toggleHideGroupsCheckBox_itemAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.toggleHideGroupsCheckBox_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI2_groupList1_itemAdapter
    implements java.awt.event.ItemListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_groupList1_itemAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.groupList1_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI2_searchTextField_keyAdapter
    extends java.awt.event.KeyAdapter {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_searchTextField_keyAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.searchTextField_keyReleased(e);
  }
}

class OptionSelectionSimpleGUI2_clearSearchButton_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_clearSearchButton_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.clearSearchButton_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_filterRequiredGroupsCheckBox_itemAdapter
    implements java.awt.event.ItemListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_filterRequiredGroupsCheckBox_itemAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.filterRequiredGroupsCheckBox_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI2_aiCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_aiCheckBox_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.aiCheckBox_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_addInOrderCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_addInOrderCheckBox_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addInOrderCheckBox_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_hideIncompatibleCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_hideIncompatibleCheckBox_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.hideIncompatibleCheckBox_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_partsList_itemAdapter
    implements java.awt.event.ItemListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_partsList_itemAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.partsList_itemStateChanged(e);
  }
}

class OptionSelectionSimpleGUI2_showPartsCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_showPartsCheckBox_actionAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.showPartsCheckBox_actionPerformed(e);
  }
}

class OptionSelectionSimpleGUI2_statusBar_mouseAdapter extends java.awt.event.MouseAdapter {
  OptionNavigatorGUI adaptee;

  OptionSelectionSimpleGUI2_statusBar_mouseAdapter(OptionNavigatorGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.statusBar_mouseClicked(e);
  }
}
