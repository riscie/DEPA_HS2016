package ColorPicker.Menu;

import ColorPicker.ColorModel;
import ColorPicker.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Menu extends VBox implements Observer {
    private ColorModel colorModel;
    private boolean preventUpdate;
    
    // XXX vielleicht wäre es einfacher ohne ToggleGroup zu arbeiten.
    //     Sie könnten im update einfach alle RadioMenuItems aktualisieren, für red z.B. mit
    //    		red.setSelected(r == 255 && g == 0 && b == 0);
    
    private ToggleGroup group;

    @FXML
    private MenuItem exit;
    @FXML
    private RadioMenuItem red;
    @FXML
    private RadioMenuItem blue;
    @FXML
    private RadioMenuItem green;
    @FXML
    private RadioMenuItem yellow;
    @FXML
    private RadioMenuItem cyan;
    @FXML
    private RadioMenuItem orange;
    @FXML
    private RadioMenuItem black;


    public Menu(ColorModel colorModel) {
        this.colorModel = colorModel;
        colorModel.registerObserver(this);

        group = new ToggleGroup();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        exit.setOnAction(t -> System.exit(0));

        //Adding Radios to group
        red.setToggleGroup(group);
        blue.setToggleGroup(group);
        green.setToggleGroup(group);
        yellow.setToggleGroup(group);
        cyan.setToggleGroup(group);
        orange.setToggleGroup(group);
        black.setToggleGroup(group);


        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null && !preventUpdate) {
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
