package controller.mode;

import controller.input.InputState;

public interface ModeHandler {
    void update(InputState input, double deltaTime);
}
