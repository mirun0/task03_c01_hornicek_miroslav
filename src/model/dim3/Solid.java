package model.dim3;

import java.util.ArrayList;
import java.util.List;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Mat4RotXYZ;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import transforms.Point3D;
import transforms.Vec3D;

public abstract class Solid {
    protected List<Point3D> vertexBuffer;
    protected List<Integer> indexBuffer;

    protected Mat4 transform;
    protected int color;

    public Solid() {
        vertexBuffer = new ArrayList<Point3D>();
        indexBuffer = new ArrayList<Integer>();

        transform = new Mat4Identity();
    }

    public void setPosition(double x, double y, double z) {
        this.transform = new Mat4Transl(x, y, z);
    }

    public void setRotation(double rotX, double rotY, double rotZ, double transZ) {
        this.transform = new Mat4RotXYZ(rotX, rotY, rotZ)
            .mul(new Mat4Transl(0, 0, transZ));
    }
    
    public void setTransform(Vec3D position, Vec3D rotation, Vec3D scale) {
        this.transform = new Mat4Scale(scale.getX(), scale.getY(), scale.getZ())
            .mul(new Mat4RotXYZ(rotation.getX(), rotation.getY(), rotation.getZ()))
            .mul(new Mat4Transl(position));
    }
    
    public Mat4 getTransform() {
        return transform;
    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
