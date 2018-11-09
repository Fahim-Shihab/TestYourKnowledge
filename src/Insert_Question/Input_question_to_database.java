package Insert_Question;

import database.Insert_question;
import debs.Mediator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;
import sample.view.AlertBox;

import java.awt.*;
import java.sql.SQLException;

public class Input_question_to_database extends Application  {

    private static String Question = null;
    private static String Answer = null;
    private static String option1 = null;
    private static String option2 = null;
    private static String option3 = null;
    Insert_question insert_question;

    Button add;

   // public static  void main(String[] args){
      //  launch(args);
    //}

    public Input_question_to_database() throws Exception {
        Stage  window = new Stage();
        //start(window);
    }

    public void start(Stage window)throws Exception{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        window.setTitle("Test Your Knowledge");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Combo box for subjects
        ComboBox subjects = new ComboBox();


        /*Show_subject_table show_subject_table = new Show_subject_table();
        int array_sizer = show_subject_table.table_size;
        String[] subject_array1 = new String[array_sizer];
        subject_array1 = show_subject_table.subject_array;
        String subject_array2[] = new String[array_sizer];
        for(int i=0; i < array_sizer; i++)
        {
            subject_array2[i] = subject_array1[i];
        }*/




        Subject_list list = new Subject_list();
        int size = list.getArray_size();
        String[] subject_array1 = new String[size];

        System.out.println(list.getArray_size());
        for(int i=0; list.hasNext(); i++ )
        {
            subject_array1[i] = list.next();
            //System.out.println( subject_array1[i]);
        }

        subjects.getItems().addAll(
               // "Physics",

                //"programming_languages",
                //"data_structure",
                //"algorithm"
               subject_array1
        );



        subjects.setPromptText("Choose your subject");
        GridPane.setConstraints(subjects,0,0);

        //laveling Question
        Label question_label = new Label("Qus:");
        GridPane.setConstraints(question_label,0,1);

        TextField type_question = new TextField();
        type_question.setPromptText("Put '?' or '-' mark at the end of the question");
        GridPane.setConstraints( type_question,1,1);

        //Labeling answer
        Label answer_level = new Label("Ans:");
        GridPane.setConstraints(answer_level,0,2);

        TextField type_answer = new TextField();
        GridPane.setConstraints(type_answer,1,2);


        //Labeling Options
        Label option1_level = new Label("Option 1:");
        GridPane.setConstraints(option1_level,0,3);

        TextField type_option1 = new TextField();
        GridPane.setConstraints(type_option1,1,3);

        Label option2_level = new Label("Option 2:");
        GridPane.setConstraints(option2_level,0,4);

        TextField type_option2 = new TextField();
        GridPane.setConstraints(type_option2,1,4);


        Label option3_level = new Label("Option 3:");
        GridPane.setConstraints(option3_level,0,5);

        TextField type_option3 = new TextField();
        GridPane.setConstraints(type_option3,1,5);


        add = new Button();
        add.setText("ADD");

        //Label question_lavel = new Label("Type your question here.");
        //question_lavel.setAlignment(Pos.TOP_CENTER);

        //add button action is defined here


        add.setOnAction(e-> {
            try {


                if ((String) subjects.getValue() == null) {
                    AlertBox.display("Error", "Choose your subject");
                } else if ( type_question.getText().equalsIgnoreCase("")  || type_question.getText().equalsIgnoreCase(" ") || type_question.getText()== null) {
                    AlertBox.display("Error", "Type a question.");
                } else if (type_answer.getText().equalsIgnoreCase("") || type_answer.getText().equalsIgnoreCase(" ")) {
                    AlertBox.display("Error", "Type an answer.");
                } else if (type_option1.getText().equalsIgnoreCase("") || type_option1.getText().equalsIgnoreCase(" ") || type_option2.getText().equalsIgnoreCase("") || type_option2.getText().equalsIgnoreCase(" ")|| type_option3.getText().equalsIgnoreCase("") || type_option3.getText().equalsIgnoreCase(" ")) {
                    AlertBox.display("Error", "Type an option");
                } else {


                    insert_question = new Insert_question((String) subjects.getValue(), type_question.getText(), type_answer.getText(), type_option1.getText(), type_option2.getText(), type_option3.getText());


                    window.close();
                }
                } catch(SQLException e1){
                    e1.printStackTrace();
                }

        });

        Mediator med = new Mediator();
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Main main = new Main(med);
                try {
                    main.start(window);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


GridPane.setConstraints(add,1,6);
GridPane.setConstraints(back,2,6);
        //StackPane layout = new StackPane();
        grid.getChildren().addAll(subjects, back, question_label,type_question,answer_level,type_answer,option1_level,type_option1,option2_level,type_option2,option3_level,type_option3,add);
        Scene scene = new Scene(grid, 350 , 300/*screenSize.getWidth()-100,screenSize.getHeight()-100*/);
        window.setScene(scene);
        window.show();



    }
}
