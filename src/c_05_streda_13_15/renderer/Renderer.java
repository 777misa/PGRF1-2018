package c_05_streda_13_15.renderer;

import c_05_streda_13_15.view.Raster;

public class Renderer {

    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;
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
                raster.drawPixel(x, y, color);
            }
        }/* else {
            // řídící osa je Y
        }*/
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
            raster.drawPixel(Math.round(x), Math.round(y), color);
            x += g;
            y += h;
        }
    }

    /*
    public void drawPolygon(List<Integer> points) {
        clear();
        drawLine(points.get(0), points.get(1), points.get(2), points.get(3));
        i += 2;
        // for cyklus po dvou se správným omezením
        drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
    }
    */
}
