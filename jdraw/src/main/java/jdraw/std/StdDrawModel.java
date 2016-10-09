/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author Matthias Langhard
 */
public class StdDrawModel implements DrawModel, FigureListener {
    private List<Figure> figures;
    private List<DrawModelListener> listeners;


    public StdDrawModel() {
        figures = new LinkedList<>();
        listeners = new CopyOnWriteArrayList<>();
    }


    @Override
    public void addFigure(Figure f) {
        if (!figures.contains(f)) {
            if (figures.add(f)) {
                f.addFigureListener(this);
                notifyObservers(f, DrawModelEvent.Type.FIGURE_ADDED);
            }
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return figures;
    }

    @Override
    public void removeFigure(Figure f) {
        //remove the figures listeners
        f.removeFigureListener(this);
        if (figures.remove(f)) {
            notifyObservers(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    @Override
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure figure, int index) {
        //Can not set an index > than last slot in list
        if (index > figures.size() - 1)
            throw new IndexOutOfBoundsException();

        //Can not change the index for a figure which we don't know about
        if (!figures.contains(figure))
            throw new IllegalArgumentException();

        figures.remove(figure);
        figures.add(index, figure);
        notifyObservers(figure, DrawModelEvent.Type.DRAWING_CHANGED);
        System.out.println(index);
    }

    @Override
    public void removeAllFigures() {
        for (Figure figure : figures) {
            figure.removeFigureListener(this);
            notifyObservers(figure, DrawModelEvent.Type.DRAWING_CLEARED);
        }
        figures.clear();
    }

    private void notifyObservers(Figure f, DrawModelEvent.Type type) {
        for (DrawModelListener listener : listeners) {
            listener.modelChanged(new DrawModelEvent(this, f, type));
        }
    }

    @Override
    public void figureChanged(FigureEvent e) {
        notifyObservers(e.getFigure(), DrawModelEvent.Type.FIGURE_ADDED);
    }
}
