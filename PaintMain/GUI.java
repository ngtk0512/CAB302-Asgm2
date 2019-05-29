package PaintMain;

import VEC.VECLoadFile;
import exception.VECFormatException;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gui handle class for the application
 */

public class GUI extends JFrame implements Runnable,ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    JButton clearBtn, blackBtn, redBtn, blueBtn;
    DrawBoard drawBoard;
    JColorChooser colorPallete;
    JMenuBar menuBar;
    JMenuItem itemOpen,itemSave, itemExit;

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
     * Set up the display of drawboard
     */
    public void setDrawBoard() {
        drawBoard = new DrawBoard();
    }

    /**
     * Set up the color pallete layout
     */

    public void setColorPallete(){
        colorPallete = new JColorChooser();
        colorPallete.setPreferredSize(new Dimension(100,100));
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

        itemOpen = new JMenuItem("Open File ...");
        itemSave = new JMenuItem("Save As");
        itemExit = new JMenuItem("Exit");


        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        fileMenu.add(itemOpen);
        fileMenu.add(itemSave);
        fileMenu.addSeparator();
        fileMenu.add(itemExit);

        itemOpen.addActionListener(this) ;


    }

    /**
     * Get the .VEC file path
     * @return
     */
    public String getFilePath(){
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("VEC file","vec");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if(returnVal==JFileChooser.APPROVE_OPTION) {
            File vec = fc.getSelectedFile();
            String filepath = fc.getSelectedFile().getAbsolutePath();
            return filepath;
        } else if(returnVal==JFileChooser.CANCEL_OPTION) {
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        if(item == itemOpen || item == itemSave ){
            String filePath = getFilePath();
            if (filePath == "") return;

            if (item == itemOpen){
                Backend be = new Backend();
                try{
                    ArrayList<ArrayList<String>> commands = be.loadCommands(filePath);
                    drawBoard.clear();
                    drawBoard.handlingCommands(commands);
                }catch (IOException e1) {
                    e1.printStackTrace();
                } catch (VECFormatException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



}


