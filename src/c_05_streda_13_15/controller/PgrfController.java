package c_05_streda_13_15.controller;

import c_05_streda_13_15.model.Point;
import c_05_streda_13_15.view.Raster;
import c_05_streda_13_15.renderer.Renderer;
import c_05_streda_13_15.fill.SeedFiller;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PgrfController {

    private Raster raster;
    private Renderer renderer;
    private SeedFiller seedFiller;
    private List<Point> polygonPoints = new ArrayList<>();
    private List<Point> linePoints = new ArrayList<>();

    public PgrfController(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();
    }

    private void initObjects() {
        renderer = new Renderer(raster);

        seedFiller = new SeedFiller();
        seedFiller.setRaster(raster);
    }

    private void initListeners() {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.add(new Point(e.getX(), e.getY()));
                    if (polygonPoints.size() == 1) { // při prvním kliknutí přidat rovnou i druhý bod
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
                    seedFiller.init(e.getX(), e.getY(), 0xff00ff);
                    seedFiller.fill();
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
                // při zmáčknutí klávesy C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
            }
        });
    }

    private void update() {
        raster.clear();
        renderer.drawLines(linePoints, 0x00ff00);
        renderer.drawPolygon(polygonPoints, 0xff0000);

    }

}
