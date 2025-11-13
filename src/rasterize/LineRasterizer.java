package rasterize;

public abstract class LineRasterizer {
    protected Raster raster;
    protected int color;

    public void setColor(int color) {
        this.color = color;
    }

    public LineRasterizer(Raster raster) {
        this.raster = raster;
        this.color = 1;
    }

    public void rasterize(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {

    }

}
