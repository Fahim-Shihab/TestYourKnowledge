package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.CategoryController;

public class CategoryView implements ViewMaker{

    private Stage stage;

    public CategoryView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        CategoryController categoryController = new CategoryController(stage);

        Button button1 = new Button("Programming Languages");
        button1.setOnMousePressed(e -> categoryController.handleOnPressButton1(e));
        Button button2 = new Button("Data Structure");
        button2.setOnMousePressed(e -> categoryController.handleOnPressButton2(e));
        Button button3 = new Button("Algorithm");
        button3.setOnMousePressed(e -> categoryController.handleOnPressButton3(e));


        VBox vbox = new VBox();
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(100));
        vbox.getChildren().addAll(button1, button2, button3);
        vbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Button BackButton = new Button("Back");
        BackButton.setOnMousePressed(e -> categoryController.handleOnFirstBack(e));

        root.setStyle("-fx-background-color: #696969");

        Button closeButton = new Button("Close");
        closeButton.setOnMousePressed(e -> stage.close());
        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(100));
        bbar.getButtons().addAll(BackButton,closeButton);
        root.setBottom(bbar);

        Scene scene = new Scene(root, 1200, 900);
        return scene;
    }
}
