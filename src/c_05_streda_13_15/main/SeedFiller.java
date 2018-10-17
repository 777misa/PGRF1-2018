package c_05_streda_13_15.main;

import java.awt.image.BufferedImage;

public class SeedFiller implements Filler {

    private BufferedImage img;
    private int background, fillColor;
    private int x, y;

    @Override
    public void setBufferedImage(BufferedImage image) {
        img = image;
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    public void init(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.fillColor = color;
        background = img.getRGB(x, y);
    }

    // pozor na rekurzivní volání
    // nutné upravit parametr pro VM "-Xss100m"
    // https://stackoverflow.com/questions/4967885/jvm-option-xss-what-does-it-do-exactly
    private void seed(int ax, int ay) {
        if (ax >= 0 && ay >= 0 && ax < img.getWidth() && ay < img.getHeight()) {
            if (background == img.getRGB(ax, ay)) {
                img.setRGB(ax, ay, fillColor);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }
}
