package controller.animation;

import controller.SceneBuilder;

public class AnimationHandler {
    
    private SceneBuilder sceneBuilder;
    private int animationAngle;

    public AnimationHandler(SceneBuilder sceneBuilder) {
        this.sceneBuilder = sceneBuilder;
        animationAngle = 0;
    }

    public void handle(double deltaTime) {
        animationAngle += 90 * deltaTime;
        sceneBuilder.getAnimatedCube().setRotation(0, Math.toRadians(animationAngle), 0);
    }
}
