package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;

abstract class AbstractDrawTool implements DrawTool {
    /**
     * the image resource path.
     */
    private static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    DrawView view;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    Point anchor = null;

    private String toolName;

    private String imageName;

    /**
     * Create a new tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    AbstractDrawTool(DrawContext context, String toolName, String imageName) {
        this.context = context;
        this.view = context.getView();
        this.toolName = toolName;
        this.imageName = imageName;
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     *
     * @see jdraw.framework.DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    public String getToolName() {
        return toolName;
    }


    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + imageName));
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public void activate() {
        this.context.showStatusText(toolName + " Mode");
    }
}
