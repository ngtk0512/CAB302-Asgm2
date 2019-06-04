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
        for(int i=0; i < commands.size(); i++){
            for(int j=0; j < commands.get(i).size(); j++){
//                System.out.println(commands.get(i).get(j));
            }
        }
        return commands;
    }


    /**
     * Validate if the given line is VEC command and
     * @param line a given string
     * @return
     */
    @Override
    protected boolean validContent(String line) throws VECFormatException {
        String[] elements = line.split(" ");


        for (String element : elements){
            System.out.println(element);

            if (!isHexColor(element) && !isCommandString(element) && !isDouble(element) ){
                throw new VECFormatException("Invalid file command");
            }
        }
        if (elements.length == 2 && isCommandString(elements[0]) && isHexColor(elements[1])) {
            return true;
        } else if (elements.length == 3 && isCommandString(elements[0])
                && isDouble(elements[1]) && isDouble(elements[2])) {
            return true;
        } else if (elements.length > 3 && isOdd(elements.length)
                && isCommandString(elements[0])) {
            for (int i = 1; i < elements.length; i++) {
                if (!isDouble(elements[i])) {
                    return false;
                } else
                    return true;
            }
        }

        return false;
        }


}
