package renderer;

import java.awt.Color;

import rasterize.Raster;
import world.Scene3D;

public class Renderer3D {
    private final Raster raster;
    private final SceneRenderer sceneRenderer;

    public Renderer3D(Raster raster, Scene3D scene) {
        this.raster = raster;
        this.sceneRenderer = new SceneRenderer(raster);
    }

    public void render(Scene3D scene) {
        raster.clear(Color.BLACK);
        sceneRenderer.renderScene(scene);
    }

    public void changeClipping() {
        sceneRenderer.changeClipping();
    }
}
