package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_connector {
    public static final String USERNAME ="root";
    public   static final String PASSWORD ="Mediocre52*7";
    public static final String CONNECTION_STRING ="jdbc:mysql://localhost:3306/testyourknowledge?verifyServerCertificate=false&useSSL=false";
    Connection conn = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
    public Database_connector() throws SQLException {
        /*Scanner scan = new Scanner(System.in);
        //String test = scan.nextLine();
        //String concate = "my "+test+"";
       // System.out.println(concate);
        Connection conn = null;
        try{

            Statement statement = conn.createStatement();
            System.out.println("connection established");


            //String study_subject1 = scan.nextLine();
            String study_subject = "physics";//scan.nextLine();

            //String question = scan.nextLine();
            //String answer = scan.nextLine();

            //String insert ="INSERT INTO "+study_subject+"(Question, Answer) VALUES ('"+question+"','"+answer+"')";
            //PreparedStatement insert_QnS =  conn.prepareStatement(insert);
            //insert_QnS.executeUpdate();

            /*ResultSet rs = statement.executeQuery("SELECT Question FROM physics");
            String[] array_question = new String[5];
            int i = 0;
            while (rs.next())
            {
                array_question[i] = rs.getString(1);
                i++;
                System.out.println(rs.getString(1));
                System.out.println(array_question[i-1]);
            }
            System.out.println(study_subject);
        } catch (SQLException e){
            System.err.println(e);
        }*/

    }
}
