package c_03_utery_16_35.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PixelTest {

    private JFrame window;
    private Raster raster;
    private Renderer renderer;
    private SeedFill seedFill;

    public PixelTest() {
        window = new JFrame();
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(800, 600); // velikost okna
        window.setLocationRelativeTo(null);// vycentrovat okno
        window.setTitle("PGRF1 cvičení"); // titulek okna

        raster = new Raster();

        window.add(raster.getCanvas()); // vložit plátno do okna
        window.setVisible(true); // zobrazit okno

        renderer = new Renderer(raster);

        seedFill = new SeedFill();
        //seedFill.setBufferedImage(img);

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
        raster.getCanvas().requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PixelTest::new);
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}
