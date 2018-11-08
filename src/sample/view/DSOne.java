package sample.view;

import javafx.stage.Stage;
import sample.controller.DSOneController;

public class DSOne extends ViewBase {

    public DSOne(Stage stage) {

        super(stage, "Data Structure questions", "Data_Structure",
                e -> new DSOneController(stage).handleMousePress(e),
                event -> new DSOneController(stage).handleNextButtonPress(event));

    }

}
