// (c) 2006-2007 NextremSoft

package nq.gui.standalone;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

import nq.session.*;

public class InformationConsoleGUI extends JDialog {

  private NextremQuoteStandaloneGUI nextremQuoteGUI;
  private LocalSession localSession;
  private String id;

  JPanel panel1 = new JPanel();
  JLabel costLabel = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel4 = new JPanel();
  Border border1;
  JTextField costField = new JTextField();
  JTextField discP100Field = new JTextField();
  JTextField listPriceField = new JTextField();
  JTextArea daughtersArea = new JTextArea();
  JLabel jLabel16 = new JLabel();
  JLabel marginLabel = new JLabel();
  JLabel jLabel20 = new JLabel();
  JTextField netPriceField = new JTextField();
  JTextField marginField = new JTextField();
  JTextField marginP100Field = new JTextField();
  JLabel marginP100Label = new JLabel();
  JLabel jLabel21 = new JLabel();
  JCheckBox groupRequiredCheckBox = new JCheckBox();
  JLabel jLabel22 = new JLabel();
  JCheckBox multipleAllowedCheckBox = new JCheckBox();
  Border border2;
  Border border3;
  Border border4;
  TitledBorder titledBorder1;
  Border border5;
  TitledBorder titledBorder2;
  Border border6;
  TitledBorder titledBorder3;
  JTextField discountField = new JTextField();
  JLabel jLabel23 = new JLabel();
  JTextField quantityField = new JTextField();
  JLabel jLabel19 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JCheckBox mandatoryCheckBox = new JCheckBox();
  JLabel jLabel24 = new JLabel();
  JComboBox currencyComboBox = new JComboBox();
  JLabel jLabel7 = new JLabel();
  JScrollPane jScrollPane4 = new JScrollPane();
  JLabel jLabel110 = new JLabel();
  JLabel jLabel111 = new JLabel();
  JTextArea conditionerOptionsArea = new JTextArea();
  JLabel nameLabel2 = new JLabel();
  JLabel nameLabel3 = new JLabel();
  JLabel nameLabel4 = new JLabel();
  JTextField customerField = new JTextField();
  JComboBox dealerComboBox = new JComboBox();
  JComboBox countryComboBox = new JComboBox();
  JLabel nameLabel5 = new JLabel();
  JPanel jPanel3 = new JPanel();
  JLabel nameLabel = new JLabel();
  JTextField groupDescriptionField = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField nameField = new JTextField();
  JTextField codeField = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField groupField = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField tmNameField = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField btdField = new JTextField();
  JTextField autField = new JTextField();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea attributesArea = new JTextArea();

  public InformationConsoleGUI(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    this.nextremQuoteGUI = (NextremQuoteStandaloneGUI) frame;
    this.costField.setVisible(false);
    this.costLabel.setVisible(false);
    this.marginField.setVisible(false);
    this.marginLabel.setVisible(false);
    this.marginP100Field.setVisible(false);
    this.marginP100Label.setVisible(false);
    this.setResizable(false);
    try {
      jbInit();
      //pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public InformationConsoleGUI() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    System.out.print("..");
    border1 = BorderFactory.createEmptyBorder();
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(103, 101, 98),
                                              new Color(148, 145, 140));
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(103, 101, 98),
                                              new Color(148, 145, 140));
    border4 = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "");
    border5 = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "");
    border6 = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "");
    panel1.setLayout(null);
    this.getContentPane().setLayout(null);
    panel1.setBounds(new Rectangle( -1, 0, 318, 579));
    costLabel.setFont(new java.awt.Font("System", 0, 9));
    costLabel.setText("Cost");
    costLabel.setBounds(new Rectangle(208, 43, 29, 20));
    jLabel10.setFont(new java.awt.Font("System", 0, 9));
    jLabel10.setText("Price");
    jLabel10.setBounds(new Rectangle(3, 8, 35, 20));
    jLabel11.setFont(new java.awt.Font("System", 0, 9));
    jLabel11.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel11.setText("Disc%");
    jLabel11.setBounds(new Rectangle(103, 43, 40, 20));
    jLabel13.setFont(new java.awt.Font("System", 0, 9));
    jLabel13.setText("Nested");
    jLabel13.setBounds(new Rectangle(3, 89, 43, 14));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setBounds(new Rectangle(6, 228, 305, 107));
    jPanel1.setLayout(null);
    jPanel2.setBorder(titledBorder1);
    jPanel2.setBounds(new Rectangle(6, 343, 305, 211));
    jPanel2.setLayout(null);
    jPanel4.setBorder(titledBorder3);
    jPanel4.setBounds(new Rectangle(6, 8, 305, 116));
    jPanel4.setLayout(null);
    costField.setBackground(Color.yellow);
    //costField.setBorder(BorderFactory.createLoweredBevelBorder());
    costField.setEditable(false);
    costField.setText("0");
    costField.setHorizontalAlignment(SwingConstants.RIGHT);
    //costField.setHorizontalAlignment(SwingConstants.TRAILING);
    costField.setBounds(new Rectangle(232, 43, 67, 20));
    discP100Field.setBounds(new Rectangle(135, 43, 67, 20));
    System.out.print("..");
    discP100Field.addKeyListener(new
                                 OptionPropertiesSimpleWindow_discP100Field_keyAdapter(this));
    discP100Field.addFocusListener(new
                                   OptionPropertiesSimpleWindow_discP100Field_focusAdapter(this));
    discP100Field.setText("0.0");
    discP100Field.setHorizontalAlignment(SwingConstants.RIGHT);
    //discP100Field.setHorizontalAlignment(SwingConstants.TRAILING);
    discP100Field.setBackground(new Color(212, 208, 201));
    //discP100Field.setBorder(BorderFactory.createLoweredBevelBorder());
    listPriceField.setBackground(Color.yellow);
    //listPriceField.setBorder(BorderFactory.createLoweredBevelBorder());
    listPriceField.setMinimumSize(new Dimension(4, 20));
    listPriceField.setPreferredSize(new Dimension(4, 20));
    listPriceField.setRequestFocusEnabled(true);
    listPriceField.setEditable(false);
    listPriceField.setText("0");
    listPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
    //listPriceField.setHorizontalAlignment(SwingConstants.TRAILING);
    listPriceField.setBounds(new Rectangle(29, 8, 67, 20));
    daughtersArea.setBackground(UIManager.getColor("Panel.background"));
    daughtersArea.setFont(new java.awt.Font("System", 0, 9));
    daughtersArea.setBorder(null);
    daughtersArea.setEditable(false);
    daughtersArea.setText("");
    daughtersArea.setLineWrap(false);
    daughtersArea.setWrapStyleWord(true);
    System.out.print("..");
    jLabel16.setFont(new java.awt.Font("System", 0, 9));
    jLabel16.setText("Items");
    jLabel16.setBounds(new Rectangle(3, 102, 43, 14));
    marginLabel.setFont(new java.awt.Font("System", 0, 9));
    marginLabel.setText("Marg");
    marginLabel.setBounds(new Rectangle(207, 79, 29, 20));
    jLabel20.setFont(new java.awt.Font("System", 0, 9));
    jLabel20.setRequestFocusEnabled(true);
    jLabel20.setText("Net");
    jLabel20.setBounds(new Rectangle(207, 8, 29, 20));
    netPriceField.setBounds(new Rectangle(232, 8, 67, 20));
    //netPriceField.setHorizontalAlignment(SwingConstants.TRAILING);
    netPriceField.setText("0");
    netPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
    netPriceField.setEditable(false);
    //netPriceField.setBorder(BorderFactory.createLoweredBevelBorder());
    netPriceField.setBackground(Color.yellow);
    marginField.setBackground(Color.yellow);
    //marginField.setBorder(BorderFactory.createLoweredBevelBorder());
    marginField.setEditable(false);
    marginField.setText("0");
    marginField.setHorizontalAlignment(SwingConstants.RIGHT);
    //marginField.setHorizontalAlignment(SwingConstants.TRAILING);
    marginField.setBounds(new Rectangle(232, 79, 67, 20));
    marginP100Field.setBounds(new Rectangle(135, 79, 67, 20));
    //marginP100Field.setHorizontalAlignment(SwingConstants.TRAILING);
    marginP100Field.setText("0.0");
    marginP100Field.setHorizontalAlignment(SwingConstants.RIGHT);
    marginP100Field.setEditable(false);
    //marginP100Field.setBorder(BorderFactory.createLoweredBevelBorder());
    marginP100Field.setBackground(Color.yellow);
    marginP100Label.setFont(new java.awt.Font("System", 0, 9));
    marginP100Label.setHorizontalAlignment(SwingConstants.LEADING);
    marginP100Label.setText("Marg%");
    marginP100Label.setBounds(new Rectangle(102, 79, 40, 20));
    jLabel21.setFont(new java.awt.Font("System", 0, 9));
    jLabel21.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel21.setText("Group Required");
    jLabel21.setBounds(new Rectangle(6, 556, 76, 14));
    groupRequiredCheckBox.setBounds(new Rectangle(87, 556, 19, 14));
    groupRequiredCheckBox.setText("");
    groupRequiredCheckBox.setToolTipText("");
    groupRequiredCheckBox.setEnabled(false);
    jLabel22.setFont(new java.awt.Font("System", 0, 9));
    jLabel22.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel22.setHorizontalTextPosition(SwingConstants.LEFT);
    System.out.print("..");
    jLabel22.setText("Multiple Allowed");
    jLabel22.setBounds(new Rectangle(104, 556, 80, 14));
    multipleAllowedCheckBox.setBounds(new Rectangle(191, 556, 21, 14));
    multipleAllowedCheckBox.setText("");
    multipleAllowedCheckBox.setToolTipText("");
    multipleAllowedCheckBox.setEnabled(false);
    discountField.setBackground(Color.yellow);
    //discountField.setBorder(BorderFactory.createLoweredBevelBorder());
    discountField.setEditable(false);
    discountField.setText("0");
    discountField.setHorizontalAlignment(SwingConstants.RIGHT);
    //discountField.setHorizontalAlignment(SwingConstants.TRAILING);
    discountField.setBounds(new Rectangle(135, 8, 67, 20));
    jLabel23.setBounds(new Rectangle(102, 8, 40, 20));
    jLabel23.setText("Disc");
    jLabel23.setFont(new java.awt.Font("System", 0, 9));
    jLabel23.setRequestFocusEnabled(true);
    jLabel23.setHorizontalAlignment(SwingConstants.LEADING);
    //quantityField.setBorder(BorderFactory.createLoweredBevelBorder());
    quantityField.setBackground(new Color(212, 208, 201));
    //quantityField.setHorizontalAlignment(SwingConstants.TRAILING);
    quantityField.setText("0");
    quantityField.setHorizontalAlignment(SwingConstants.RIGHT);
    quantityField.setBounds(new Rectangle(29, 79, 67, 20));
    quantityField.addKeyListener(new
                                 OptionPropertiesSimpleWindow_quantityField_keyAdapter(this));
    quantityField.addFocusListener(new
                                   OptionPropertiesSimpleWindow_quantityField_focusAdapter(this));
    jLabel19.setBounds(new Rectangle(4, 79, 33, 20));
    jLabel19.setFont(new java.awt.Font("System", 0, 9));
    jLabel19.setText("Units");
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
    jScrollPane1.setBounds(new Rectangle(51, 90, 247, 55));
    mandatoryCheckBox.setEnabled(false);
    mandatoryCheckBox.setToolTipText("");
    mandatoryCheckBox.setText("");
    mandatoryCheckBox.setBounds(new Rectangle(289, 556, 19, 14));
    jLabel24.setBounds(new Rectangle(214, 556, 72, 14));
    jLabel24.setFont(new java.awt.Font("System", 0, 9));
    jLabel24.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel24.setText("Part Mandatory");
    currencyComboBox.setBounds(new Rectangle(238, 7, 58, 20));
    currencyComboBox.setBounds(new Rectangle(29, 43, 67, 20));
    jLabel7.setFont(new java.awt.Font("System", 0, 9));
    jLabel7.setText("Curr");
    jLabel7.setBounds(new Rectangle(3, 43, 34, 20));
    jScrollPane4.setBounds(new Rectangle(50, 152, 247, 50));
    jScrollPane4.setBorder(BorderFactory.createEtchedBorder());
    jScrollPane4.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_NEVER);
    jLabel110.setBounds(new Rectangle(3, 151, 43, 14));
    jLabel110.setFont(new java.awt.Font("System", 0, 9));
    jLabel110.setText("Combo");
    jLabel111.setText("Items");
    jLabel111.setFont(new java.awt.Font("System", 0, 9));
    jLabel111.setToolTipText("");
    jLabel111.setBounds(new Rectangle(3, 164, 43, 14));
    conditionerOptionsArea.setBackground(UIManager.getColor("Panel.background"));
    conditionerOptionsArea.setFont(new java.awt.Font("System", 0, 9));
    conditionerOptionsArea.setEditable(false);
    conditionerOptionsArea.setText("");
    nameLabel2.setBounds(new Rectangle(6, 34, 57, 20));
    nameLabel2.setFont(new java.awt.Font("System", 0, 9));
    nameLabel2.setToolTipText("");
    nameLabel2.setText("Country");
    nameLabel3.setFont(new java.awt.Font("System", 0, 9));
    nameLabel3.setText("Dealer");
    nameLabel3.setBounds(new Rectangle(6, 60, 57, 20));
    nameLabel4.setBounds(new Rectangle(6, 86, 57, 20));
    nameLabel4.setFont(new java.awt.Font("System", 0, 9));
    nameLabel4.setText("Customer");
    customerField.setBounds(new Rectangle(53, 86, 239, 20));
    customerField.addKeyListener(new OptionPropertiesSimpleWindow_customerField_keyAdapter(this));
    customerField.addFocusListener(new OptionPropertiesSimpleWindow_customerField_focusAdapter(this));
    dealerComboBox.setBounds(new Rectangle(53, 60, 239, 20));
    dealerComboBox.addFocusListener(new OptionPropertiesSimpleWindow_dealerComboBox_focusAdapter(this));
    dealerComboBox.addItemListener(new OptionPropertiesSimpleWindow_dealerComboBox_itemAdapter(this));
    countryComboBox.setBounds(new Rectangle(53, 34, 239, 20));
    countryComboBox.addFocusListener(new OptionPropertiesSimpleWindow_countryComboBox_focusAdapter(this));
    countryComboBox.addItemListener(new OptionPropertiesSimpleWindow_countryComboBox_itemAdapter(this));

    nameLabel5.setBounds(new Rectangle(6, 6, 57, 20));
    nameLabel5.setFont(new java.awt.Font("System", 0, 9));
    nameLabel5.setText("Manager");
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setBounds(new Rectangle(6, 130, 305, 90));
    jPanel3.setLayout(null);
    nameLabel.setFont(new java.awt.Font("System", 0, 9));
    nameLabel.setText("Name");
    nameLabel.setBounds(new Rectangle(7, 7, 40, 14));
    groupDescriptionField.setBounds(new Rectangle(48, 26, 245, 14));
    groupDescriptionField.setText("");
    groupDescriptionField.setHorizontalAlignment(SwingConstants.LEFT);
    groupDescriptionField.setBorder(border1);
    groupDescriptionField.setEditable(false);
    groupDescriptionField.setBackground(UIManager.getColor(
        "ColorChooser.background"));
    jLabel6.setFont(new java.awt.Font("System", 0, 9));
    jLabel6.setText("Desc");
    jLabel6.setBounds(new Rectangle(7, 27, 40, 14));
    nameField.setBackground(UIManager.getColor("ColorChooser.background"));
    nameField.setBorder(border1);

    nameField.setEditable(false);
    nameField.setText("");
    nameField.setHorizontalAlignment(SwingConstants.LEFT);
    nameField.setBounds(new Rectangle(48, 7, 245, 14));
    codeField.setBounds(new Rectangle(48, 66, 151, 14));
    codeField.setText("");
    codeField.setHorizontalAlignment(SwingConstants.LEFT);
    codeField.setBorder(border1);
    codeField.setEditable(false);
    codeField.setBackground(UIManager.getColor("ColorChooser.background"));
    jLabel3.setFont(new java.awt.Font("System", 0, 9));
    jLabel3.setText("Code");
    jLabel3.setBounds(new Rectangle(7, 66, 40, 14));
    groupField.setBounds(new Rectangle(47, 46, 151, 14));
    groupField.setText("");
    groupField.setHorizontalAlignment(SwingConstants.LEFT);
    groupField.setBorder(border1);
    groupField.setEditable(false);
    groupField.setBackground(UIManager.getColor("ColorChooser.background"));
    jLabel5.setFont(new java.awt.Font("System", 0, 9));
    jLabel5.setRequestFocusEnabled(true);
    jLabel5.setText("Group");
    jLabel5.setBounds(new Rectangle(7, 46, 40, 14));
    tmNameField.setBackground(UIManager.getColor("ColorChooser.background"));
    tmNameField.setBorder(border1);
    tmNameField.setEditable(false);
    tmNameField.setText("");
    tmNameField.setHorizontalAlignment(SwingConstants.LEFT);
    tmNameField.setBounds(new Rectangle(53, 8, 239, 16));
    jLabel1.setFont(new java.awt.Font("System", 0, 9));
    jLabel1.setText("Disc%");
    jLabel1.setBounds(new Rectangle(214, 46, 42, 14));
    jLabel2.setFont(new java.awt.Font("System", 0, 9));
    jLabel2.setText("Max%");
    jLabel2.setBounds(new Rectangle(214, 66, 42, 14));
    btdField.setBackground(UIManager.getColor(
        "ColorChooser.background"));
    btdField.setEditable(false);
    btdField.setBorder(border1);
    btdField.setHorizontalAlignment(SwingConstants.LEFT);
    btdField.setText("");
    btdField.setBounds(new Rectangle(262, 46, 35, 14));
    autField.setBounds(new Rectangle(262, 66, 35, 14));
    autField.setText("");
    autField.setHorizontalAlignment(SwingConstants.LEFT);
    autField.setBorder(border1);
    autField.setEditable(false);
    autField.setBackground(UIManager.getColor(
        "ColorChooser.background"));
    jLabel17.setBounds(new Rectangle(3, 20, 43, 14));
    jLabel17.setText("Attribs");
    jLabel17.setFont(new java.awt.Font("System", 0, 9));
    jLabel14.setFont(new java.awt.Font("System", 0, 9));
    jLabel14.setText("Compat");
    jLabel14.setBounds(new Rectangle(3, 7, 43, 14));
    jScrollPane2.setBorder(BorderFactory.createEtchedBorder());
    jScrollPane2.setBounds(new Rectangle(51, 7, 247, 75));
    attributesArea.setBackground(UIManager.getColor("Panel.background"));
    attributesArea.setFont(new java.awt.Font("System", 0, 9));
    attributesArea.setEditable(false);
    attributesArea.setText("");
    jPanel2.add(jScrollPane4, null);
    jPanel2.add(jLabel17, null);
    jPanel2.add(jLabel14, null);
    jPanel2.add(jLabel111, null);
    jPanel2.add(jLabel110, null);
    jPanel2.add(jScrollPane1, null);
    jPanel2.add(jLabel13, null);
    jPanel2.add(jLabel16, null);
    jPanel2.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(attributesArea, null);
    jScrollPane1.getViewport().add(daughtersArea, null);
    panel1.add(jLabel24, null);
    panel1.add(multipleAllowedCheckBox, null);
    System.out.print("....");
    panel1.add(jLabel22, null);
    panel1.add(mandatoryCheckBox, null);
    panel1.add(jLabel21, null);
    panel1.add(groupRequiredCheckBox, null);
    jScrollPane4.getViewport().add(conditionerOptionsArea, null);
    panel1.add(jPanel3, null);
    jPanel3.add(nameField, null);
    jPanel3.add(nameLabel, null);
    jPanel3.add(codeField, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(btdField, null);
    jPanel3.add(autField, null);
    jPanel3.add(groupDescriptionField, null);
    jPanel3.add(jLabel6, null);
    jPanel3.add(groupField, null);
    jPanel3.add(jLabel5, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    panel1.add(jPanel1, null);
    panel1.add(jPanel4, null);
    this.getContentPane().add(panel1, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jLabel20, null);
    jPanel1.add(costLabel, null);
    jPanel1.add(marginLabel, null);
    jPanel1.add(jLabel23, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(marginP100Label, null);
    jPanel1.add(listPriceField, null);
    jPanel1.add(quantityField, null);
    jPanel1.add(jLabel19, null);
    jPanel1.add(currencyComboBox, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(discountField, null);
    jPanel1.add(discP100Field, null);
    jPanel1.add(marginP100Field, null);
    jPanel1.add(netPriceField, null);
    jPanel1.add(costField, null);
    jPanel1.add(marginField, null);
    jPanel4.add(nameLabel2, null);
    jPanel4.add(nameLabel3, null);
    jPanel4.add(nameLabel5, null);
    jPanel4.add(nameLabel4, null);
    jPanel4.add(countryComboBox, null);
    jPanel4.add(tmNameField, null);
    jPanel4.add(dealerComboBox, null);
    jPanel4.add(customerField, null);
    panel1.add(jPanel2, null);
  }

  public String getDisplayedDiscount() {
    return this.discP100Field.getText();
  }

  public String getDisplayedMaxDiscount() {
    return this.autField.getText();
  }

  // For the sales territory details section upon loading of this class
  public void changeSalesTerritoryDetails() {

    this.countryComboBox.removeAllItems();
    this.dealerComboBox.removeAllItems();
    String[] countryNames = localSession.getCountryNamesForUserName();
    if (countryNames != null) {
      for (int i = 0; i < countryNames.length; i++) {
        this.countryComboBox.addItem(countryNames[i]);
      }
    }
  }

  // Invoked when the country combo box is changed
  private void doLoadDealersForTmAndCountry() {
    this.tmNameField.setText(localSession.getUserName());
    this.dealerComboBox.removeAllItems();

    // Set the bsket country
    if (this.countryComboBox.getItemCount() > 0) {
      localSession.setBasketCountry(this.countryComboBox.getSelectedItem().toString());

      // Get the list of dealers in this country
      ArrayList myDealers =
          localSession.getDealersForTmAndCountry(this.tmNameField.getText(),
                                      this.countryComboBox.getSelectedItem().toString());
      if (myDealers != null) {
        java.util.Iterator ite = myDealers.iterator();
        while(ite.hasNext())
          this.dealerComboBox.addItem(ite.next().toString());
      }
    }

  }

  // Invoked when the dealer combo box is changed
  public void doSelectDealer() {

    if (this.dealerComboBox.getItemCount() > 0) {
      // Set basket country
      localSession.setBasketCountry(this.countryComboBox.getSelectedItem().toString());

      // Set basket dealer
      localSession.setBasketDealer(extractDealerCode(this.dealerComboBox.getSelectedItem().toString()));

      // Refresh TM name (why?)
      this.tmNameField.setText(localSession.getUserName());
    }

    // New dealer and country means financials are different, so update
    localSession.resetDiscounts();
    this.doUpdateFields();
  }

  private String extractDealerCode(String string) {
    return string.substring(0, string.indexOf(" - "));
  }

  // Used when loading from file a basket with a specific country, dealer and customer
  public void setSalesDetails() {
    this.countryComboBox.setSelectedItem(localSession.getBasketCountryName());
    this.dealerComboBox.setSelectedItem(localSession.getBasketDealerCode() + " - " + localSession.getBasketDealerName());
    this.customerField.setText(localSession.getBasketCustomerName());
    this.doUpdateFields();
  }

  public void displayProperties(String id) {
    this.localSession = this.nextremQuoteGUI.unitBuilder();
    this.id = id;
    this.nameField.setText(localSession.getOptionName(id));
    this.groupField.setText(localSession.getOptionGroup(id));
    this.groupDescriptionField.setText(localSession.getOptionGroupDesc(id));
    this.codeField.setText(localSession.getOptionCode(id));
    this.doUpdateFields();
  }

  private void doUpdateFields() {
    // Fields
    this.customerField.setText(localSession.getBasketCustomerName());
    this.listPriceField.setText(localSession.getOptionListPrice(id));
    this.marginField.setText(localSession.getOptionMargin(id));
    this.netPriceField.setText(localSession.getOptionNetPrice(id));
    this.marginP100Field.setText(localSession.getOptionMarginP100(id));
    this.costField.setText(localSession.getOptionCost(id));
    this.btdField.setText(localSession.getOptionBtd(id));
    this.autField.setText(localSession.getOptionAut(id));
    this.discP100Field.setText(localSession.getOptionDiscP100(id));
    this.discountField.setText(localSession.getOptionDiscP100(id));
    this.quantityField.setText(localSession.getOptionQuantity(id));

    // Format the discount field in red if above Max for a unit
    if (
      (localSession.getOptionType(id).equals("UNIT") || localSession.getOptionType(id).equals("OPTION"))
      && localSession.isOptionBtdAboveAut(id)) {
      this.discP100Field.setFont(new java.awt.Font("System", 1, 12));
      this.discP100Field.setForeground(Color.red);
    }
    else {
      this.discP100Field.setForeground(Color.black);
      this.discP100Field.setFont(new java.awt.Font("System", 0, 12));
    }


    // To display the selected option's attributes
    String[] attributes = localSession.getOptionAttributeNames(id);
    this.attributesArea.setText("");
    for (int i = 0; i < attributes.length; i++) this.attributesArea.append(attributes[i]);

    // To display the selected option's daughters
    String[] daughterNames = localSession.getOptionDaughtersNames(id);
    daughtersArea.setText("");
    for (int i = 0; i < daughterNames.length; i++) daughtersArea.append(daughterNames[i] + "\n");

    // To display the selected option's conditioners and cond values
    String[] conditioners = localSession.getOptionConditionerNames(id);
    conditionerOptionsArea.setText("");
    for (int i = 0; i < conditioners.length; i++) conditionerOptionsArea.append(conditioners[i]);

    // Check boxes
    this.groupRequiredCheckBox.setSelected(localSession.isOptionGroupRequired(id));
    this.multipleAllowedCheckBox.setSelected(localSession.isOptionMultipleAllowed(id));
    this.mandatoryCheckBox.setSelected(localSession.isOptionGroupMandatory(id));

    // Update totals in main GUI
    nextremQuoteGUI.doUpdateFields();

    // Update basket currency
    this.currencyComboBox.removeAllItems();
    this.currencyComboBox.addItem(localSession.getBasketCurrency());
  }

  public void resetWarnings() {
    this.discP100Field.setForeground(Color.black);
    discP100Field.setFont(new java.awt.Font("System", 0, 12));
  }

  public void toggleMargin() {
    this.marginField.setVisible(!this.marginField.isVisible());
    this.marginP100Field.setVisible(!this.marginP100Field.isVisible());
    this.marginLabel.setVisible(!this.marginLabel.isVisible());
    this.marginP100Label.setVisible(!this.marginP100Label.isVisible());
  }

  public void toggleCost() {
    this.costField.setVisible(!this.costField.isVisible());
    this.costLabel.setVisible(!this.costLabel.isVisible());
  }

  void discP100Field_focusGained(FocusEvent e) {
    discP100Field.setBackground(Color.white);
    discP100Field.setSelectionStart(0);
    discP100Field.setSelectionEnd(discP100Field.getText().length());
  }

  void discP100Field_focusLost(FocusEvent e) {
    discP100Field.setBackground(new Color(204, 204, 204));
    this.doUpdateFields();
  }

  void discP100Field_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 10) {
      this.localSession.setChangesMade(true);
      discP100Field.setText(localSession.setDiscountP100(id, this.discP100Field.getText()));
      this.nameLabel.requestFocus();
      this.doUpdateFields();
    }
  }

  void quantityField_focusGained(FocusEvent e) {
    quantityField.setBackground(Color.white);
    quantityField.setSelectionStart(0);
    quantityField.setSelectionEnd(quantityField.getText().length());
  }

  void quantityField_focusLost(FocusEvent e) {
    quantityField.setBackground(new Color(204, 204, 204));
    this.doUpdateFields();
  }

  void quantityField_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 10) {
      this.localSession.setChangesMade(true);
      quantityField.setText(localSession.setQuantity(this.id, this.quantityField.getText()));
      this.nameLabel.requestFocus();
      this.doUpdateFields();
    }
  }

  void quantityField_keyReleased(KeyEvent e) {
    //quantityField.setText(new StringManipulationToolkit().processQuantity(quantityField.getText()));
  }

  void customerField_focusLost(FocusEvent e) {
    this.customerField.setText(localSession.setBasketCustomerName(customerField.getText()));
  }

  void countryComboBox_itemStateChanged(ItemEvent e) {
    doLoadDealersForTmAndCountry();
  }

  void dealerComboBox_itemStateChanged(ItemEvent e) {
    this.doSelectDealer();
  }


  void customerField_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 10) {
      this.localSession.setChangesMade(true);
      this.customerField.setText(localSession.setBasketCustomerName(customerField.getText()));
    }
  }



  void countryComboBox_mouseClicked(MouseEvent e) {
    this.localSession.setChangesMade(true);
  }

  void dealerComboBox_mouseClicked(MouseEvent e) {
    this.localSession.setChangesMade(true);
  }



  void countryComboBox_focusGained(FocusEvent e) {
   this.localSession.setChangesMade(true);
  }

  void dealerComboBox_focusGained(FocusEvent e) {
   this.localSession.setChangesMade(true);
  }



}

class OptionPropertiesSimpleWindow_discP100Field_focusAdapter
    extends java.awt.event.FocusAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_discP100Field_focusAdapter(InformationConsoleGUI
      adaptee) {
    this.adaptee = adaptee;
  }

  public void focusGained(FocusEvent e) {
    adaptee.discP100Field_focusGained(e);
  }

  public void focusLost(FocusEvent e) {
    adaptee.discP100Field_focusLost(e);
  }
}

class OptionPropertiesSimpleWindow_discP100Field_keyAdapter
    extends java.awt.event.KeyAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_discP100Field_keyAdapter(InformationConsoleGUI
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.discP100Field_keyPressed(e);
  }
}

class OptionPropertiesSimpleWindow_quantityField_focusAdapter
    extends java.awt.event.FocusAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_quantityField_focusAdapter(InformationConsoleGUI
      adaptee) {
    this.adaptee = adaptee;
  }

  public void focusGained(FocusEvent e) {
    adaptee.quantityField_focusGained(e);
  }

  public void focusLost(FocusEvent e) {
    adaptee.quantityField_focusLost(e);
  }
}

class OptionPropertiesSimpleWindow_quantityField_keyAdapter
    extends java.awt.event.KeyAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_quantityField_keyAdapter(InformationConsoleGUI
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.quantityField_keyReleased(e);
  }

  public void keyPressed(KeyEvent e) {
    adaptee.quantityField_keyPressed(e);
  }

}

class OptionPropertiesSimpleWindow_customerField_focusAdapter
    extends java.awt.event.FocusAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_customerField_focusAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.customerField_focusLost(e);
  }
}

class OptionPropertiesSimpleWindow_countryComboBox_itemAdapter
    implements java.awt.event.ItemListener {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_countryComboBox_itemAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.countryComboBox_itemStateChanged(e);
  }
}

class OptionPropertiesSimpleWindow_dealerComboBox_itemAdapter
    implements java.awt.event.ItemListener {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_dealerComboBox_itemAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.dealerComboBox_itemStateChanged(e);
  }
}

class OptionPropertiesSimpleWindow_customerField_keyAdapter
    extends java.awt.event.KeyAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_customerField_keyAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.customerField_keyPressed(e);
  }
}

class OptionPropertiesSimpleWindow_countryComboBox_focusAdapter extends java.awt.event.FocusAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_countryComboBox_focusAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.countryComboBox_focusGained(e);
  }
}

class OptionPropertiesSimpleWindow_dealerComboBox_focusAdapter extends java.awt.event.FocusAdapter {
  InformationConsoleGUI adaptee;

  OptionPropertiesSimpleWindow_dealerComboBox_focusAdapter(InformationConsoleGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.dealerComboBox_focusGained(e);
  }
}
