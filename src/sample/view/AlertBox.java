package sample.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void display(String title, String message ){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(200);
        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);
        Button close = new Button("OK");
        close.setAlignment(Pos.BOTTOM_CENTER);
        close.setOnAction(e-> window.close());
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}