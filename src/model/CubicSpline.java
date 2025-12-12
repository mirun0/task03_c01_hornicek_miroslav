package model;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public class CubicSpline extends Solid implements Transformable {

    public CubicSpline(Mat4 baseMatrix, Point3D p0, Point3D p1, Point3D p2, Point3D p3, int segments) {
        
        Cubic curve = null;
        if(baseMatrix.eEquals(Cubic.BEZIER)) {
            curve = new Cubic(baseMatrix, p0, p1, p2, p3);
        } else if(baseMatrix.eEquals(Cubic.FERGUSON)) {
            curve = new Cubic(baseMatrix, p0, p1, p2, p3);
        } else if(baseMatrix.eEquals(Cubic.COONS)) {
            curve = new Cubic(baseMatrix, p0, p1, p2, p3);
        }

        if(curve != null) {
            for (int i = 0; i <= segments; i++) {
                double t = i / (double) segments;
                Point3D p = curve.compute(t);
                vertexBuffer.add(p);

                if (i > 0) {
                    indexBuffer.add(i - 1);
                    indexBuffer.add(i);
                }
            }
        }

        /*vertexBuffer.add(p0);
        vertexBuffer.add(p1);
        vertexBuffer.add(p2);
        vertexBuffer.add(p3);

        indexBuffer.add(vertexBuffer.indexOf(p0));
        indexBuffer.add(vertexBuffer.indexOf(p1));
        indexBuffer.add(vertexBuffer.indexOf(p1));
        indexBuffer.add(vertexBuffer.indexOf(p2));
        indexBuffer.add(vertexBuffer.indexOf(p2));
        indexBuffer.add(vertexBuffer.indexOf(p3));*/
        
        calcPivot();
    }
}