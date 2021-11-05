package utilies;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class FileReader {
    public static String laodFileIntoString(String filePath,String encoding) throws FileNotFoundException, IOException{
    return IOUtils.toString(new FileInputStream(filePath),encoding);
    }
}
