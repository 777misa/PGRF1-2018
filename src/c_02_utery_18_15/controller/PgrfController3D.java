package c_02_utery_18_15.controller;

import c_02_utery_18_15.model3D.Cube;
import c_02_utery_18_15.model3D.Solid;
import c_02_utery_18_15.renderer.Renderer3D;
import c_02_utery_18_15.view.Raster;

public class PgrfController3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;

    public PgrfController3D(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();

    }

    private void initObjects() {
        cube = new Cube();
        renderer3D = new Renderer3D(raster);
        renderer3D.draw(cube);
    }

    private void initListeners() {

    }
}
