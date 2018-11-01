package c_03_utery_16_35.controller;

import c_03_utery_16_35.model.Point;
import c_03_utery_16_35.view.Raster;
import c_03_utery_16_35.renderer.Renderer;
import c_03_utery_16_35.fill.SeedFill;
import c_03_utery_16_35.view.PGRFWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private SeedFill seedFill;
    private Raster raster;
    private Renderer renderer;
    private final List<Point> polygonPoints = new ArrayList<>();
    private final List<Point> linePoints = new ArrayList<>();

    public Controller(PGRFWindow window) {
        initObjects(window);
        initListeners();
    }

    private void initObjects(PGRFWindow window) {
        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus();
        window.add(raster); // vložit plátno do okna

        renderer = new Renderer(raster);

        seedFill = new SeedFill();
        seedFill.setRaster(raster);
    }

    private void initListeners() {

        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown() || e.isShiftDown()) return;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.add(new Point(e.getX(), e.getY()));
                    if (polygonPoints.size() == 1) { // pokud přidáme první, tak rovnou přidat i druhý
                        polygonPoints.add(new Point(e.getX(), e.getY()));
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    linePoints.add(new Point(e.getX(), e.getY()));
                    linePoints.add(new Point(e.getX(), e.getY()));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    seedFill.init(e.getX(), e.getY(), 0x00ffff);
                    seedFill.fill();
                } else {
                    raster.drawPixel(e.getX(), e.getY(), 0xffffff);
                }
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.get(polygonPoints.size() - 1).x = e.getX();
                    polygonPoints.get(polygonPoints.size() - 1).y = e.getY();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    linePoints.get(linePoints.size() - 1).x = e.getX();
                    linePoints.get(linePoints.size() - 1).y = e.getY();
                }
                update();
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
            }
        });
    }

    private void update() {
        raster.clear();
        // vykresli polygon
        renderer.drawPolygon(polygonPoints, 0xff0000);
        // vykresli úsečky
        for (int i = 0; i < linePoints.size(); i += 2) {
            renderer.drawDDA(
                    linePoints.get(i).x,
                    linePoints.get(i).y,
                    linePoints.get(i + 1).x,
                    linePoints.get(i + 1).y,
                    0xffff00
            );
        }
    }


}
