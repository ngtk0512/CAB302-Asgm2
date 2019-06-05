package shapes;

import java.awt.*;

public class Plot extends VECShape {

    public Plot(){super();}

    public Plot(int x1 , int y1 ,Color penColor) {
        super(x1, y1,penColor);
    }

    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());
        drawboard.fillOval(getX1(),getY1(),5,5);
    }
}