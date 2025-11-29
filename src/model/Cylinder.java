package model;

import transforms.Point3D;

public class Cylinder extends Solid implements Transformable {
    
    public Cylinder(int segments, double radius, double height) {
        if (segments < 3) {
            System.err.println("Cylinder needs at least 3 segments");
            return;
        }

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double x = radius * Math.cos(angle); // polar -> kartezska ss
            double y = radius * Math.sin(angle);
            vertexBuffer.add(new Point3D(x, y, 0));
        }

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            vertexBuffer.add(new Point3D(x, y, height));
        }

        for (int i = 0; i < segments; i++) {
            int next = (i + 1) % segments;
            indexBuffer.add(i);
            indexBuffer.add(next);
        }

        for (int i = 0; i < segments; i++) {
            int top = i + segments;
            int nextTop = ((i + 1) % segments) + segments;
            indexBuffer.add(top);
            indexBuffer.add(nextTop);
        }

        for (int i = 0; i < segments; i++) {
            int bottom = i;
            int top = i + segments;
            indexBuffer.add(bottom);
            indexBuffer.add(top);
        }

        calcPivot();
    }
}
