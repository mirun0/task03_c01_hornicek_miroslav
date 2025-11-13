package fill.scanline;

import java.util.ArrayList;
import java.util.Comparator;

import fill.Fill;
import fill.Fillable;
import fill.Filler;
import model.dim2.Polygon;
import rasterize.Raster;
import transforms.Point2D;

public class ScanlineFiller implements Filler {

    private Raster raster;
    private Polygon polygon;

    public ScanlineFiller(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill(Fillable fillable) {

        boolean pattern = false;
        if(fillable instanceof Fill) {
            pattern = false;
        } else {
            pattern = true;
        }

        ArrayList<EdgeTableEntry> et = new ArrayList<>();
        for (int i = 0; i < polygon.size(); i++) {
            Point2D a = polygon.getPoints().get(i);
            Point2D b = polygon.getPoints().get((i + 1) % polygon.size());
            EdgeTableEntry e = new EdgeTableEntry(a, b);
            if (e.isValid()) et.add(e);
        }

        et.sort(Comparator.comparingDouble(e -> e.yMin));

        if(et.isEmpty()) {
            return;
        }

        ArrayList<EdgeTableEntry> aet = new ArrayList<>();
        double yPointer = et.get(0).yMin;

        while(!et.isEmpty() || !aet.isEmpty()) {

            for (int i = 0; i < et.size(); ) {
                EdgeTableEntry e = et.get(i);
                if (Math.round(e.yMin) == Math.round(yPointer)) {
                    aet.add(e);
                    et.remove(i);
                } else {
                    i++;
                }
            }

            for (int i = 0; i < aet.size(); ) {
                if (yPointer >= aet.get(i).yMax) {
                    aet.remove(i);
                } else {
                    i++;
                }
            }

            if (aet.isEmpty() && et.isEmpty()) {
                break;
            }

            aet.sort(Comparator.comparingDouble(e -> e.currentX));

            for (int i = 0; i + 1 < aet.size(); i += 2) {
                int xStart = (int)Math.round(aet.get(i).currentX);
                int xEnd = (int)Math.round(aet.get(i + 1).currentX);

                if (xStart > xEnd) {
                    int t = xStart;
                    xStart = xEnd;
                    xEnd = t;
                }

                for (int x = xStart + 1; x < xEnd; x++) {
                    int c = 0;
                    if(pattern) {
                        Pattern p = ((Pattern)fillable);
                        int m = (x / p.getScale()) % p.getWidth();
                        int n = ((int)yPointer / p.getScale()) % p.getHeight();
                        c = p.getPixel(m, n);
                    } else {
                        c = ((Fill)fillable).getColor();
                    }

                    raster.setPixel(x, (int) yPointer, c);
                }
            }

            yPointer++;

            for (EdgeTableEntry e : aet) {
                e.currentX += e.inverseSlope;
            }

        }
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
    
}