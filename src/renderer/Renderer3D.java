package renderer;

import java.awt.Color;

import controller.input.InputState;
import controller.mode.Mode;
import rasterize.Raster;
import renderer.ui.UIRenderer;
import world.Scene3D;

public class Renderer3D {
    private final Raster raster;
    
    private final SceneRenderer sceneRenderer;
    private final UIRenderer uiRenderer;

    public Renderer3D(Raster raster, Scene3D scene, InputState inputState) {
        this.raster = raster;

        this.sceneRenderer = new SceneRenderer(raster, inputState);
        this.uiRenderer = new UIRenderer(raster.getGraphics());
    }

    public void render(Scene3D scene, Mode activeMode, int width, int height) {
        raster.clear(Color.BLACK);
        sceneRenderer.renderScene(scene);

        uiRenderer.renderUI(activeMode, width, height);
    }

    public void setClipping(boolean clipping) {
        sceneRenderer.setClipping(clipping);
    }
}
