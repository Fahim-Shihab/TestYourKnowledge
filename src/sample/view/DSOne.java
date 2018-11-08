package sample.view;

import javafx.stage.Stage;
import sample.controller.DSOneController;

import java.sql.SQLException;

public class DSOne extends ViewBase {

    public DSOne(Stage stage) throws SQLException {

        super(stage, "Data Structure questions", "Data_Structure",
                e -> new DSOneController(stage).handleMousePress(e),
                event -> new DSOneController(stage).handleNextButtonPress(event));

    }

}
