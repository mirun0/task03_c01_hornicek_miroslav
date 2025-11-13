package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import controller.input.InputListeners;
import controller.input.InputState;
import controller.input.RenderLoop;
import model.dim3.Axis;
import model.dim3.Cube;
import model.dim3.Solid;
import model.dim3.Tetrahedron;
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

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.camera = new Camera3D(new Vec3D(2, -10, 3), 10, 0.020);
        this.scene = new Scene3D(camera);
        this.renderer = new Renderer3D(panel.getRaster(), scene);
        this.renderLoop = new RenderLoop(this);
        this.sceneBuilder = new SceneBuilder(scene);

        input = new InputState();
        listeners = new InputListeners(input);
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

    private void initScene() {
        this.camera = scene.getCamera();
        sceneBuilder.buildScene();
    }

    public void update(double deltaTime) {

        if(input.isKeyDown(KeyEvent.VK_W)) camera.forward(deltaTime);
        if(input.isKeyDown(KeyEvent.VK_S)) camera.backward(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_A)) camera.left(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_D)) camera.right(deltaTime);
        if(input.isKeyDown(KeyEvent.VK_SPACE)) camera.up(deltaTime); 
        if(input.isKeyDown(KeyEvent.VK_SHIFT)) camera.down(deltaTime); 

        if(input.isButtonDown(MouseEvent.BUTTON1)) camera.look(input.getDeltaX(), input.getDeltaY());

        renderer.render(scene);
        panel.repaint();

        input.resetMouseDelta();
    }

    public void clear() {
        panel.getRaster().clear(Color.BLACK);
        scene.clear();
    }

}
