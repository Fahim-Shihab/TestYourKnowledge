package sample.view;

import database.Insert_question;
import database.Verify;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login extends Application {

     public static  void main(String[] args){
     launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Log In");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label name_label = new Label("User Name: ");
        GridPane.setConstraints(name_label,0,0);
        TextField getName = new TextField();
        GridPane.setConstraints(getName,1,0);

        Label pass_label = new Label("Password: ");
        GridPane.setConstraints(pass_label,0,1);
        PasswordField getPass = new PasswordField();
        GridPane.setConstraints(getPass,1,1);

        CheckBox Student = new CheckBox("Student");
        CheckBox Teacher = new CheckBox("Teacher");
        GridPane.setConstraints(Student,1,2);
        GridPane.setConstraints(Teacher,1,3);

        String name = getName.getText();
        String pass = getPass.getText();

        Button ok = new Button("Log In");
        ok.setOnAction(e-> {

            try {
                Verify verify = new Verify(getName.getText());

                if( Teacher.isSelected()&&Student.isSelected() )
                {
                    AlertBox.display("Error message","Select Teacher or Sutdent not both");
                }
                else if(getPass.getText().equalsIgnoreCase(verify.getPassword()) && Student.isSelected())
                {
                    System.out.println("Goto main page and save information");
                }
                else if(getPass.getText().equalsIgnoreCase(verify.getPassword()) && Teacher.isSelected())
                {
                    System.out.println("Goto main page and open editing mode");
                }

                window.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                AlertBox.display("WRONG PASSWORD","Type username and password correctly");

            }

        });

        GridPane.setConstraints(ok,1,4);

        grid.getChildren().addAll(name_label,getName,pass_label,getPass,Student,Teacher,ok);
        Scene scene = new Scene(grid, 340,200);
        window.setScene(scene);
        window.show();
    }
}
