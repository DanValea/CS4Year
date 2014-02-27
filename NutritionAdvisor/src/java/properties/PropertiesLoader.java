package properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class PropertiesLoader
{
	private Properties prop ;

	public PropertiesLoader(String configFile) throws IOException
	{
		prop= new Properties();

		//InputStream is = new FileInputStream(configFile);

		//prop.load(is);

		
	}
        
        public String getProperty(String property){
        return prop.getProperty(property);
        }
}
