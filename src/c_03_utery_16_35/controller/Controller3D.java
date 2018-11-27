package c_03_utery_16_35.controller;

import c_03_utery_16_35.model3d.Cube;
import c_03_utery_16_35.model3d.Solid;
import c_03_utery_16_35.renderer.Renderer3D;
import c_03_utery_16_35.view.Raster;

import java.awt.*;

public class Controller3D {

    private Renderer3D renderer3D;
    private Solid cube;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners();
        renderer3D.draw(cube);
    }

    private void initObjects() {
        cube = new Cube(Color.CYAN);
    }

    private void initListeners() {
    }
}
