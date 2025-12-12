// (c) 2006-2007 NextremSoft

package nq.gui.standalone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InfoSplashScreen extends JDialog {
  JLabel logoLabel = new JLabel();
  JLabel infoLabel = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;

  public InfoSplashScreen() {
    this.setUndecorated(true);
    this.setResizable(false);
    this.requestFocus();
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateInfoLabel(String text) {
    this.infoLabel.setText(text);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createLineBorder(Color.lightGray, 2);
    logoLabel.setIcon(new ImageIcon(InfoSplashScreen.class.getResource("nextrem.gif")));
    logoLabel.addFocusListener(new SplashScreen_logoLabel_focusAdapter(this));
    logoLabel.addMouseListener(new SplashScreen_logoLabel_mouseAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    infoLabel.setBackground(Color.gray);
    infoLabel.setMaximumSize(new Dimension(61, 18));
    infoLabel.setMinimumSize(new Dimension(61, 18));
    infoLabel.setPreferredSize(new Dimension(61, 18));
    infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    infoLabel.setText("NextremSoft");
    this.addFocusListener(new InfoSplashScreen_this_focusAdapter(this));
    this.getContentPane().add(logoLabel, BorderLayout.NORTH);
    this.getContentPane().add(infoLabel, BorderLayout.CENTER);
  }

  void logoLabel_mouseClicked(MouseEvent e) {
    this.dispose();
  }

  void logoLabel_focusLost(FocusEvent e) {
    this.dispose();
  }

  void this_focusLost(FocusEvent e) {
    this.dispose();
  }
}

class SplashScreen_logoLabel_mouseAdapter
    extends java.awt.event.MouseAdapter {
  InfoSplashScreen adaptee;

  SplashScreen_logoLabel_mouseAdapter(InfoSplashScreen adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.logoLabel_mouseClicked(e);
  }
}

class SplashScreen_logoLabel_focusAdapter
    extends java.awt.event.FocusAdapter {
  InfoSplashScreen adaptee;

  SplashScreen_logoLabel_focusAdapter(InfoSplashScreen adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.logoLabel_focusLost(e);
  }
}

class InfoSplashScreen_this_focusAdapter
    extends java.awt.event.FocusAdapter {
  InfoSplashScreen adaptee;

  InfoSplashScreen_this_focusAdapter(InfoSplashScreen adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.this_focusLost(e);
  }
}
