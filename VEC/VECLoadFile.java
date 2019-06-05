package VEC;

import exception.VECFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * VECLoad subclass for read data from an .VEC file
 */
public class VECLoadFile extends VECLoad {

    /**
     * Initital for file reading
     *
     * @param filePath
     */
    public VECLoadFile(String filePath) throws FileNotFoundException,VECFormatException {
        super(filePath);
    }

    /**
     * Reading the given .VEC file and returning an array of items
     */

    public ArrayList<ArrayList<String>> loadFile() throws IOException, VECFormatException {
        ArrayList<ArrayList<String>>  commands = new ArrayList<ArrayList<String>> ();
        String line = br.readLine();

        while(line!=null){
            ArrayList<String> elements = new ArrayList<String>(Arrays.asList(line.split(" ")));
            if (validContent(line)) {
                commands.add(elements);
                line = br.readLine();
            }
        }
        br.close();
        fr.close();

        return commands;
    }


    /**
     * Validate if the given line is VEC command and
     * @param line a given string
     * @return
     */
    @Override
    protected boolean validContent(String line) throws VECFormatException,RuntimeException {
        String[] elements = line.split(" ");

        for (String element : elements){

            if(isHexColor(element) || isCommandString(element) || !isDouble(element)){
                return true;
            } else {
                throw new VECFormatException("Invalid file command");
            }
        }
        return false;
    }
}
