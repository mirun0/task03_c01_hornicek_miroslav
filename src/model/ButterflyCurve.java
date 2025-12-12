package model;

import java.util.ArrayList;

import transforms.Point3D;

public class ButterflyCurve extends Solid implements Transformable {

    private int segments;

    // https://en.wikipedia.org/wiki/Butterfly_curve_(transcendental)
    public ButterflyCurve(int segments) {
        this.segments = segments;
        calcPoints();
    }

    public void plusSegments() {
        this.segments++;
        vertexBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
        calcPoints();
    }

    public void minusSegments() {
        this.segments--;
        vertexBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
        calcPoints();
    }

    public void calcPoints() {
        System.out.println(segments);
        double maxT = 2 * Math.PI; // ta rovnice by fungovala az do 12*pi t, tohle je jen jedna "cesta"
        double dt = maxT / (double) segments;

        for (int i = 0; i <= segments; i++) {
            double t = i * dt;

            double base = Math.exp(Math.cos(t)) - 2 * Math.cos(4 * t) - Math.pow(Math.sin(t / 12), 5);
            double x = Math.sin(t) * base;
            double y = Math.cos(t) * base;

            vertexBuffer.add(new Point3D(x, y, 0));

            if (i > 0) {
                indexBuffer.add(i - 1);
                indexBuffer.add(i);
            }
        }

        indexBuffer.add(segments);
        indexBuffer.add(0);

        calcPivot();
    }
}
