// (c) 2003-2007 NextremSoft

package jdbc;

import java.sql.*;
import java.util.*;
import utilities.string.*;

public class DBConn {
  public DBConn() {}

  public Vector getData(String field, String value) {
    Vector dataLines = new Vector();
    String data = "jdbc:odbc:MI_LIVE_DB2";
    try {
      // Load class
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      // Create connection to the DB data source
      Connection conn = DriverManager.getConnection(data, "", "");
      System.out.println("\t- Connected. JDBC/ODBC module...OK");
      // Set up a statement that we will pass to the DB
      Statement st = conn.createStatement();
      String query;
      // if(field.length() == 0 && value.length() == 0) query = "SELECT * FROM LIVE_VALUES";
      // Create the query that will be inside the statement
      query = "SELECT * FROM LIVE_VALUES_2";

      //else query = "SELECT * FROM Movies WHERE (" + field + "='" + value + "') ORDER BY Name";
      // Execute the query in the statement and get results set
      System.out.println("\t- Passing SQL statement. Be patient, this can take some time.");
      ResultSet rec = st.executeQuery(query);

      //System.out.println("TITLE\tHYSTER\tYALE");

      // Loop thru result set getting lines 8 LINES NOW
      while (rec.next()) {
        String temp1 = rec.getString(1);
        //System.out.println(temp1);
        //dataLines.addElement(temp1); // BRAND
        String temp2 = rec.getString(2);
        //System.out.println(temp2);
        //dataLines.addElement(temp2); // TYPE
        String temp3 = rec.getString(3);
        //dataLines.addElement(temp3); // CB/WHE
        //System.out.println(temp3);
        String temp4 = rec.getString(4);
        if(temp4.indexOf(".") != -1) temp4 = temp4.substring(0, temp4.indexOf("."));
        dataLines.addElement(temp4); // VALUE
        //System.out.println(temp4);
      }
      st.close();

      //PrintWriter toFile = new PrintWriter(new FileWriter("index.nxml"));
      //toFile.println(new WebPageCreator().createPage(dataLines));
      //toFile.close();
    }
    catch (SQLException s) {
      System.out.println("\t- SQL Error: " + s.toString() + " "
                         + s.getErrorCode() + " " + s.getSQLState());
    }
    catch (Exception e) {
      System.out.println("\t -Error: " + e.toString() + e.getMessage());
    }
    System.out.println("\t- Done ");
    return dataLines;

  }


  public Vector getMessage(String field, String value) {
      Vector dataLines = new Vector();
      String data = "jdbc:odbc:MI_LIVE_DB2";
      try {
        // Load class
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // Create connection to the DB data source
        Connection conn = DriverManager.getConnection(data, "", "");
        //System.out.println("\t- Connected. JDBC/ODBC module...OK");
        // Set up a statement that we will pass to the DB
        Statement st = conn.createStatement();
        String query;
        // if(field.length() == 0 && value.length() == 0) query = "SELECT * FROM LIVE_VALUES";
        // Create the query that will be inside the statement
        query = "SELECT * FROM JAVA_MESSAGE";

        //else query = "SELECT * FROM Movies WHERE (" + field + "='" + value + "') ORDER BY Name";
        // Execute the query in the statement and get results
        //System.out.println("\t- Passing SQL statement. Be patient, this can take some time.");
        ResultSet rec = st.executeQuery(query);

        //System.out.println("TITLE\tHYSTER\tYALE");

        // Loop thru result set getting lines 8 LINES NOW
        while (rec.next()) {
          String temp1 = rec.getString(1);
          //System.out.println(temp1);
          dataLines.addElement(temp1); // message
        }
        st.close();

        //PrintWriter toFile = new PrintWriter(new FileWriter("index.nxml"));
        //toFile.println(new WebPageCreator().createPage(dataLines));
        //toFile.close();
      }
      catch (SQLException s) {
        System.out.println("\t- SQL Error: " + s.toString() + " "
                           + s.getErrorCode() + " " + s.getSQLState());
      }
      catch (Exception e) {
        System.out.println("\t -Error: " + e.toString() + e.getMessage());
      }
      //System.out.println("\t- Data retrieved OK. DBConn.class succeeded!");
      return dataLines;

    }

  public static void main(String[] args) {
    new DBConn().getData(args[0], args[1]);
  }

  public boolean writeData(String dsnName, String[][] values) {
    System.out.println();
    System.out.print("Loading JDBC-ODBC...");
    String url = "jdbc:odbc:" + dsnName;
    boolean success = true;
    try{
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection conn = DriverManager.getConnection(url, "odbc", "");
      Statement st = conn.createStatement();
      String query;
      for (int i = 1; i < values.length; i++) { // To exclude titles
        query = "INSERT INTO midm_unit_options_transactional_data " +
            "(user_name, basket_id, time_stamp, name, type, code, option_group, quantity, gross_value, discount, net_value, markup, street, country, dealer_code, customer)" +
            " VALUES (";
        for(int j=0; j<values[i].length-1; j++)
          query += "'" + new StringManipulationToolkit().replaceAll(values[i][j], "'", "") + "',";
        query += "'" + new StringManipulationToolkit().replaceAll(values[i][values[i].length-1], "'", "") + "')";
        System.out.print(".");
        try {
          st.execute(query);
        }
        catch (SQLException s) {
          success = false;
          System.out.println("\t- SQL Error: " + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
        }
      }
      st.close();
    }
    catch (Exception e) {
      success = false;
      System.out.println("\t -Error: " + e.toString() + e.getMessage());
    }
    return success;
  }


}
