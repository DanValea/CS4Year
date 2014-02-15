import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class PropertiesLoader
{
	private String configFile = "files/configuration-file.config";

	public static String realOntologyFile;
	public static String virtualOntologyFile;

	public PropertiesLoader() throws IOException
	{
		Properties prop = new Properties();

		InputStream is = new FileInputStream(configFile);

		prop.load(is);

		realOntologyFile = prop.getProperty("real_ontology_file");
		virtualOntologyFile = prop.getProperty("virtual_ontology_file");
	}
}
