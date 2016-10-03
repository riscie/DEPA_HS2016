package ColorPicker;

import java.util.ArrayList;
import java.util.List;

public class ColorModel implements Observable {
    private List<Observer> observers;
    private int r;
    private int g;
    private int b;

    public ColorModel() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
    	// XXX OK, ich würde direkt remove aufrufen; innerhalb von remove wird das Element (wie bei indexOf) auch gesucht und dann gerade gelöscht.
    	//     Falls das Element nicht in der LIste ist, dann ist das rmeove ein no-op
        int i = observers.indexOf(o);
        if (i > -1) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(r, g, b);
        }
    }

    public void updateRed(int value) {
        r = value;
        notifyObservers();
    }

    public void updateGreen(int value) {
        g = value;
        notifyObservers();
    }

    public void updateBlue(int value) {
        b = value;
        notifyObservers();
    }

    public void setColorByName(String selectedColor) {
        java.awt.Color tmpColor = new java.awt.Color(0, 0, 0);
        switch (selectedColor) {
            case "red":
                tmpColor = java.awt.Color.RED;
                break;
            case "blue":
                tmpColor = java.awt.Color.BLUE;
                break;
            case "green":
                tmpColor = java.awt.Color.GREEN;
                break;
            case "yellow":
                tmpColor = java.awt.Color.YELLOW;
                break;
            case "cyan":
                tmpColor = java.awt.Color.CYAN;
                break;
            case "orange":
                tmpColor = java.awt.Color.ORANGE;
                break;
            case "black":
                tmpColor = java.awt.Color.BLACK;
                break;
        }
        r = tmpColor.getRed();
        g = tmpColor.getGreen();
        b = tmpColor.getBlue();
        notifyObservers();
    }

    public void makeDarker() {
        java.awt.Color tmpColor = new java.awt.Color(r, g, b).darker();
        r = tmpColor.getRed();
        g = tmpColor.getGreen();
        b = tmpColor.getBlue();
        notifyObservers();
    }

    public void makeBrighter() {
        java.awt.Color tmpColor = new java.awt.Color(r, g, b).brighter();
        r = tmpColor.getRed();
        g = tmpColor.getGreen();
        b = tmpColor.getBlue();
        notifyObservers();
    }


}
