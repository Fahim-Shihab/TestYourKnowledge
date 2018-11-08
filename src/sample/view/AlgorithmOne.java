package sample.view;

import javafx.stage.Stage;
import sample.controller.AlgorithmOneController;

public class AlgorithmOne extends ViewBase {
    public AlgorithmOne(Stage stage) {
        super(stage, "Algorithm questions", "Algorithm",
                e -> new AlgorithmOneController(stage).handleMousePress(e),
                event -> new AlgorithmOneController(stage).handleNextButtonPress(event));
    }
}
