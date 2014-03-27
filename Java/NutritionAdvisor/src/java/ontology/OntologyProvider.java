/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import  com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.ARQConstants;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
import entity.CategoryWs;
import entity.DiseaseWS;
import entity.FoodEntryWS;
import entity.FoodWS;
import entity.NutrientsWS;
import entity.PersonWS;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import properties.PropertiesLoader;

/**
 *
 * @author Mada
 */
public class OntologyProvider {

    private Model model;

    public OntologyProvider(String ontologyFile) throws IOException {
        createModel(ontologyFile);
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
