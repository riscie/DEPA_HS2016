package ColorPicker;

// XXX hier stellt sich natürlich die Frage, ob man Observable nun wirklich als Interface definieren will oder ob man diese MEthoden nicht gleich im COlorModel definiert.

public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
