package shapes;

import java.awt.*;

/**
 *  A subclass for Ellipse shape type, extend an abstract of VECShape class
 */
public class Ellipse extends VECShape {
    /**
     * Initialize a Ellipse shape
     */
    public Ellipse(){super();}

    /**
     * Initilaize a Ellipse shape with overload data
     * @param x1 the first x coordination where the shape should be draw from
     * @param y1 the first y coordination where the shape should be draw from
     * @param x2 the ending x coordination where the shape should be ending
     * @param y2 the ending y coordination where the shape should be ending
     * @param penColor the chosen pen color to draw the shape
     * @param fillColor the chosen fill color to fill the shape
     * @param isFilled to check if the shape is filled or not
     */
    public Ellipse(int x1 , int y1 , int x2 , int y2, Color penColor,Color fillColor, boolean isFilled){
        super(x1,y1,x2,y2,penColor,fillColor,isFilled);
    }

    /**
     * Drawing method for Ellipse shape while checking if it is a filled shape or not
     * Fill the shape when it is a filled shape
     * @param drawboard - The graphic object to draw with
     */
    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());

        drawboard.drawOval(Integer.min(getX1(),getX2()),Integer.min(getY1(), getY2()),
                Integer.max(getX1(), getX2())-Integer.min(getX1(),getX2()),
                        Integer.max(getY1(),getY2())-Integer.min(getY1(), getY2()));

        if (getFilled()==true){
            drawboard.setColor(getfillColor());
            drawboard.fillOval(Integer.min(getX1(),getX2()),Integer.min(getY1(), getY2()),
                    Integer.max(getX1(), getX2())-Integer.min(getX1(),getX2()),
                    Integer.max(getY1(),getY2())-Integer.min(getY1(), getY2()));
        }

    }
}
