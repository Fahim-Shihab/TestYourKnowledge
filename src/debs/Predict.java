import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Predict extends Application {
    ArrayList<Code> Codes= new ArrayList<>();
    Text txtQuestion;
    TextArea txtCode;
    TextField txtAns;
    Button btnSubmit;

    private void init(GridPane gridPane) throws IOException {
        txtQuestion= new Text();
        txtCode= new TextArea();
        txtAns= new TextField();
        btnSubmit= new Button("Submit");

        txtCode.setEditable(false);

        gridPane.setMinSize(800, 720);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(30);
        gridPane.setHgap(30);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(txtQuestion, 0, 0);
        gridPane.add(txtCode, 0, 1);
        gridPane.add(txtAns, 0, 2);
        gridPane.add(btnSubmit, 0, 3);

        gridPane.setStyle("-fx-background-color: BEIGE;");
        //gridPane.setStyle("-fx-background-color: #008080;");
    }

    void ProgressUpdate() throws IOException {
        FileWriter fout= new FileWriter("progress.txt");
        fout.write(pos+"");
        fout.flush();
    }

    int getPos() throws FileNotFoundException {
        FileReader fin= new FileReader("progress.txt");
        Scanner sc= new Scanner(fin);

        String str="";
        if(sc.hasNext())
            str= sc.nextLine();

        int pos= 0, base= 0;
        for(int i=0;i<str.length();i++){
            pos= pos*10+ (str.charAt(i)-'0');
        }

        return pos;
    }

    void addQuesntion() throws IOException {
        FileReader fin= new FileReader("codes.txt");
        Scanner sc= new Scanner(fin);

        String q="", code="",ans="";
        int fl=0;
        while(sc.hasNext()){
            String str= sc.nextLine();

            if(str.equals("-->")){
                Codes.add(new Code(ans,q,code));
                q=""; code=""; ans="";
                fl=0;
                continue;
            }

            if(fl==0){
                ans+=str;
                fl=1;
            }
            else if(fl==1) {
                q+= str;
                fl=2;
            }
            else
                code+=str+"\n";
        }
        fin.close();
        sc.close();
    }

    void setQuesion(int pos) {
        Code code= Codes.get(pos);

        txtQuestion.setText(code.getQ());
        txtCode.setText(code.getCode());
    }

    boolean match(int pos){
        Code code= Codes.get(pos);
        String corrAns= code.getAns();
        String ans= txtAns.getText().trim();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");

        if(ans.equals("")){
            alert.setHeaderText("You can't leave the ans field empty");
            alert.showAndWait();
        }

        if(ans.equals(corrAns)){
            Paint value0 = Paint.valueOf("00FF00");
            txtAns.setStyle("-fx-control-inner-background: #" + value0.toString().substring(2));
            alert.setHeaderText("Correct Answer!\n"+ans);
            alert.showAndWait();
            Paint value1 = Paint.valueOf("FFFFFF");
            txtAns.setStyle("-fx-control-inner-background: #" + value1.toString().substring(2));
            return true;
        }

        else{
            Paint value0 = Paint.valueOf("FF0000");
            txtAns.setStyle("-fx-control-inner-background: #" + value0.toString().substring(2));
            alert.setHeaderText("Wrong Answer! :(\nCorrect answer is: "+corrAns);
            alert.showAndWait();
            Paint value1 = Paint.valueOf("FFFFFF");
            txtAns.setStyle("-fx-control-inner-background: #" + value1.toString().substring(2));
            return false;
        }
    }

    int pos= 0;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane= new GridPane();
        init(gridPane);

        Scene scene = new Scene(gridPane);
        stage.setTitle("Predict The Output");
        stage.setScene(scene);
        stage.show();

        addQuesntion();

        pos= getPos();
        setQuesion(pos);
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                match(pos);
                setQuesion(++pos);
                txtAns.setText("");
                try {
                    ProgressUpdate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

