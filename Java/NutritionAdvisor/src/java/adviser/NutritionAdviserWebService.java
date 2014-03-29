/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adviser;

import entity.FoodSolutionWS;
import entity.NutrientRestrictionWS;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import entity.NutrientsWS;
import entity.PersonWS;
import entity.UserPreferenceConstraintWS;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import ontology.DiseaseOntology;
import ontology.FoodOntology;
import ontology.IngredientOntology;
import ontology.OntologyProvider;
import properties.PropertiesLoader;

/**
 *
 * @author Mada
 */
@WebService(serviceName = "NutritionAdviserWebService")
public class NutritionAdviserWebService {

    private PropertiesLoader propertiesLoader;
    private OntologyProvider ontologyProvider;
    private HashMap<String, NutrientsWS> ingredients;
    private static final String ontologyConfigFile = "files/configuration-file.config";

    public NutritionAdviserWebService() throws IOException {
        propertiesLoader = new PropertiesLoader(ontologyConfigFile);
        ontologyProvider = new OntologyProvider(propertiesLoader.getProperty("ontology_file"));

        long start = System.currentTimeMillis();
        
        ingredients = IngredientOntology.getAllIngredients(ontologyProvider.getModel());

        long end = System.currentTimeMillis();
        System.out.println("time ingr" + (end - start));
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "computeNutrientsSum")
    public NutrientsWS computeNutrientsSum(@WebParam(name = "foodSolution") FoodSolutionWS foodSolution) {

        // long start=System.currentTimeMillis();
        NutrientsWS nutrientsSum = FoodOntology.getFoodNutrients(ingredients, foodSolution.getFoodWS());
       // foodSolution=NutrientsFoodSolutionConverter.convertNutrientsToFoodSolution(nutrientsSum, foodSolution);

        // long end= System.currentTimeMillis(); 
        // System.out.println("time"+(end-start));
        return nutrientsSum;

    }

    @WebMethod(operationName = "foodSolutionRespectsUserConstraints")
    public boolean foodSolutionRespectsUserConstraints(@WebParam(name = "foodSolutionWS") FoodSolutionWS foodSolution, @WebParam(name = "userConstraint") UserPreferenceConstraintWS userConstraint) {

        return FoodOntology.foodSolutionRespectsUserConstraints(ontologyProvider.getModel(), foodSolution.getFoodWS(), userConstraint);

    }

    @WebMethod(operationName = "getNutrientsRestrictions")
    public List<NutrientRestrictionWS> getNutrientsRestrictions(@WebParam(name = "person") PersonWS person) {

        return DiseaseOntology.getNutrientsRestrictions(ontologyProvider.getModel(), person);

    }
}
