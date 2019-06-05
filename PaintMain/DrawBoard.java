package PaintMain;

import shapes.*;
import shapes.Rectangle;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * This customize class for create a drawboard panel
 * will be use to draw on using GUI
 */
public class DrawBoard extends JPanel {
    final Color Default_pen_color = Color.BLACK;
    final Color Default_fill_color = null;

    private Graphics2D graphic2d;
    private ArrayList<VECShape> shapes;
    public VECShape currentShape;
    public int currentShapeType ;
    private int x1,y1,x2,y2;
    private String tool;
    public Color currentpenColor = Default_pen_color;
    public Color currentfillColor = Default_fill_color;
    private boolean isFilled = false;
    private JLabel statusLabel;

    /**
     * Initialize the drawboard panel
     */
    public DrawBoard() {
        shapes = new ArrayList<VECShape>();

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500,500));

        statusLabel = new JLabel("(0,0)"); //shows the curser value
        statusLabel.setOpaque(true);
        add(statusLabel,BorderLayout.SOUTH);

        mouseHandler mouseHandler1 = new mouseHandler();
        addMouseListener(mouseHandler1);;
        addMouseMotionListener(mouseHandler1);

        currentShape = null;
        setCurrentShapeType(1);

    }

    /**
     * Set up drawing function for the panel
     * @param drawboard the panel use to drawing
     */
    @Override
    public void paintComponent (Graphics drawboard) {
        super.paintComponent(drawboard);
        graphic2d = (Graphics2D) drawboard;
        graphic2d.setColor(Color.BLACK);

        for(int i = 0; i< shapes.size(); i++)
        {
            shapes.get(i).draw(graphic2d);
        }
        if (currentShape != null)
        {
            currentShape.draw(graphic2d);
        }
    }

    /**
     * Set the current drawing tool
     * @param type index type for the drawing tool 0 for PLOT 
     *             1 for LINE 2 For RECTANGLE 3 for ELLIPSE
     */
    public void setCurrentShapeType(int type){
        this.currentShapeType = type;
    }

    /**
     * Clear board by draw white over it and clear all the saved shapes
     *  
     */
    public void clearAll(){
        graphic2d.setPaint(Color.WHITE);
        graphic2d.fillRect(0,0,getWidth(),getHeight());
        graphic2d.setColor(Default_pen_color);
        shapes.clear();
        repaint();
    }

    /**
     * Remove the newest action by delete the last shape stored 
     */
    public void undo(){
        if (shapes.size()!= 0){
            shapes.remove(shapes.size()-1);
        }
        repaint();
    }


    public void setcurrentPenColor(Color color) { this.currentpenColor = color; }
    public Color getcurrentPenColor(){ return currentpenColor; }
    public void setcurrenFillColor(Color color){this.currentfillColor = color;}
    public Color getcurrentFillColor(){return this.currentfillColor;}
    public void setFilled(boolean Filled) {isFilled = Filled;}
    public boolean getFilled(){return isFilled;}

    /**
     * Handling the commands have imported in the VEC file to create the wanted view
     * @param commands lists of command in VEC format
     */
    public void handlingCommands(ArrayList<ArrayList<String>> commands){
        for (ArrayList<String> command : commands ){
            tool =  command.get(0) ;
            if (command.size() > 3 ){
                x1 = stringToPixel(command.get(1));
                y1 = stringToPixel(command.get(2));
                x2 = stringToPixel(command.get(3));
                y2 = stringToPixel(command.get(4));

                if (tool.equals("LINE")) {
                    currentShape = new Line(x1,y1,x2,y2,currentpenColor, currentfillColor,isFilled);
                } else if (tool.equals("RECTANGLE")){
                    currentShape = new Rectangle(x1,y1,x2,y2,currentpenColor, currentfillColor,isFilled);
                } else if (tool.equals("ELLIPSE")) {
                    currentShape = new Ellipse(x1, y1, x2, y2, currentpenColor, currentfillColor,isFilled);
                }

                if (currentShape.getfillColor()!=null){
                    currentShape.setFilled(true);
                }
                shapes.add(currentShape);


            }else if (tool.equals("PLOT")){
                x1 = stringToPixel(command.get(1));
                y1 = stringToPixel(command.get(2));
                currentShape = new Plot(x1,y1,currentpenColor);
                shapes.add(currentShape);

            } else if (tool.equals("PEN")){
                setcurrentPenColor(Color.decode(command.get(1)));
            } else if (tool.equals("FILL") && command.get(1).matches("OFF")) {
                setFilled(false);
                setcurrenFillColor(null);
            } else if (tool.equals("FILL")) {
                setFilled(true);
                setcurrenFillColor(Color.decode(command.get(1)));
            }
        }
    }

    /**
     * Convert the String type VEC parameter to the current panel scale
     * @param s the String type VEC parameter
     * @return the equivalent pixel based on the panel scale
     */
    public int stringToPixel(String s){
        return (int) (Float.parseFloat((s))*getHeight());
    }

    /**
     *
     */
    private class mouseHandler extends MouseInputAdapter implements MouseListener{
        @Override
        public void mousePressed(MouseEvent e) {

            //Identify what shape is being use 0 for PLOT 1 for LINE 2 for TRIANGLE 3 for ELLIPSE
            switch (currentShapeType){

                case 0:
                    currentShape = new Plot(e.getX(),e.getY(),currentpenColor);
                    break;
                case 1:
                    currentShape = new Line(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor, currentfillColor, isFilled );
                    break;
                case 2:
                    currentShape = new Rectangle(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor, currentfillColor, isFilled);
                    break;
                case 3:
                    currentShape =  new Ellipse(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor, currentfillColor, isFilled);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            if (currentShape != null){

            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());

            shapes.add(currentShape);
            currentShape = null;

            repaint();
            } else throw new NullPointerException("Need to choose a shape first");
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());

            repaint();
        }


        @Override
        public void mouseMoved(MouseEvent e) {
            statusLabel.setText("("+e.getX()+","+ e.getY()+"");

        }
    }

}

