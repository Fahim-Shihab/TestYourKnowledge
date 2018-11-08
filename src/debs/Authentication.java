import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Authentication extends Application {
    public static Stage classStage= new Stage();
    final static Mediator med= new Mediator();

    @Override
    public void start(Stage stage) {
        //creating label email
        Text text1 = new Text("UserID");

        //creating label password
        Text text2 = new Text("Password");

        //Creating Text Filed for email
        TextField txtID = new TextField();

        //Creating Text Filed for password
        PasswordField txtPass = new PasswordField();

        //Creating Buttons
        Button button1 = new Button("SignUP");
        Button button2 = new Button("Already a member?");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login(txtID.getText(),txtPass.getText(),txtID,txtPass);

            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignIN sin= new SignIN(med);
                sin.start(sin.SignInStage);

                Stage stage = (Stage) button2.getScene().getWindow();
                stage.close();
            }
        });

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(800, 720);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(30);
        gridPane.setHgap(30);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(txtID, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(txtPass, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(button2, 1, 2);

        //Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #00BFFF;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        classStage= stage;
        //Setting title to the Stage
        stage.setTitle("CSS Example");

        //Adding scene to the stage
        stage.setScene(scene);


        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }


    public void login(String newUser, String newPassword, TextField txtNewID, PasswordField txtNewPass) {
        int flag= 0;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");


        if(newUser.equals("")){
            alert.setHeaderText("UserID is Empty");
            alert.showAndWait();
            txtNewID.setText("");
            txtNewPass.setText("");
        }
        else if(newUser.contains(" ")){

            alert.setHeaderText("Space is not allowed in userID");
            alert.showAndWait();

            txtNewID.setText("");
            txtNewPass.setText("");

        }
        else if(newPassword.equals("")){
            alert.setHeaderText("Password is Empty");
            alert.showAndWait();
            txtNewID.setText("");
            txtNewPass.setText("");
        }
        else if(newPassword.contains(" ")){
            alert.setHeaderText("Space is not allowed in PassWord");
            alert.showAndWait();
            txtNewID.setText("");
            txtNewPass.setText("");

        }

        else{
            try {
                FileReader fin = new FileReader("ids.txt");
                BufferedWriter bw = null;
                Scanner sc = new Scanner(fin);

                while (sc.hasNext()) {
                    String str = sc.nextLine();
                    StringTokenizer st = new StringTokenizer(str);
                    String txtid = st.nextToken();
                    String txtpassword = st.nextToken();

                    if (newUser.equals(txtid)) {
                        alert.setHeaderText("UserID already exists");
                        Optional<ButtonType> results = alert.showAndWait();
                        flag = 1;
                    }

                }

                if (flag == 0) {
                    try {
                        bw = new BufferedWriter(new FileWriter("ids.txt", true));
                        bw.write(newUser + " " + newPassword);
                        bw.newLine();
                        bw.flush();

                        alert.setHeaderText("Account Successfully Created");
                        Optional<ButtonType> process = alert.showAndWait();

                    }
                    catch (IOException ex) {
                        //Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally {                       // always close the file
                        if (bw != null) {
                            try {
                                bw.close();
                            } catch (IOException ioe2) {
                                // just ignore it
                            }
                        }
                    }

                }

            } catch (FileNotFoundException ex) {
                System.err.println("exception");
            }
        }

    }
}