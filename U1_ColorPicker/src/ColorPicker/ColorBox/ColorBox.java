package ColorPicker.ColorBox;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class ColorBox extends VBox implements Observer {
    private ColorModel colorModel;

    @FXML
    private Rectangle colorBox;

    public ColorBox(ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("colorBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setColor(int r, int g, int b) {
        colorBox.setFill(Color.rgb(r, g, b));
    }

    @Override
    public void update(int r, int g, int b) {
        this.setColor(r, g, b);
    }
}
