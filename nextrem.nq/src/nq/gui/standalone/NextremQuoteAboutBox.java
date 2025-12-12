// (c) 2006-2007 NextremSoft

package nq.gui.standalone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NextremQuoteAboutBox
    extends JDialog {
  JPanel mainPanel = new JPanel();
  JTextArea jTextArea1 = new JTextArea();
  JTextArea licensedToTextArea = new JTextArea();
  JTextArea jTextArea3 = new JTextArea();
  JTextArea versionArea = new JTextArea();
  JTextArea systemInfoArea = new JTextArea();
  private String version = "Version 1.0.x";
  private String licence = "(Unlicensed)";
  JTextArea copyrightArea = new JTextArea();
  JLabel jLabel1 = new JLabel();
  JTextArea versionArea1 = new JTextArea();

  public static void main(String[] args) {
    NextremQuoteAboutBox t = new NextremQuoteAboutBox();
    t.setSize(340, 230);
    t.setLocation(300, 300);
    t.setTitle("About NextremQuote");
    t.show();

  }

  public void setVersion(String version) {
    this.version = version;
    this.versionArea.setText("NextremQuote. " + version);
  }

  public void setLicence(String licence) {
    this.licence = licence;
    this.licensedToTextArea.setText(licence);
  }

  public NextremQuoteAboutBox(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      String javaVendor = System.getProperty("java.vendor");
      String javaVersion = System.getProperty("java.vm.version");
      String osNameString = System.getProperty("os.name");
      String osVersionString = System.getProperty("os.version");
      String userNameString = System.getProperty("user.name");
      this.systemInfoArea.setText("System information\n" +
                                  "Java Vendor: " + javaVendor + "\n" +
                                  "Java Version: " + javaVersion + "\n" +
                                  "OS: " + osNameString + " Version: " + osVersionString + "\n" +
                                  "User Account: " + userNameString);
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public NextremQuoteAboutBox() {
    this(null, "", true);

  }

  private void jbInit() throws Exception {
    jLabel1.setIcon(new ImageIcon(InfoSplashScreen.class.getResource("java.png")));
    mainPanel.setLayout(null);
    jTextArea1.setBackground(UIManager.getColor("Panel.background"));
    jTextArea1.setFont(new java.awt.Font("System", 0, 11));
    jTextArea1.setBorder(null);
    jTextArea1.setEditable(false);
    jTextArea1.setText("This computer program is protected by copyright law\nand international " +
                       "treaties. Unauthorized reproduction\nor distribution of this program, " +
                       "or any portion of it, may\nresult in severe civil and criminal penalties, " +
                       "and will be\nprosecuted to the maximum extent possible under the law.");
    jTextArea1.setBounds(new Rectangle(12, 159, 312, 74));
    jTextArea1.addMouseListener(new NextremQuoteAboutBox_jTextArea1_mouseAdapter(this));
    licensedToTextArea.setBackground(UIManager.getColor("Panel.background"));
    licensedToTextArea.setFont(new java.awt.Font("System", 0, 11));
    licensedToTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
    licensedToTextArea.setEditable(false);
    licensedToTextArea.setMargin(new Insets(0, 0, 0, 0));
    licensedToTextArea.setBounds(new Rectangle(12, 100, 312, 41));
    licensedToTextArea.addMouseListener(new NextremQuoteAboutBox_licensedToTextArea_mouseAdapter(this));
    jTextArea3.setBounds(new Rectangle(12, 80, 312, 17));
    jTextArea3.addMouseListener(new NextremQuoteAboutBox_jTextArea3_mouseAdapter(this));
    jTextArea3.setText("This product is licensed to:");
    jTextArea3.setBorder(null);
    jTextArea3.setEditable(false);
    jTextArea3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextArea3.setBackground(UIManager.getColor("Panel.background"));
    versionArea.setBackground(UIManager.getColor("Panel.background"));
    versionArea.setFont(new java.awt.Font("Dialog", 0, 11));
    versionArea.setBorder(null);
    versionArea.setEditable(false);
    versionArea.setText("Version 1.2.x");
    versionArea.setBounds(new Rectangle(12, 7, 312, 18));
    versionArea.addMouseListener(new NextremQuoteAboutBox_versionArea_mouseAdapter(this));
    mainPanel.addMouseListener(new NextremQuoteAboutBox_mainPanel_mouseAdapter(this));
    this.getContentPane().setBackground(new Color(204, 204, 204));
    this.setResizable(false);
    this.addFocusListener(new NextremQuoteAboutBox_this_focusAdapter(this));
    this.addMouseListener(new NextremQuoteAboutBox_this_mouseAdapter(this));
    systemInfoArea.setBounds(new Rectangle(85, 243, 219, 74));
    systemInfoArea.addMouseListener(new NextremQuoteAboutBox_systemInfoArea_mouseAdapter(this));
    systemInfoArea.setEditable(false);
    systemInfoArea.setText("");
    systemInfoArea.setBorder(null);
    systemInfoArea.setFont(new java.awt.Font("System", 0, 11));
    systemInfoArea.setBackground(UIManager.getColor("Panel.background"));
    copyrightArea.setText("Copyright (c) 2005-2007 NextremSoft. All rights reserved." + "\n" +
                          "Portions Copyright (c) 1999-2004 NextremSoft." + "\n" +
                          "Cryptography Copyright (c) 1998-2006 FS Foundation, Inc.");
    copyrightArea.setBounds(new Rectangle(12, 27, 312, 50));
    copyrightArea.addMouseListener(new NextremQuoteAboutBox_copyrightArea_mouseAdapter(this));
    copyrightArea.setEditable(false);
    copyrightArea.setBorder(null);
    copyrightArea.setFont(new java.awt.Font("System", 0, 11));
    copyrightArea.setBackground(UIManager.getColor("Panel.background"));
    jLabel1.setToolTipText("");
    jLabel1.setBounds(new Rectangle(12, 245, 49, 52));
    jLabel1.addMouseListener(new NextremQuoteAboutBox_jLabel1_mouseAdapter(this));
    versionArea1.setBounds(new Rectangle(12, 144, 312, 18));
    versionArea1.setText("Warning");
    versionArea1.setEditable(false);
    versionArea1.setBorder(null);
    versionArea1.setFont(new java.awt.Font("Dialog", 0, 11));
    versionArea1.setBackground(UIManager.getColor("Panel.background"));
    getContentPane().add(mainPanel);
    mainPanel.add(versionArea, null);
    mainPanel.add(jLabel1, null);
    mainPanel.add(jTextArea1, null);
    mainPanel.add(copyrightArea, null);
    mainPanel.add(systemInfoArea, null);
    mainPanel.add(versionArea1, null);
    mainPanel.add(jTextArea3, null);
    mainPanel.add(licensedToTextArea, null);
  }

  void mainPanel_mouseClicked(MouseEvent e) {

  }

  void jTextArea1_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void licensedToTextArea_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void jTextArea3_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void versionArea_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void this_mouseEntered(MouseEvent e) {
  }

  void this_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void copyrightArea_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void systemInfoArea_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void jLabel1_mouseClicked(MouseEvent e) {
    this.dispose();
  }

}

class NextremQuoteAboutBox_mainPanel_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_mainPanel_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.mainPanel_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_jTextArea1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_jTextArea1_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextArea1_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_licensedToTextArea_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_licensedToTextArea_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.licensedToTextArea_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_jTextArea3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_jTextArea3_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextArea3_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_versionArea_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_versionArea_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.versionArea_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_this_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_this_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.this_mouseEntered(e);
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.this_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_copyrightArea_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_copyrightArea_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.copyrightArea_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_systemInfoArea_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_systemInfoArea_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.systemInfoArea_mouseClicked(e);
  }
}

class NextremQuoteAboutBox_this_focusAdapter
    extends java.awt.event.FocusAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_this_focusAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }
}

class NextremQuoteAboutBox_jLabel1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  NextremQuoteAboutBox adaptee;

  NextremQuoteAboutBox_jLabel1_mouseAdapter(NextremQuoteAboutBox adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jLabel1_mouseClicked(e);
  }
}
