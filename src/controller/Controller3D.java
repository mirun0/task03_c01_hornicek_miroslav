package controller;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import controller.animation.AnimationHandler;
import controller.input.InputListeners;
import controller.input.InputState;
import controller.input.KeyHandler;
import controller.input.RenderLoop;
import controller.mode.Action;
import controller.mode.CameraMovingHandler;
import controller.mode.Mode;
import controller.mode.ModeHandler;
import controller.mode.ObjectTransformHandler;
import renderer.Renderer3D;
import transforms.Vec3D;
import view.Panel;
import view.Window;
import world.Scene3D;

public class Controller3D {

    private Panel panel;
    private Scene3D scene;
    private InputState input;
    private InputListeners listeners;
    private Renderer3D renderer;
    private RenderLoop renderLoop;
    private Camera3D camera;
    private SceneBuilder sceneBuilder;

    private Map<Mode, ModeHandler> modeHandlers;
    private Mode activeMode;

    private AnimationHandler animationHandler;
    private KeyHandler keyHandler;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.camera = new Camera3D(new Vec3D(2, -10, 3), 10, 0.020);
        this.scene = new Scene3D(camera);
        input = new InputState();
        this.renderer = new Renderer3D(panel.getRaster(), scene, input);
        this.renderLoop = new RenderLoop(this);
        this.sceneBuilder = new SceneBuilder(scene);
        this.modeHandlers = new HashMap<Mode, ModeHandler>();
        this.activeMode = Mode.CAMERA_MOVING;
        initHandlers();

        this.animationHandler = new AnimationHandler(sceneBuilder);
        listeners = new InputListeners(input);
        this.keyHandler = new KeyHandler(input);
        initListeners();
        initScene();

        renderLoop.start();
        panel.repaint();
    }

    private void initListeners() {        
        panel.addMouseListener(listeners);
        panel.addKeyListener(listeners);
        panel.addMouseMotionListener(listeners);
    }

    private void initHandlers() {
        modeHandlers.put(Mode.CAMERA_MOVING, new CameraMovingHandler(camera));
        modeHandlers.put(Mode.OBJECT_TRANSFORM, new ObjectTransformHandler(renderer, scene));
    }

    private void initScene() {
        this.camera = scene.getCamera();
        sceneBuilder.buildScene();
    }

    public void update(double deltaTime) {
        activeMode = keyHandler.getActiveMode();
        for (Mode mode : modeHandlers.keySet()) {
            if(activeMode == mode) {
                ModeHandler modeHandler = modeHandlers.get(mode);
                modeHandler.update(input, deltaTime);
            }
        }
        if(keyHandler.isModChanged()) {
            if(activeMode == Mode.CAMERA_MOVING) {
                ((ObjectTransformHandler)modeHandlers.get(Mode.OBJECT_TRANSFORM)).clear();
            }
            keyHandler.setModChanged(false);
        }

        if(Action.PROJECTION.isOn()) {
            scene.setProjection(true);
        } else {
            scene.setProjection(false);
        }

        if(Action.CLIPPING.isOn()) {
            renderer.setClipping(true);
        } else {
            renderer.setClipping(false);
        }

        animationHandler.handle(deltaTime);
        keyHandler.handle();

        renderer.render(scene, activeMode, Window.WIDTH, Window.HEIGHT);
        panel.repaint();
    }

    public void clear() {
        panel.getRaster().clear(Color.BLACK);
        scene.clear();
    }

}
