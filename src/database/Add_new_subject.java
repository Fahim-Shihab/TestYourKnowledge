package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Add_new_subject extends Database_connector {
    private String subject_name;

    public Add_new_subject(String subject_name) throws SQLException {


        //Sakib's code after Shan's editing
       /* this.subject_name = subject_name;

        PreparedStatement dropTable = conn.prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", subject_name));
        dropTable.execute();

        PreparedStatement createTable = conn.prepareStatement("CREATE TABLE "+subject_name+"(id int NOT NULL AUTO_INCREMENT, Question varchar(5000), Answer varchar(5000), Option1 varchar(5000),Option2 varchar(5000),Option3 varchar(5000), PRIMARY KEY(id))") ;
        createTable.executeUpdate();*/

       //Sakib's code after edited by Sakib adding some new lines
       this.subject_name = subject_name;
        //Connection conn = null;
        //conn = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
       PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+subject_name+"(id int NOT NULL AUTO_INCREMENT, Question varchar(5000), Answer varchar(5000), Option1 varchar(5000),Option2 varchar(5000),Option3 varchar(5000), PRIMARY KEY(id))") ;
//        PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+subject_name+"(id int NOT NULL AUTO_INCREMENT, Name varchar(500), Teacher_id varchar(500), Department varchar(500), User_name varchar(500), Password varchar(500),Subject varchar(500), PRIMARY KEY(id))") ;
        createTable1.executeUpdate();
       String insert ="INSERT INTO subject_table (Subjects) VALUES ('"+subject_name+"')";
       PreparedStatement insert_subject =  conn.prepareStatement(insert);
       insert_subject.executeUpdate();

    }

}
