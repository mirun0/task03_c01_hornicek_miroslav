package model.dim3;

import java.util.ArrayList;
import java.util.List;

import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import transforms.Point3D;
import transforms.Vec3D;

public abstract class Solid {
    protected List<Point3D> vertexBuffer;
    protected List<Integer> indexBuffer;

    protected Vec3D position;
    protected Vec3D rotation;
    protected Vec3D scale;

    protected int color;

    public Solid() {
        vertexBuffer = new ArrayList<Point3D>();
        indexBuffer = new ArrayList<Integer>();

        position = new Vec3D(0);
        rotation = new Vec3D(0);
        scale = new Vec3D(1);
    }

    public void setRotation(double rotX, double rotY, double rotZ) {
        this.rotation = new Vec3D(rotX, rotY, rotZ);
    }

    public void setPosition(double posX, double posY, double posZ) {
        this.position = new Vec3D(posX, posY, posZ);
    }

    public void setScale(double scale) {
        this.rotation = new Vec3D(scale);
    }
    
    public void setTransform(Vec3D position, Vec3D rotation, Vec3D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    
    // TODO: tu by mozno bylo lepsi si uchovavat pouze transform a ne jednotlive vec3
    public Mat4 getTransform() {
        Mat4 transform = new Mat4Scale(scale)
            .mul(new Mat4RotXYZ(rotation.getX(), rotation.getY(), rotation.getZ()))
            .mul(new Mat4Transl(position));
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
