import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Score extends Application {
    public static Stage classStage= new Stage();
    public Mediator med;
    public String userID;

    public Score(Mediator med) {
        this.med = med;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Creating a Grid Pane
        GridPane gridPane = new GridPane();
        init(gridPane);


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

    private void init(GridPane gridPane) throws IOException {
        Text text[]= new Text[6];
        TextField scr[]= new TextField[6];

        for(int i=0;i<6;i++){
            text[i]= new Text();
            text[i].setFill(Color.WHITE);
            scr[i]= new TextField();
            scr[i].setEditable(false);
        }


        //Creating Buttons
        Button button1 = new Button("SignIN");
        Button button2 = new Button("Not a Member?");

        gridPane.setMinSize(800, 720);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(30);
        gridPane.setHgap(30);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        for(int i=0;i<6;i++) gridPane.add(text[i], 0, i);
        for(int i=0;i<6;i++) gridPane.add(scr[i], 1, i);

        //gridPane.setStyle("-fx-background-color: BEIGE;");
        gridPane.setStyle("-fx-background-color: #008080;");

        setScore(text,scr);

    }

    void setScore(Text text[], TextField scr[]) throws IOException {
        FileReader fin= new FileReader("Score.txt");
        Scanner sc= new Scanner(fin);
        int i=1;

        userID= med.getUser(); //usage of mediator pattern

        text[0].setText("User");
        scr[0].setText(userID);

        while(sc.hasNext()) {
            String str = sc.nextLine();
            StringTokenizer st = new StringTokenizer(str);
            String txtid = st.nextToken();

            if (userID.equals(txtid)) {
                text[i].setText(st.nextToken());
                scr[i].setText(st.nextToken());
                ++i;
            }
        }

        fin.close();
        sc.close();
    }
}
