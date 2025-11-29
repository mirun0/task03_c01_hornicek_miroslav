package renderer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import controller.mode.Action;
import controller.mode.Mode;

public class UIRenderer {

    private final Graphics g;
    private final Font font;
    private final Font boldFont;
    private final FontMetrics fm;

    private String projection = "Projection: Perspective/Orthogonal (P)";
    private String clipping = "Clipping: Full/Trivial (C)";
    private String selection = "Transform: On/Off (T)";
    private String camMoveHelp = "Move: WSAD  Look: Mouse dragging";
    private String objSelHelp = "Mouse: Select object  S: Scale  R: Rotate  G: Translation  X: Axis  Y: Axis  Z: Axis";

    public UIRenderer(Graphics g) {
        this.g = g;
        font = new Font("Calibri", Font.PLAIN, 13);
        boldFont = new Font("Calibri", Font.BOLD, 13);
        fm = g.getFontMetrics(font);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    public void renderUI(Mode activeMode, int w, int h) {
        g.setColor(Color.decode("#181818"));
        g.fillRoundRect(5, h - 40 - 5, w - 10, 40, 10, 10);

        int x = 5 + 5;
        int y = h - 5 - 7;
        int spacing = 5;

        int wProjection = fm.stringWidth(projection);
        int wClipping = fm.stringWidth(clipping);
        int wSelection = fm.stringWidth(selection);

        int spaceW = spacing * fm.stringWidth(" ");

        int total = wProjection + spaceW + wClipping + spaceW + wSelection;
        x = (w - total) / 2;

        printAction(Action.PROJECTION, projection, x, y, spacing);
        x += fm.stringWidth(projection) - 1 + (spacing * fm.stringWidth(" "));
        printAction(Action.CLIPPING, clipping, x, y, spacing);
        x += fm.stringWidth(clipping) - 1 + (spacing * fm.stringWidth(" "));
        printMode(Mode.OBJECT_TRANSFORM, activeMode, selection, x, y, spacing);
        x += fm.stringWidth(selection) - 1 + (spacing * fm.stringWidth(" "));

        y = h - 25 - 4;
        printModeHelp(x, y, w, activeMode);

        x = 5;
        y = h - 25;
    }

    private void printModeHelp(int x, int y, int w, Mode activeMode) {
        if(activeMode == Mode.CAMERA_MOVING) {
            x = (w - fm.stringWidth(camMoveHelp)) / 2;
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(camMoveHelp, x, y);
        } else if(activeMode == Mode.OBJECT_TRANSFORM) {
            x = (w - fm.stringWidth(objSelHelp)) / 2;
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(objSelHelp, x, y);
        }
    }

    private void printMode(Mode mode, Mode activeMode, String fullString, int x, int y, int spacing) {
        String[] parts = fullString.split(" ");
        String desc = parts[0];
        String[] opts = parts[1].split("/");
        String optTrue = opts[0];
        String optFalse = opts[1];

        String key = parts[2];

        g.setColor(Color.decode("#e1e1e1"));
        g.setFont(font);
        g.drawString(desc, x, y);
        x += fm.stringWidth(desc) + spacing;
        if(mode == activeMode) {
            g.setColor(Color.decode("#FFA500"));
            g.setFont(boldFont);
            g.drawString(optTrue, x, y);
            x += fm.stringWidth(optTrue);
            g.setColor(Color.decode("#e1e1e1"));
            g.setFont(font);
            String o = "/" + optFalse;
            g.drawString(o, x, y);
            x += fm.stringWidth(o);
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(" " + key, x, y);
        } else {
            g.setColor(Color.decode("#e1e1e1"));
            g.setFont(font);
            g.drawString(optTrue + "/", x, y);
            x += fm.stringWidth(optTrue) + fm.stringWidth("/");            
            g.setColor(Color.decode("#FFA500"));
            g.setFont(boldFont);
            g.drawString(optFalse, x, y);
            x += fm.stringWidth(optFalse);
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(" " + key, x, y);
        }
    }

    private void printAction(Action action, String fullString, int x, int y, int spacing) {
        String[] parts = fullString.split(" ");
        String desc = parts[0];
        String[] opts = parts[1].split("/");
        String optTrue = opts[0];
        String optFalse = opts[1];

        String key = parts[2];

        g.setColor(Color.decode("#e1e1e1"));
        g.setFont(font);
        g.drawString(desc, x, y);
        x += fm.stringWidth(desc) + spacing;
        if(action.isOn()) {
            g.setColor(Color.decode("#FFA500"));
            g.setFont(boldFont);
            g.drawString(optTrue, x, y);
            x += fm.stringWidth(optTrue);
            g.setColor(Color.decode("#e1e1e1"));
            g.setFont(font);
            String o = "/" + optFalse;
            g.drawString(o, x, y);
            x += fm.stringWidth(o);
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(" " + key, x, y);
        } else {
            g.setColor(Color.decode("#e1e1e1"));
            g.setFont(font);
            g.drawString(optTrue + "/", x, y);
            x += fm.stringWidth(optTrue) + fm.stringWidth("/");            
            g.setColor(Color.decode("#FFA500"));
            g.setFont(boldFont);
            g.drawString(optFalse, x, y);
            x += fm.stringWidth(optFalse);
            g.setColor(Color.decode("#8e8e8e"));
            g.setFont(font);
            g.drawString(" " + key, x, y);
        }
    }
}
