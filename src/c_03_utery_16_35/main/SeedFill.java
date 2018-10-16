package c_03_utery_16_35.main;

import java.awt.image.BufferedImage;

public class SeedFill implements Filler {

    private int x;
    private int y;
    private int color;
    private int backGround;

    private BufferedImage image;

    @Override
    public void setBufferedImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    public void init(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        backGround = image.getRGB(x, y);
    }

    private void seed(int ax, int ay) {
        if ((ax >= 0) && (ay >= 0) && (ax < image.getWidth()) && (ay < image.getHeight()) && backGround == image.getRGB(ax, ay)) {
            image.setRGB(ax, ay, color);
            seed(ax + 1, ay);
            seed(ax - 1, ay);
            seed(ax, ay + 1);
            seed(ax, ay - 1);
        }

    }
}
