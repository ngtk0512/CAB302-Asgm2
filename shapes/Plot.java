package shapes;

import java.awt.*;

/**
 *  A subclass for Plot shape type, extend an abstract of VECShape class
 */
public class Plot extends VECShape {

    /**
     * Initialize a Plot
     */
    public Plot(){super();}

    /**
     * Initialize a Plot with overload data
     * @param x1 the x coordination to draw the Plot shape
     * @param y1 the y coordination to draw the Plot shape
     * @param penColor the color of the Plot
     */
    public Plot(int x1 , int y1 ,Color penColor) {
        super(x1, y1,penColor);
    }

    /**
     * Drawing method for Plot shape
     * @param drawboard - The graphic object to draw with
     */
    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());
        drawboard.fillOval(getX1(),getY1(),5,5);
    }
}