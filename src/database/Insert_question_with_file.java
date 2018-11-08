package database;
import javax.security.auth.Subject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert_question_with_file extends Database_connector {

    private String subject = null;
    private String question = null;
    private String answer = null;
    private String option1 = null;
    private String option2 = null;
    private String option3 = null;
    public Insert_question_with_file(String subject, String filename) throws SQLException {

        this.subject = subject;

        String delete = "DELETE FROM "+ subject;
        PreparedStatement delete_QmS = conn.prepareStatement(delete);
        delete_QmS.execute();

        String[] fileData = new String[60];
        int lines=1, lineNo=1;
        FileReader fr= null;
        try {
            fr = new FileReader(""+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        Scanner sc = new Scanner(br);

        while(sc.hasNext()){

            fileData[lineNo]=sc.nextLine();

            if(lineNo%5==1)  this.question = fileData[lineNo];
            else if(lineNo%5==2)  this.answer = fileData[lineNo];
            else if(lineNo%5==3)  this.option1 = fileData[lineNo];
            else if(lineNo%5==4)  this.option2 = fileData[lineNo];
            else if(lineNo%5==0) {

                this.option3 = fileData[lineNo];

                String insert = "INSERT INTO " + subject
                        + "(Question, Answer, Option1, Option2, Option3 ) VALUES ('" + question + "','" + answer + "','" + option1 + "','" + option2 + "','" + option3 + "')";
                PreparedStatement insert_QnS = conn.prepareStatement(insert);
                insert_QnS.executeUpdate();

            }
            lineNo++;
        }

        /*
        String insert ="INSERT INTO "+subject+"(Question, Answer, Option1, Option2, Option3 ) VALUES ('"+question+"','"+answer+"','"+option1+"','"+option2+"','"+option3+"')";
        PreparedStatement insert_QnS =  conn.prepareStatement(insert);
        insert_QnS.executeUpdate();*/
    }
}
