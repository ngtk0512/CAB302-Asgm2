//package command;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
///**
// * An command
// */
//public class Command {
//    ArrayList<ArrayList<String>> commands;
//    JPanel canvas;
//
//    public Command(ArrayList<ArrayList<String>> c){
//        commands = c;
//    }
//
////    public void process(JPanel canvas){
////        this.canvas = canvas;
////        this.canvas.
////        for(int i = 0 ; i < commands.size(); i++)
////            drawLine(commands.get(i));
////    }
//
////    public void drawLine(ArrayList<String> elements){
////        if(elements.get(0) == "LINE"){
////            Graphics2D graphics2D;
////            graphics2D.drawLine();
////        }
////    }
//
//
////    private String name;
////    private String color;
////    private double pastX,pastY,currentX,currentY;
////
////    /**
////     * Constructor for PEN and FILL command
////     */
////    public Command(String name,String color){
////        this.name = name;
////        this.color = color;
////    }
////
////    /**
////     * Constructor for PLOT command
////     */
////    public Command(String name,double pastX, double pastY){
////        this.name = name;
////        this.pastX = pastX;
////        this.pastY = pastY;
////    }
////
////    /**
////     * Constructor for LINE command
////     * @param name
////     *
////     */
////    public Command(String name,double pastX,double pastY,double currentX, double currentY){
////        this.name = name;
////        this.pastX = pastX;
////        this.pastY = pastY;
////        this.currentX = currentX;
////        this.currentY = currentY;
////    }
//
//
//}
