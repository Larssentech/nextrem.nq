// (c) 2006-2007 NextremSoft

package nq.gui.standalone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.HashMap;

import nq.session.LocalSession;

import utilities.*;
import utilities.io.*;
import utilities.settings.*;
import utilities.string.*;

public class NextremQuoteStandaloneGUI extends JFrame {
  OptionNavigatorGUI optionNavigator;
  private String marginPassword = "";
  private String costPassword = "";
  private String basketName = "";

  private final String sep = System.getProperty("file.separator");
  private String version = "1.4.0.0";
  private InformationConsoleGUI oPW;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  protected LocalSession localSession;

  private OptionSelectionSimpleGUI myOptionSelectionSimpleGUI;
  JTextField listPriceField = new JTextField();
  JTextField costField = new JTextField();
  JMenuBar unitBuilderMenuBar = new JMenuBar();
  JMenu fileMenu = new JMenu();
  JMenu editMenu = new JMenu();
  JMenu viewMenu = new JMenu();
  JMenu helpMenu = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem aboutMenuItem = new JMenuItem();
  JMenu jMenu6 = new JMenu();
  JMenuItem newUnitMenuItem = new JMenuItem();
  JMenu jMenu7 = new JMenu();
  JMenuItem closeMenuItem = new JMenuItem();
  JMenuItem jMenuItem9 = new JMenuItem();
  java.awt.List optionList = new java.awt.List();
  JPanel jPanel3 = new JPanel();
  JButton deleteItemButton = new JButton();
  JLabel costLabel = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton substituteItemButton = new JButton();
  JMenu actionsMenu = new JMenu();
  JTextField marginP100Field = new JTextField();
  JLabel marginP100Label = new JLabel();
  JLabel statusBar = new JLabel();
  JLabel statusBar2 = new JLabel();
  JTextField discountP100Field = new JTextField();
  JLabel jLabel5 = new JLabel();
  JButton createUnitButton = new JButton();
  JButton addOptionButton = new JButton();
  JButton addPartButton = new JButton();
  JLabel marginLabel = new JLabel();
  JTextField marginField = new JTextField();
  JTextField netPriceField = new JTextField();
  JLabel jLabel7 = new JLabel();
  JPanel jPanel6 = new JPanel();
  //JTextField parentCodeField = new JTextField();

  JMenu jMenu3 = new JMenu();
  JMenuItem saveMenuItem = new JMenuItem();
  JMenuItem viewPropertiesWindowMenuItem = new JMenuItem();
  JMenuItem openMenuItem = new JMenuItem();
  JMenuItem showSplashMenuItem = new JMenuItem();
  JMenu addMenuItem = new JMenu();
  JMenuItem addOptionMenuItem = new JMenuItem();
  JMenuItem addPartMenuItem = new JMenuItem();
  JTextField quantityField = new JTextField();
  JTextField discountField = new JTextField();
  JLabel jLabel8 = new JLabel();
  TitledBorder titledBorder1;
  JLabel jLabel6 = new JLabel();
  Border border1;
  TitledBorder titledBorder2;
  Border border2;
  TitledBorder titledBorder3;
  JMenuItem viewEulaMenuItem = new JMenuItem();

  private String user;

  // Static variable to count instances of this GUI class
  private static int guiCount = 0;



  public NextremQuoteStandaloneGUI(){
  }


  public NextremQuoteStandaloneGUI(LocalSession localSession) {

    // Increase the GUI counter
    NextremQuoteStandaloneGUI.guiCount = NextremQuoteStandaloneGUI.guiCount + 1;

    // Create the splash screen
    NextremQuoteStandaloneGUI.displaySplashScreen(doSplashScreen(1000), 0);
    // Store the domain model
    this.localSession = localSession;

    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    // Location and size go before we fireUp... as this will determine
    // the location of other windows

    // Place GUI where user last saved it
    this.setGUILocation(this);

    // Give the GUI the right size
    this.setGUISize(this);

    this.fireUpUnitBuilder();
  }

  void jbInit() throws Exception {
    border3 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "Mark-up");
    border1 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(148, 145, 140));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)),
                                     "Quote Information");
    border2 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(148, 145, 140));
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
        white, new Color(148, 145, 140)), "");
    marginP100Label.setFont(new java.awt.Font("System", 0, 9));
    marginP100Label.setHorizontalAlignment(SwingConstants.LEADING);
    costLabel.setFont(new java.awt.Font("System", 0, 9));
    costLabel.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel7.setFont(new java.awt.Font("System", 0, 9));
    jLabel7.setHorizontalAlignment(SwingConstants.LEADING);
    marginLabel.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel5.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel8.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel2.setHorizontalAlignment(SwingConstants.LEADING);
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)), "Actions");
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setEnabled(true);
    this.setForeground(Color.gray);
    this.setLocale(java.util.Locale.getDefault());
    this.setJMenuBar(unitBuilderMenuBar);
    this.setResizable(false);
    this.setTitle("");
    this.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentMoved(ComponentEvent e) {
        this_componentMoved(e);
      }
    });
    this.addWindowListener(new NextremQuoteSimpleGUI_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setLayout(null);
    listPriceField.setBackground(Color.yellow);
    listPriceField.setAlignmentX( (float) 0.5);
    listPriceField.setEditable(false);
    listPriceField.setText("0");
    listPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
    listPriceField.setBounds(new Rectangle(35, 27, 77, 21));
    costField.setBounds(new Rectangle(260, 64, 77, 21));
    costField.setBackground(Color.yellow);
    costField.setEditable(false);
    costField.setText("0");
    costField.setHorizontalAlignment(SwingConstants.RIGHT);
    fileMenu.setText("File");
    editMenu.setText("Edit");
    viewMenu.setText("View");
    helpMenu.setText("Help");
    jMenuItem1.setEnabled(false);
    jMenuItem1.setText("Help");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem1_actionPerformed(e);
      }
    });
    aboutMenuItem.setText("About NextremQuote");
    aboutMenuItem.addActionListener(new
                                    NextremQuoteSimpleGUI_aboutMenuItem_actionAdapter(this));

    jMenu6.setText("New");
    newUnitMenuItem.setToolTipText("");
    newUnitMenuItem.setText("Blank Quote");
    newUnitMenuItem.addActionListener(new
                                      NextremQuoteSimpleGUI_newUnitMenuItem_actionAdapter(this));
    jMenu7.setText("Open");
    closeMenuItem.setText("Close");
    closeMenuItem.addActionListener(new
                                    NextremQuoteSimpleGUI_closeMenuItem_actionAdapter(this));
    jMenuItem9.setText("Exit");
    jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem9_actionPerformed(e);
      }
    });
    optionList.setBackground(Color.white);
    optionList.setCursor(null);
    optionList.setFont(new java.awt.Font("System", 0, 11));
    optionList.setBounds(new Rectangle(17, 155, 457, 308));
    optionList.addItemListener(new NextremQuoteSimpleGUI_optionList_itemAdapter(this));
    jPanel3.setBorder(titledBorder2);
    jPanel3.setBounds(new Rectangle(10, 6, 346, 134));
    jPanel3.setLayout(null);
    deleteItemButton.setBounds(new Rectangle(193, 20, 83, 26));
    deleteItemButton.setEnabled(true);
    deleteItemButton.setRequestFocusEnabled(false);
    deleteItemButton.setToolTipText("Deletes the selected OPTION");
    deleteItemButton.setMargin(new Insets(2, 2, 2, 2));
    deleteItemButton.setText("Delete");
    deleteItemButton.addActionListener(new
                                       NextremQuoteSimpleGUI_deleteItemButton_actionAdapter(this));
    costLabel.setBounds(new Rectangle(234, 64, 27, 21));
    //costLabel.setHorizontalAlignment(SwingConstants.TRAILING);
    costLabel.setText("Cost");
    jLabel2.setFont(new java.awt.Font("System", 0, 9));
    jLabel2.setRequestFocusEnabled(true);
    jLabel2.setHorizontalAlignment(SwingConstants.LEADING);
    //jLabel2.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel2.setText("Price");
    jLabel2.setBounds(new Rectangle(9, 27, 32, 21));
    substituteItemButton.setBounds(new Rectangle(374, 20, 83, 26));
    substituteItemButton.setEnabled(false);
    substituteItemButton.setRequestFocusEnabled(false);
    substituteItemButton.setToolTipText("Substitutes the selected PART with an alternative");
    substituteItemButton.setMargin(new Insets(2, 2, 2, 2));
    substituteItemButton.setText("Substitute");
    substituteItemButton.addActionListener(new
                                           NextremQuoteSimpleGUI_substituteItemButton_actionAdapter(this));
    actionsMenu.setText("Actions");
    marginP100Field.setHorizontalAlignment(SwingConstants.RIGHT);
    marginP100Field.setText("0.0");
    marginP100Field.setBackground(Color.yellow);
    marginP100Field.setEditable(false);
    marginP100Field.setBounds(new Rectangle(153, 101, 77, 21));
    //marginP100Label.setHorizontalAlignment(SwingConstants.TRAILING);
    marginP100Label.setText("Marg");
    marginP100Label.setBounds(new Rectangle(234, 101, 27, 21));
    statusBar.setFont(new java.awt.Font("System", 0, 12));
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar.setMaximumSize(new Dimension(34, 22));
    statusBar.setMinimumSize(new Dimension(34, 22));
    statusBar.setPreferredSize(new Dimension(34, 22));
    statusBar.setRequestFocusEnabled(true);
    statusBar.setText("NextremQuote");
    statusBar.setBounds(new Rectangle(0, 530, 319, 22));
    statusBar.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        statusBar_mouseClicked(e);
      }
    });
    statusBar2.setBounds(new Rectangle(322, 530, 168, 22));
    statusBar2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        statusBar2_mouseClicked(e);
      }
    });
    statusBar2.setRequestFocusEnabled(true);
    statusBar2.setHorizontalAlignment(SwingConstants.CENTER);
    statusBar2.setHorizontalTextPosition(SwingConstants.CENTER);
    statusBar2.setText("");
    statusBar2.setPreferredSize(new Dimension(34, 22));
    statusBar2.setMinimumSize(new Dimension(34, 22));
    statusBar2.setMaximumSize(new Dimension(34, 22));
    statusBar2.setFont(new java.awt.Font("System", 0, 12));
    statusBar2.setBorder(BorderFactory.createLoweredBevelBorder());
    discountP100Field.setBounds(new Rectangle(153, 64, 77, 21));
    discountP100Field.setBackground(Color.yellow);
    discountP100Field.setEditable(false);
    discountP100Field.setText("0.0");
    discountP100Field.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel5.setBounds(new Rectangle(119, 64, 39, 21));
    jLabel5.setFont(new java.awt.Font("System", 0, 9));
    jLabel5.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel5.setText("Disc%");
    createUnitButton.setBounds(new Rectangle(11, 20, 83, 26));
    createUnitButton.setRequestFocusEnabled(false);
    createUnitButton.setToolTipText("Adds a UNIT to the quote");
    //createUnitButton.setVerifyInputWhenFocusTarget(true);
    createUnitButton.setMargin(new Insets(2, 2, 2, 2));
    createUnitButton.setText("Add Unit");
    createUnitButton.addActionListener(new
                                       NextremQuoteSimpleGUI_createUnitButton_actionAdapter(this));

    addOptionButton.setBounds(new Rectangle(102, 20, 83, 26));
    addOptionButton.setEnabled(true);
    addOptionButton.setRequestFocusEnabled(false);
    addOptionButton.setToolTipText("Adds an OPTION to a unit");
    addOptionButton.setMargin(new Insets(2, 2, 2, 2));
    addOptionButton.setText("Add Option");
    addOptionButton.addActionListener(new
                                      NextremQuoteSimpleGUI_addOptionButton_actionAdapter(this));
    addPartButton.setBounds(new Rectangle(283, 20, 83, 26));
    addPartButton.setEnabled(false);
    addPartButton.setRequestFocusEnabled(false);
    addPartButton.setToolTipText("Adds a PART to an option");
    addPartButton.setMargin(new Insets(2, 2, 2, 2));
    addPartButton.setText("Add Part");
    addPartButton.addActionListener(new
                                    NextremQuoteSimpleGUI_addPartButton_actionAdapter(this));
    marginLabel.setBounds(new Rectangle(119, 101, 39, 21));
    marginLabel.setFont(new java.awt.Font("System", 0, 9));
    marginLabel.setHorizontalAlignment(SwingConstants.LEADING);
    //marginLabel.setHorizontalTextPosition(SwingConstants.TRAILING);
    marginLabel.setText("Marg%");
    marginField.setBounds(new Rectangle(260, 101, 77, 21));
    marginField.setBackground(Color.yellow);
    marginField.setEditable(false);
    marginField.setText("0");
    marginField.setHorizontalAlignment(SwingConstants.RIGHT);
    netPriceField.setBounds(new Rectangle(260, 27, 77, 21));
    netPriceField.setBackground(Color.yellow);
    netPriceField.setEditable(false);
    netPriceField.setText("0");
    netPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel7.setText("Net");
    //jLabel7.setHorizontalAlignment(SwingConstants.TRAILING);
    //jLabel7.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel7.setBounds(new Rectangle(234, 27, 27, 21));
    jPanel6.setBorder(titledBorder1);
    jPanel6.setLayout(null);
    jPanel6.setBounds(new Rectangle(11, 467, 469, 57));
    jPanel6.setDebugGraphicsOptions(0);
    jMenu3.setText("Save");
    saveMenuItem.setText("Static Quote");
    saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveMenuItem_actionPerformed(e);
      }
    });
    viewPropertiesWindowMenuItem.setText("Information Console");
    viewPropertiesWindowMenuItem.addActionListener(new java.awt.event.
        ActionListener() {
      public void actionPerformed(ActionEvent e) {
        viewPropertiesWindowMenuItem_actionPerformed(e);
      }
    });
    openMenuItem.setText("Static Quote");
    openMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        openMenuItem_actionPerformed(e);
      }
    });
    showSplashMenuItem.setText("Show Splash Screen Logo");
    showSplashMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showSplashMenuItem_actionPerformed(e);
      }
    });
    addPartMenuItem.setEnabled(false);
    addPartMenuItem.setText("Add Part");
    addPartMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addPartMenuItem_actionPerformed(e);
      }
    });
    quantityField.setBackground(Color.yellow);
    quantityField.setEditable(false);
    quantityField.setText("1");
    quantityField.setHorizontalAlignment(SwingConstants.RIGHT);
    //quantityField.setHorizontalAlignment(SwingConstants.TRAILING);
    quantityField.setBounds(new Rectangle(35, 101, 77, 21));
    discountField.setHorizontalAlignment(SwingConstants.RIGHT);
    discountField.setText("0");
    discountField.setEditable(false);
    discountField.setBackground(Color.yellow);
    discountField.setBounds(new Rectangle(153, 27, 77, 21));
    jLabel8.setFont(new java.awt.Font("System", 0, 9));
    jLabel8.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel8.setText("Disc");
    jLabel8.setBounds(new Rectangle(119, 27, 39, 21));
    jLabel6.setFont(new java.awt.Font("System", 0, 9));
    jLabel6.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel6.setText("Units");
    jLabel6.setBounds(new Rectangle(9, 101, 40, 21));

    viewEulaMenuItem.setText("View End User License Agreement (EULA)");
    viewEulaMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        viewEulaMenuItem_actionPerformed(e);
      }
    });
    //makeDependetMenuItem.setVerifyInputWhenFocusTarget(true);
    jMenu9.setText("Basket Font Size");
    jMenuItem10.setText("Largest: 13pt");
    jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem10_actionPerformed(e);
      }
    });
    jMenuItem13.setText("Larger: 12pt");
    jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem13_actionPerformed(e);
      }
    });
    jMenuItem14.setText("Medium: 11pt");
    jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem14_actionPerformed(e);
      }
    });
    jMenuItem15.setText("Smaller: 10pt");
    jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem15_actionPerformed(e);
      }
    });
    currencyComboBox.setBounds(new Rectangle(238, 7, 58, 20));
    currencyComboBox.setBounds(new Rectangle(35, 64, 77, 21));
    jLabel1.setFont(new java.awt.Font("System", 0, 9));
    jLabel1.setText("Curr");
    jLabel1.setBounds(new Rectangle(9, 64, 39, 21));
    jMenu12.setText("Sync");
    syncMenuItem.setEnabled(true);
    syncMenuItem.setText("Sync Saved Quotes With Database");
    syncMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        syncMenuItem_actionPerformed(e);
      }
    });
    jMenu13.setText("Hide/Show");
    toggleMarginMenuItem.setText("Unlock Margins");
    toggleMarginMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        toggleMarginMenuItem_actionPerformed(e);
      }
    });
    jMenuItem7.setText("Unlock Costs");
    jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem7_actionPerformed(e);
      }
    });
    buildUnitWithDefaultsMenuItem.setText("Add Unit");
    buildUnitWithDefaultsMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buildUnitWithDefaultsMenuItem_actionPerformed(e);
      }
    });
    togglePartsInfoMenuItem.setText("Parts Information");
    togglePartsInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        togglePartsInfoMenuItem_actionPerformed(e);
      }
    });
    markupP100Label.setText("%");
    markupP100Label.setHorizontalAlignment(SwingConstants.LEADING);
    markupP100Label.setBounds(new Rectangle(9, 27, 42, 21));
    markupP100Label.setFont(new java.awt.Font("System", 0, 9));
    markupP100Label.setHorizontalAlignment(SwingConstants.LEADING);
    streetPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
    streetPriceField.setText("0");
    streetPriceField.setBackground(Color.orange);
    streetPriceField.setEditable(false);
    streetPriceField.setBounds(new Rectangle(41, 101, 79, 21));
    markupP100Field.setBounds(new Rectangle(41, 27, 79, 21));
    markupP100Field.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        markupP100Field_keyPressed(e);
      }
    });
    markupP100Field.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(FocusEvent e) {
        markupP100Field_focusGained(e);
      }

      public void focusLost(FocusEvent e) {
        markupP100Field_focusLost(e);
      }
    });
    markupP100Field.setBackground(new Color(212, 208, 200));
    markupP100Field.setEditable(true);
    markupP100Field.setText("0.0");
    markupP100Field.setHorizontalAlignment(SwingConstants.RIGHT);
    streetPriceLabel.setBounds(new Rectangle(9, 101, 50, 21));
    streetPriceLabel.setText("Street");
    streetPriceLabel.setFont(new java.awt.Font("System", 0, 9));
    streetPriceLabel.setHorizontalAlignment(SwingConstants.LEADING);
    markupLabel.setText("Value");
    markupLabel.setBounds(new Rectangle(9, 64, 48, 21));
    markupLabel.setFont(new java.awt.Font("System", 0, 9));
    markupLabel.setHorizontalAlignment(SwingConstants.LEADING);
    markupPanel.setBorder(titledBorder4);
    markupPanel.setBounds(new Rectangle(357, 6, 128, 134));
    markupPanel.setLayout(null);
    markupField.setHorizontalAlignment(SwingConstants.RIGHT);
    markupField.setText("0");
    markupField.setBackground(Color.yellow);
    markupField.setEditable(false);
    markupField.setBounds(new Rectangle(41, 64, 79, 21));
    jMenu4.setForeground(Color.gray);
    jMenu4.setText("Debug");
    jMenu4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenu4_actionPerformed(e);
      }
    });
    jMenuItem19.setForeground(Color.gray);
    jMenuItem19.setText("Change User Name");

    jMenuItem8.setForeground(Color.gray);
    jMenuItem8.setText("Print Sync Journal");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem8_actionPerformed(e);
      }
    });
    jMenuItem23.setText("Connect to Database");
    jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem23_actionPerformed(e);
      }
    });
    jMenu8.setText("Delete");
    jMenuItem20.setText("Delete Selection");
    jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem20_actionPerformed(e);
      }
    });

    addMenuItem.setText("Add");
    jMenuItem25.setEnabled(false);
    jMenuItem25.setText("Delete Part");
    jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem25_actionPerformed(e);
      }
    });
    jMenuItem26.setEnabled(false);
    jMenuItem26.setText("Substitute Part");
    jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem26_actionPerformed(e);
      }
    });
    jMenuItem22.setForeground(Color.gray);
    jMenuItem22.setText("Print Required Groups");
    jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem22_actionPerformed(e);
      }
    });
    jMenuItem24.setForeground(Color.gray);
    jMenuItem24.setText("Print Selected Option Sales Details");

    jMenuItem18.setEnabled(false);
    jMenuItem18.setText("Preferences");

    jMenuItem2.setForeground(Color.gray);
    jMenuItem2.setText("Launch Crypto");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem2_actionPerformed(e);
      }
    });
    addOptionMenuItem.setText("Add Option");
    jMenu2.setText("Tools");
    jMenu5.setText("Export");
    jMenuItem4.setText("Export to Excel");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem4_actionPerformed(e);
      }
    });
    jMenu10.setText("Import");
    jMenuItem3.setText("Import from CSV");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem3_actionPerformed(e);
      }
    });
    jMenuItem5.setText("Dynamic Quote");
    jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem5_actionPerformed(e);
      }
    });

    jMenu11.setText("Save As");
    jMenuItem6.setText("Static Quote");
    jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem6_actionPerformed(e);
      }
    });
    jMenuItem11.setText("Dynamic Quote");
    jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem11_actionPerformed(e);
      }
    });
    jMenuItem12.setText("Quote Window");
    jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem12_actionPerformed(e);
      }
    });

    jMenuItem16.setText("Print Client Sessions");
    jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem16_actionPerformed(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    unitBuilderMenuBar.add(fileMenu);
    unitBuilderMenuBar.add(editMenu);
    unitBuilderMenuBar.add(viewMenu);
    unitBuilderMenuBar.add(jMenu12);
    unitBuilderMenuBar.add(actionsMenu);
    unitBuilderMenuBar.add(jMenu2);
    unitBuilderMenuBar.add(helpMenu);
    unitBuilderMenuBar.add(jMenu4);

    helpMenu.add(jMenuItem1);
    helpMenu.addSeparator();
    helpMenu.add(viewEulaMenuItem);
    helpMenu.add(showSplashMenuItem);
    helpMenu.add(aboutMenuItem);
    editMenu.add(jMenuItem18);
    fileMenu.add(jMenu6);
    fileMenu.add(jMenu7);
    fileMenu.add(jMenu3);
    fileMenu.add(jMenu11);
    fileMenu.addSeparator();
    fileMenu.add(closeMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(jMenuItem9);
    jMenu6.add(newUnitMenuItem);
    jMenu6.add(jMenuItem12);
    jMenu7.add(openMenuItem);
    jMenu7.add(jMenuItem11);
    jPanel6.add(createUnitButton, null);
    jPanel6.add(addOptionButton, null);
    jPanel6.add(deleteItemButton, null);
    jPanel6.add(addPartButton, null);
    jPanel6.add(substituteItemButton, null);
    jPanel1.add(markupPanel, null);
    jPanel1.add(statusBar, null);
    jPanel1.add(statusBar2, null);
    markupPanel.add(markupLabel, null);
    markupPanel.add(streetPriceLabel, null);
    markupPanel.add(markupP100Label, null);
    markupPanel.add(markupP100Field, null);
    markupPanel.add(markupField, null);
    markupPanel.add(streetPriceField, null);
    jPanel1.add(jPanel3, null);
    jPanel1.add(optionList, null);
    viewMenu.add(viewPropertiesWindowMenuItem);
    viewMenu.add(jMenu13);
    viewMenu.addSeparator();
    viewMenu.add(jMenu9);
    actionsMenu.add(addMenuItem);
    actionsMenu.add(jMenu8);
    jMenu3.add(saveMenuItem);
    jMenu3.add(jMenuItem5);
    addMenuItem.add(buildUnitWithDefaultsMenuItem);
    addMenuItem.add(addOptionMenuItem);
    addMenuItem.add(addPartMenuItem);
    jPanel3.add(jLabel6, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jLabel8, null);
    jPanel3.add(jLabel5, null);
    jPanel3.add(marginLabel, null);
    jPanel3.add(marginP100Label, null);
    jPanel3.add(jLabel7, null);
    jPanel3.add(costLabel, null);
    jPanel3.add(listPriceField, null);
    jPanel3.add(currencyComboBox, null);
    jPanel3.add(quantityField, null);
    jPanel3.add(discountField, null);
    jPanel3.add(discountP100Field, null);
    jPanel3.add(marginP100Field, null);
    jPanel3.add(netPriceField, null);
    jPanel3.add(costField, null);
    jPanel3.add(marginField, null);
    jPanel1.add(jPanel6, null);

    // To close the window
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        doExit();
      }
    });
    createUnitButton.setMnemonic('0');
    jMenu9.add(jMenuItem10);
    jMenu9.add(jMenuItem13);
    jMenu9.add(jMenuItem14);
    jMenu9.add(jMenuItem15);
    jMenu12.add(syncMenuItem);
    jMenu12.addSeparator();
    jMenu12.add(jMenuItem23);
    jMenu13.add(togglePartsInfoMenuItem);
    jMenu13.add(toggleMarginMenuItem);
    jMenu13.add(jMenuItem7);
    jMenu4.add(jMenuItem19);
    jMenu4.add(jMenuItem8);
    jMenu4.add(jMenuItem22);
    jMenu4.add(jMenuItem24);
    jMenu4.add(jMenuItem2);
    jMenu4.add(jMenuItem16);
    jMenu8.add(jMenuItem20);
    jMenu8.add(jMenuItem25);
    jMenu8.add(jMenuItem26);
    jMenu2.add(jMenu10);
    jMenu2.add(jMenu5);
    jMenu5.add(jMenuItem4);
    jMenu10.add(jMenuItem3);
    jMenu11.add(jMenuItem6);
  }

  // END OF JBUILDER CODE - END OF JBUILDER CODE - END OF JBUILDER CODE - END OF JBUILDER CODE -
  // END OF JBUILDER CODE - END OF JBUILDER CODE - END OF JBUILDER CODE - END OF JBUILDER CODE -


  private void doExportToCSV() {
    if(localSession.saveQuoteToCSV())
      JOptionPane.showMessageDialog(null, "Quote dynamically saved to your Desktop folder", "NextremQuote", 1);
    else
      JOptionPane.showMessageDialog(null, "Quote could not be saved to your Desktop folder", "Error", 0);
  }

  private void doImportFromCSV() {
    String customerName = localSession.getBasketCustomerName();
    if (customerName.length() == 0) {
      customerName = JOptionPane.showInputDialog(
          "The quote still has no customer in the customer box.\nThis is required. Would you like to type one now?");
      if (customerName == null) {
        customerName = "";
      }
      else if (customerName.length() == 0) {
        customerName = "New Customer";
      }
    }
    if (customerName.length() > 0) {
      localSession.setBasketCustomerName(new StringManipulationToolkit().getAlphaNumericString(customerName));
      this.selectFirstEntry();
      System.out.println(this.localSession.importPath());
      JFileChooser j = new JFileChooser(localSession.importPath());
      ExampleFileFilter filter = new ExampleFileFilter();
      filter.addExtension("csv");
      j.setFileFilter(filter);
      int i = j.showOpenDialog(this);
      if (i == 0) {
        String importFilename = j.getSelectedFile().getAbsolutePath();

        java.util.ArrayList rejected = localSession.importFromCSV(importFilename);

        // Refresh the screen so the user sees the changes
        //t.getCurrentUnitId() = t.currentUnitId();
        doUpdateScreen();
        this.selectEntry(this.optionList.getItemCount() - 1);

        // Tell the user about any potential problem
        String message = "Import finished.\n";
        if (rejected.size() > 0) {
          java.util.Iterator ite = rejected.iterator();
          String rejectedString = "";
          while (ite.hasNext()) {
            rejectedString += ite.next() + "\n";
          }
          message += "There were problems with one or more option codes:\n";
          message += rejectedString;
          message += "You can check these codes going to the\nOption Navigator (click the 'Add Option' button).";
        }
        else {
          message += "All option codes were accepted.";

          // And tell the user
        }
        JOptionPane.showMessageDialog(null, message);
      }
    }
    else {
      this.selectEntry(0);
    }
  }

  private void doFindMissingGroups() {
    String missingString = localSession.doFindMissingGroups();
    if(localSession.getOptionType(localSession.getSelectedItemId()).equals("UNIT")){
      if (missingString.length() > 0) {

        JOptionPane.showMessageDialog(null,
                                      "Missing Option(s) from group(s):\n" +
                                      missingString);
      }
    }
    else JOptionPane.showMessageDialog(null,
                                       "Select a unit and click here again.",
                                       "Select a unit",
                                       0);
  }

  private void doOpenOptionNavigator(String unitSeriesCode) {
    if (optionNavigator == null) {
      optionNavigator = new OptionNavigatorGUI();
    }
    optionNavigator.setTitle("NextremQuote - Option Navigator - Option Selection");
    optionNavigator.setSize(750, 600);
    optionNavigator.setLocation(this.oPW.getLocation().x + 30, this.oPW.getLocation().y + 30);
    optionNavigator.clientSession(localSession);
    optionNavigator.nextremQuoteSimpleGUI(this);
    optionNavigator.parentId(localSession.getCurrentUnitId());
    optionNavigator.
        setSeriesFilter(localSession.getOptionNameByCode(unitSeriesCode) +
                        " (" +
                        unitSeriesCode +
                        ")");
    optionNavigator.show();

  }

  private void doSync() {
    localSession.sync();
    JOptionPane.showMessageDialog(null, "All saved quotes have been submitted to the database");
  }

  /**
   * Method code 72
   */
  private void doHideShowPartsInformation() {
    localSession.hideParts(!localSession.hideParts());
    this.doUpdateScreen();
    this.selectFirstEntry();
  }

  /**
   * Method code 71
   */
  private void doRegisterDB() {
    localSession.registerDB();
  }

  /**
   * Method code 62
   */
  private void doExportToExcel() {
    if (this.optionList.getItemCount() > 1) {
      String message = "";
      int i = 0;
      if (localSession.exportBasketToCSV()) {
        message = "Succesfully exported to Excel (Desktop).";
        i = 1;
      }
      else {
        message = "Basket was NOT exported to Excel. (Code 010162a)";
        i = 0;
      }
      JOptionPane.showMessageDialog(null, message, "NextremQuote", i);
    }
    else {
      JOptionPane.showMessageDialog(null, "Nothing to export! (Code: 010162b)", "NextremQuote", 2);
    }
  }

  private void doShowPropertiesWindow() {
    oPW.show();
    oPW.displayProperties(localSession.getSelectedItemId());
  }

  /**
   * Method code 19
   * Handles item substitution. Will only work...
   */
  public void doSubstituteItem() {
    this.doAddPartToOption();
    int selectedIndex = optionList.getSelectedIndex();
    //
    String parentId = this.localSession.getOptionParentId(localSession.getSelectedItemId());
    if (this.localSession.getOptionType(localSession.getSelectedItemId()).equals("PART")) {
      String selectedPartCode =
          doOpenOptionSelector("NextremQuote - Select Part To Use As Substitute...",
                               localSession.getParentCode(),
                               false,
                               0);
      if (selectedPartCode.length() > 0) {
        localSession.removeOptionFromOption(localSession.getSelectedItemId());
        localSession.addPartToOption(selectedPartCode, parentId, true);
      }
      doUpdateScreen();
      this.selectEntry(selectedIndex);
    }
    else {
      JOptionPane.showMessageDialog(null, "Please select a part to substitute. (Code: 010119)", "NextremQuote", 0);
    }
  }

  /**
   * Method code 23
   * Toggles sensitive financial information
   */
  private void doHideShowMargin() {
    boolean auth = false;
    String currPwd = "nmhgmi1";
    if (!this.marginPassword.equals(currPwd)) {
      JPasswordField pwd = new JPasswordField();
      Object[] message = {
          "Please type in your password\n", pwd};
      int resp = JOptionPane.showConfirmDialog(null, message, "NextremQuote", JOptionPane.OK_CANCEL_OPTION, 1);
      if (resp == 0) {
        this.marginPassword = new String(pwd.getPassword());
        if (this.marginPassword.equals(currPwd)) {
          auth = true;
        }
      }
      else {
        this.marginPassword = "";
      }
    }
    else {
      auth = true;
    }
    if (auth) {
      marginField.setVisible(!marginField.isVisible());
      marginP100Field.setVisible(!marginP100Field.isVisible());
      marginLabel.setVisible(!marginLabel.isVisible());
      marginP100Label.setVisible(!marginP100Label.isVisible());
      this.oPW.toggleMargin();
    }
    else {
      JOptionPane.showMessageDialog(null, "Wrong password", "NextremQuote", 0);
    }
  }

  private void unlockHiddenFields(String userName) {
    if (userName.equals("efnceras") ||
        userName.equals("jcerasuolo") ||
        userName.equals("efnmanzo")) {

      costField.setVisible(!costField.isVisible());
      costLabel.setVisible(!costLabel.isVisible());

      // For the cost
      this.oPW.toggleCost();
      this.addPartButton.setEnabled(true);
      this.substituteItemButton.setEnabled(true);
      this.addPartMenuItem.setEnabled(true);
      this.jMenuItem25.setEnabled(true);
      this.jMenuItem26.setEnabled(true);

      // For the margins
      marginField.setVisible(!marginField.isVisible());
      marginP100Field.setVisible(!marginP100Field.isVisible());
      marginLabel.setVisible(!marginLabel.isVisible());
      marginP100Label.setVisible(!marginP100Label.isVisible());
      this.oPW.toggleMargin();
    }
  }

  private void doHideShowCost() {
    boolean auth = false;
    String currPwd = "nmhgmiz";
    if (!this.costPassword.equals(currPwd)) {
      JPasswordField pwd = new JPasswordField();
      Object[] message = {
          "Please type in your password\n", pwd};
      int resp = JOptionPane.showConfirmDialog(null, message, "NextremQuote", JOptionPane.OK_CANCEL_OPTION, 1);
      if (resp == 0) {
        this.costPassword = new String(pwd.getPassword());
        if (this.costPassword.equals(currPwd)) {
          auth = true;
        }
      }
      else {
        this.costPassword = "";
      }
    }
    else {
      auth = true;
    }
    if (auth) {
      costField.setVisible(!costField.isVisible());
      costLabel.setVisible(!costLabel.isVisible());
      this.oPW.toggleCost();
      this.addPartButton.setEnabled(true);
      this.substituteItemButton.setEnabled(true);
      this.addPartMenuItem.setEnabled(true);
      this.jMenuItem25.setEnabled(true);
      this.jMenuItem26.setEnabled(true);
    }
    else {
      JOptionPane.showMessageDialog(null, "Wrong password", "NextremQuote", 0);
    }
  }

  /**
   * Method code 24
   * This is what happens when an item in the list of objects in the basket is selected
   */
  private String doListItemSelection() {
    if (optionList.getItemCount() > 0) {
      String leftTrimmedOptionName = new StringManipulationToolkit().
          replaceAll(optionList.getSelectedItem(),
                     "[+] ",
                     "");
      leftTrimmedOptionName = new StringManipulationToolkit().replaceAll(leftTrimmedOptionName, "�_", "");
      leftTrimmedOptionName = new StringManipulationToolkit().replaceAll(leftTrimmedOptionName, "X", "");
      while (leftTrimmedOptionName.indexOf(" ") == 0)
        leftTrimmedOptionName = leftTrimmedOptionName.substring
            (1,
             leftTrimmedOptionName.length());

      localSession.selectOption
          (leftTrimmedOptionName.substring(leftTrimmedOptionName.indexOf("id=") + 3,
                                           leftTrimmedOptionName.length()));

      oPW.displayProperties(localSession.getSelectedItemId());
      statusBar.setText(leftTrimmedOptionName);

      return localSession.getSelectedItemId();
    }
    else {
      return "";
    }
  }

  /**
   * Method code 25
   * Empties the basket
   */
  private void doCreateNewUnit() {
    if (this.optionList.getItemCount() > 1) {
      int selectedIndex = optionList.getSelectedIndex();
      if (JOptionPane.showConfirmDialog(null, "Discard all changes and start again?", "NextremQuote", 0) == 0) {
        this.resetAll();
      }
      else {
        this.selectEntry(selectedIndex);
      }
    }
  }

  /**
   * Method code 41
   * Empties the basket saving the contents
   */
  private void doCloseBasket() {
    if (JOptionPane.showConfirmDialog(null, "Do you want to save your changes?", "NextremQuote", 0) == 0) {
      this.doSaveBasketToLibrary();
    }
    this.resetAll();
  }

  private void resetAll() {
    localSession.clearOptionBasket();
    localSession.renameBasket("NEW QUOTE");
    this.doUpdateScreen();
    this.selectFirstEntry();
    this.oPW.displayProperties(localSession.getSelectedItemId());
    this.oPW.setSalesDetails();
    this.statusBar2.setFont(new java.awt.Font("System", 0, 12));
    this.statusBar.setFont(new java.awt.Font("System", 0, 12));
    this.discountP100Field.setFont(new java.awt.Font("System", 0, 12));
    this.statusBar2.setForeground(Color.black);
    this.statusBar2.setText("");
    this.oPW.resetWarnings();
  }

  /**
   * Method code 40
   * Handles opening a unit from file
   */
  private void doOpenBasket() {
    int selectedIndex = optionList.getSelectedIndex();
    JFileChooser j = new JFileChooser(localSession.libraryPath());
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("quote");
    j.setFileFilter(filter);
    int i = j.showOpenDialog(this);

    // If the user actually opened something
    if (i == 0) {
      String thisBasketsFileName = j.getSelectedFile().getAbsolutePath();
      String basketFileName = j.getSelectedFile().getName();
      this.basketName = basketFileName;

      // If what he opened is a *.quote file
      if (basketFileName.endsWith(".quote"))
        basketName = basketFileName.substring(0,
                                              basketFileName.lastIndexOf(".quote"));

      localSession.openQuoteFromFile(thisBasketsFileName);

      this.oPW.setSalesDetails();

      localSession.renameBasket(basketName);
      this.doUpdateScreen();
      if (this.optionList.getItemCount() > 1) {
        this.selectEntry(1);
      }
    }
    else {
      this.selectEntry(selectedIndex);
    }
  }

  /**
   * basketName: myQuote
   * thisBasketsFileName: C:\quotes\<country>\<dealer>\yyyy-mmm\<customer>.quote
   * libraryPath: C:\quotes
   * Method code 73
   */
  private void doSaveBasketToLibrary() {
    int selectedIndex = optionList.getSelectedIndex();
    if (this.localSession.getBasketItems().length > 0) {
      if (!localSession.libraryPathExists()) {
        // Get *.quote file name from user if customer name not set
        this.basketName = this.localSession.getBasketCustomerName();
        if (this.basketName.length() == 0) {
          this.basketName = JOptionPane.showInputDialog(null, "Please give this quote a name.");
        }
        if (this.basketName != null) {
          // Remove non letters or digits
          this.basketName = new StringManipulationToolkit().getAlphaNumericString(this.basketName);
          String thisBasketsFileName = localSession.saveBasketToFile(basketName);
          String savedOK = localSession.libraryPathExists() ? " OK" : " Error";
          localSession.renameBasket(basketName);
          this.doUpdateScreen();
          JOptionPane.showMessageDialog(null, "Quote saved to: " + thisBasketsFileName + savedOK, "NextremQuote", 1);
        }
      }
      else {
        localSession.saveBasketToFile(basketName);
      }
    }
    else {
      JOptionPane.showMessageDialog(null, "Nothing to save! (Code: 010173)", "NextremQuote", 2);
    }
    this.selectEntry(selectedIndex);
  }

  /**
   * basketName: myQuote
   * thisBasketsFileName: C:\quotes\2006\Oct\Monday\10AM\myQuote.quote
   * libraryPath: C:\quotes
   * Method code 74
   */
  private void doSaveBasketAs() {
    int selectedIndex = optionList.getSelectedIndex();
    if (this.localSession.getBasketItems().length > 0) {
      String tempName = new StringManipulationToolkit().getAlphaNumericString
          (JOptionPane.showInputDialog(null, "Please give this quote a name.", "NextremQuote", 1));
      if (tempName != null && tempName.length() > 0) {
        String thisBasketsFileName = localSession.saveBasketToFileAs(tempName);
        boolean savedOK = localSession.libraryPathExists();
        if (savedOK) {
          localSession.renameBasket(tempName);
          this.basketName = tempName;
          this.doUpdateScreen();
          JOptionPane.showMessageDialog(null, "Quote saved to: " + thisBasketsFileName, "NextremQuote", 1);
        }
        else {
          JOptionPane.showMessageDialog(null, "The file name given is not valid: " + tempName, "NextremQuote", 1);
        }
      }
      else {
        JOptionPane.showMessageDialog(null, "The file name given is not valid: " + tempName, "NextremQuote", 1);

      }
    }
    else {
      JOptionPane.showMessageDialog(null, "Nothing to save! (Code: 010174)", "NextremQuote", 2);
    }
    this.selectEntry(selectedIndex);
  }

  /**
   * Method code 17
   * Offers the user the series selector to add a new unit to an empty basket and then calls the domain model
   * method to add all options that are detected as required for the unit series. Returns void.
   */
  private void doAddUnitWithDefaults() {
    String customerName = this.localSession.getBasketCustomerName();
    if (customerName.length() == 0) {
      customerName = JOptionPane.showInputDialog(
          "The quote still has no customer in the customer box.\nThis is required. Would you like to type one now?");
      if (customerName == null) {
        customerName = "";
      }
      else if (customerName.length() == 0) {
        customerName = "New Customer";
      }
    }
    if (customerName.length() > 0) {
      this.localSession.setBasketCustomerName
          (new StringManipulationToolkit().getAlphaNumericString(customerName));
      this.selectFirstEntry();
      String selectedSeries = doOpenOptionSelector("NextremQuote - Select Unit Series...", "OPTCTLG", false, 0);
      if (selectedSeries.length() > 0) {
        localSession.generateOptionCatalogForCode(selectedSeries);
        localSession.addUnitToBasket(selectedSeries, true);
        doUpdateScreen();
        this.selectEntry(this.optionList.getItemCount() - 1);
        // Open the add option dialog
        this.doAddOptionToUnit2();
      }
      else {
        this.selectEntry(0);
      }
    }
  }

  /**
   * Method 15
   * Method to add an option to a unit
   */

  private void doAddOptionToUnit2() {
    if (!this.localSession.getOptionType(localSession.getSelectedItemId()).equals("UNIT")) {
      JOptionPane.showMessageDialog(null, "Please select a unit from the basket first. (Code: 010115)", "NextremQuote",
                                    0);
    }
    else {
      this.doOpenOptionNavigator(localSession.getSelectedItemCode());
    }
  }



  public HashMap addOptionSilently(String optionCode, boolean enforceAI, boolean enforceUniqueness) {
    int selectedIndex = optionList.getSelectedIndex();
    HashMap transactionSummary = new HashMap();

    if (optionCode.length() > 0) {
      transactionSummary = localSession.addOptionToUnit(optionCode, localSession.getCurrentUnitId(), enforceAI, enforceUniqueness);
      this.doUpdateScreen();

      // If conditioners are needed
      if (!transactionSummary.get("Conditioners Available").toString().equals("0")) {

        this.selectEntry(selectedIndex);
        // Tell the user
        JOptionPane.showMessageDialog(null,
                                      "Option not added yet, a combo is required." + "\n" +
                                      "The option navigator dialog will now close" + "\n" +
                                      "and a smaller combo option selector will open.", "NextremQuote", 1);

        // Open the combo selector
        String selectedOptionCode = doOpenOptionSelector
            ("NextremQuote - Combo Selector...",
             optionCode,
             enforceAI,
             2);

        // If the user actually selected one
        if (selectedOptionCode.length() > 0)
          localSession.addOptionToUnit(selectedOptionCode,
                             localSession.getCurrentUnitId(),
                             enforceAI,
                             enforceUniqueness);
      }
      this.selectEntry(selectedIndex);
      //this.doAddOptionToUnit2();
      return transactionSummary;
    }
    else {
      return transactionSummary;
    }

  }

  /**
   * Method code: 14
   * Method to add a part to an option
   */
  private void doAddPartToOption() {
    int selectedIndex = optionList.getSelectedIndex();
    if (!this.localSession.getOptionType(localSession.getSelectedItemId()).equals("OPTION")) {
      JOptionPane.showMessageDialog(null, "Please select an Option from the basket and try again. (Code: 010114)",
                                    "NextremQuote", 0);
    }
    else {
      String selectedPartCode = doOpenOptionSelector("NextremQuote - Select Part to Add...", localSession.getSelectedItemCode(), false,
          0);
      if (selectedPartCode.length() > 0) {
        localSession.addPartToOption(selectedPartCode, localSession.getSelectedItemId(), true);
      }
    }
    doUpdateScreen();
    this.selectEntry(selectedIndex);
  }

  /**
   * Method code 36
   * Method that takes care of the update of the main GUI
   */
  private void doUpdateScreen() {
    this.doUpdateFields();
    this.doUpdateBasket();
  }

  /**
   * Method code 35
   */
  public void doUpdateFields() {
    // Calculate the values
    double listPrice = this.localSession.getBasketRollUpListPrice();
    double netPrice = this.localSession.getBasketRollUpNetPrice();
    int units = this.localSession.getBasketDaughterQuantity();
    double cost = this.localSession.getBasketRollUpCost();
    double discount = listPrice - netPrice;
    double discountP100;
    if (listPrice > 0) {
      discountP100 = 100 * discount / listPrice;
    }
    else {
      discountP100 = 0.0;
    }
    double margin = (int) Math.round(listPrice) - (int) Math.round(cost) - (int) Math.round(discount);
    double marginP100 = 0;
    if (listPrice > 0) {
      marginP100 = 100 * margin / listPrice;
    }
    int markup = (int) Math.round(Math.round(netPrice) * Double.parseDouble(this.markupP100Field.getText()) / 100);
    int streetPrice = markup + (int) Math.round(netPrice);

    // Update the fields
    listPriceField.setText(new StringManipulationToolkit().insertThousandSeparator("" + (int) Math.round(listPrice)));
    costField.setText(new StringManipulationToolkit().insertThousandSeparator("" + (int) Math.round(cost)));
    discountP100Field.setText(new StringManipulationToolkit().processP100(discountP100 + ""));
    markupP100Field.setText(this.localSession.getBasketMarkup() + "");
    discountField.setText(new StringManipulationToolkit().insertThousandSeparator("" + (int) Math.round(discount)));
    marginField.setText(new StringManipulationToolkit().insertThousandSeparator("" + (int) Math.round(margin)));
    marginP100Field.setText(new StringManipulationToolkit().processP100("" + marginP100));
    netPriceField.setText(new StringManipulationToolkit().insertThousandSeparator("" + (int) Math.round(netPrice)));
    quantityField.setText(new StringManipulationToolkit().insertThousandSeparator("" + units));
    this.markupField.setText(new StringManipulationToolkit().insertThousandSeparator(markup + ""));
    this.streetPriceField.setText(new StringManipulationToolkit().insertThousandSeparator(streetPrice + ""));
    this.currencyComboBox.removeAllItems();
    this.currencyComboBox.addItem(this.localSession.getBasketCurrency());
  }

  /**
   * Method code
   */
  private void doUpdateBasket() {
    this.doUpdateFields();
    String[] treeBranches = localSession.getBasketItems();
    this.optionList.removeAll();
    for (int i = 0; i < treeBranches.length; i++) {
      this.optionList.add(treeBranches[i]);
    }
    oPW.displayProperties(localSession.getSelectedItemId());
    //jLabel2.requestFocus();
  }

  /**
   * Method code: 13
   * Method that handles the deletion of the item with the given ID
   * @param id String
   */
  private void deleteOption() {
    if (optionNavigator != null) {
      optionNavigator.dispose();
    }
    int selectedIndex = optionList.getSelectedIndex();
    if (localSession.getSelectedItemCode().equals("Empty") ||
        this.localSession.getOptionType(localSession.getSelectedItemId()).equals("OPTCTLG")) {
      JOptionPane.showMessageDialog(null, "Please select a Unit or Option first. (Code: 010113)", "NextremQuote", 0);
    }
    else if (this.localSession.getOptionType(localSession.getSelectedItemId()).equals("PART")) {
      deletePart();
    }
    else {
      if (JOptionPane.showConfirmDialog
          (null,
           "Are you sure you want to remove: " +
           this.localSession.getOptionName(localSession.getSelectedItemId()) + "?", "Confirm Deletion", 0) ==
          0) {
        localSession.removeOptionFromOption(localSession.getSelectedItemId());
        this.doUpdateScreen();
        if (this.optionList.getItemCount() <= selectedIndex) {
          this.selectEntry(this.optionList.getItemCount() - 1);
        }
        else {
          this.selectEntry(selectedIndex);
        }
      }
    }
  }

  private void deletePart() {
    int selectedIndex = optionList.getSelectedIndex();
    if (this.localSession.getOptionType(localSession.getSelectedItemId()).equals("PART")) {
      if (JOptionPane.showConfirmDialog
          (null, "The selected item is a PART. This often carries the cost for the option,\n" +
           "so deleting it will leave the option empty and the unit incomplete.\n" +
           "Are you sure you want to remove: " +
           this.localSession.getOptionName(localSession.getSelectedItemId()) + "?", "Confirm Deletion", 0) ==
          0) {
        localSession.removeOptionFromOption(localSession.getSelectedItemId());
        this.doUpdateScreen();
        if (this.optionList.getItemCount() <= selectedIndex) {
          this.selectEntry(this.optionList.getItemCount() - 1);
        }
        else {
          this.selectEntry(selectedIndex);
        }
      }
    }
    else {
      JOptionPane.showMessageDialog(null, "Please select a Part first. (Code: 010113)", "NextremQuote", 0);
    }
  }

  /**
   * Method code 34
   * Method to select the first entry in the list
   */
  private void selectFirstEntry() {
    optionList.select(0);
    this.doListItemSelection();
  }

  /**
   * Method code 33
   * @param index int
   */
  private void selectEntry(int index) {
    if (optionList.getItemCount() > index) {
      optionList.select(index);
      this.doListItemSelection();
    }
  }

  /**
   * Method code 12
   * Method to launch the GUI for the option selection process that will be passed the code for the option
   * that contains the items we need the user to select from. Will return the user selection
   * @param parentCode String
   * @return String
   */
  private String doOpenOptionSelector(String title, String parentCode, boolean addButtonEnabled, int optionsArrayToUse) {
    String selectedCode = "";
    String[][] optionNames = localSession.getCatalogMatrixForCode(parentCode, optionsArrayToUse);
    if (optionNames.length > 0) {
      myOptionSelectionSimpleGUI = new OptionSelectionSimpleGUI(this, title, true);
      myOptionSelectionSimpleGUI.enableAddButton(addButtonEnabled);
      myOptionSelectionSimpleGUI.populateOptionList(optionNames);
      myOptionSelectionSimpleGUI.setLocation(this.getLocation().x + 30, this.getLocation().y + 30);
      myOptionSelectionSimpleGUI.show();
      selectedCode = myOptionSelectionSimpleGUI.selectedCode();
      myOptionSelectionSimpleGUI.dispose();
    }
    else {
      JOptionPane.showMessageDialog(null, "No sub-items are/remain available to the selected item. (Code: 010112)",
                                    "NextremQuote", 0);
    }
    doUpdateScreen();
    return selectedCode;
  }

  /**
   * Method code 03
   * @param delay int
   * @return InfoSplashScreen
   */
  private static InfoSplashScreen doSplashScreen(int delay) {
    /* Calculate the screen size */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /* Create the splash screen */
    InfoSplashScreen aSplashScreen = new InfoSplashScreen();
    aSplashScreen.pack();
    /* Center splash screen */
    Dimension splashScreenSize = aSplashScreen.getSize();
    if (splashScreenSize.height > screenSize.height) {
      splashScreenSize.height = screenSize.height;
    }
    if (splashScreenSize.width > screenSize.width) {
      splashScreenSize.width = screenSize.width;
    }
    aSplashScreen.setLocation( (screenSize.width - splashScreenSize.width) / 2,
                              (screenSize.height - splashScreenSize.height) / 2);
    aSplashScreen.show();
    aSplashScreen.toFront();
    try {
      Thread.sleep(delay);
    }
    catch (InterruptedException ie) {}
    return aSplashScreen;
  }

  /**
   * Method code 02
   * Method to load the main objects and settings
   */

  private static void displaySplashScreen(InfoSplashScreen infoSplashScreen, int delay){
    infoSplashScreen.updateInfoLabel("(c) 1999 - 2007 NextremSoft");
    try {
      Thread.sleep(delay / 2);
    }
    catch (InterruptedException ie) {}
    infoSplashScreen.updateInfoLabel("Reading NOMS Configuration File...");
    try {
      Thread.sleep(delay / 2);
    }
    catch (InterruptedException ie) {}
    infoSplashScreen.updateInfoLabel("Processing Option Logical Rules...");
    try {
      Thread.sleep(delay);
    }
    catch (InterruptedException ie) {}
    infoSplashScreen.updateInfoLabel("Loading Java Classes...");
    try {
      Thread.sleep(delay / 2);
    }
    catch (InterruptedException ie) {}
    infoSplashScreen.updateInfoLabel("Setting Up User Environment...");
    try {
      Thread.sleep(delay);
    }
    catch (InterruptedException ie) {}
    infoSplashScreen.dispose();


  }

  private static void setGUILocation(NextremQuoteStandaloneGUI gui){
    String locationX = "200";
   if ( (locationX = new SettingsExtractor().extractThis
         ("config" + System.getProperty("file.separator") +
          "guisettings.ini", "app_gui_x")).equals("Error")) {
     locationX = "200";
   }
   String locationY = "200";
   if ( (locationY = new SettingsExtractor().extractThis
         ("config" + System.getProperty("file.separator") +
          "guisettings.ini", "app_gui_y")).equals("Error")) {
     locationY = "200";
   }
   int locX = Integer.parseInt(locationX);
   int locY = Integer.parseInt(locationY);
   gui.setLocation(locX, locY);
  }

  private static void setGUISize(NextremQuoteStandaloneGUI gui){
    gui.setSize(500, 600);
    gui.setResizable(false);
  }

  private void fireUpUnitBuilder() {

    this.costField.setVisible(false);
    this.costLabel.setVisible(false);
    this.marginField.setVisible(false);
    this.marginLabel.setVisible(false);
    this.marginP100Field.setVisible(false);
    this.marginP100Label.setVisible(false);

    this.version = "v." + this.version + " - Client Session: " + NextremQuoteStandaloneGUI.guiCount;
    this.setTitle("NextremQuote - " + version + " - " + this.localSession.getUserName());

    // Create a new side information window
    oPW = new InformationConsoleGUI(this, "NextremQuote Information Console", false);
    oPW.setSize(320, 600);
    oPW.setLocation(this.getLocation().x - oPW.getSize().width, this.getLocation().y);

    // Pass it the selected item by default
    oPW.displayProperties(localSession.getSelectedItemId());

    // Rename the basket
    localSession.renameBasket("NEW QUOTE");

    // Refresh the main screen and select the first entry
    doUpdateScreen();
    this.selectFirstEntry();

    // Start the status bar updater thread
    unitBuilderStatusUpdater.start();

    // Show both windows
    this.show();
    oPW.show();

    // Inform the user
    System.out.println("\n" + "Loading graphics" + "\tOK");

    // Update sales details based on user logon
    oPW.changeSalesTerritoryDetails();
    unlockHiddenFields(System.getProperty("user.name"));

    // Inform the user
    System.out.println("\n" + "Loading sales details" + "\tOK");
  }

  private void changeBasketFontSize(int i) {
    optionList.setFont(new java.awt.Font("System", 0, i));
  }

  /**
   * MOVE THIS TO AN AGENT
   */
  private Thread unitBuilderStatusUpdater = new Thread() {

    private boolean discountProblem = false;

    public void run() {
      while (true) {
        // Check all required groups for the current unit
        if (optionList.getItemCount() > 1) { // To skip root

          if (localSession.checkMissingOptions()) {
            statusBar.setForeground(Color.red);
            statusBar.setText("Incomplete unit(s) detected. Select unit and click here.");
            statusBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
          }
          else {
            statusBar.setForeground(Color.black);
            statusBar.setText("All units in the basket are complete.");
          }

          if (localSession.checkExcessiveDiscount()) {
            statusBar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            statusBar2.setForeground(Color.red);
            statusBar2.setText("Discount Problem");
          }
          else {
            statusBar2.setForeground(Color.black);
            statusBar2.setText("Unit discount OK");
          }


          if (localSession.checkExcessiveDiscount()) {
            discountP100Field.setForeground(Color.red);
            discountP100Field.setFont(new Font("System", 1, 12));
          }
          else {
            discountP100Field.setForeground(Color.black);
            discountP100Field.setFont(new Font("System", 0, 12));
          }
        }
        // If basket is empty reset
        else {
          statusBar2.setForeground(Color.black);
          statusBar2.setText("");
          statusBar.setForeground(Color.black);
          statusBar.setText("Quote is empty.");
          discountP100Field.setForeground(Color.black);
        }
        // Pause
        try {
          this.sleep(500);
        }
        catch (InterruptedException ie) {
          System.out.println("Interrupted Exception. Ignore if not debugging");
        }

      }

    }
  };

  JMenu jMenu9 = new JMenu();
  JMenuItem jMenuItem10 = new JMenuItem();
  JMenuItem jMenuItem13 = new JMenuItem();
  JMenuItem jMenuItem14 = new JMenuItem();
  JMenuItem jMenuItem15 = new JMenuItem();
  JComboBox currencyComboBox = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JMenu jMenu12 = new JMenu();
  JMenuItem syncMenuItem = new JMenuItem();
  JMenu jMenu13 = new JMenu();
  JMenuItem toggleMarginMenuItem = new JMenuItem();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem buildUnitWithDefaultsMenuItem = new JMenuItem();
  JMenuItem togglePartsInfoMenuItem = new JMenuItem();
  Border border3;
  TitledBorder titledBorder4;
  JLabel markupP100Label = new JLabel();
  JTextField streetPriceField = new JTextField();
  JTextField markupP100Field = new JTextField();
  JLabel streetPriceLabel = new JLabel();
  JLabel markupLabel = new JLabel();
  JPanel markupPanel = new JPanel();
  JTextField markupField = new JTextField();
  JMenu jMenu4 = new JMenu();
  JMenuItem jMenuItem19 = new JMenuItem();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem jMenuItem23 = new JMenuItem();
  JMenu jMenu8 = new JMenu();
  JMenuItem jMenuItem20 = new JMenuItem();
  JMenuItem jMenuItem25 = new JMenuItem();
  JMenuItem jMenuItem26 = new JMenuItem();
  JMenuItem jMenuItem22 = new JMenuItem();
  JMenuItem jMenuItem24 = new JMenuItem();
  JMenuItem jMenuItem18 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenu jMenu2 = new JMenu();
  JMenu jMenu5 = new JMenu();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenu jMenu10 = new JMenu();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenu jMenu11 = new JMenu();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenuItem jMenuItem11 = new JMenuItem();
  JMenuItem jMenuItem12 = new JMenuItem();
  JMenuItem jMenuItem16 = new JMenuItem();

  /**
   * Method code 27
   */
  private void doSaveSettings() {
    new SettingsUpdater().updateLine("config" + sep + "guisettings.ini", "app_gui_x", "" + this.getLocation().x);
    new SettingsUpdater().updateLine("config" + sep + "guisettings.ini", "app_gui_y", "" + this.getLocation().y);
    localSession.saveJournal();
  }

  private void doExit() {
    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit NextremQuote?", "NextremQuote", 2, 3) == 0) {
      this.doSaveSettings();
      if (localSession.isChangesMade()) {
        if (JOptionPane.showConfirmDialog(null, "Do you want to save your changes?", "NextremQuote", 0) == 0) {
          this.doSaveBasketToLibrary();
        }
      }
      System.exit(0);
    }
  }

  /**
   * Method code 26
   */
  private void doShowAboutBox() {
    NextremQuoteAboutBox myAboutBox = new NextremQuoteAboutBox();
    myAboutBox.setSize(335, 355);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    InfoSplashScreen aSplashScreen = new InfoSplashScreen();
    aSplashScreen.pack();
    Dimension splashScreenSize = aSplashScreen.getSize();
    if (splashScreenSize.height > screenSize.height) {
      splashScreenSize.height = screenSize.height;
    }
    if (splashScreenSize.width > screenSize.width) {
      splashScreenSize.width = screenSize.width;
    }
    myAboutBox.setLocation( (screenSize.width - splashScreenSize.width) / 2,
                           (screenSize.height - splashScreenSize.height) / 2);
    myAboutBox.setTitle("About NextremQuote");
    myAboutBox.setVersion("Version: " + localSession.internalVersion());
    myAboutBox.setLicence("(Unlicensed)");
    myAboutBox.show();
  }

  void createUnitButton_actionPerformed(ActionEvent e) {
    this.doAddUnitWithDefaults();
    this.localSession.setChangesMade(true);
  }

  void scanNOMSDownloadButton_actionPerformed(ActionEvent e) {
  }

  void addPartButton_actionPerformed(ActionEvent e) {
    this.doAddPartToOption();
    this.localSession.setChangesMade(true);
  }

  void addOptionButton_actionPerformed(ActionEvent e) {
    this.doAddOptionToUnit2();
    this.localSession.setChangesMade(true);
  }

  void optionList_itemStateChanged(ItemEvent e) {
    this.doListItemSelection();
  }

  void deleteItemButton_actionPerformed(ActionEvent e) {
    this.deleteOption();
    this.localSession.setChangesMade(true);
  }

  void saveMenuItem_actionPerformed(ActionEvent e) {
    this.doSaveBasketToLibrary();
  }

  void closeMenuItem_actionPerformed(ActionEvent e) {
    this.doCloseBasket();
  }

  void openUnitMenuItem_actionPerformed(ActionEvent e) {
    this.doOpenBasket();
  }

  void aboutMenuItem_actionPerformed(ActionEvent e) {
    doShowAboutBox();
  }

  void newUnitMenuItem_actionPerformed(ActionEvent e) {
    this.doCreateNewUnit();
  }

  void addPartMenuItem_actionPerformed(ActionEvent e) {
    this.doAddPartToOption();
  }

  void jMenuItem8_actionPerformed(ActionEvent e) {
    localSession.printJournal();
  }

  void substituteItemButton_actionPerformed(ActionEvent e) {
    this.doSubstituteItem();
    this.localSession.setChangesMade(true);
  }

  void jMenuItem9_actionPerformed(ActionEvent e) {
    this.doExit();
  }

  void viewPropertiesWindowMenuItem_actionPerformed(ActionEvent e) {
    this.doShowPropertiesWindow();
  }

  void openMenuItem_actionPerformed(ActionEvent e) {
    this.doOpenBasket();
  }

  void showSplashMenuItem_actionPerformed(ActionEvent e) {
    this.doSplashScreen(0);
  }

  void buildUnitWithDefaultsMenuItem_actionPerformed(ActionEvent e) {
    this.doAddUnitWithDefaults();
  }

  void this_componentMoved(ComponentEvent e) throws NullPointerException {
    if (oPW != null) {
      oPW.setLocation(this.getLocation().x - oPW.getSize().width, this.getLocation().y);
    }
  }

  /**
   * Method code 26
   */
  private void doShowEULA() {
    SimpleNotepad s = new SimpleNotepad();
    String[] eulaLines = new ReadFromFile().readFromFile("config" + sep + "eula.txt");
    for (int i = 0; i < eulaLines.length; i++) {
      s.appendText(eulaLines[i]);

    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension splashScreenSize = s.getSize();
    if (splashScreenSize.height > screenSize.height) {
      splashScreenSize.height = screenSize.height;
    }
    if (splashScreenSize.width > screenSize.width) {
      splashScreenSize.width = screenSize.width;
    }
    s.setLocation( (screenSize.width - splashScreenSize.width) / 2, (screenSize.height - splashScreenSize.height) / 2);
    s.show();
  }

  /**
   * Method code 64
   */
  private void doShowHelp() {
    SimpleNotepad s = new SimpleNotepad();
    String[] eulaLines = new ReadFromFile().readFromFile("help.dat");
    for (int i = 0; i < eulaLines.length; i++) {
      s.appendText(eulaLines[i]);
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension splashScreenSize = s.getSize();
    if (splashScreenSize.height > screenSize.height) {
      splashScreenSize.height = screenSize.height;
    }
    if (splashScreenSize.width > screenSize.width) {
      splashScreenSize.width = screenSize.width;
    }
    s.setLocation( (screenSize.width - splashScreenSize.width) / 2, (screenSize.height - splashScreenSize.height) / 2);
    s.show();
  }

  void viewEulaMenuItem_actionPerformed(ActionEvent e) {
    doShowEULA();

  }

  public LocalSession unitBuilder() {
    return this.localSession;
  }

  void jMenuItem6_actionPerformed(ActionEvent e) {
    this.doSaveBasketAs();
  }

  void jCheckBoxMenuItem5_actionPerformed(ActionEvent e) {
    this.changeBasketFontSize(12);
  }

  void jMenuItem13_actionPerformed(ActionEvent e) {
    this.changeBasketFontSize(11);
  }

  void jMenuItem14_actionPerformed(ActionEvent e) {
    this.changeBasketFontSize(10);
  }

  void jMenuItem15_actionPerformed(ActionEvent e) {
    this.changeBasketFontSize(9);
  }

  void syncMenuItem_actionPerformed(ActionEvent e) {
    this.doSync();
  }

  void jMenuItem10_actionPerformed(ActionEvent e) {
    this.changeBasketFontSize(13);
  }

  void substituteItemMenuItem_actionPerformed(ActionEvent e) {
    this.doSubstituteItem();
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    this.doShowHelp();
  }


  void hideShowPartsMenuItem_actionPerformed(ActionEvent e) {
    this.doHideShowPartsInformation();
  }

  void jMenuItem21_actionPerformed(ActionEvent e) {
    this.doRegisterDB();
  }

  void toggleMarginMenuItem_actionPerformed(ActionEvent e) {
    doHideShowMargin();
  }


  void jMenuItem7_actionPerformed(ActionEvent e) {
    this.doHideShowCost();
  }

  void jMenuItem16_actionPerformed(ActionEvent e) {
    this.localSession.printUserSessions();
  }

  void markupP100Field_focusGained(FocusEvent e) {
    markupP100Field.setBackground(Color.white);
    markupP100Field.setSelectionStart(0);
    markupP100Field.setSelectionEnd(markupP100Field.getText().length());
  }

  void markupP100Field_focusLost(FocusEvent e) {
    markupP100Field.setBackground(new Color(204, 204, 204));
    this.doUpdateFields();
  }

  void markupP100Field_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 10) {
      this.localSession.setChangesMade(true);
      this.doAddPartToOption();
      markupP100Field.setText(new StringManipulationToolkit().processP100(markupP100Field.getText()));
      localSession.setMarkup(markupP100Field.getText());
      this.listPriceField.requestFocus();
      this.doUpdateFields();
    }
  }

  void togglePartsInfoMenuItem_actionPerformed(ActionEvent e) {
    this.doHideShowPartsInformation();
  }

  void markupP100Field_keyReleased(KeyEvent e) {

  }

  void markupP100Field_keyTyped(KeyEvent e) {

  }

  void jMenuItem22_actionPerformed(ActionEvent e) {
    this.doFindMissingGroups();
  }

  void jMenuItem23_actionPerformed(ActionEvent e) {
    this.doRegisterDB();
  }

  void jMenuItem20_actionPerformed(ActionEvent e) {
    this.deleteOption();
  }

  void jMenu4_actionPerformed(ActionEvent e) {

  }

  void jMenuItem25_actionPerformed(ActionEvent e) {
    this.deletePart();
  }

  void jMenuItem26_actionPerformed(ActionEvent e) {
    this.doSubstituteItem();
  }

  void statusBar_mouseClicked(MouseEvent e) {
    this.doFindMissingGroups();
  }


  void jMenuItem27_actionPerformed(ActionEvent e) {
    String[] treeBranches = localSession.getBasketItems();
    for (int i = 0; i < treeBranches.length; i++) {
      System.out.println(treeBranches[i]);

    }
  }

  void statusBar2_mouseClicked(MouseEvent e) {
    JOptionPane.showMessageDialog(null, "Check all units in the quote for discounts above the Max.\n" +
                                  "If the discount is above Max, the quote may not be authorized.\n" +
                                  "You should only continue if you are certain you will receive authorization.\n" +
                                  "To resolve any discount issues, select each unit in the basket and make sure\n" +
                                  "you set the discount on the Information Console to a value that is below the Max.",
                                  "NextremQuote", 1);
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    crypto.GNUPGCryptographicEngine gnupgCrypto = new crypto.GNUPGCryptographicEngine();
    boolean cryptoOK = gnupgCrypto.setupWin32("GnuPG", "GnuPG");
    JOptionPane.showMessageDialog(null, "GnuPG (Cryptographic module) installed OK: " + cryptoOK);
    String[] lines = gnupgCrypto.decrypt("config" + sep + "ConfFile2.dat.gpg");
    JOptionPane.showMessageDialog(null, "Lines read: " + lines.length);
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
    this.doExportToExcel();
  }

  void jMenuItem3_actionPerformed(ActionEvent e) {
    this.doImportFromCSV();
  }

  void jMenuItem5_actionPerformed(ActionEvent e) {
    this.doExportToCSV();
  }

  void saveAsMenuItem_actionPerformed(ActionEvent e) {
    this.doSaveBasketAs();
  }

  void jMenuItem11_actionPerformed(ActionEvent e) {
    this.doImportFromCSV();
  }

  void jMenuItem12_actionPerformed(ActionEvent e) {

  }

}

class NextremQuoteSimpleGUI_optionList_itemAdapter
    implements java.awt.event.ItemListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_optionList_itemAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.optionList_itemStateChanged(e);
  }
}

class NextremQuoteSimpleGUI_deleteItemButton_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_deleteItemButton_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.deleteItemButton_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_closeMenuItem_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_closeMenuItem_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.closeMenuItem_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_aboutMenuItem_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_aboutMenuItem_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.aboutMenuItem_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_newUnitMenuItem_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_newUnitMenuItem_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.newUnitMenuItem_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_createUnitButton_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_createUnitButton_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.createUnitButton_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_addOptionButton_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_addOptionButton_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addOptionButton_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_addPartButton_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_addPartButton_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addPartButton_actionPerformed(e);
  }

}

class NextremQuoteSimpleGUI_substituteItemButton_actionAdapter
    implements java.awt.event.ActionListener {
  NextremQuoteStandaloneGUI adaptee;

  NextremQuoteSimpleGUI_substituteItemButton_actionAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.substituteItemButton_actionPerformed(e);
  }
}

class NextremQuoteSimpleGUI_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  NextremQuoteStandaloneGUI adaptee;
  NextremQuoteSimpleGUI_this_windowAdapter(NextremQuoteStandaloneGUI adaptee) {
    this.adaptee = adaptee;
  }
}
