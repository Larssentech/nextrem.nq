// (c) 2006-2007 NextremSoft

package nq.net;

import utilities.string.*;

import java.net.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class NextremQuoteWebClient {

  private BufferedReader fromNet;
  private PrintWriter toNet;

  public NextremQuoteWebClient() {
  }

  public void initComms(String ip, int port){
    try{
      Socket s = new Socket(ip, port);
      this.fromNet = new BufferedReader(new InputStreamReader(s.getInputStream()));
      this.toNet = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
    }
    catch(Exception e){
      System.out.println("Exception @ NextremQuoteWebClient. Failed to create socket");
      System.out.println(e.toString());
    }
  }

  /**
   * Method to send one-line commands to the server as the domain model
   * typically expects them. After the server has processed the commands
   * it will return some multi-line message that is passed back to the
   * sender session invoking this mehtod.
   * @param command String
   * @return ArrayList
   */
  public String[] sendCommand(String command){
    ArrayList returnMessage = new ArrayList();
    this.toNet.print(command);
    this.toNet.flush();
    String thisLine;
    try {
      while ( (thisLine = this.fromNet.readLine()).length() > 0)
        returnMessage.add(thisLine);
    }
    catch(Exception e){
      System.out.println("Exception @ NextremQuoteWebClient. Stream comms failed");
      System.out.println(e.toString());
      returnMessage.add("Error");
      returnMessage.add(e);
    }
    return new StringManipulationToolkit().createArrayFromArrayList(returnMessage);
  }


}
