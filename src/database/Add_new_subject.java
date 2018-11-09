package database;

import java.sql.*;
import java.util.Scanner;

public class Add_new_subject extends Database_connector {
    private String subject_name;

    public Add_new_subject(String subject_name) throws SQLException {


       this.subject_name = subject_name;
        //Connection conn = null;
        //conn = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
        PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+subject_name+"(id int NOT NULL AUTO_INCREMENT, Question varchar(5000), Answer varchar(5000), Option1 varchar(5000),Option2 varchar(5000),Option3 varchar(5000), PRIMARY KEY(id))") ;
       // PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+subject_name+"(id int NOT NULL AUTO_INCREMENT, Name varchar(500), Teacher_id varchar(500), Department varchar(500), User_name varchar(500), Password varchar(500),Subject varchar(500), PRIMARY KEY(id))") ;
        createTable1.executeUpdate();
        Statement statement = conn.createStatement();


        ResultSet rs = statement.executeQuery("SELECT Subjects FROM subject_table");
        int i = 0;
        int flag = 0;
        while (rs.next())
        {
            System.out.println(rs.getString(1));
            if(subject_name.equals(rs.getString(1)))
            flag = 1;
            //System.out.println(rs.getString(1));
            //  System.out.println(this.subject_array[i-1]);
        }

        if(flag == 0) {
            String insert = "INSERT INTO subject_table (Subjects) VALUES ('" + subject_name + "')";
            PreparedStatement insert_subject = conn.prepareStatement(insert);
            insert_subject.executeUpdate();
        }
    }

}
