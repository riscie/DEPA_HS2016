package ColorPicker.Buttons;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.awt.Color;
import java.io.IOException;

public class Buttons extends VBox implements Observer {
	// XXX Referenz auf model wird nicht verwendet (nur im Konstruktor)
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

        //Action Listeners
        darker.setOnAction(event -> colorModel.makeDarker());
        brighter.setOnAction(event -> colorModel.makeBrighter());
    }


    @Override
    public void update(int r, int g, int b) {
    	// XXX kleine Bemerkung: Die Klammern rund um diesen logischen Ausdruck könnten natürlich weggelassen werden.
    	// XXX wieso Boolean (mit grossem B)? Ich würde hier ein boolen nehmen, und überhaupt würde ich KEINE Variable deklarieren.
    	//     darker.setDisable(r == 0 || g == 0 || b == 0);
    	
    	// XXX die Logik ist hier falsch. Dark ist nur dann disabled wenn alle, r, g und b 0 sind.
        Boolean isDarkDisabled = (r == 0 || g == 0 || b == 0);
        darker.setDisable(isDarkDisabled);

        // XXX OK, das ist die Interpretation von brighter bei FX, aber bei Swing kann eine Farbe noch heller gemacht werden, ausser die Werte von r,g und b sind alle entweder 255 oder 0 (und natürlich nicht alle 0).
        //     Am einfachsten testen Sie es indem sie aus r,g,b eine Farbe erzeugen und prüfen, ob diese gliech ist wie die hellere davon.
        //        Color c = new Color(r,g,b);
        //        boolean isBrighDisabled = c.equals(c.brighter());
        Boolean isBrighDisabled = (r == 255 || g == 255 || b == 255);
        brighter.setDisable(isBrighDisabled);
    }
}
