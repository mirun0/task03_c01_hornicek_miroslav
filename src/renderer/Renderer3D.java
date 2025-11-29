package renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import controller.input.InputState;
import controller.mode.Mode;
import model.Solid;
import rasterize.Raster;
import renderer.ui.UIRenderer;
import transforms.Point2D;
import transforms.Vec2D;
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
        raster.clear(Color.decode("#3f3f3f"));
        sceneRenderer.renderScene(scene, activeMode);

        uiRenderer.renderUI(activeMode, width, height);
    }

    public void setClipping(boolean clipping) {
        sceneRenderer.setClipping(clipping);
    }

    public Solid getSelectedSolid() {
        return sceneRenderer.getSelectedSolid();
    }

    public Solid getActiveSolid() {
        return sceneRenderer.getActiveSolid();
    }

    public void setActiveSolid(Solid s) { 
        sceneRenderer.setActiveSolid(s);
    }

    public void setSelectedSolid(Solid s) { 
        sceneRenderer.setSelectedSolid(s);
    }

    public void setTransformingLine(Vec2D mousePos) {
        sceneRenderer.setTransformingLine(mousePos);
    }

    public Point2D getSelectedSolidPivot() {
        return sceneRenderer.getSelectedSolidPivot();
    }

    public void setSelectedSolidPivot(Point2D selectedSolidPivot) {
        sceneRenderer.setSelectedSolidPivot(selectedSolidPivot);
    }

    public HashMap<Solid, ArrayList<Point2D>> getSolidPoints2D() {
        return sceneRenderer.getSolidPoints2D();
    }
}
