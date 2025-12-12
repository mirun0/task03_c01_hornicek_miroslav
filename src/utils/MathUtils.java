package utils;

public class MathUtils {
    
    public static float length(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static double length(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double length = Math.sqrt(dx * dx + dy * dy);
        return length;
    }

    public static double lengthDir(double x1, double x2) {
        return x2 - x1;
    }
}
