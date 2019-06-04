package shapes;

import java.awt.*;
import java.util.ArrayList;

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
     * Constructors for different shape
     */
    public VECShape()
    {super(); }

    public VECShape(int x1, int y1, Color penColor) {
        setX1(x1);
        setY1(y1);
    }

    public VECShape(int x1, int y1, int x2, int y2, Color penColor, Color fillColor){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.penColor = penColor;
        this.fillColor= fillColor;
    }




    /**
     * Mutator and Accessor method for shapes
     */
    public void setX1(int x1)
    {
        this.x1=x1;
    }
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

    public void setFilled(boolean filled) { isFilled = filled; }

    public boolean getFilled() { return isFilled; }

    public void setpenWidth(int penWidth){}

    public int getpenWidth(){return width;}


    /**
     * Abstract method for drawing the shape that must be overriden
     */

    public abstract void draw(Graphics2D drawboard);

}
