package world;

import java.util.ArrayList;

import controller.Camera3D;
import model.dim3.Solid;
import transforms.Mat4;
import transforms.Mat4OrthoRH;
import transforms.Mat4PerspRH;
import transforms.Mat4Scale;
import view.Window;

public class Scene3D {

    private ArrayList<Solid> solids;
    private Mat4 view;
    private Mat4 projection;

    private Camera3D camera;
    

    public Scene3D(Camera3D camera) {
        solids = new ArrayList<>();
        this.camera = camera;

        projection = new Mat4PerspRH(
            Math.toRadians(60),
            (double) Window.HEIGHT / Window.WIDTH,
            0.1, 100.0
        );

        //projection = new Mat4OrthoRH(15, 15, 0.1, 100);

    }

    public void addSolid(Solid solid) {
        solids.add(solid);
    }

    public int size() {
        return solids.size();
    }

    public ArrayList<Solid> getSolids() {
        return solids;
    }

    public Mat4 getView() {
        return view;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public Camera3D getCamera() {
        return camera;
    }

    public void clear() {
        
    }
}
