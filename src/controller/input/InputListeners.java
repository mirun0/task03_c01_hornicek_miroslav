package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class InputListeners implements KeyListener, MouseListener, MouseMotionListener {

    private final InputState inputState;

    public InputListeners(InputState inputState) {
        this.inputState = inputState;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        inputState.mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        inputState.mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        inputState.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        inputState.mouseReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputState.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
