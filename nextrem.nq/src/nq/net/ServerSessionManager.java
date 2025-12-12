// (c) 2006-2007 NextremSoft

package nq.net;

import nq.orchestrating.*;
import nq.sales.User;


import java.util.*;

public class ServerSessionManager {

  // To store all server sessions with their IP address as key
  private HashMap servertSessions;

  // The server session manager needs to store
  // a reference to these to pass to each new network
  // client session created so that they all share the
  // domain model
  private NextremQuote nq;
  private User user;

  public ServerSessionManager(NextremQuote nq, User user) {
    this.nq = nq;
    this.user = user;
    this.servertSessions = new HashMap();
  }

  public ServerSession getClientSessionByIP(String ipAddress){

    // If no session exists with this IP address, create a new one
    if(this.servertSessions.get(ipAddress) == null)
      this.servertSessions.put(ipAddress, new ServerSession(this.nq, this.user));

    return (ServerSession)this.servertSessions.get((Object)ipAddress);
  }
}
