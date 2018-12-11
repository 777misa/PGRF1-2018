package c_03_utery_16_35.controller;

import c_03_utery_16_35.model3d.Cube;
import c_03_utery_16_35.model3d.Cubic3D;
import c_03_utery_16_35.model3d.Solid;
import c_03_utery_16_35.model3d.Spiral;
import c_03_utery_16_35.renderer.Renderer3D;
import c_03_utery_16_35.view.Raster;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;

    private int mx, my;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners(raster);
    }

    private void initObjects() {
        cube = new Cube(Color.CYAN);
        renderer3D.add(cube);
        renderer3D.add(new Spiral());
        Cubic3D cubic = new Cubic3D();
        cubic.create();
        renderer3D.add(cubic);
        resetCamera();
    }

    private void resetCamera() {
        camera = new Camera(
                new Vec3D(0, -5, 3),
                Math.toRadians(90),
                Math.toRadians(-40),
                1, true
        );
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initListeners(Raster raster) {
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
                    double azimut = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimut);
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
