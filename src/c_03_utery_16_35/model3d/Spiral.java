package c_03_utery_16_35.model3d;

import transforms.Point3D;

import java.awt.*;

public class Spiral extends Solid {

    public Spiral() {
        int i = 0;
        for (double a = 0; a <= 5; a += 0.1) {
            double x = Math.cos(a);
            double y = Math.sin(a);
            double z = a / 20;
            vertices.add(new Point3D(x, y, z));
            if (a > 0) {
                indices.add(i);
                indices.add(++i);
            }
        }
        color = Color.YELLOW;
    }
}
