package shapes;

import java.awt.*;

/**
 * An abstract class for .VEC type shape
 */
public abstract class VECShape {

    private String name;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color penColor, fillColor;
    private boolean isFilled;
    private int width;
    private float length[];

    /**
     * Initialize shape
     */
    public VECShape() {super(); }

    /**
     * Initialize a shape with 3 overload data
     * @param x1 the x coordination to draw the shape
     * @param y1 the y coordination to draw the shape
     * @param penColor the chosen color for the Shape
     */
    public VECShape(int x1, int y1, Color penColor) {
        this.x1 =x1;
        this.y1 =y1;
        this.penColor = penColor;
    }

    /**
     * Initialize a shape with 7 overload data
     * @param x1 the first x coordination where the shape should be draw from
     * @param y1 the first y coordination where the shape should be draw from
     * @param x2 the ending x coordination where the shape should be ending
     * @param y2 the ending y coordination where the shape should be ending
     * @param penColor the chosen pen color to draw the shape
     * @param fillColor the chosen fill color to fill the shape
     * @param isFilled to check if the shape is filled or not
     */
    public VECShape(int x1, int y1, int x2, int y2, Color penColor, Color fillColor,boolean isFilled){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.penColor = penColor;
        this.fillColor= fillColor;
        this.isFilled = isFilled;
    }

    /**
     * Accessor and Mutator for the VECShape class
     *
     */
    public void setX1(int x1) { this.x1=x1; }
    public void setY1(int y1) { this.y1=y1; }
    public void setX2(int x2) { this.x2=x2; }
    public void setY2(int y2) { this.y2=y2; }
    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }

    public void setpenColor(Color color) { penColor = color; }

    public Color getpenColor() { return penColor; }

    public void setfillColor(Color color) { fillColor = color; }

    public Color getfillColor() { return fillColor; }

    public void setFilled(boolean isFilled) { isFilled = isFilled; }

    public boolean getFilled() { return isFilled; }

    public void setpenWidth(int width){}

    public int getpenWidth(){return width;}


    /**
     * Draw the object in the given Graphic2D panel.
     * Each shape is implement differently
     * @param drawboard - The graphic object to draw with
     */

    public abstract void draw(Graphics2D drawboard);

}
