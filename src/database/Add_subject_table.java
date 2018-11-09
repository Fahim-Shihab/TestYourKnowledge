package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_subject_table extends Database_connector {


    public Add_subject_table() throws SQLException {

        PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS subject_table(id int NOT NULL AUTO_INCREMENT, Subjects varchar(5000), PRIMARY KEY(id))") ;
        createTable1.executeUpdate();

    }
}
