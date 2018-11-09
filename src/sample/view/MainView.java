package sample.view;
import Insert_Question.Input_question_to_database;
import database.Insert_question;
import debs.Mediator;
import debs.Score;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.controller.MainController;

public class MainView implements ViewMaker {

    private static Stage stage;
    Mediator med;

    /** Must inject a stage */
    public MainView(Stage stage, Mediator med) {
        this.stage = stage;
        this.med= med;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        MainController controller = new MainController(stage);

        Button start_test = new Button("Test");
        Button score_button = new Button("Score");

        start_test.setOnMousePressed(e -> controller.handleOnPressButton1(e));

        score_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Score scr = new Score(med);
                try {
                    scr.start(Score.classStage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        Button addNewQuestion = new Button("Add Question");
        addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Input_question_to_database insert_question_to_database = new Input_question_to_database();
                    insert_question_to_database.start(stage);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            });

        BorderPane root = new BorderPane();
        //root.setLeft(vbox);
        Label label = new Label("Test your knowledge in various Computer\n        Science & Engineering fields\n");
        label.setFont(new Font(32));

        label.setPrefHeight(120.0);
        label.prefWidthProperty().bind(root.widthProperty());
        label.setStyle("-fx-border-style: dotted; -fx-border-width: 1 0 0 0;-fx-font-weight: bold");
        label.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(label);

        start_test.setAlignment(Pos.CENTER);
        VBox vbox = new VBox();
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(start_test, score_button, addNewQuestion);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        Button closeButton = new Button("Close");
        closeButton.setOnMousePressed(e -> stage.close());

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10, 10, 10, 10));
        bbar.getButtons().add(closeButton);
        root.setBottom(bbar);

        Scene scene = new Scene(root, 1200, 900);

        return scene;
    }
}


