package PaintMain;


import exception.VECFormatException;
/**
 * A GUI handling class for the application
 */

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Create Gui for the application
 */

public class GUI extends JFrame implements Runnable {
    final int WIDTH = 750;
    final int HEIGHT = 800;

    private JButton clearBtn, undoBtn,plotBtn, lineBtn,rectBtn,ellipseBtn ;
    private JToggleButton filledBtn ;
    private JRadioButton penclrBtn, fillclrBtn;
    private DrawBoard drawBoard;
    private JColorChooser colorPallete;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JMenuItem itemOpen,itemSave, itemExit;


    private Container content = this.getContentPane();


    /**
     * Initial Gui constructor
     */
    public GUI(){
        setSize(WIDTH,HEIGHT);
        setTitle("My Paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawBoard = new DrawBoard();

        setColorPallete();
        setMenuBar();
        setToolBar();

        content.add(menuBar,BorderLayout.NORTH);
        content.add(drawBoard,BorderLayout.CENTER);
        content.add(colorPallete,BorderLayout.SOUTH);
        content.add(toolBar,BorderLayout.WEST);

        repaint();
        setVisible(true);
    }

    @Override
    public void run() {}

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
        colorPallete.getSelectionModel().addChangeListener(new ColorSelection());

        colorPallete.setLayout(new FlowLayout());

        ButtonGroup buttons = new ButtonGroup();
        penclrBtn = new JRadioButton("Pen Color");
        fillclrBtn = new JRadioButton("Fill Color");

        buttons.add(penclrBtn);
        buttons.add(fillclrBtn);
        buttons.setSelected(penclrBtn.getModel(),true);

        colorPallete.add(penclrBtn);
        colorPallete.add(fillclrBtn);
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

        dropMenuHandler dh = new dropMenuHandler();

        itemOpen.addActionListener(dh) ;


    }

    /**
     * Set up toolbar
     */
    public void setToolBar(){
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(100,getHeight()));
        toolBar.setLayout(new BoxLayout(toolBar,BoxLayout.PAGE_AXIS));

        clearBtn = new JButton("Clear All");
        undoBtn = new JButton("Undo");

        keyPress k= new keyPress();
        undoBtn.addKeyListener(k);


        filledBtn = new JToggleButton("Filled");
        filledBtn.setSelected(false);
        filledBtn.addActionListener(e -> {
            JToggleButton tgBtn = (JToggleButton) e.getSource();
            if (tgBtn.isSelected()){
                drawBoard.setFilled(true);
            }else if(!tgBtn.isSelected()) {
                drawBoard.setFilled(false);
            }
        });

        plotBtn = new JButton("Plot");
        lineBtn = new JButton("Line");
        rectBtn = new JButton("Rectangle");
        ellipseBtn = new JButton("Ellipse");


        addButtontoToolbar(toolBar,clearBtn,new toolbarHandler());
        addButtontoToolbar(toolBar,undoBtn,new toolbarHandler());



        addButtontoToolbar(toolBar,plotBtn,new toolbarHandler());
        addButtontoToolbar(toolBar,lineBtn,new toolbarHandler());
        addButtontoToolbar(toolBar,rectBtn,new toolbarHandler());
        addButtontoToolbar(toolBar,ellipseBtn, new toolbarHandler());

        toolBar.addSeparator();
        toolBar.add(filledBtn);
    }

    /**
     * Add button to toolbar with an ButtonListener for each
     */
    private void addButtontoToolbar(final JToolBar toolBar, JButton button,
                                    final ActionListener actionListener){
        button.addActionListener(actionListener);
        toolBar.add(button);
    }

    /**
     * Get the .VEC file path
     * @return the string contain the file path
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

    /**
     * A listener class for handling the dropdown menu
     */
    private class dropMenuHandler implements ActionListener{

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
                        drawBoard.clearAll();
                        drawBoard.handlingCommands(commands);
                    }catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (VECFormatException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (item == itemExit){

            }
        }
    }

    /**
     * A listener class for handling the toolbar
     */
    private class toolbarHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == clearBtn){
                drawBoard.clearAll();
            }else if (e.getSource() == undoBtn){
                drawBoard.undo();
            }else if (e.getSource() == plotBtn){
                drawBoard.setCurrentShapeType(0);
            }else if(e.getSource()== lineBtn){
                drawBoard.setCurrentShapeType(1);
            }else if(e.getSource()== rectBtn){
                drawBoard.setCurrentShapeType(2);
            }else if(e.getSource()==ellipseBtn)
                drawBoard.setCurrentShapeType(3);
        }
    }

    /**
     * A listener class for handling the color chooser
     */
    private class ColorSelection implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if(penclrBtn.getModel().isSelected()) {
                Color color = colorPallete.getColor();
                drawBoard.setcurrentPenColor(color);
            } else {
                Color color = colorPallete.getColor();
                drawBoard.setcurrenFillColor(color);
            }
        }
    }
    private class keyPress implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)){
                drawBoard.undo();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }



}


