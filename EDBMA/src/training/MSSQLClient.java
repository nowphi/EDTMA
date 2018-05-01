package training;


public class MSSQLClient
  extends DBClient
{
  public MSSQLClient(String server, String user, String password)
  {
    super("jdbc:sqlserver://" + server + ";user=" + user + ";password=" + password);
  }
}