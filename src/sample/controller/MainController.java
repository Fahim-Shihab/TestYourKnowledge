package sample.controller;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.model.SceneName;

public class MainController {
    private Stage stage;

    /** Inject the stage from {@link Main} */
    public MainController(Stage stage) {
        this.stage = stage;
    }

    /*/** Display the first scene */
    public void handleOnPressButton1(MouseEvent event) {

        stage.setScene(Main.getScenes().get(SceneName.CATEGORIES));
    }
    public void handleOnScorePressed(MouseEvent event){
        stage.setScene(Main.getScenes().get(SceneName.Score));
    }
}
