package view;

import javax.swing.*;

import rasterize.Raster;
import rasterize.RasterBufferedImage;

import java.awt.*;

public class Panel extends JPanel {

    private final Raster raster;

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        raster = new RasterBufferedImage(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(raster.getImage(), 0, 0, null);
    }

    public Raster getRaster() {
        return raster;
    }
}
