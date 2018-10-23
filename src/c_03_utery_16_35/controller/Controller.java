package c_03_utery_16_35.controller;

import c_03_utery_16_35.view.Raster;
import c_03_utery_16_35.renderer.Renderer;
import c_03_utery_16_35.fill.SeedFill;
import c_03_utery_16_35.view.PGRFWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {

    private SeedFill seedFill;
    private Raster raster;
    private Renderer renderer;

    public Controller(PGRFWindow window) {
        initObjects(window);
        initListeners();
    }

    private void initObjects(PGRFWindow window) {
        raster = new Raster();
        window.add(raster.getCanvas()); // vložit plátno do okna

        renderer = new Renderer(raster);

        seedFill = new SeedFill();
        seedFill.setRaster(raster);
    }

    private void initListeners() {

        raster.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    seedFill.init(e.getX(), e.getY(), 0x00ffff);
                    seedFill.fill();
                } else {
                    raster.drawPixel(e.getX(), e.getY(), 0xffffff);
                }

                //points.add(e.getX());
                //points.add(e.getY());
                //renderer.drawPolygon(points);
            }
        });
        raster.getCanvas().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                raster.clear();
                renderer.drawDDA(400, 300, e.getX(), e.getY(), 0xffff00);
            }
        });
        raster.getCanvas().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
            }
        });
        // chceme, aby canvas měl focus hned při spuštění
        raster.requestFocus();
    }


}
