package controller;

import model.Axis;
import model.ButterflyCurve;
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

        // CURVES

        Solid butterfly = new ButterflyCurve(100);
        butterfly.setTransform(new Vec3D(0, 10, 5), new Vec3D(Math.toRadians(90), 0, 0), new Vec3D(1));
        scene.addSolid(butterfly);

        Solid bezier = new CubicSpline(Cubic.BEZIER,
            new Point3D(0, 0, 0),
            new Point3D(1, 2, 1),
            new Point3D(3, 2, 2),
            new Point3D(4, 5, 10),
            80
        );
        bezier.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(1));
        scene.addSolid(bezier);

        Solid ferguson = new CubicSpline(Cubic.FERGUSON,
            new Point3D(5, 0, 0),
            new Point3D(6, 2, 1),
            new Point3D(8, 2, 2),
            new Point3D(9, 5, 10),
            80
        );
        ferguson.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(1));
        scene.addSolid(ferguson);

        Solid coons = new CubicSpline(Cubic.COONS,
            new Point3D(10, 0, 0),
            new Point3D(11, 2, 1),
            new Point3D(13, 2, 2),
            new Point3D(14, 5, 10),
            80
        );
        coons.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(1));
        scene.addSolid(coons);
    }

    private void buildAxes() {
        Solid axisX = new Axis(false);
        axisX.setTransform(new Vec3D(0), new Vec3D(0), new Vec3D(50));
        axisX.setColor(0xf63652); // e27c8c
        scene.addSolid(axisX);

        Solid axisY = new Axis(false);
        axisY.setTransform(new Vec3D( 0), new Vec3D(0, 0, Math.toRadians(90)), new Vec3D(50));
        axisY.setColor(0x70a41c); // a7d164
        scene.addSolid(axisY);

        Solid axisZ = new Axis(false);
        axisZ.setTransform(new Vec3D(0), new Vec3D(0, -Math.toRadians(90), 0), new Vec3D(50));
        axisZ.setColor(0x2f84e3); // 77aae2
        scene.addSolid(axisZ);
    }
}
