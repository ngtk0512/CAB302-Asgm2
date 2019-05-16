package Asgm2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class DrawBoard  extends JComponent{
    private Image image;

    private Graphics2D graphic2d;

    private int currentX, currentY, pastX, pastY ;

    public DrawBoard(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pastX = e.getX();
                pastY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                if (graphic2d != null){
                    //draw a line if graphic not null
                    graphic2d.drawLine(pastX,pastY,currentX,currentY);
                    //refresh the draw board
                    repaint();
                    //store current coordinates x,y as past x,y
                    pastX = currentX;
                    pastY = currentY;
                }
            }

            protected void paintComponent (Graphics graphic) {
                if (image == null){
                    //create image if image = null
                    image = createImage(getWidth(),getHeight());
                    graphic2d = (Graphics2D) image.getGraphics();
                    //clear draw area
                    clear();
                }
                graphic.drawImage(image,0,0,null);
            }

            //
            public void clear(){
                graphic2d.setPaint(Color.WHITE);
                //clear board by draw white on it
                graphic2d.fillRect(0,0,getWidth(),getHeight());
                graphic2d.setPaint(Color.black);
                repaint();
            }
            public void black(){
                //apply black color on graphic2d context
                graphic2d.setPaint(Color.BLACK);
            }
            public void red(){
                //apply black color on graphic2d context
                graphic2d.setPaint(Color.red);
            }
            public void blue(){
                //apply black color on graphic2d context
                graphic2d.setPaint(Color.blue);
            }
            public void green(){
                //apply black color on graphic2d context
                graphic2d.setPaint(Color.green);
            }
            //https://www.youtube.com/watch?v=OOb1eil4PCo

        });


    }

}
