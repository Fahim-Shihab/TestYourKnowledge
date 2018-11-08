package sample.view;
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

    private Stage stage;

    /** Must inject a stage */
    public MainView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        MainController controller = new MainController(stage);

        Button start_test = new Button("Test");
        Button login = new Button("Log In");
        Button signup = new Button("Sign Up");
        start_test.setOnMousePressed(e -> controller.handleOnPressButton1(e));

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
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.getChildren().addAll(start_test, login, signup);
        vbox.setAlignment(Pos.CENTER);


        root.setCenter(vbox);

        Button closeButton = new Button("Close");
        closeButton.setOnMousePressed(e -> stage.close());

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10,10,10,10));
        bbar.getButtons().add(closeButton);
        root.setBottom(bbar);

        Scene scene = new Scene(root, 1200, 900);

        return scene;
    }

}
