package sample;

import database.Add_new_subject;
import database.Insert_question;
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
        stage.setTitle("Test Your Knowledge");
        stage.show();
    }

    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public static void main(String[] args) throws SQLException{

        launch(args);

    }
}
