package sample;

import database.Add_new_subject;
import database.Insert_question;
import database.Insert_question_with_file;
import debs.Mediator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.model.SceneName;
import sample.view.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import database.Show_question_with_options;
import debs.Score;
public class Main extends Application {
    public static Stage clsStage= new Stage();
    private static Map<SceneName, Scene> scenes = new HashMap<>();

    public Main(Mediator med) {
        this.med = med;
    }

    public Mediator med;
    @Override
    public void start(Stage stage) throws SQLException {
        clsStage= stage;

        // Create and store all scenes up front
        scenes.put(SceneName.MAIN, new MainView(stage,med).getScene());
        scenes.put(SceneName.CATEGORIES, new CategoryView(stage).getScene());
        scenes.put(SceneName.LANGUAGE1, new LangaugeOne(stage).getScene());
        scenes.put(SceneName.DS1, new DSOne(stage).getScene());
        scenes.put(SceneName.ALGO1, new AlgorithmOne(stage).getScene());

        // Start with the main scene
        stage.setScene(scenes.get(SceneName.MAIN));
        //stage.setMinHeight(900);
        //stage.setMinWidth(1200);
        stage.setMinWidth(1200);
        stage.setMaxHeight(900);
        stage.setTitle("Test Your Knowledge");
        stage.show();
    }

    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public static void main(String[] args) throws SQLException{

        Add_new_subject add_new_subject1 = new Add_new_subject("Algorithm");
        Show_question_with_options show_question_with_options = new Show_question_with_options("Algorithm");
        if(show_question_with_options.tableShowLength==0) {
            Insert_question_with_file insert_question_with_file = new Insert_question_with_file("Algorithm", "Algorithm");
        }

        Add_new_subject add_new_subject2 = new Add_new_subject("Data_Structure");

        Show_question_with_options show_question_with_options1 = new Show_question_with_options("Data_Structure");
        if(show_question_with_options1.tableShowLength==0) {
            Insert_question_with_file insert_question_with_file1 = new Insert_question_with_file("Data_Structure", "Data_Structure");
        }

        Add_new_subject add_new_subject3 = new Add_new_subject("Language");

        Show_question_with_options show_question_with_options2 = new Show_question_with_options("Language");
        if(show_question_with_options2.tableShowLength==0) {
            Insert_question_with_file insert_question_with_file3 = new Insert_question_with_file("Language", "Language");
        }
        launch(args);

    }
}
