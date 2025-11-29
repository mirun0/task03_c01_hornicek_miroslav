package rasterize;


public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {
        // trivialRasterize(x1, y1, z1, x2, y2, z2, zBuffer);
        wuLine(x1, y1, z1, x2, y2, z2, zBuffer);
    }

    private void plot(double x, double y, double z, double[][] zBuffer, double alpha) {
        int ix = (int) x;
        int iy = (int) y;

        if (ix < 0 || iy < 0 || ix >= raster.getWidth() || iy >= raster.getHeight())
            return;

        if (z >= zBuffer[ix][iy])
            return;

        zBuffer[ix][iy] = z;

        int dst = raster.getPixel(ix, iy).get();
        int dr = (dst >> 16) & 0xFF;
        int dg = (dst >> 8)  & 0xFF;
        int db = (dst)       & 0xFF;

        int sr = (color >> 16) & 0xFF;
        int sg = (color >> 8)  & 0xFF;
        int sb = (color)       & 0xFF;

        double inv = 1.0 - alpha;

        int r = (int) (sr * alpha + dr * inv);
        int g = (int) (sg * alpha + dg * inv);
        int b = (int) (sb * alpha + db * inv);

        raster.setPixel(ix, iy, (r << 16) | (g << 8) | b);
    }

    private void wuLine(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        if (steep) {
            int tmp;
            tmp = x1; x1 = y1; y1 = tmp;
            tmp = x2; x2 = y2; y2 = tmp;
        }

        if (x1 > x2) {
            int tmpi;
            double tmpd;

            tmpi = x1; x1 = x2; x2 = tmpi;
            tmpi = y1; y1 = y2; y2 = tmpi;

            tmpd = z1; z1 = z2; z2 = tmpd;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        double slope = (dx == 0) ? 0 : dy / dx;

        int x = x1;
        double y = y1;
        double z = z1;

        double dz = (dx == 0) ? 0 : (z2 - z1) / dx;

        while (x <= x2) {
            int ix = x;
            int iy = (int) Math.floor(y);
            double frac = y - iy;

            if (steep) {
                plot(iy, ix, z, zBuffer, 1 - frac);
                plot(iy + 1, ix, z, zBuffer, frac);
            } else {
                plot(ix, iy, z, zBuffer, 1 - frac);
                plot(ix, iy + 1, z, zBuffer, frac);
            }

            x++;
            y += slope;
            z += dz;
        }
    }

    /*private void trivialRasterize(int x1, int y1, double z1, int x2, int y2, double z2, double[][] zBuffer) {
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
    }*/
}
