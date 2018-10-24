package c_05_streda_13_15.controller;

import c_05_streda_13_15.view.Raster;
import c_05_streda_13_15.renderer.Renderer;
import c_05_streda_13_15.fill.SeedFiller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PgrfController {

    private Raster raster;
    private Renderer renderer;
    private SeedFiller seedFiller;

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
            public void mouseClicked(MouseEvent e) {

                if (e.isControlDown()) {
                    seedFiller.init(e.getX(), e.getY(), 0xff00ff);
                    seedFiller.fill();
                } else {
                    raster.drawPixel(e.getX(), e.getY(), 0xffffff);
                }

                //points.add(e.getX());
                //points.add(e.getY());
                //renderer.drawPolygon(points);
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                raster.clear();
                renderer.lineDDA(400, 300, e.getX(), e.getY(), 0xffffff);
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                // při zmáčknutí klávesy C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
            }
        });
        raster.requestFocus();
    }

}
