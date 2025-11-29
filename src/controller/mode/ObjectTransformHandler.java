package controller.mode;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

import controller.input.InputState;
import model.Axis;
import model.Solid;
import model.Transformable;
import renderer.Renderer3D;
import transforms.Mat3;
import transforms.Point2D;
import transforms.Vec2D;
import transforms.Vec3D;
import utils.MathUtils;
import world.Scene3D;

class KeyPair {
    int transformKey = 0;
    int axisKey = 0;

    void set(int t, int a) {
        transformKey = t;
        axisKey = a;
    }
}

public class ObjectTransformHandler implements ModeHandler {

    private Renderer3D renderer;
    private Scene3D scene;

    private int[] transformKeys = {KeyEvent.VK_S, KeyEvent.VK_R, KeyEvent.VK_G};
    private int[] axisKeys = {KeyEvent.VK_X, KeyEvent.VK_Y, KeyEvent.VK_Z};
    private KeyPair pressedKeyPair;

    private Mat3 oldTransform;
    private boolean changed = false;

    private Solid axisX;
    private Solid axisY;
    private Solid axisZ;

    private Solid activeAxis;

    public ObjectTransformHandler(Renderer3D renderer, Scene3D scene) {
        this.renderer = renderer;
        this.scene = scene;
        this.pressedKeyPair = new KeyPair();
        this.oldTransform = null;

        this.axisX = new Axis(true);
        axisX.setColor(0xe27c8c);
        axisX.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(100));
        this.axisY = new Axis(true);
        axisY.setColor(0xa7d164);
        axisY.setTransform(new Vec3D( 0), new Vec3D(0, 0, Math.toRadians(90)), new Vec3D(100));
        this.axisZ = new Axis(true);
        axisZ.setColor(0x77aae2);
        axisZ.setTransform(new Vec3D(0), new Vec3D(0, -Math.toRadians(90), 0), new Vec3D(100));
    }

    @Override
    public void update(InputState input, double deltaTime) {

        Solid solid = pickSolid(input.getMouseX(), input.getMouseY());
        if(solid != null && solid instanceof Transformable) {
            renderer.setActiveSolid(solid);
        } else {
            renderer.setActiveSolid(null);
        }

        if(pressedKeyPair.transformKey == 0 && pressedKeyPair.axisKey == 0 && 
            input.isButtonJustPressed(MouseEvent.BUTTON1) && solid instanceof Transformable) {
            renderer.setSelectedSolid(solid);
        }

        if(renderer.getSelectedSolid() != null) {
            for (int transformKey : transformKeys) {
                if(input.isKeyJustPressed(transformKey)) {
                    setActiveAxis(0);
                    pressedKeyPair.set(transformKey, 0);
                    changed = true;
                }
            }

            for (int axisKey : axisKeys) {
                if(input.isKeyJustPressed(axisKey) && pressedKeyPair.transformKey != 0) {
                    setActiveAxis(axisKey);
                    pressedKeyPair.axisKey = axisKey;
                    changed = true;
                }
            }
        }

        if(changed) {
            if(renderer.getSelectedSolid() != null && oldTransform != null) {
                renderer.getSelectedSolid().setTransform(oldTransform);
                oldTransform = null;
            }
            changed = false;
        }

        if(pressedKeyPair.transformKey != 0 || pressedKeyPair.axisKey != 0) {
            if(oldTransform == null) {
                oldTransform = renderer.getSelectedSolid().getTransformVectors();
            }
            if(renderer.getSelectedSolidPivot() != null) {
                Point2D solidPivot2D = renderer.getSelectedSolidPivot();
                renderer.setTransformingLine(new Vec2D(input.getMouseX(), input.getMouseY()));
                renderer.getSelectedSolid().setTransform(transform(renderer.getSelectedSolid(),
                MathUtils.length(input.getMouseX(), input.getMouseY(), solidPivot2D.getX(), solidPivot2D.getY())));

                if(input.isButtonJustPressed(MouseEvent.BUTTON1)) {
                    clear();
                } else if(input.isKeyJustPressed(KeyEvent.VK_ESCAPE)) {
                    renderer.getSelectedSolid().setTransform(oldTransform);
                    clear();
                }
            } else {
                renderer.setTransformingLine(null);
            }
        } else {
            renderer.setTransformingLine(null);
        }
    }

    private void setActiveAxis(int axisKey) {
        if(scene.containsSolid(activeAxis)) {
            scene.removeSolid(activeAxis);
        }
        if(axisKey == KeyEvent.VK_X) {
            axisX.setPosition(renderer.getSelectedSolid().getTransformVectors().getRow(0));
            activeAxis = axisX;
        } else if(axisKey == KeyEvent.VK_Y) {
            axisY.setPosition(renderer.getSelectedSolid().getTransformVectors().getRow(0));
            activeAxis = axisY;
        } else if(axisKey == KeyEvent.VK_Z) {
            axisZ.setPosition(renderer.getSelectedSolid().getTransformVectors().getRow(0));
            activeAxis = axisZ;
        }
        if(axisKey != 0) {
            scene.addSolid(activeAxis);
        }
    }

    private Mat3 transform(Solid solid, double value) {

        Vec3D position = solid.getTransformVectors().getRow(0);
        Vec3D rotation = solid.getTransformVectors().getRow(1);
        Vec3D scale = solid.getTransformVectors().getRow(2);

        Vec3D cur = null;
        if(pressedKeyPair.transformKey == KeyEvent.VK_S) {
            cur = scale;
        } else if(pressedKeyPair.transformKey == KeyEvent.VK_R) {
            cur = rotation;
        } else if(pressedKeyPair.transformKey == KeyEvent.VK_G) {
            cur = position;
        }

        if(pressedKeyPair.axisKey == 0) {
            cur = new Vec3D(value * 0.1);
        } else if(pressedKeyPair.axisKey == KeyEvent.VK_X) {
            cur = cur.withX(value * 0.1);
        } else if(pressedKeyPair.axisKey == KeyEvent.VK_Y) {
            cur = cur.withY(value * 0.1);
        } else if(pressedKeyPair.axisKey == KeyEvent.VK_Z) {
            cur = cur.withZ(value * 0.1);
        }

        if(pressedKeyPair.transformKey == KeyEvent.VK_S) {
            return new Mat3(position, rotation, cur);
        } else if(pressedKeyPair.transformKey == KeyEvent.VK_R) {
            return new Mat3(position, cur, scale);
        } else if(pressedKeyPair.transformKey == KeyEvent.VK_G) {
            return new Mat3(cur, rotation, scale);
        }
        return null;
    }

    private Solid pickSolid(double x, double y) {
        Solid closestSolid = null;
        double closestPoint = Double.MAX_VALUE;
        for (Entry<Solid, ArrayList<Point2D>> solid : renderer.getSolidPoints2D().entrySet()) {
            for (Point2D point : solid.getValue()) {
                double l = MathUtils.length(x, y, point.getX(), point.getY());
                if(closestPoint > l) {
                    closestPoint = l;
                    closestSolid = solid.getKey();
                }
            }
        }
        return closestPoint <= 20 ? closestSolid : null;
    }

    public void clear() {
        oldTransform = null;
        renderer.setSelectedSolid(null);
        renderer.setActiveSolid(null);
        renderer.setSelectedSolidPivot(null);
        pressedKeyPair.set(0, 0);
        scene.removeSolid(activeAxis);
    }
    
}
