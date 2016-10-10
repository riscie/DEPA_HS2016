package jdraw.figures;

import jdraw.framework.DrawContext;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends AbstractDrawTool {


    private Line newLine = null;

    public LineTool(DrawContext context) {
        super(context, "Line", "line.png");
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newLine != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newLine = new Line(x, y, x, y);
        view.getModel().addFigure(newLine);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newLine.setBounds(anchor, new Point(x, y));
        long width = Math.round(new Point(x, y).distance(newLine.getBounds().x, newLine.getBounds().y));
        this.context.showStatusText("length: " + width);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newLine = null;
        anchor = null;
        this.context.showStatusText("Line Mode");
    }


}
