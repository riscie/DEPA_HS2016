package patterns.observer.cycle;

import java.awt.*;

public class ColorListener implements Observer {
    private final ColorModel model;

    public ColorListener(ColorModel model) {
        this.model = model;
    }

    @Override
    public void update(Observable source, Object arg) {
        System.out.println("someone changed the scrollbar value => adjust the color model");
        Color current = model.getColor();
        model.setColor(new Color((int)arg, current.getGreen(), current.getBlue()));
    }
}