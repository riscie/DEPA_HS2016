package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract class AbstractFigure implements Figure {

    AbstractFigure() {
        figureListeners = new CopyOnWriteArrayList<>();
    }

    private List<FigureListener> figureListeners;


    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figureListeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListeners.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    void notifyObservers() {
        for (FigureListener figureListener : figureListeners) {
            figureListener.figureChanged(new FigureEvent(this));
        }
    }
}
