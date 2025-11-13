package rasterize;


public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {
        trivialRasterize(x1, y1, z1, x2, y2, z2, zBuffer);
    }

    private void trivialRasterize(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int steps = Math.max(dx, dy);

        for (int i = 0; i <= steps; i++) {
            double t = (steps == 0) ? 0 : (double) i / steps;
            int x = (int) Math.round(x1 + t * (x2 - x1));
            int y = (int) Math.round(y1 + t * (y2 - y1));
            double z = z1 + t * (z2 - z1);

            if (x >= 0 && x < raster.getWidth() && y >= 0 && y < raster.getHeight()) {
                if (z < zBuffer[x][y]) {
                    zBuffer[x][y] = z;
                    raster.setPixel(x, y, color);
                }
            }
        }
    }
}
