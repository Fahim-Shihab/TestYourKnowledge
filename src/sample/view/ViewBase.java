package sample.view;

import database.Add_new_subject;
import database.Database_connector;
import database.Show_score;
import debs.Mediator;
import debs.SignIN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import database.Show_question_with_options;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ViewBase extends Database_connector implements ViewMaker {
    private Stage stage;
    private String labelText;
    private String subject;
    private EventHandler<? super MouseEvent> handlerBack;
    private EventHandler<? super MouseEvent> handlerNext;
    private int score=0;
    private boolean doNotInsert=false;

    Label time = new Label();

    public ViewBase (Stage stage, String labelText, String subject,
                    EventHandler<? super MouseEvent> handlerBack,
                     EventHandler<? super MouseEvent> handlerNext) throws SQLException{
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }

        if (handlerBack == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }
        if (handlerNext == null){
            throw new IllegalArgumentException("Handler cannot be null");
        }

        this.stage = stage;
        this.labelText = labelText;
        this.subject = subject;
        this.handlerBack = handlerBack;
        this.handlerNext = handlerNext;

        try {
            PreparedStatement createTable1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+
                            SignIN.UserID+
                    " (id int NOT NULL AUTO_INCREMENT," +
                    " UserID varchar(500), " +
                    "Subject varchar(500), " +
                    "Marks varchar(500)," +" PRIMARY KEY(id))") ;
            createTable1.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Scene getScene() throws SQLException {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        Label label = new Label(labelText);
        label.setFont(new Font(32));
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.BASELINE_LEFT);

//        System.out.println("the........."+subject+"............");


        /*Thread getScene = new Thread(){
            public void run() {
                try {
                    Calendar calendar = new GregorianCalendar();
                    int secend = calendar.get(Calendar.SECOND);
                    int minutes = calendar.get(Calendar.MINUTE);
                    int hours = calendar.get(Calendar.HOUR);
                    int ampm = calendar.get(Calendar.AM_PM);
                    time.setText(hours+":"+minutes+":"+secend);
                    time.setAlignment(Pos.TOP_RIGHT);
                    sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };*/

        root.add(label,2,0,48,1);

        ScrollPane scrollPane = new ScrollPane();

        Show_question_with_options show = new Show_question_with_options(subject);

        int length = show.tableShowLength;

       root.add(time,10,0);
       Text[] question_number;
        Text[] Question;
        Question = new Text[50];
        CheckBox[] answer = new CheckBox[50];
        CheckBox[] Options1 = new CheckBox[50];
        CheckBox[] Options2 = new CheckBox[50];
        CheckBox[] Options3 = new CheckBox[50];

        for(int i=0; i<length; i++){
            Question[i] = new Text((i+1)+". "+show.question[i]);
        }
        for(int i = 0; i<length; i++)
        {
            answer[i] = new CheckBox(show.answer[i]);
            answer[i].setMnemonicParsing(false);

            Options1[i] = new CheckBox(show.option1[i]);
            Options1[i].setMnemonicParsing(false);

            Options2[i] = new CheckBox(show.option2[i]);
            Options2[i].setMnemonicParsing(false);

            Options3[i] = new CheckBox(show.option3[i]);
            Options3[i].setMnemonicParsing(false);

        }


        for(int i=0; i<length; i++) {

            Random rand = new Random();
            int random = rand.nextInt(4);

            int p = 4 * (i + 1) + 1 + 2 * i;
            int q = 4 * (i + 1) + 2 + 2 * i;
            int r = 4 * (i + 1) + 3 + 2 * i;
            int s = 4 * (i + 1) + 4 + 2 * i;

            if(random == 1) {
                int temp = p;
                p = q;
                q = temp;
            }
            else if(random == 2){
                int temp = p;
                p = r;
                r = temp;
            }
            else if(random == 3){
                int temp = p;
                p = s;
                s = temp;
            }

            root.add(Question[i], 2, 4 * (i + 1) + 2 * i);
            root.add(answer[i], 2, p);
            root.add(Options1[i], 2, q);
            root.add(Options2[i], 2, r);
            root.add(Options3[i], 2, s);
        }

        Button backButton = new Button("Back");
        backButton.setOnMousePressed(handlerBack);

        Button nextButton = new Button("Next");
        //nextButton.setOnMousePressed(handlerNext);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int selected = 0, score=0;
                for(int i=0; i<length; i++){
                    if(answer[i].isSelected())  {
                        selected++;
                        score++;
                    }
                    if(Options1[i].isSelected() || Options2[i].isSelected() || Options3[i].isSelected())
                        selected++;
                }
                System.out.println("Selected optins: "+selected+" Score = "+score);
                if(selected>10) {
                    Text warning = new Text("You can't select more than one answer for a single question");
                    root.add(warning,54,62);
                }
                    else{

                    try {
                        Show_score showScore = new Show_score(SignIN.UserID, SignIN.UserID);

                        if(showScore.tablelength!=0){
                        for (int i = 0; i < showScore.tablelength; i++) {
                            if (showScore.user[i].equals(SignIN.UserID) &&
                                    showScore.subject_name[i].equals(subject)) {
                                String update = "UPDATE "+SignIN.UserID+ " SET Marks = '" + score +
                                        "' WHERE UserID = '" + showScore.user[i] +
                                        "' and Subject = '" + showScore.subject_name[i] + "'";
                                PreparedStatement insert_QnS = null;

                                insert_QnS = conn.prepareStatement(update);
                                insert_QnS.executeUpdate();
                                doNotInsert = true;
                            }
                        }}

                        if (!doNotInsert) {
                            String insert = "INSERT INTO " +SignIN.UserID+
                                    " (UserID, Subject, Marks ) VALUES ('" +
                                    SignIN.UserID + "','" + subject + "','" + score + "')";
                            PreparedStatement insert_QnS = null;

                            insert_QnS = conn.prepareStatement(insert);
                            insert_QnS.executeUpdate();
                        }
                        } catch(SQLException e1){
                            e1.printStackTrace();
                        }


                    nextButton.setText("Finish");
                    nextButton.setOnMousePressed(handlerNext);
                }
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnMousePressed(e -> stage.close());

        ButtonBar bbar = new ButtonBar();
        bbar.getButtons().addAll(backButton, nextButton, closeButton);
        root.add(bbar,54,64);

        scrollPane.setContent(root);

        return new Scene(scrollPane,1200,900);
    }
}
