package c_02_utery_18_15.model;

public class Edge {

    private int x1, y1, x2, y2;

    public Edge(Point p1, Point p2) {
        x1 = p1.x;
        y1 = p1.y;
        x2 = p2.x;
        y2 = p2.y;
    }

    /**
     * Zjistí, zda je hrana vodorovná
     *
     * @return true pokud je vodorovná, jinak false
     */
    public boolean isHorizontal() {
        // TODO y1 = y2
        return false;
    }

    /**
     * Zorientuje hranu odshora dolů
     */
    public void orientate() {
        // TODO prohození, když y1 je vetší než y2
    }

    /**
     * Zjistí, zda existuje průsečík scanline s hranou
     *
     * @param y y-ová souřadnice scanline
     * @return true pokud průsečík existuje, jinak false
     */
    public boolean intersectionExists(int y) {
        // TODO y, y1, y2
        return false;
    }

    /**
     * Vypočítá a vrátí x-ovou souřadnici průsečíku se scanline
     *
     * @param y y-ová souřadnice scanline
     * @return souřadnice x
     */
    public int getIntersection(int y) {
        // TODO vypočítat průsečík pomocí y, k, q (osa Y)
        return 0;
    }

    public boolean inside(Point point) {
        Point t = new Point(x2 - x1, y2 - y1);
        Point n = new Point(t.y, -t.x);
        //Point n = new Point(-t.y, t.x);
        Point v = new Point(point.x - x1, point.y - y1);
        return (v.x * n.x + v.y * n.y < 0);
    }

    public Point getIntersection(Point v1, Point v2) {

        float x0 = ((v1.x * v2.y - v1.y * v2.x) * (x1 - x2) - (x1 * y2 - y1 * x2) * (v1.x - v2.x))

                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));

        float y0 = ((v1.x * v2.y - v1.y * v2.x) * (y1 - y2) - (x1 * y2 - y1 * x2) * (v1.y - v2.y))

                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));


        return new Point(Math.round(x0), Math.round(y0));
    }
}
