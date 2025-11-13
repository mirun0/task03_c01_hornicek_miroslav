package controller.mode;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import controller.Camera3D;
import controller.input.InputState;

public class CameraMovingHandler implements ModeHandler {

    private Camera3D camera;

    public CameraMovingHandler(Camera3D camera) {
        this.camera = camera;
    }

    @Override
    public void update(InputState input, double deltaTime) {
        if(input.isKeyDown(KeyEvent.VK_W)) camera.forward(deltaTime);
        if(input.isKeyDown(KeyEvent.VK_S)) camera.backward(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_A)) camera.left(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_D)) camera.right(deltaTime);
        if(input.isKeyDown(KeyEvent.VK_SPACE)) camera.up(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_SHIFT)) camera.down(deltaTime); 
        if(input.isButtonDown(MouseEvent.BUTTON1)) camera.look(input.getDeltaX(), input.getDeltaY());

        input.resetMouseDelta();
    }
}
