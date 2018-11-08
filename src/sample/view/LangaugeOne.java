package sample.view;

import javafx.stage.Stage;
import sample.controller.LanguageOneController;

public class LangaugeOne extends ViewBase {
    /** Must inject a stage */
    public LangaugeOne(Stage stage) {

        super(stage, "Programming languages questions","Language",
                e -> new LanguageOneController(stage).handleMousePress(e),
                event -> new LanguageOneController(stage).handleNextButtonPress(event));

    }
}
