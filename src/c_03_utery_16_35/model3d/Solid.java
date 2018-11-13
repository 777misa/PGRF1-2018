package c_03_utery_16_35.model3d;

import transforms.Point3D;

import java.util.List;

public abstract class Solid {

    int color;
    List<Point3D> vertices;
    List<Integer> indices;

    public int getColor() {
        return color;
    }

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Integer> getIndices() {
        return indices;
    }
}
