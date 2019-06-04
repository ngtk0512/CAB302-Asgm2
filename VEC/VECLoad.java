package VEC;

import exception.VECFormatException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class VECLoad {
    protected FileReader fr ;
    protected BufferedReader br;
    /**
     * Initital for file reading
     */
    public VECLoad(String filePath) throws FileNotFoundException{
        fr = new FileReader(filePath);
        br = new BufferedReader(fr);
    }

    /**
     * An method for checking whether a content of a given file is correct or not.
     * @param line a given string
     * @return return true if the line is in correct format, else return false+
     */
    protected abstract boolean validContent(String line) throws VECFormatException;

    /**
     * Checking whether a given string can be converted to a double.
     * @param element a given string
     * @return true if a given string can be converted to a double, otherwise return false
     */
    protected boolean isDouble(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    protected boolean isHexColor(String element){
        try {
            Pattern hexcolorPattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
            Matcher m = hexcolorPattern.matcher(element);
            return m.matches();
        } catch (Exception e){
            return false ;
        }
    }

    protected boolean isCommandString(String element) {
     return ((element != null)
             && (!element.equals(""))
             && (element.matches("^[A-Z]*$")));
    }

    protected boolean isOdd(int length) {
        try {
            return length % 2 != 0;
        } catch (NumberFormatException e){
            return false ;
        }
    }

}
