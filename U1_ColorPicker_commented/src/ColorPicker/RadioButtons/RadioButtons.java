package ColorPicker.RadioButtons;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RadioButtons extends VBox implements Observer {
	// XXX colorModel-Referenz wird nicht verwendet (da alles im Konstruktor definiert wird)
    private ColorModel colorModel;
    private boolean preventUpdate;
    private ToggleGroup group;

    @FXML
    private RadioButton red;
    @FXML
    private RadioButton blue;
    @FXML
    private RadioButton green;
    @FXML
    private RadioButton yellow;
    @FXML
    private RadioButton cyan;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton black;


    public RadioButtons(final ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

        group = new ToggleGroup();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("radioButtons.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //Adding Radios to group
        red.setToggleGroup(group);
        blue.setToggleGroup(group);
        green.setToggleGroup(group);
        yellow.setToggleGroup(group);
        cyan.setToggleGroup(group);
        orange.setToggleGroup(group);
        black.setToggleGroup(group);


        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	// XXX ich verstehe die Verwendung von preventUpdate nicht ganz. Ich habe unten im update alle preventUpdate=true
        	//     auskommentiert, und das Programm läuft immer noch. Wieso braucht es diese Flags? Ich sehe eine potentielle
        	//     Gefahr, dass es einen Zyklus geben kann (ich glaube sie haben auch explizit danach gefragt im Unterricht).
        	//     Ich werde nächste Woche noch daruaf eingehen. Aber vielleicht können Sie mir mitteilen was für ein Problem
        	//     sie angetroffen haben.
            if (group.getSelectedToggle() != null) {
                String selectedColor = group.getSelectedToggle().getUserData().toString();
                colorModel.setColorByName(selectedColor);
            }
        });

    }

    @Override
    public void update(int r, int g, int b) {
        if (r == 255 && g == 0 && b == 0) {
          preventUpdate = true;
            red.setSelected(true);
          preventUpdate = false;
        } else if (r == 0 && g == 0 && b == 255) {
            preventUpdate = true;
            blue.setSelected(true);
            preventUpdate = false;
        } else if (r == 0 && g == 255 && b == 0) {
            preventUpdate = true;
            green.setSelected(true);
            preventUpdate = false;
        } else if (r == 255 && g == 255 && b == 0) {
            preventUpdate = true;
            yellow.setSelected(true);
            preventUpdate = false;
        } else if (r == 0 && g == 255 && b == 255) {
            preventUpdate = true;
            cyan.setSelected(true);
            preventUpdate = false;
        } else if (r == 255 && g == 200 && b == 0) {
            preventUpdate = true;
            orange.setSelected(true);
            preventUpdate = false;
        } else if (r == 0 && g == 0 && b == 0) {
            preventUpdate = true;
            black.setSelected(true);
            preventUpdate = false;
        } else {
            Toggle selected = group.getSelectedToggle();
            if (selected != null)
                selected.setSelected(false);
        }
    }
}
