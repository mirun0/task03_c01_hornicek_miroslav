package model.dim3;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public class CubicSpline extends Solid {

    public CubicSpline(Mat4 baseMatrix, Point3D p0, Point3D p1, Point3D p2, Point3D p3, int segments) {
        
        Cubic curve;
        if(baseMatrix.eEquals(Cubic.FERGUSON)) {
            Vec3D v0 = new Vec3D(p0.getX(), p0.getY(), p0.getZ());
            Vec3D v1 = new Vec3D(p1.getX(), p1.getY(), p1.getZ());
            Vec3D v2 = new Vec3D(p2.getX(), p2.getY(), p2.getZ());
            Vec3D v3 = new Vec3D(p3.getX(), p3.getY(), p3.getZ());
            Vec3D t0v = v2.sub(v0);
            Vec3D t1v = v3.sub(v1);
            Point3D t0 = new Point3D(t0v.getX(), t0v.getY(), t0v.getZ());
            Point3D t1 = new Point3D(t1v.getX(), t1v.getY(), t1v.getZ());
            curve = new Cubic(baseMatrix, p1, t0, p2, t1);
        } else {
            curve = new Cubic(baseMatrix, p0, p1, p2, p3);
        }

        for (int i = 0; i <= segments; i++) {
            double t = i / (double) segments;
            Point3D p = curve.compute(t);
            vertexBuffer.add(p);

            if (i > 0) {
                indexBuffer.add(i - 1);
                indexBuffer.add(i);
            }
        }

        vertexBuffer.add(p0);
        vertexBuffer.add(p1);
        vertexBuffer.add(p2);
        vertexBuffer.add(p3);

        indexBuffer.add(vertexBuffer.indexOf(p0));
        indexBuffer.add(vertexBuffer.indexOf(p1));
        indexBuffer.add(vertexBuffer.indexOf(p1));
        indexBuffer.add(vertexBuffer.indexOf(p2));
        indexBuffer.add(vertexBuffer.indexOf(p2));
        indexBuffer.add(vertexBuffer.indexOf(p3));
    }
}