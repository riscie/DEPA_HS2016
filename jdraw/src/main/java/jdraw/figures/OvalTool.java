package jdraw.figures;

import jdraw.framework.DrawContext;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractDrawTool {
    private Oval newOval = null;

    public OvalTool(DrawContext context) {
        super(context, "Oval", "oval.png");
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newOval != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newOval = new Oval(x, y, 0, 0);
        view.getModel().addFigure(newOval);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newOval.setBounds(anchor, new Point(x, y));
        long width = Math.round(new Point(x, y).distance(newOval.getBounds().x, newOval.getBounds().y));
        this.context.showStatusText("length: " + width);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newOval = null;
        anchor = null;
        this.context.showStatusText("Oval Mode");
    }
}
