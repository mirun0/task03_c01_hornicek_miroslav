package renderer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import controller.mode.Action;
import controller.mode.Mode;

public class UIRenderer {

    private final Graphics g;
    private final Font font;
    private final Font boldFont;
    private final FontMetrics fm;

    public UIRenderer(Graphics g) {
        this.g = g;
        font = new Font("Monospaced", Font.PLAIN, 13);
        boldFont = new Font("Monospaced", Font.BOLD, 13);
        fm = g.getFontMetrics(font);
    }

    public void renderUI(Mode activeMode, int w, int h) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, h - 40, w, 40);
        int x = 5;
        int y = h - 5;
        int spacing = 5;

        String projection = "[P]rojection: Perspective/Orthogonal";
        printAction(Action.PROJECTION, projection, x, y, spacing);

        x += fm.stringWidth(projection) - 1 + spacing;
        String clipping = "[C]lipping: Full/Trivial";
        printAction(Action.CLIPPING, clipping, x, y, spacing);

        x = 5;
        y = h - 25;
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(font);
        g.drawString("Move: WSAD  Look: Mouse dragging", x, y);
    }

    private void printAction(Action action, String fullString, int x, int y, int spacing) {
        String[] parts = fullString.split(" ");
        String desc = parts[0];
        String[] opts = parts[1].split("/");
        String optTrue = opts[0];
        String optFalse = opts[1];

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(desc, x, y);
        x += fm.stringWidth(desc) + spacing;
        if(action.isOn()) {
            g.setColor(Color.YELLOW);
            g.setFont(boldFont);
            g.drawString(optTrue, x, y);
            x += fm.stringWidth(optTrue);
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("/" + optFalse, x, y);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString(optTrue + "/", x, y);
            x += fm.stringWidth(optTrue) + fm.stringWidth("/");            
            g.setColor(Color.YELLOW);
            g.setFont(boldFont);
            g.drawString(optFalse, x, y);
        }
    }
}
