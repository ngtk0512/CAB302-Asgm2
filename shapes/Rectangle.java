package shapes;

import java.awt.*;

public class Rectangle extends VECShape {
    public Rectangle(){super();}

    public Rectangle(int x1,int y1, int x2, int y2, Color penColor, Color fillColor,boolean isFilled){
        super(x1,y1,x2,y2,penColor,fillColor,isFilled);
    }

    @Override
    public void draw(Graphics2D drawboard) {
        drawboard.setColor(getpenColor());

        drawboard.drawRect(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()),
                Integer.max(getX1(), getX2())-Integer.min(getX1(),getX2()),
                Integer.max(getY1(),getY2())-Integer.min(getY1(), getY2()));
        if (getFilled()==true){
            drawboard.setColor(getfillColor());
            drawboard.fillRect(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()),
                    Integer.max(getX1(), getX2())-Integer.min(getX1(),getX2()),
                    Integer.max(getY1(),getY2())-Integer.min(getY1(), getY2()));
        }

    }
}
