package nq.loader;

import nq.sales.User;
import nq.orchestrating.NextremQuote;
import nq.net.*;

public class NextremQuoteServerLoader {

  /**
   * This is the main method that will be invoked from the command line.
   * It will create a default user class and pass it to the orchestrating
   * class which is the class that controls the domain model.
   * Then a client session object is created and passed the orchestrating
   * Finally the client session is passed to a GUI object
   * @param args String[]
   */
  public static void main(String[] args) {
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

    // Create the server session manager which will store references to
    // the various server sessions running attached to the various
    // remote users and linked to the various user sessions in the
    // domain model
    ServerSessionManager serverSesisonManager = new ServerSessionManager(nq, user);

    // Start the werver
    new NextremQuoteWebServer(serverSesisonManager, "34001").start();
  }
}
