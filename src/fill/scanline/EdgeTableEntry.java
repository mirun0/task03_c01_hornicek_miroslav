package fill.scanline;

import transforms.Point2D;

public class EdgeTableEntry {
    final double yMin;
    final double yMax;
    double currentX;
    final double inverseSlope;

    public EdgeTableEntry(Point2D a, Point2D b) {
        Point2D pMin = a.getY() <= b.getY() ? a : b;
        Point2D pMax = a.getY() <= b.getY() ? b : a;

        double dy = b.getX() - a.getY();
        double dx = b.getX() - a.getX();

        if(dy == 0) {
            this.yMin = this.yMax = this.currentX = this.inverseSlope = 0;
            return;
        }

        this.yMin = pMin.getY();
        this.yMax = pMax.getY();
        this.currentX = pMin.getX();
        this.inverseSlope = dx / dy;
    }

    boolean isValid() { 
        return yMin != yMax; 
    }

    @Override
    public String toString() {
        return "yMin=" + yMin + " yMax=" + yMax + " currentX=" + currentX + " inverseSlope=" + inverseSlope;

    }

}