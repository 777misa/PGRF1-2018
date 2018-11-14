package c_05_streda_13_15.renderer;

import c_05_streda_13_15.model3d.Solid;
import c_05_streda_13_15.view.Raster;
import transforms.Mat4;
import transforms.Point3D;

public class Renderer3D {

    private Raster raster;
    private Mat4 model;
    private Mat4 view;
    private Mat4 projection;

    public Renderer3D(Raster raster) {
        this.raster = raster;
    }

    public void draw(Solid... solids) {
        for (Solid solid : solids) {
            for (int i = 0; i < solid.getIndices().size(); i+=2) {
                Integer i1 = solid.getIndices().get(i);
                Point3D a = solid.getVertices().get(i1);
                Integer i2 = solid.getIndices().get(i+1);
                Point3D b = solid.getVertices().get(i2);
                drawLine(a, b);
            }
        }
    }

    private void drawLine(Point3D a, Point3D b) {

    }
}
