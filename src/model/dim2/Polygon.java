package model.dim2;
import java.util.ArrayList;

import transforms.Point2D;

public class Polygon {
    
    private ArrayList<Point2D> points;
    private boolean fill;

    public Polygon(ArrayList<Point2D> points, boolean fill) {
        this.points = points;
        this.fill = fill;
    }

    public int size() {
        return points.size();
    }

    public void addPoint(Point2D point) {
        points.add(point);
    }
    
    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public boolean getFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void clear() {
        points.clear();
    }

}
