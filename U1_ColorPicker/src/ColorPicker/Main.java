package ColorPicker;

import ColorPicker.Buttons.Buttons;
import ColorPicker.ColorBox.ColorBox;
import ColorPicker.RadioButtons.RadioButtons;
import ColorPicker.Sliders.Sliders;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) {
        ColorModel colorModel = new ColorModel();
        Sliders sliders = new Sliders(colorModel);
        ColorBox colorBox = new ColorBox(colorModel);
        RadioButtons radioButtons = new RadioButtons(colorModel);
        Buttons buttons = new Buttons(colorModel);

        //Whole Pane
        FlowPane whole = new FlowPane();
        whole.getChildren().addAll(sliders, colorBox, radioButtons, buttons);

        mainStage.setScene(new Scene(whole));
        mainStage.setTitle("DEPA ColorPicker");
        mainStage.setWidth(300);
        mainStage.setHeight(300);
        //Prevents resizing
        mainStage.setMaxWidth(300);
        mainStage.setMaxHeight(300);
        mainStage.setMinWidth(300);
        mainStage.setMinHeight(300);

        mainStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
