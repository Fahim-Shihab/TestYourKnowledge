import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SignIN extends Application {
    public static Scene scene;
    public static Stage classStage = new Stage();
    public Mediator med;
    public UserRepository userRepository;

    public SignIN(Mediator med) {
        this.med = med;
        userRepository= new UserRepository();
    }

    public static void main(String args[]) {
        launch(args);
    }

    public static Scene getScens(){
        return scene;
    }

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
        Button button1 = new Button("SignIN");
        Button button2 = new Button("Not a Member?");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean success= signIn(txtID.getText(),txtPass.getText());

                if(success){
                    med.setUser(txtID.getText());
                    Score score= new Score(med);
                    try {
                        score.start(Score.classStage);
                        Stage stage = (Stage) button1.getScene().getWindow();
                        stage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{

                }

            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Authentication auth= new Authentication();
                auth.start(auth.classStage);

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
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        scene = new Scene(gridPane);

        classStage = stage;
        //Setting title to the Stage
        stage.setTitle("CSS Example");

        //Adding scene to the stage
        stage.setScene(scene);


        //Displaying the contents of the stage
        stage.show();
    }

    public boolean signIn(String ID, String Pass) {
        int flag=0;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");

        if(ID.equals("")){
            alert.setHeaderText("UserID is Empty");
            alert.showAndWait();

        }
        else if(Pass.equals("")){
            alert.setHeaderText("PassWord is Empty");
            alert.showAndWait();
        }
        else{
            try {
                userRepository= new UserRepository();

                FileReader fin= new FileReader("ids.txt");
                Scanner sc= new Scanner(fin);

                while(sc.hasNext()){
                    String str= sc.nextLine();
                    StringTokenizer st= new StringTokenizer(str);
                    String txtid = st.nextToken();
                    String pass = st.nextToken();

                    userRepository.users.add(new User(txtid,pass));
                }

                Iterator itr= userRepository.getIterator();

                for( ;itr.hasNext();){
                    User user= (User) itr.next();

                    String txtid = user.userID;

                    if(ID.equals(txtid)){
                        if(Pass.equals(user.pass)){
                            flag=1;

                            //currentID=ID;
                            alert.setHeaderText("Logged in as "+ID);
                            alert.showAndWait();
                            fin.close();

                            return true;
                        }
                    }

                }

                /*while(sc.hasNext()){
                    String str= sc.nextLine();
                    StringTokenizer st= new StringTokenizer(str);
                    String txtid = st.nextToken();

                    if(ID.equals(txtid)){
                        if(Pass.equals(st.nextToken())){
                            flag=1;

                            //currentID=ID;
                            alert.setHeaderText("Logged in as "+ID);
                            alert.showAndWait();
                            fin.close();

                            return true;

                        }

                    }


                }*/
                fin.close();
                if(flag==0){
                    alert.setHeaderText("UserID ans Password didn't match");
                    alert.showAndWait();
                }

            } catch (FileNotFoundException ex) {
                System.err.println("IOex");
            } catch (IOException ex) {

            }
        }

        return false;
    }
}
