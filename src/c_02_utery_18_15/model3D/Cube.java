package c_02_utery_18_15.model3D;

import transforms.Point3D;

public class Cube extends Solid {

    public Cube(double size) {
        vertices.add(new Point3D(-1,-1,1));
        vertices.add(new Point3D(1,-1,1));
        vertices.add(new Point3D(1,1,1));
        vertices.add(new Point3D(-1,1,1));

        vertices.add(new Point3D(-1,-1,-1));
        vertices.add(new Point3D(1,-1,-1));
        vertices.add(new Point3D(1,1,-1));
        vertices.add(new Point3D(-1,1,-1));

        indices.add(0); indices.add(1);
        indices.add(1); indices.add(2);
        indices.add(2); indices.add(3);
        indices.add(3); indices.add(0);

        indices.add(4); indices.add(5);
        indices.add(5); indices.add(6);
        indices.add(6); indices.add(7);
        indices.add(7); indices.add(4);

        indices.add(0); indices.add(4);
        indices.add(1); indices.add(5);
        indices.add(2); indices.add(6);
        indices.add(3); indices.add(7);
    }

}
