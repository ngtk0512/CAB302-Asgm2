package PaintMain;

import VEC.VECLoadFile;
import exception.VECFormatException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Back end of the application
 * -Load vec file
 */
public class Backend {

    public Backend() {
    }

//    CMD .....
//
//    CMD == FILL || PEN
//            param = Colourcode
//    CMD == RECTANGLE || LINE
//            param = x_from, y_from, x_to, y_to
//    CMD == PLOT
//            param = x, y
//
//
//    array = split(line, " ")
//if (array[0] == FILL || ){
//        set colourpen = array[1]
//    }
//

    /**
     * The method use VECLoadFile class to read a given file. VECLoadFile return an array of commands
     * These commands will be used to draw picture to drawboard
     * @param filePath
     */
    public ArrayList<ArrayList<String>> loadCommands(String filePath) throws VECFormatException, IOException {
        VECLoadFile vec = new VECLoadFile(filePath);
        ArrayList<ArrayList<String>> commands = vec.loadFile();
        return commands;
    }

}
