package rasterize;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterBufferedImage implements Raster {

    private BufferedImage image;

    public RasterBufferedImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        if(image.getRaster().getWidth() <= x || x < 0 || image.getRaster().getHeight() <= y || y < 0) {
            return;
        }

        image.setRGB(x, y, color);
    }

    @Override
    public Optional<Integer> getPixel(int x, int y) {
        if(image.getRaster().getWidth() <= x || x < 0 || image.getRaster().getHeight() <= y || y < 0) {
            return Optional.empty();
        }

        return Optional.of(image.getRGB(x, y));
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void clear(Color color) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Graphics getGraphics() {
        return image.getGraphics();
    }
    
}
