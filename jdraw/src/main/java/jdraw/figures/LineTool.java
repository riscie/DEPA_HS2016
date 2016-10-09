package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool implements DrawTool {
    private static final String IMAGES = "/images/";
    private DrawContext context;
    private DrawView view;
    private Line newLine = null;
    private Point anchor = null;


    public LineTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    @Override
    public void activate() {
        this.context.showStatusText("Line Mode");
    }

    @Override
    public void deactivate() {
        this.context.showStatusText("");
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

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "line.png"));
    }

    @Override
    public String getName() {
        return "Line";
    }
}
