package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class InputState {
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Set<Integer> pressedButtons = new HashSet<>();
    private double mouseX, mouseY;
    private double lastMouseX, lastMouseY;
    private double deltaX, deltaY;

    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public boolean isKeyDown(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public void mousePressed(MouseEvent e) {
        pressedButtons.add(e.getButton());
    }

    public void mouseReleased(MouseEvent e) {
        pressedButtons.remove(e.getButton());
    }

    public boolean isButtonDown(int button) {
        return pressedButtons.contains(button);
    }

    public void mouseMoved(MouseEvent e) {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        mouseX = e.getX();
        mouseY = e.getY();
        deltaX = mouseX - lastMouseX;
        deltaY = mouseY - lastMouseY;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void resetMouseDelta() {
        deltaX = 0;
        deltaY = 0;
    }
}
