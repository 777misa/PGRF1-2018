package c_03_utery_16_35.renderer;

import c_03_utery_16_35.model.Point;
import c_03_utery_16_35.view.Raster;
import transforms.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        float k = (y2 - y1) / (float) (x2 - x1);
        // https://www.google.com/search?q=java+dividing+two+integers
        float q = y1 - k * x1;

        // řídící osa X
        if (Math.abs(k) < 1) {
            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;

                a = y1;
                y1 = y2;
                y2 = a;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                raster.drawPixel(x, y, color);
            }
        } else {
            // řídící osa Y
        }
    }

    public void drawDDA(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float k, g, h;

        k = dy / (float) dx;
        if (Math.abs(k) < 1) {
            // řídící osa X
            g = 1;
            h = k;
            if (x1 > x2) {
                x1 = x2;
                y1 = y2;
            }
        } else {
            // řídící osa Y
            g = 1 / k;
            h = 1;
            if (y1 > y2) {
                x1 = x2;
                y1 = y2;
            }
        }

        float x = x1;
        float y = y1;

        for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
            raster.drawPixel(Math.round(x), Math.round(y), color);
            x += g;
            y += h;
        }
    }

    public void drawPolygon(List<Point> polygonPoints, int color) {
        for (int i = 0; i < polygonPoints.size() - 1; i++) {
            drawDDA(polygonPoints.get(i).x,
                    polygonPoints.get(i).y,
                    polygonPoints.get(i + 1).x,
                    polygonPoints.get(i + 1).y,
                    color
            );
        }
        // spoj poslední a první
        drawDDA(polygonPoints.get(0).x,
                polygonPoints.get(0).y,
                polygonPoints.get(polygonPoints.size() - 1).x,
                polygonPoints.get(polygonPoints.size() - 1).y,
                color
        );
    }

    public void drawLines(List<Point2D> linePoints, int color) {
        for (int i = 0; i < linePoints.size() - 1; i += 2) {
            drawDDA((int) linePoints.get(i).getX(),
                    (int) linePoints.get(i).getY(),
                    (int) linePoints.get(i + 1).getX(),
                    (int) linePoints.get(i + 1).getY(),
                    color
            );
        }
    }

    public List<Point> clip(List<Point> polygonPoints, List<Point> clipPoints) {
        // in - seznam vtcholů ořezávaného polygonu (na tabuli je ten černý)
        // clipPoints - seznam vrcholů ořezávacího polygonu (na tabuli ten zelený)
        // out - seznam vrcholů ořezaného polygonu (na tabuli ten čárkovaný)

        List<Point> in = polygonPoints;

        Point p1 = null;// vložit poslední clip point
        for (Point p2 : clipPoints) {
            List<Point> out = new ArrayList<>();
            // vytvoř hranu z bodů p1 a p2
            // Point v1 = in.last;
            for (Point v2 : in) {
                // TODO algoritmus
            }
            p1 = p2;
            in = out; // aktualizuj ořezávaný polygon
        }

        return in;
    }

}
