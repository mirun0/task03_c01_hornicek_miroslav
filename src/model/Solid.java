package model;

import java.util.ArrayList;
import java.util.List;

import transforms.Mat3;
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

    protected Point3D pivot;

    public Point3D getPivot() {
        return pivot;
    }

    protected int color;

    public Solid() {
        vertexBuffer = new ArrayList<Point3D>();
        indexBuffer = new ArrayList<Integer>();

        color = 0x8e8f91;
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

    public void setPosition(Vec3D position) {
        this.position = position;
    }

    public void setScale(double scale) {
        this.scale = new Vec3D(scale);
    }

    public void setScaleX(double scale) {
        this.scale = new Vec3D(scale, this.scale.getY(), this.scale.getZ());
    }
    
    public void setTransform(Vec3D position, Vec3D rotation, Vec3D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void setTransform(Mat3 transform) {
        this.position = transform.getRow(0);
        this.rotation = transform.getRow(1);
        this.scale = transform.getRow(2);
    }

    public Mat3 getTransformVectors() {
        return new Mat3(position, rotation, scale);
    }
    
    public Mat4 getTransform() {

        if(pivot == null) {
            return new Mat4RotXYZ(rotation.getX(), rotation.getY(), rotation.getZ())
            .mul(new Mat4Scale(scale))
            .mul(new Mat4Transl(position));
        }

        Mat4 pivotTranslate  = new Mat4Transl(pivot.getX(), pivot.getY(), pivot.getZ());
        Mat4 backPivotTranslate  = new Mat4Transl(-pivot.getX(), -pivot.getY(), -pivot.getZ());

        Mat4 R = new Mat4RotXYZ(rotation.getX(), rotation.getY(), rotation.getZ());
        Mat4 S = new Mat4Scale(scale);
        Mat4 T = new Mat4Transl(position);

        return backPivotTranslate.mul(R).mul(S).mul(pivotTranslate).mul(T);
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

    public void calcPivot() {
        double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY, maxZ = Double.NEGATIVE_INFINITY;

        for (Point3D v : vertexBuffer) {
            minX = Math.min(minX, v.getX());
            minY = Math.min(minY, v.getY());
            minZ = Math.min(minZ, v.getZ());
            maxX = Math.max(maxX, v.getX());
            maxY = Math.max(maxY, v.getY());
            maxZ = Math.max(maxZ, v.getZ());
        }

        this.pivot = new Point3D((minX + maxX) / 2, (minY + maxY) / 2, (minZ + maxZ) / 2);
    }
}
