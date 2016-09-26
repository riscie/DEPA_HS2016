package ColorPicker.Sliders;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Sliders extends VBox implements Observer {
    private ColorModel colorModel;

    //Red
    @FXML
    private Slider r;
    @FXML
    private TextField rDec;
    @FXML
    private TextField rHex;

    //Green
    @FXML
    private Slider g;
    @FXML
    private TextField gDec;
    @FXML
    private TextField gHex;

    //Blue
    @FXML
    private Slider b;
    @FXML
    private TextField bDec;
    @FXML
    private TextField bHex;



    public Sliders(final ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sliders.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Listen for red value changes
        r.valueProperty().addListener((observable, oldValue, newValue) -> colorModel.updateRed(newValue.intValue()));

        // Listen for red value changes
        g.valueProperty().addListener((observable, oldValue, newValue) -> colorModel.updateGreen(newValue.intValue()));

        // Listen for red value changes
        b.valueProperty().addListener((observable, oldValue, newValue) -> colorModel.updateBlue(newValue.intValue()));
    }


    @Override
    public void update(int r, int g, int b) {
        //Sliders
        this.r.setValue(r);
        this.g.setValue(g);
        this.b.setValue(b);

        //Decimal Values
        rDec.setText(String.valueOf(r));
        gDec.setText(String.valueOf(g));
        bDec.setText(String.valueOf(b));

        //Hex Values
        rHex.setText(intToHexString(r));
        gHex.setText(intToHexString(g));
        bHex.setText(intToHexString(b));
    }

    private static String intToHexString(int n) {
        return "0x"+Integer.toHexString(n);
    }
}
