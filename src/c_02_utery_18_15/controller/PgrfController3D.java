package c_02_utery_18_15.controller;

import c_02_utery_18_15.model3D.Cube;
import c_02_utery_18_15.model3D.Cubic3D;
import c_02_utery_18_15.model3D.Solid;
import c_02_utery_18_15.model3D.Spiral;
import c_02_utery_18_15.renderer.Renderer3D;
import c_02_utery_18_15.view.Raster;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PgrfController3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;

    private int mx, my;

    public PgrfController3D(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();
    }

    private void initObjects() {
        cube = new Cube();
        renderer3D = new Renderer3D(raster);
        renderer3D.add(cube);
        renderer3D.add(new Spiral());
        Cubic3D cubic3D = new Cubic3D();
        cubic3D.create();
        renderer3D.add(cubic3D);
        resetCamera();
    }

    private void resetCamera() {
        camera = new Camera(
                new Vec3D(0, -5, 2),
                Math.toRadians(90),
                Math.toRadians(-25),
                1, true
        );
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initListeners() {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    double diff = (mx - e.getX()) / 100.0;
                    double azimuth = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimuth);
                    renderer3D.setView(camera.getViewMatrix());

                    // dodělat zenit, ořezat <-PI/2,PI/2>
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    double rotX = (mx - e.getX()) / -200.0;
                    double rotY = (my - e.getY()) / -200.0;
                    Mat4 rot = renderer3D.getModel().mul(new Mat4RotXYZ(rotY, 0, rotX));
                    renderer3D.setModel(rot);
                }

                // dodělat posunutí a změnu měřítka
                mx = e.getX();
                my = e.getY();
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        camera = camera.forward(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    // dodělat ovládání
                }
            }
        });
    }
}
