package PaintMain;

import javax.swing.*;

/**
 * Main program
 *
 */
public class Main {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUI());
    }
}
