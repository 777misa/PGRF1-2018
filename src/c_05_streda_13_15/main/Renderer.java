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
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
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
        // https://stackoverflow.com/a/5843470
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, 800, 600);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float k = dy / (float) dx;
        // https://www.google.com/search?q=java+dividing+two+integers
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

    public void lineDDA(int x1, int y1, int x2, int y2, int color) {
        int dx, dy;
        dx = x2 - x1;
        dy = y2 - y1;
        float k = dy / (float) dx;
        float g, h;
        if (Math.abs(k) < 1) {
            // řídící osa X
            g = 1;
            h = k;
        } else {
            // řídící osa Y
            g = 1/k;
            h = 1;
        }
        float x = x1;
        float y = y1;
        for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
            drawPixel(Math.round(x), Math.round(y), color);
            x += g;
            y += h;
        }
    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }
}
