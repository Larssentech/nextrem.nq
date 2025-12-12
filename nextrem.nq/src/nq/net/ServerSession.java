// (c) 2006-2007 NextremSoft

package nq.net;

import nq.session.*;
import nq.orchestrating.*;
import nq.sales.User;

import java.io.*;
import java.net.*;
import java.util.*;


public class ServerSession extends LocalSession implements Runnable{

  private Socket clientSocket;
  private BufferedReader netReader;
  private PrintWriter netWriter;
  private int requests;

  private boolean sessionValid;

  public ServerSession(NextremQuote nq, User user) {
    super(nq, user);
    this.requests = 0;
    this.sessionValid = true;
  }

  private void handleRequest(ArrayList arrayList){
    // Send HTTP response first with content type
    netWriter.println("HTTP/1.0 200 ");
    netWriter.println("Content-type: text/html");
    // And an empty line to indicate begin of page content
    netWriter.println();
    netWriter.flush();

    // Pump out the stored lines
    Iterator ite = arrayList.iterator();
    while(ite.hasNext()) {
      // As it is text/html a line break ensures the lines are lines <br>
      String thisLine = ite.next().toString();
      netWriter.println(thisLine + "<br>");
    }
    netWriter.flush();
    netWriter.close();
  }

  // This will start the an independent thread and will wait
  // for th client to send a request (which should not be long)
  public void run(){
    while(sessionValid){
      try{
        String thisLine;
        ArrayList arrayList = new ArrayList();
        while((thisLine = this.netReader.readLine()).length() > 0) arrayList.add(thisLine);
        this.handleRequest(arrayList);
      }
      catch(Exception e){
      }
    }
  }

  public Socket getClientSocket() {
    return clientSocket;
  }

  public void setClientSocket(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public boolean initComms(){
    try{
      this.requests += 1;
      System.out.print(" >> Server: Request: " + this.requests + "; Socket " + this.clientSocket.toString());
      this.netReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
      this.netWriter = new PrintWriter(this.clientSocket.getOutputStream());
      return true;
    }
    catch(Exception e){
      return false;
    }
  }


}
