/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adviser;

import entity.CategoryWS;
import entity.DiseaseWS;
import entity.FoodWS;
import entity.NutrientRestrictionWS;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import entity.NutrientsWS;
import entity.UserPreferenceConstraintWS;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import ontology.DiseaseOntology;
import ontology.FoodOntology;
import ontology.IngredientOntology;

/**
 *
 * @author Mada
 */
@WebService(serviceName = "NutritionAdviserWebService")
public class NutritionAdviserWebService {

    private HashMap<String, NutrientsWS> ingredients;

    public NutritionAdviserWebService() throws IOException {

        //long start = System.currentTimeMillis();
        ingredients = IngredientOntology.getAllIngredients();

        //long end = System.currentTimeMillis();
        //System.out.println("time ingr" + (end - start));
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "computeNutrientsSum")
    public NutrientsWS computeNutrientsSum(@WebParam(name = "foods") List<FoodWS> foods) {

        // long start=System.currentTimeMillis();
        NutrientsWS nutrientsSum = FoodOntology.getFoodNutrients(ingredients, foods);

        // long end= System.currentTimeMillis(); 
        // System.out.println("time"+(end-start));
        return nutrientsSum;

    }

    @WebMethod(operationName = "foodSolutionRespectsUserConstraints")
    public boolean foodSolutionRespectsUserConstraints(@WebParam(name = "foods") List<FoodWS> foods, @WebParam(name = "userConstraint") UserPreferenceConstraintWS userConstraint) {

        return FoodOntology.foodSolutionRespectsUserConstraints(foods, userConstraint);

    }

    @WebMethod(operationName = "getNutrientsRestrictions")
    public List<NutrientRestrictionWS> getNutrientsRestrictions(@WebParam(name = "diseases") List<String> diseases) {

        return DiseaseOntology.getNutrientsRestrictions(diseases);

    }

    @WebMethod(operationName = "getCategories")
    public List<CategoryWS> getCategories() {

        return FoodOntology.getCategories();

    }
}
