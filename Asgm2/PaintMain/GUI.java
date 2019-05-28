package Asgm2.PaintMain;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.io.File;

public class GUI extends JFrame implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    JButton clearBtn, blackBtn, redBtn, blueBtn;
    DrawBoard drawBoard;
    JColorChooser colorPallete;
    JMenuBar menuBar;
    Container content = this.getContentPane();

    public GUI(){}

    /**
     * Create Gui
     */
    public void setupGUI(){
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setDrawBoard();
        setColorPallete();
        setMenuBar();

        content.add(menuBar,BorderLayout.NORTH);
        content.add(drawBoard,BorderLayout.CENTER);
        content.add(colorPallete,BorderLayout.SOUTH);


        repaint();
        this.setVisible(true);
    }


    public void run() {setupGUI(); }

    /**
     * Set up the display of Drawboard
     */
    public void setDrawBoard(){
        drawBoard = new DrawBoard();

    }

    /**
     * Set up the color pallete layout
     */

    public void setColorPallete(){
        colorPallete = new JColorChooser();
        // Only display swatches panel
        AbstractColorChooserPanel[] panels =colorPallete.getChooserPanels();
        for (AbstractColorChooserPanel panel : panels){
            if(!panel.getDisplayName().equals("Swatches")){
                colorPallete.removeChooserPanel(panel);
            }
        }
        // Disable the preview panel
        colorPallete.setPreviewPanel(new JPanel());
    }

    /**
     * Set up the menuBar
     */
    public void setMenuBar(){
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem itemOpen = new JMenuItem("Open File ...");
        JMenuItem itemSave = new JMenuItem("Save As");
        JMenuItem itemExit = new JMenuItem("Exit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        fileMenu.add(itemOpen);
        fileMenu.add(itemSave);
        fileMenu.addSeparator();
        fileMenu.add(itemExit);

    }

    /**
     * Get the .vec file path
     * @return
     */
    public String getFilePath(){
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if(returnVal==JFileChooser.APPROVE_OPTION) {
            File vec = fc.getSelectedFile();
            String filename = fc.getSelectedFile().getAbsolutePath();
            return filename;
        } else if(returnVal==JFileChooser.CANCEL_OPTION) {
        }
        return "";
    }

}


