package ColorPicker.RadioButtons;

import ColorPicker.ColorModel;
import ColorPicker.Observer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RadioButtons extends VBox implements Observer {
    ColorModel colorModel;

    ToggleGroup group = new ToggleGroup();
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

    boolean preventUpdate;



    public RadioButtons(final ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

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




        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null && !preventUpdate) {
                    String selectedColor = group.getSelectedToggle().getUserData().toString();
                    colorModel.setColorByName(selectedColor);

                }

            }
        });

    }

    @Override
    public void update(int r, int g, int b) {
        if (r == 255 && g == 0 && b == 0){
            preventUpdate = true;
            red.setSelected(true);
            preventUpdate = false;
        }
        if (r == 0 && g == 255 && b == 0){
            preventUpdate = true;
            blue.setSelected(true);
            preventUpdate = false;
        }
        if (r == 0 && g == 255 && b == 0){
            preventUpdate = true;
            green.setSelected(true);
            preventUpdate = false;
        }
        if (r == 255 && g == 255 && b == 0){
            preventUpdate = true;
            yellow.setSelected(true);
            preventUpdate = false;
        }
        if (r == 0 && g == 0 && b == 255){
            preventUpdate = true;
            cyan.setSelected(true);
            preventUpdate = false;
        }
        if (r == 255 && g == 200 && b == 0){
            preventUpdate = true;
            orange.setSelected(true);
            preventUpdate = false;
        }
        if (r == 0 && g == 0 && b == 0){
            preventUpdate = true;
            black.setSelected(true);
            preventUpdate = false;
        }
    }
}
