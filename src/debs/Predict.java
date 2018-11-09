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

public class Predict extends Application {
    public static Stage classStage;
    ArrayList<Code> Codes= new ArrayList<>();
    Text txtQuestion, txtScore;
    TextArea txtCode;
    TextField txtAns;
    Button btnSubmit;
    int pos= 0, corr=0;

    private void init(GridPane gridPane) throws IOException {
        txtQuestion= new Text();
        txtScore= new Text();
        txtCode= new TextArea();
        txtAns= new TextField();
        btnSubmit= new Button("Submit");

        txtQuestion.setStyle("-fx-font: 22 arial;");
        txtCode.setStyle("-fx-font: 22 arial;");
        txtQuestion.setFill(Color.WHITE);

        txtScore.setStyle("-fx-font: 16 arial;");
        txtScore.setFill(Color.WHITE);
        txtCode.setEditable(false);

        gridPane.setMinSize(1200, 900);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(50);
        gridPane.setHgap(50);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(txtQuestion, 0, 0);
        gridPane.add(txtCode, 0, 1);
        gridPane.add(txtAns, 0, 2);
        gridPane.add(btnSubmit, 0, 3);
        gridPane.add(txtScore, 0, 4);

        //gridPane.setStyle("-fx-background-color: BEIGE;");
        gridPane.setStyle("-fx-background-color: #005682;");
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

        if(sc.hasNext())
            str= sc.nextLine();

        int cor= 0;
        for(int i=0;i<str.length();i++){
            cor= cor*10+ (str.charAt(i)-'0');
        }

        corr= cor;
        return pos;
    }



    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane= new GridPane();
        init(gridPane);
        classStage= stage;

        Scene scene = new Scene(gridPane);
        stage.setTitle("Predict The Output");
        stage.setScene(scene);
        stage.show();

        pos= getPos();
        CodeArranger codeArranger= new CodeArranger();
        codeArranger.addQuestion();
        codeArranger.setQuestion(pos);
        codeArranger.setScore(pos);

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(pos>=Codes.size()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("You have attempted all the questions available!");
                    alert.showAndWait();
                    return;
                }

                try {
                    pos= codeArranger.Judge(pos);
                } catch (IOException e) {
                    System.out.println("failed");
                }
            }
        });

    }

    private class CodeArranger extends Template{
        @Override
        void addQuestion() throws IOException {
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

        @Override
        void setQuestion(int pos) {
            if(pos>=Codes.size()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("You have attempted all the questions available!");
                alert.showAndWait();
                return;
            }
            Code code= Codes.get(pos);

            txtQuestion.setText(code.getQ());
            txtCode.setText(code.getCode());
        }

        @Override
        void setScore(int pos) {
            txtScore.setText("Attemped: "+pos+"\n\nCorrect: "+corr);
        }

        @Override
        void match(int pos) {
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
                corr++;
                Paint value0 = Paint.valueOf("00FF00");
                txtAns.setStyle("-fx-control-inner-background: #" + value0.toString().substring(2));
                alert.setHeaderText("Correct Answer!\n"+ans);
                alert.showAndWait();
                Paint value1 = Paint.valueOf("FFFFFF");
                txtAns.setStyle("-fx-control-inner-background: #" + value1.toString().substring(2));
                txtAns.setText("");
            }

            else{
                Paint value0 = Paint.valueOf("FF0000");
                txtAns.setStyle("-fx-control-inner-background: #" + value0.toString().substring(2));
                alert.setHeaderText("Wrong Answer! :(\nCorrect answer is: "+corrAns);
                alert.showAndWait();
                Paint value1 = Paint.valueOf("FFFFFF");
                txtAns.setStyle("-fx-control-inner-background: #" + value1.toString().substring(2));
                txtAns.setText("");
            }
        }

        @Override
        void progressUpdate(int pos) throws IOException {
            FileWriter fout= new FileWriter("progress.txt");
            fout.write(pos+"\n"+corr);
            fout.flush();
        }
    }
}
