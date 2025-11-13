package controller;

import model.dim3.Axis;
import model.dim3.Cube;
import model.dim3.Solid;
import model.dim3.Tetrahedron;
import transforms.Vec3D;
import world.Scene3D;

public class SceneBuilder {
    
    private Scene3D scene;
    
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
        tetra.setColor(0xff00ff);
        scene.addSolid(tetra);

        Solid cube = new Cube();
        cube.setTransform(
            new Vec3D(0, 0, 0), 
            new Vec3D(0, Math.toRadians(45), 0), 
            new Vec3D(1));
        cube.setColor(0xffffff);
        scene.addSolid(cube);
    }

    private void buildAxes() {
        Solid axisX = new Axis();
        axisX.setTransform(new Vec3D(0), new Vec3D(0),new Vec3D(50));
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
