package c_05_streda_13_15.controller;

import c_05_streda_13_15.model3d.Cube;
import c_05_streda_13_15.model3d.Solid;
import c_05_streda_13_15.renderer.Renderer3D;
import c_05_streda_13_15.view.Raster;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private final Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;

    private int mx, my;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners(raster);
    }

    private void initObjects() {
        cube = new Cube();
        renderer3D.add(cube);
        resetCamera();
    }

    private void resetCamera() {
        camera = new Camera(
                new Vec3D(0, -5, 4),
                Math.toRadians(90), // azimuth
                Math.toRadians(-40), // zenith
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
                    double diff = (mx - e.getX()) / 200.0;
                    double azimuth = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimuth);

                    // dodělat zenit, ořezat <-PI/2,PI/2>
                    renderer3D.setView(camera.getViewMatrix());
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
