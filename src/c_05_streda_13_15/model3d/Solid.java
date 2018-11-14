package c_05_streda_13_15.model3d;

import transforms.Point3D;

import java.util.List;

public abstract class Solid {

    List<Point3D> vertices;
    List<Integer> indices;
    int color;

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public int getColor() {
        return color;
    }

}
