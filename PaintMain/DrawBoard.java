package PaintMain;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


public class DrawBoard extends JPanel{
    Image image;

    Graphics2D graphic2d;

    int currentX, currentY, pastX, pastY ;

    public DrawBoard() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed (MouseEvent e) {
                //save coordinate x,y when press mouse
                pastX = e.getX();
                pastY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                if (graphic2d != null) {
                    //draw a line if graphic not null
                    graphic2d.drawLine(pastX, pastY, currentX, currentY);
                    //refresh the draw board
                    repaint();
                    //store current coordinates x,y as past x,y
                    pastX = currentX;
                    pastY = currentY;
                }
            }
        });
    }

        protected void paintComponent (Graphics graphic) {
            super.paintComponent(graphic);
            if (image == null){
                //create image if image = null
                image = createImage(getWidth(),getHeight());
                graphic2d = (Graphics2D) image.getGraphics();
                //enable antialiasing
                graphic2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                //clear draw area
                clear();
            }
            graphic.drawImage(image,0,0,null);
        }


        public void clear(){
            graphic2d.setPaint(Color.WHITE);
            //clear board by draw white on it
            graphic2d.fillRect(0,0,getWidth(),getHeight());
            graphic2d.setPaint(Color.black);
            repaint();
        }

        public void pencolorChange(Color color){
            graphic2d.setPaint(color);
        }


        public void drawLine(ArrayList<String> command){
            pastX = stringToPixel(command.get(1));
            pastY = stringToPixel(command.get(2));
            currentX = stringToPixel(command.get(3));
            currentY = stringToPixel(command.get(4));
            graphic2d.drawLine(pastX, pastY, currentX, currentY);
            repaint();
        }
        public void drawPlot(ArrayList<String> command){}

        public void handlingCommands(ArrayList<ArrayList<String>> commands){

            for (ArrayList<String> command : commands ){
                if (command.get(0).equals("PLOT")){

                } else if (command.get(0).equals("LINE")) {
                    drawLine(command);
//                } else if (command.get(0).equals("PEN")){
//
//                } else if (command.get(0).equals("COLOR")){
//
//                } else if (command.get(0).equals("RECTANGLE")){
//
//                } else if (command.get(0).equals("ELLIPSE")){
//
//                } else if (command.get(0).equals("POLYGON")){
//
//                }
                }

            }
        }

        public int stringToPixel(String s){
            return (int) (Float.parseFloat((s))*getHeight());
        }

}

