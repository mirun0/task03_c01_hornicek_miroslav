package controller;

import model.Axis;
import model.Cube;
import model.CubicSpline;
import model.Cylinder;
import model.Solid;
import model.Tetrahedron;
import transforms.Cubic;
import transforms.Point3D;
import transforms.Vec3D;
import world.Scene3D;

public class SceneBuilder {
    
    private Scene3D scene;
    private Cube animatedCube;
    
    public Cube getAnimatedCube() {
        return animatedCube;
    }

    public SceneBuilder(Scene3D scene) {
        this.scene = scene;
    }

    public void buildScene() {
        buildAxes();

        Solid tetra = new Tetrahedron();
        tetra.setTransform(
            new Vec3D(5, 5, 5), 
            new Vec3D(0, 0, 0), 
            new Vec3D(1));
        //tetra.setColor(0xff00ff);
        scene.addSolid(tetra);

        animatedCube = new Cube();
        animatedCube.setTransform(
            new Vec3D(0, 0, 0), 
            new Vec3D(0, Math.toRadians(45), 0), 
            new Vec3D(1));
        //animatedCube.setColor(0xffffff);
        scene.addSolid(animatedCube);

        Solid cylinder = new Cylinder(10, 1, 3);
        cylinder.setTransform(new Vec3D(0, 5, 0), new Vec3D(0), new Vec3D(1));
        //cylinder.setColor(0xffff00);
        scene.addSolid(cylinder);

        Solid curve = new CubicSpline(Cubic.BEZIER,
            new Point3D(0, 0, 0),
            new Point3D(1, 2, 1),
            new Point3D(3, 2, 2),
            new Point3D(4, 5, 10),
            80
        );
        //curve.setColor(0xff0ff0);
        scene.addSolid(curve);
    }

    private void buildAxes() {
        Solid axisX = new Axis();
        axisX.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(50));
        axisX.setColor(0xff0000);
        scene.addSolid(axisX);

        Solid axisY = new Axis();
        axisY.setTransform(new Vec3D( 0), new Vec3D(0, 0, Math.toRadians(90)), new Vec3D(50));
        axisY.setColor(0x00ff00);
        scene.addSolid(axisY);

        Solid axisZ = new Axis();
        axisZ.setTransform(new Vec3D(0), new Vec3D(0, -Math.toRadians(90), 0), new Vec3D(50));
        axisZ.setColor(0x0000ff);
        scene.addSolid(axisZ);
    }
}
