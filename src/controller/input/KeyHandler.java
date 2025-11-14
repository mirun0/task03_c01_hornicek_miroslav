package controller.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import controller.mode.Action;
import controller.mode.Mode;

public class KeyHandler {

    private ArrayList<Integer> validModeKeys;
    private ArrayList<Integer> validActionKeys;

    private Mode activeMode;
    private boolean modChanged;

    private InputState inputState;

    public KeyHandler(InputState inputState) {
        this.inputState = inputState;
        this.validModeKeys = new ArrayList<>();
        this.validActionKeys = new ArrayList<>();
        this.activeMode = Mode.CAMERA_MOVING;
        this.modChanged = false;

        validModeKeys.addAll(Arrays.asList(KeyEvent.VK_M));
        validActionKeys.addAll(Arrays.asList(KeyEvent.VK_P, KeyEvent.VK_C));
    }

    public void handle() {
        for (int keyCode : inputState.getJustPressedKeys()) {
            if(validActionKeys.contains(keyCode)) {
                switch (keyCode) {
                    case KeyEvent.VK_P: {
                        Action.PROJECTION.toggle();
                        break;
                    }
                    case KeyEvent.VK_C: {
                        Action.CLIPPING.toggle();
                        break;
                    }
                    default:
                        break;
                }
            }

            if(validModeKeys.contains(keyCode)) {
                modChanged = true;
                // switch(keyCode)
            }
        }

        inputState.clear();
    }

    public Mode getActiveMode() {
        return activeMode;
    }

    public boolean isModChanged() {
        return modChanged;
    }

    public void setModChanged(boolean modChanged) {
        this.modChanged = modChanged;
    }
}
