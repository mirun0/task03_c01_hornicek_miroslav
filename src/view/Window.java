package view;

import javax.swing.*;


public class Window extends JFrame {

    private final Panel panel;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("task 03");
        setResizable(false);
        setVisible(true);

        panel = new Panel(WIDTH, HEIGHT);
        add(panel);
        pack();
        setLocationRelativeTo(null);

        panel.setFocusable(true);
        panel.grabFocus();
    }
    
    public Panel getPanel() {
        return panel;
    }
}
