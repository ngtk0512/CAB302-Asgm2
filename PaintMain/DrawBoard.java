package PaintMain;


import shapes.*;
import shapes.Rectangle;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class DrawBoard extends JPanel {
    private Image image,undoTemp;
    private Graphics2D graphic2d;
    private int X1, Y1, X2, Y2;
    private ArrayList<VECShape> shapes;
    public VECShape currentShape;
    private int currentShapeType ;

    public Color currentpenColor = Color.BLACK;
    public Color currenfillColor= null;

    private boolean filledShape = false;


    private JLabel statusLabel;


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

    public void paintComponent (Graphics drawboard) {
        super.paintComponent(drawboard);
        graphic2d = (Graphics2D) drawboard;

        for(int i = 0; i< shapes.size(); i++)
        {
            shapes.get(i).draw(graphic2d);
        }
        if(currentShape != null)
        {
            currentShape.draw(graphic2d);
        }


    }

    public void setCurrentShapeType(int type){
        this.currentShapeType = type;
    }


    public void clearAll(){
        graphic2d.setPaint(Color.WHITE);
        //clear board by draw white on it
        graphic2d.fillRect(0,0,getWidth(),getHeight());
        graphic2d.setPaint(Color.black);
        shapes.clear();
        repaint();
    }
    public void undo(){
        if (shapes.size()!= 0){
            shapes.remove(shapes.size()-1);
        }
        repaint();
    }

    public void pencolorChange(ArrayList<String> command){
        graphic2d.setPaint(Color.decode(command.get(1)));
    }

    public void setcurrentPenColor(Color color) { this.currentpenColor = color; }

    public Color getcurrentPenColor(){
        return currentpenColor;
    }

    public void setcurrenFillColor(Color color){this.currenfillColor = color;}

    public Color getcurrentFillColor(){return this.currenfillColor;}

    public void drawPlot(ArrayList<String> command){
        X1 = stringToPixel(command.get(1));
        Y1 = stringToPixel(command.get(2));
        graphic2d.fillRect(X1, Y1,1,1);
        repaint();
    }

    public void drawLine(ArrayList<String> command){
        X2 = stringToPixel(command.get(1));
        Y2 = stringToPixel(command.get(2));
        X1 = stringToPixel(command.get(3));
        Y1 = stringToPixel(command.get(4));
        graphic2d.drawLine(X2, Y2, X1, Y1);
        repaint();
    }


    public void drawRectangle(ArrayList<String> command){
    X2 = stringToPixel(command.get(1));
    Y2 = stringToPixel(command.get(2));
    X1 = stringToPixel(command.get(3));
    Y1 = stringToPixel(command.get(4));
    graphic2d.drawLine(X2, Y2, X1, Y2);
    graphic2d.drawLine(X1, Y2, X1, Y1);
    graphic2d.drawLine(X1, Y1, X2, Y1);
    graphic2d.drawLine(X2, Y1, X2, Y2);
    repaint();
    }


    public void handlingCommands(ArrayList<ArrayList<String>> commands){

        for (ArrayList<String> command : commands ){
            if (command.get(0).equals("PLOT")){
                drawPlot(command);
            } else if (command.get(0).equals("LINE")) {
                drawLine(command);
            }  else if (command.get(0).equals("RECTANGLE")){
                drawRectangle(command);
//                } else if (command.get(0).equals("ELLIPSE")){

//                } else if (command.get(0).equals("POLYGON")){
            } else if (command.get(0).equals("PEN")){
                pencolorChange(command);
////                } else if (command.get(0).equals("FILL")){
////
//                }
            }
        }
    }

    public int stringToPixel(String s){
        return (int) (Float.parseFloat((s))*getHeight());
    }


    private class mouseHandler extends MouseInputAdapter implements MouseListener{
        @Override
        public void mousePressed(MouseEvent e) {

            //Identify what shape is being use 0 for PLOT 1 for LINE 2 for TRIANGLE
            switch (currentShapeType){

                case 0:
                    currentShape = new Plot(e.getX(),e.getY(),currentpenColor);
                    break;

                case 1:
                    currentShape = new Line(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor,currenfillColor);


                    break;

                case 2:
                    currentShape = new Rectangle(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor,currenfillColor);
                    break;
                case 3:
                    currentShape =  new Ellipse(e.getX(),e.getY(),e.getY(),e.getY(),currentpenColor,currenfillColor);
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

