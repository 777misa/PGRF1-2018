package c_03_utery_16_35.apl;

import c_03_utery_16_35.controller.Controller;
import c_03_utery_16_35.view.PGRFWindow;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PGRFWindow pgrfWindow = new PGRFWindow();
            new Controller(pgrfWindow);
            pgrfWindow.setVisible(true);
        });
    }
}
