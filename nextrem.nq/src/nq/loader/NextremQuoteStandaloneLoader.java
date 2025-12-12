// (c) 2006-2007 NextremSoft

package nq.loader;

import nq.gui.standalone.*;
import nq.sales.User;
import nq.orchestrating.NextremQuote;
import nq.session.LocalSession;

import javax.swing.*;

public class NextremQuoteStandaloneLoader {

  /**
   * This is the main method that will be invoked from the command line.
   * It will create a default user class and pass it to the orchestrating
   * class which is the class that controls the domain model.
   * Then a client session object is created and passed the orchestrating
   * Finally the client session is passed to a GUI object
   * @param args String[]
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      System.out.println("UI LnF failed");
    }
      System.out.println("");
        System.out.println("Copyright (c) 2005-2007 NextremSoft.");
        System.out.println("All Rights Reserved.");
        System.out.println("NextremQuote, the product builder");
        System.out.println("and quote creation toolkit");
        System.out.println("===============================================");
        System.out.println("");

    // Create an instance of our client session class
    User user = new User();
    user.setName("UK Regional Sales Director");
    user.setLogin(System.getProperty("user.name"));
    System.out.print(".");

    // Create the orchestrating class with our default user
    NextremQuote nq = new NextremQuote(user);

    // Create the default client session for this GUI and pass
    // it the domain model and user
    LocalSession localSesison = new LocalSession(nq, user);

    // Create the default instance of the GUI and pass it the client session
    // that the GUI will interact with
    new NextremQuoteStandaloneGUI(localSesison);
  }
}
