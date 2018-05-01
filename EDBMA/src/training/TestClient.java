package training;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestClient
{
  DBClient client = null;
  DBClientUI clientUI = null;
  Connection con = null;
  String server = null;
  String user; String passwd; Properties prop = null;
  
  public TestClient()
  {
    prop = DBClient.readProperties("MSSQL.properties");
    server = prop.getProperty("SERVER");
    user = prop.getProperty("USER");
    passwd = prop.getProperty("PASSWORT");
    System.out.println("Server: " + server);
    System.out.println("User: " + user);
    try
    {
      client = new MSSQLClient(server, user, passwd);
      clientUI = new DBClientUI();
      
      con = client.connect();
      String dbName = con.getCatalog();
      System.out.println("Verbindung mit Datenbank " + dbName + " war erfolgreich\n");

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      try { if (client != null) client.closeConnection();
      } catch (Exception localException) {}
    }
  }
  
  public void closeConnection() { try { if (client != null) client.closeConnection();
    } catch (Exception localException) {}
  }
  
  public void A2_1() throws SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Beruf eingeben: ");
    String Beruf = sc.next();
    sc.close();
    
    String sql = "SELECT Mitnr, Name, Vorname, Beruf FROM Mitarbeiter3 WHERE Beruf='" + Beruf + "'";
    Statement stmt = con.createStatement();
    boolean ok = stmt.execute(sql);
    System.out.println(ok);
    if (ok)
    {
      ResultSet rs = stmt.getResultSet();
      java.sql.ResultSetMetaData rsmd = rs.getMetaData();
      int colNumber = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= colNumber; i++) {
          String columnValue = rs.getString(i);
          System.out.print(columnValue + " ");
        }
        System.out.println("");
      }
    }
  }
  
  public void A2_2() throws SQLException
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("Beruf eingeben: ");
    String Beruf = sc.next();
    


    String sql = "SELECT Mitnr, Name, Vorname, Beruf FROM Mitarbeiter3 WHERE Beruf='" + Beruf + "'";
    Statement stmt = con.createStatement();
    boolean ok = stmt.execute(sql);
    System.out.println(ok);
    if (ok) {
      clientUI.showRows(stmt.getResultSet());
    }
    


    System.out.print("MitarbeiterID eingeben: ");
    String MitID = sc.next();
    sc.close();
    

    sql = "SELECT Mitnr, Name, Vorname, Beruf FROM Mitarbeiter3 WHERE MitNr='" + MitID + "'";
    stmt = con.createStatement();
    ok = stmt.execute(sql);
    System.out.println(ok);
    if (ok) {
      clientUI.showRows(stmt.getResultSet());
    }
  }
  
  public void A2_3() throws SQLException
  {
    String sql = "select Mitarbeiter3.Mitnr, Mitarbeiter3.Vorname, Mitarbeiter3.Name, Mitarbeiter3.Gebdat, Mitarbeiter3.Beruf, Mitarbeiter3.Ort, Mitarbeiter3.Telnr, Projekt3.Proname, MiPro3.Istanteil, MiPro3.Plananteil from Mitarbeiter3 left join MiPro3 on MiPro3.Mitnr = Mitarbeiter3.Mitnr left join Projekt3 on MiPro3.Pronr=Projekt3.Pronr";
    
    Statement stmt = con.createStatement();
    boolean ok = stmt.execute(sql);
    System.out.println(ok);
    if (ok) {
      clientUI.showRows(stmt.getResultSet());
    }
  }
  
  public void A2_4() {
    String sql = "select ProNr from Mitarbeiter3";
    
    try
    {
      Statement stmt = con.createStatement();
      boolean ok = stmt.execute(sql);
      System.out.println(ok);
      if (ok) {
        clientUI.showRows(stmt.getResultSet());
      }
      
    }
    catch (SQLException e)
    {
      System.out.println("Fehlercode: " + e.getErrorCode() + "\n" + e.getMessage());
    }
  }
  
  public void A2_5() {}
  
  public void A4_1_2(String beruf)
    throws SQLException
  {
    String sql = "SELECT * FROM Mitarbeiter3 WHERE Beruf='" + beruf + "'";
    System.out.println(sql);
    Statement stmt = con.createStatement();
    stmt.executeQuery(sql);
    clientUI.showRows(stmt.getResultSet());
  }
  
  public void A4_3()
    throws SQLException
  {
    String beruf = clientUI.readStringLe("Beruf eingeben: ");
    
    PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Mitarbeiter3 WHERE Beruf=?");
    pstmt.setString(1, beruf);
    pstmt.executeQuery();
    
    clientUI.showRows(pstmt.getResultSet());
  }
}

