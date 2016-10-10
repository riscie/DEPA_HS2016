package jdraw.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends AbstractFigure {
    private Ellipse2D.Double oval;

    public Oval(int x, int y, int w, int h) {
        this.oval = new Ellipse2D.Double(x, y, w, h);
    }


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
}
