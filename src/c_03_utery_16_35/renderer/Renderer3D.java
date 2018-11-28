package c_03_utery_16_35.renderer;

import c_03_utery_16_35.model3d.Solid;
import c_03_utery_16_35.view.Raster;
import transforms.*;

import java.awt.*;
import java.util.List;

public class Renderer3D {

    private final Raster raster;
    private Mat4 model, view, projection;

    public Renderer3D(Raster raster) {
        this.raster = raster;

        model = new Mat4Identity();

        Vec3D e = new Vec3D(0, -5, 3);
        Vec3D d = new Vec3D(0, 5, -3);
        Vec3D u = new Vec3D(0, 0, 1);
        view = new Mat4ViewRH(e, d, u);

        projection = new Mat4PerspRH(Math.PI / 4, Raster.HEIGHT / (float) Raster.WIDTH, 1, 200);
    }

    public void draw(Solid... solids) {
        for (int i = 0; i < solids.length; i++) {
            List<Point3D> vertices = solids[i].getVertices();
            List<Integer> indices = solids[i].getIndices();
            for (int j = 0; j < indices.size(); j = j + 2) {
                Point3D a = vertices.get(indices.get(j));
                Point3D b = vertices.get(indices.get(j + 1));
                drawLine(a, b, solids[i].getColor());
            }
        }
    }

    private void drawLine(Point3D a, Point3D b, Color color) {
        a = a.mul(model).mul(view).mul(projection);
        b = b.mul(model).mul(view).mul(projection);
/*
Homogeneous Coordinates
https://youtu.be/BwJ8sLYcPzc

https://prateekvjoshi.com/2014/06/13/the-concept-of-homogeneous-coordinates/

Perspective Projection
https://youtu.be/veLvYQpxe6Y

Homogeneous Coordinates
https://youtu.be/GGG3cL6vfSc
*/
        Vec3D v1, v2;
        if (a.dehomog().isPresent()) {
            v1 = a.dehomog().get();
        } else {
            v1 = new Vec3D(0, 0, 0);
        }
        if (b.dehomog().isPresent()) {
            v2 = b.dehomog().get();
        } else {
            v2 = new Vec3D(0, 0, 0);
        }

        // TODO ořezání

        v1 = v1.mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));
        v2 = v2.mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));

        // TODO transformace do okna

        raster.drawLine(
                v1.getX(), v1.getY(),
                v2.getX(), v2.getY(),
                color);
    }

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public Mat4 getView() {
        return view;
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }
}

