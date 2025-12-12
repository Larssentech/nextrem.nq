// (c) 2006-2007 NextremSoft

package nq.net;

import java.net.*;
import nq.session.*;


// This class is the server socket. It will launch a Conn class every time a request is
// made from a client. Each Conn class will run in a separate thread
public class NextremQuoteWebServer extends Thread {
  private int port;
  private ServerSocket ss;

  private ServerSessionManager serverSessionManager;

  private boolean listenerOn;

  public NextremQuoteWebServer(ServerSessionManager serverSessionManager, String port) {
    // Hold an instance of the stored session manager
    this.serverSessionManager = serverSessionManager;

    this.listenerOn = true;
    this.port = Integer.parseInt(port);
    this.listenerOn = true;
  }

  public void run() {
    try {
      ss = new ServerSocket(port);
      while (listenerOn) {
        try {
          System.out.println(" >> Server: NextremQuote Server class running");
          // Create a socket with the client
          Socket clientSocket = ss.accept();
          System.out.println(" >> Server: Request received");

          // Get the client's IP address
          String clientIp = clientSocket.getInetAddress().toString();
          System.out.println(" >> Server: client IP is: \t" + clientIp);

          // Find the stored session for this IP address. If there is no
          // stored session, this will be a brand new session
          ServerSession thisSession = serverSessionManager.getClientSessionByIP(clientIp);

          // Pass it the client socket so it can talk to the client
          thisSession.setClientSocket(clientSocket);

          // And initialise comms
          thisSession.initComms();

          // And start the thread
          new Thread(thisSession).start();
        }
        catch (Exception ee) {
          System.out.println(ee.toString());
        }
      }
      System.out.flush();
    }
    catch (Exception e) {
      System.out.println(" - Listener.class -->" + e.toString());
      try {
        ss.close();
      }
      catch (Exception e1) {}
    }
  }

  public void stopListener() {
    listenerOn = false;
    try {
      new Socket("localhost", port); // This is to unblock the accept() method and exit the loop
      //ss.close();
    }
    catch (Exception e) {}
  }
}
