package training;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DBClient
{
  private String connectionUrl;
  protected Connection con;
  
  public Connection connect() throws SQLException
  {
    con = java.sql.DriverManager.getConnection(connectionUrl);
    return con;
  }
  
  public void closeConnection() throws SQLException
  {
    if (con != null) { con.close();
    }
  }
  

  public Connection getConnection()
  {
    return con;
  }
  
  public DBClient(String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }
  
  public void setConnectionUrl(String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }
  
  public static Properties readProperties(String name) {
    Properties prop = new Properties();
    java.io.InputStream is = DBClient.class.getClassLoader().getResourceAsStream(name);
    try {
      prop.load(is);
      is.close();
    }
    catch (java.io.IOException e) {
      e.printStackTrace();
    }
    return prop;
  }
  

  public void changeDatabase(String dbName)
    throws SQLException
  {
    con.setCatalog("Verkauf");
  }
}