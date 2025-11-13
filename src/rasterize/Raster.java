package rasterize;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Optional;

public interface Raster {

    public void setPixel(int x, int y, int color);
    public Optional<Integer> getPixel(int x, int y);
    public int getWidth();
    public int getHeight();
    public void clear(Color color);
    public Graphics getGraphics();
    public BufferedImage getImage();
}
