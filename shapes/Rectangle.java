package shapes;

import java.awt.*;

public class Rectangle extends VECShape {
    public Rectangle(){super();}

    public Rectangle(int x1,int y1, int x2, int y2, Color penColor, Color fillColor){
        super(x1,y1,x2,y2,penColor,fillColor);
    }

    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());
        drawboard.drawLine(getX2(), getY2(), getX1(), getY2());
        drawboard.drawLine(getX1(), getY2(), getX1(), getY1());
        drawboard.drawLine(getX1(), getY1(), getX2(), getY1());
        drawboard.drawLine(getX2(), getY1(), getX2(), getY2());
    }
}
