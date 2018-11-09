package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Show_score extends Show{

    public String userID;
    public String[] user = new String[100];
    public String[] subject_name = new String[100];
    public String[] marks = new String[100];
    public String tableName;
    public int tablelength=0;

    public Show_score(String userID, String tableName) throws SQLException {

        this.userID = userID;
        this.tableName = tableName;

        ResultSet rs = statement.executeQuery("SELECT UserID, Subject, Marks FROM "+
                tableName+"");
        for(int i=0; rs.next(); i++,tablelength++)
        {
                this.user[i] = rs.getString(1);
                this.subject_name[i] = rs.getString(2);
                this.marks[i] = rs.getString(3);
            }
        }
    }

