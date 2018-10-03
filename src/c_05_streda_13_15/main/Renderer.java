package c_05_streda_13_15.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000 / 30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.img = img;
        this.canvas = canvas;
        setLoop();
    }

    private void setLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                canvas.getGraphics().drawImage(img, 0, 0, null);
                // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
            }
        }, 0, FPS);
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, 800, 600);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float k = dy / (float) dx;
        float q = y1 - k * x1;

        if (Math.abs(k) < 1) {
            // řídící osa X
            if (x1 > x2) {
                int pomocna = x1;
                x1 = x2;
                x2 = pomocna;
                // prohození y vhodné, ale není aspoň tuto chívli nutné
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                drawPixel(x, y, color);
            }
        } else {
            // řídící osa je Y
        }

    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }
}
