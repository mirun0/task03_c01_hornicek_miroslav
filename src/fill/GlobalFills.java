package fill;

import java.awt.Color;

import fill.scanline.Pattern;

public class GlobalFills {

    public static final Fillable[] fills = new Fillable[] {
        new Fill(Color.GREEN.getRGB()),
        new Fill(Color.PINK.getRGB()),
        Pattern.rocketPattern,
        Pattern.checkeredPattern,
    };

    public static Fillable activeFill = fills[0];
    private static int pointer = 0;

    public static void next() {
        pointer++;
        if (pointer == fills.length) {
            pointer = 0;
        }
        activeFill = fills[pointer];
    }

    public static void back() {
        pointer--;
        if (pointer < 0) {
            pointer = fills.length - 1;
        }
        activeFill = fills[pointer];
    }

}
