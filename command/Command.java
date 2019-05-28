package command;

/**
 * An command
 */
public class Command {
    private String name;
    private String color;
    private double pastX,pastY,currentX,currentY;

    /**
     * Constructor for PEN and FILL command
     */
    public Command(String name,String color){
        this.name = name;
        this.color = color;
    }

    /**
     * Constructor for PLOT command
     */
    public Command(String name,double pastX, double pastY){
        this.name = name;
        this.pastX = pastX;
        this.pastY = pastY;
    }

    /**
     * Constructor for LINE command
     * @param name
     *
     */
    public Command(String name,double pastX,double pastY,double currentX, double currentY){
        this.name = name;
        this.pastX = pastX;
        this.pastY = pastY;
        this.currentX = currentX;
        this.currentY = currentY;
    }


}
