package c_05_streda_13_15.controller;

import c_05_streda_13_15.model3d.Cube;
import c_05_streda_13_15.model3d.Solid;
import c_05_streda_13_15.renderer.Renderer3D;
import c_05_streda_13_15.view.Raster;

public class Controller3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;

    public Controller3D(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();
        renderer3D.draw(cube);
    }

    private void initObjects() {
        renderer3D = new Renderer3D(raster);
        cube = new Cube();
    }

    private void initListeners() {

    }
}
