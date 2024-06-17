package Request;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileClass {
	
	static File file = new File("./PropertyFolder/PropertyFile.properties");
    static FileInputStream fi = null;
    static Properties prop = new Properties();
    static {
        try {
            fi = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        try {
            prop.load(fi);
        } catch (IOException el) {
            el.printStackTrace();

        }
    }
}
