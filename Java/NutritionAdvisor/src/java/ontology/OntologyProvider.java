/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import properties.PropertiesLoader;

/**
 *
 * @author Mada
 */
public class OntologyProvider {

    private static OntologyProvider instance = null;
    private Model model;
    
    private static final String ontologyConfigFile = "files/configuration-file.config";

    protected OntologyProvider() {
        try {
            PropertiesLoader propertiesLoader = new PropertiesLoader(ontologyConfigFile);
            createModel(propertiesLoader.getProperty("ontology_file"));
        } catch (IOException ex) {
            Logger.getLogger(OntologyProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OntologyProvider getInstance() {
        if (instance == null) {
            
            instance = new OntologyProvider();
        }
        return instance;
    }

    private void createModel(String ontologyFile) throws IOException {
        InputStream in = new FileInputStream(new File(ontologyFile));
        setModel(ModelFactory.createDefaultModel());
        getModel().read(in, null);

    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

}
