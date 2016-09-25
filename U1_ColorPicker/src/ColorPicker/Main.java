package ColorPicker;

import ColorPicker.Buttons.Buttons;
import ColorPicker.ColorBox.ColorBox;
import ColorPicker.Menu.Menu;
import ColorPicker.RadioButtons.RadioButtons;
import ColorPicker.Sliders.Sliders;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) {
        //Constants
        int WIDTH = 300;
        int HEIGHT = 320;

        //ColorModel
        ColorModel colorModel = new ColorModel();

        //UI Components
        Menu menu = new Menu(colorModel);
        Sliders sliders = new Sliders(colorModel);
        ColorBox colorBox = new ColorBox(colorModel);
        RadioButtons radioButtons = new RadioButtons(colorModel);
        Buttons buttons = new Buttons(colorModel);

        //Whole Pane
        FlowPane whole = new FlowPane();
        whole.getChildren().addAll(menu, sliders, colorBox, radioButtons, buttons);

        mainStage.setScene(new Scene(whole));
        mainStage.setTitle("DEPA ColorPicker");
        mainStage.setWidth(WIDTH);
        mainStage.setHeight(HEIGHT);
        //Prevents resizing
        mainStage.setMaxWidth(WIDTH);
        mainStage.setMaxHeight(HEIGHT);
        mainStage.setMinWidth(WIDTH);
        mainStage.setMinHeight(HEIGHT);

        mainStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
