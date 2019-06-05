package VEC;

import exception.VECFormatException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An Abstract class for VEC file parsing which have methods to check if the selected file
 * is in the right format or not
 */
public abstract class VECLoad {
    protected FileReader fr ;
    protected BufferedReader br;

    /**
     * Initializing for reading file
     * @param filePath a file path in form of a String
     * @throws FileNotFoundException if file is not found, throw exception
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
     * Checking whether the given string can be converted to double.
     * @param element the given string
     * @return true if the given string can be converted to double, otherwise return false
     */
    protected boolean isDouble(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checking whether the given string is in Hex color format
     * @param element the given string
     * @return true if the given string is in Hex color format, otherwise return false
     */
    protected boolean isHexColor(String element){
        try {
            Pattern hexcolorPattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
            Matcher m = hexcolorPattern.matcher(element);
            return m.matches();
        } catch (Exception e){
            return false ;
        }
    }

    /**
     * Checking whether the given string is in VEC command format
     * @param element the given string
     * @return true if the given string is in VEC command format, otherwise return false
     */
    protected boolean isCommandString(String element) {
     return ((element != null)
             && (element.matches("^[A-Za-z]*$")));
    }

}
