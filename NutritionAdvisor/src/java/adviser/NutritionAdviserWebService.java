/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adviser;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
import entity.Nutrients;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ontology.OntologyProvider;
import properties.PropertiesLoader;
/**
 *
 * @author Mada
 */
@WebService(serviceName = "NutritionAdviserWebService")
public class NutritionAdviserWebService {

    private PropertiesLoader propertiesLoader;
    private String ontologyConfigFile = "files/configuration-file.config";

    public NutritionAdviserWebService() throws IOException {
        propertiesLoader=new PropertiesLoader(ontologyConfigFile);
    }


    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "foodIsSuitable")
    public boolean foodIsSuitable(@WebParam(name = "foods") List<String> foods, @WebParam(name = "providers") List<String> providers) throws  IOException{

        foods = new ArrayList<>();
        foods.add("Apple soup");
        foods.add("Goulash soup");
        providers = new ArrayList<>();
        providers.add("provider_1");
        providers.add("provider_2");
        OntologyProvider ontologyProvider=new OntologyProvider(propertiesLoader.getProperty("ontology_file"));

        Nutrients solutionNutrients=ontologyProvider.getFoodNutrients(foods, providers);
        String personName="x";
        List<Nutrients> dailyRequiredNutrients=ontologyProvider.getDailyRequieredNutrients(personName);
   
            return false;
        }

    

  
}
