package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class InputState {

    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Set<Integer> justPressedKeys = new HashSet<>();
    private final Set<Integer> justReleasedKeys = new HashSet<>();

    private final Set<Integer> pressedButtons = new HashSet<>();
    private final Set<Integer> justPressedButtons = new HashSet<>();
    private final Set<Integer> justReleasedButtons = new HashSet<>();

    private double mouseX, mouseY;
    private double lastMouseX, lastMouseY;
    private double deltaX, deltaY;

    // -------------------- KEYBOARD --------------------

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!pressedKeys.contains(key)) {
            justPressedKeys.add(key);
        }
        pressedKeys.add(key);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys.remove(key);
        justReleasedKeys.add(key);
    }

    public boolean isKeyDown(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public boolean isKeyJustPressed(int keyCode) {
        return justPressedKeys.contains(keyCode);
    }

    public boolean isKeyJustReleased(int keyCode) {
        return justReleasedKeys.contains(keyCode);
    }

    // -------------------- MOUSE -------------------------

    public void mousePressed(MouseEvent e) {
        int btn = e.getButton();
        if (!pressedButtons.contains(btn)) {
            justPressedButtons.add(btn);
        }
        pressedButtons.add(btn);
    }

    public void mouseReleased(MouseEvent e) {
        int btn = e.getButton();
        pressedButtons.remove(btn);
        justReleasedButtons.add(btn);
    }

    public boolean isButtonDown(int button) {
        return pressedButtons.contains(button);
    }

    public boolean isButtonJustPressed(int button) {
        return justPressedButtons.contains(button);
    }

    public boolean isButtonJustReleased(int button) {
        return justReleasedButtons.contains(button);
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

    public void clear() {
        justPressedKeys.clear();
        justReleasedKeys.clear();
        justPressedButtons.clear();
        justReleasedButtons.clear();

        resetMouseDelta();
    }

    public Set<Integer> getPressedKeys() {
        return pressedKeys;
    }

    public Set<Integer> getJustPressedKeys() {
        return justPressedKeys;
    }

    public Set<Integer> getJustReleasedKeys() {
        return justReleasedKeys;
    }

    public Set<Integer> getPressedButtons() {
        return pressedButtons;
    }

    public Set<Integer> getJustPressedButtons() {
        return justPressedButtons;
    }

    public Set<Integer> getJustReleasedButtons() {
        return justReleasedButtons;
    }
}
