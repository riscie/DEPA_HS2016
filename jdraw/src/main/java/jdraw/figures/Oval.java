package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Oval implements Figure {
    private Ellipse2D.Double oval;

    public Oval(int x, int y, int w, int h) {
        this.oval = new Ellipse2D.Double(x, y, w, h);
        figureListeners = new CopyOnWriteArrayList<>();
    }
1
    private List<FigureListener> figureListeners;

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
        g.setColor(Color.BLACK);
        g.drawOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
    }

    @Override
    public void move(int dx, int dy) {
        if (!(dx == 0 && dy == 0)) {
            oval.x = oval.x + dx;
            oval.y = oval.y + dy;
            notifyObservers();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return oval.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        int height = origin.y - corner.y > 0 ? origin.y - corner.y : corner.y - origin.y;
        int width = origin.x - corner.x > 0 ? origin.x - corner.x : corner.x - origin.x;
        oval.x = origin.x;
        oval.y = origin.y;
        oval.width = width;
        oval.height = height;
        notifyObservers();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
    }

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

    private void notifyObservers() {
        for (FigureListener figureListener : figureListeners) {
            figureListener.figureChanged(new FigureEvent(this));
        }
    }
}
