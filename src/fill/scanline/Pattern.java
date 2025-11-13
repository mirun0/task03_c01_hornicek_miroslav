package fill.scanline;

import java.awt.Color;

import fill.Fillable;

public class Pattern implements Fillable {

    private int width;
    private int height;
    private int[][] data;
    private int scale;

    public Pattern(int width, int height, int[][] data, int scale) {
        this.width = width;
        this.height = height;
        this.data = data;
        this.scale = scale;
    }

    private static final int[][] rocketPatternData = new int[][]{
        {Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB()},
        {Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB()},
        {Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.BLUE.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB()},
        {Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB()},
        {Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB()}
    };

    private static final int[][] checkeredPatternData = new int[][] {
        {Color.BLACK.getRGB(), Color.WHITE.getRGB()},
        {Color.WHITE.getRGB(), Color.BLACK.getRGB()}
    };

    public static final Pattern rocketPattern = new Pattern(5, 5, rocketPatternData, 10);
    public static final Pattern checkeredPattern = new Pattern(2, 2, checkeredPatternData, 10);

    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixel(int m, int n) {
        return data[n][m];
    }

    public int getScale() {
        return scale;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}