import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config { 
    public Config() throws IOException {
        Properties prop = new Properties();
        FileInputStream propFile = null;
        
        try {
			propFile = new FileInputStream("config.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
 
        /*InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }*/
        
        try {
			prop.load(propFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Setting the system property that we just loaded along with default
		// system properties

		System.setProperties(prop);
        //return "pos."+prop.getProperty("name");
    }
}

