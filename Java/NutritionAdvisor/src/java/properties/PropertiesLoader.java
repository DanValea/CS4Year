package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private Properties prop;

    public PropertiesLoader(String configFile) throws IOException {

        prop = new Properties();
        InputStream resource = this.getClass().getResource(configFile).openStream();
	prop.load(resource);
    }


    public String getProperty(String property) {
        return prop.getProperty(property);
    }
}
