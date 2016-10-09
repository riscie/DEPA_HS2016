package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Line implements Figure {
    private LineObject line;

    private static class LineObject {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        LineObject(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public void setByPoints(Point o, Point c) {
            this.x1 = o.x;
            this.y1 = o.y;
            this.x2 = c.x;
            this.y2 = c.y;
        }

        public Point getP1() {
            return new Point(x1, y1);
        }

        public Point getP2() {
            return new Point(x2, y2);
        }


    }

    public Line(int x1, int y1, int x2, int y2) {
        line = new LineObject(x1, y1, x2, y2);
        figureListeners = new CopyOnWriteArrayList<>();
    }

    private List<FigureListener> figureListeners;


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }

    @Override
    public void move(int dx, int dy) {
        if (!(dx == 0 && dy == 0)) {
            line.setByPoints(new Point(line.x1 + dx, line.y1 + dy), new Point(line.x2 + dx, line.y2 + dy));
            notifyObservers();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        Point click = new Point(x, y);
        double distanceToLine = click.distance(line.getP1()) + click.distance(line.getP2()) - line.getP1().distance(line.getP2());
        return distanceToLine < 0.04;
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setByPoints(origin, corner);
        notifyObservers();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(line.x1, line.y1, line.x2, line.y2);
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
