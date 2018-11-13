package c_03_utery_16_35.controller;

import c_03_utery_16_35.renderer.Renderer3D;
import c_03_utery_16_35.view.Raster;

public class Controller3D {

    private Renderer3D renderer3D;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
    }
}
