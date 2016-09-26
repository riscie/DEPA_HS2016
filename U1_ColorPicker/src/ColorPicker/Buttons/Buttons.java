package ColorPicker.Buttons;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Buttons extends VBox implements Observer {
    private ColorModel colorModel;

    @FXML
    private Button darker;

    @FXML
    private Button brighter;



    public Buttons(final ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buttons.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        darker.setOnAction(event -> colorModel.makeDarker());

        brighter.setOnAction(event -> colorModel.makeBrighter());
    }


    @Override
    public void update(int r, int g, int b) {
        Boolean isDarkDisabled = (r == 0 || g == 0 || b == 0);
        darker.setDisable(isDarkDisabled);

        Boolean isBrighDisabled = (r == 255 || g == 255 || b == 255);
        brighter.setDisable(isBrighDisabled);
    }
}
