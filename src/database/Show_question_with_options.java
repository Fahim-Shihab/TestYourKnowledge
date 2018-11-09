package database;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Show_question_with_options extends Show {
    private String subject;
    public String[] question = new String[50];
    public String[] answer = new String[50];
    public String[] option1 = new String[50];
    public String[] option2 = new String[50];
    public String[] option3 = new String[50];
    public int[] id_question_ans_opts = new int[50];
    public int tableShowLength;

    public Show_question_with_options(String subject) throws SQLException {
        this.subject = subject;
        ResultSet rs = statement.executeQuery("SELECT id,Question,Answer,Option1,Option2,Option3 FROM "+subject+"");
        for(int i=0; rs.next(); i++)
        {
            this.id_question_ans_opts[i] = rs.getInt(1);
            this.question[i] = rs.getString(2);
            this.answer[i]  =rs.getString(3);
            this.option1[i] = rs.getString(4);
            this.option2[i] = rs.getString(5);
            this.option3[i] = rs.getString(6);
            tableShowLength++;

        }
    }
}
