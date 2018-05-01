package training;

public class usage {

  
  public static void main(String[] args) throws java.sql.SQLException {
    TestClient tc = new TestClient();
    try
    {
      System.out.println("*** Aufgaben Datenbanksystem: ***");
      
  //    tc.A2_1();
  //    tc.A2_2();
  //    tc.A2_3();
  //    tc.A2_4();
      // Zusatz -> leer
 //     tc.A2_5();
      
   //   tc.A4_1_2(" ' or 1=1; --");
    //  tc.A4_1_2(" ' or 1=1; UPDATE Mitarbeiter3 SET Ort = 'Irgendwo' WHERE Beruf='Azubi';-- ");
      //tc.A4_3();
      System.out.println("Ergebnis von Aufgabe A3_1: " + tc.A3_1_2_countStaff("Dipl.-Ing."));
   
      System.out.println("*********************************");
      
    }
    finally
    {

      tc.closeConnection();
    }
  }
}



