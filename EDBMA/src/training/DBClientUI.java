package training;

import java.sql.ResultSet;
import java.util.Scanner;

public class DBClientUI
{
  public DBClientUI() {}
  
  private Scanner sc = new Scanner(System.in);
  
  public void showRows(ResultSet rs) throws java.sql.SQLException {
    java.sql.ResultSetMetaData rsmd = rs.getMetaData();
    int colNumber = rsmd.getColumnCount();
    while (rs.next()) {
      for (int i = 1; i <= colNumber; i++)
      {
        String columnValue = rs.getString(i);
        
        System.out.print(columnValue + " ");
      }
      System.out.println("");
    }
  }
  
  @SuppressWarnings("resource")
public String readString(String label)
  {
    Scanner sc = new Scanner(System.in);
    System.out.print(label);
    String input = sc.next();
    
    return input;
  }
  
  @SuppressWarnings("resource")
public String readStringLe(String label)
  {
   
	Scanner sc = new Scanner(System.in);
    System.out.print(label);
    String input = sc.nextLine();
    
    return input;
  }
  
  public int readInt(String label) throws java.util.InputMismatchException
  {
    System.out.print(label);
    int input = sc.nextInt();
    
    return input;
  }
}


