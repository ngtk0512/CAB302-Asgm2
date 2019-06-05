package shapes;

import java.awt.*;

public class Line extends VECShape {

    public Line(){super();}

    public Line(int x1 , int y1 , int x2 , int y2, Color penColor,Color fillColor,boolean isFilled) {
        super(x1, y1, x2, y2,penColor,fillColor,isFilled);

    }

    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());
        drawboard.drawLine(getX1(),getY1(),getX2(),getY2());
    }


}
