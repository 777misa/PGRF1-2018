package c_05_streda_13_15.controller;

import c_05_streda_13_15.renderer.Renderer3D;
import c_05_streda_13_15.view.Raster;

public class Controller3D {

    private Raster raster;
    private Renderer3D renderer3D;

    public Controller3D(Raster raster) {
        this.raster = raster;
        initObjects();

    }

    private void initObjects() {
        renderer3D = new Renderer3D(raster);
    }
}
