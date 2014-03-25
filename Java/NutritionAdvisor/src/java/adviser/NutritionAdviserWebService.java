/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adviser;

import converter.NutrientsFoodSolutionConverter;
import entity.FoodSolutionWS;
import entity.FoodWS;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import entity.Nutrients;
import entity.PersonWS;
import java.io.IOException;
import java.util.ArrayList;
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
    private OntologyProvider ontologyProvider;
    private static final String ontologyConfigFile = "files/configuration-file.config";

    public NutritionAdviserWebService() throws IOException {
        propertiesLoader = new PropertiesLoader(ontologyConfigFile);
         ontologyProvider= new OntologyProvider(propertiesLoader.getProperty("ontology_file"));
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "foodIsSuitable")
    public FoodSolutionWS foodIsSuitable(@WebParam(name = "foodSolution") FoodSolutionWS foodSolution, @WebParam(name = "person") PersonWS  person) throws IOException {
       long start=System.currentTimeMillis();
        Nutrients nutrientsSum = ontologyProvider.getFoodNutrients(foodSolution.getFoodWS());
        List<Nutrients> dailyRequiredNutrients = ontologyProvider.getDailyRequieredNutrients(person);
        foodSolution=NutrientsFoodSolutionConverter.convertNutrientsToFoodSolution(nutrientsSum, foodSolution);
        foodSolution.setIsSuitable(dailyNutrientsIntakeIsReached(nutrientsSum, dailyRequiredNutrients));
        long end= System.currentTimeMillis();
        System.out.println("time"+(end-start));
        return foodSolution;

    }

    private boolean dailyNutrientsIntakeIsReached(Nutrients nutrientsSum, List<Nutrients> dailyRequiredNutrients) {
       /* System.out.println("Calori:" + nutrientsSum.getCalories() + " Proteine:" + nutrientsSum.getProteins()
                + " Carbo:" + nutrientsSum.getCarbohydrates() + " Fats:" + nutrientsSum.getFats() + 
                
                " Fe:" + nutrientsSum.getIron() + " Na:"
                + nutrientsSum.getSodium() + " vitA:" + nutrientsSum.getVitaminA() + " vitB:"
                + nutrientsSum.getVitaminB() + " vitC:" + nutrientsSum.getVitaminC());
       */ if (nutrientsSum.getCalories() < dailyRequiredNutrients.get(0).getCalories() || nutrientsSum.getCalories() > dailyRequiredNutrients.get(1).getCalories()
                || nutrientsSum.getProteins() < dailyRequiredNutrients.get(0).getProteins() || nutrientsSum.getProteins() > dailyRequiredNutrients.get(1).getProteins()
                || nutrientsSum.getCarbohydrates() < dailyRequiredNutrients.get(0).getCarbohydrates() || nutrientsSum.getCarbohydrates() > dailyRequiredNutrients.get(1).getCarbohydrates()
                || nutrientsSum.getFats() < dailyRequiredNutrients.get(0).getFats() || nutrientsSum.getFats() > dailyRequiredNutrients.get(1).getFats()
              || nutrientsSum.getIron() < dailyRequiredNutrients.get(0).getIron() || nutrientsSum.getIron() > dailyRequiredNutrients.get(1).getIron()
                || nutrientsSum.getSodium() < dailyRequiredNutrients.get(0).getSodium() || nutrientsSum.getSodium() > dailyRequiredNutrients.get(1).getSodium()
                || nutrientsSum.getVitaminA() < dailyRequiredNutrients.get(0).getVitaminA() || nutrientsSum.getVitaminA() > dailyRequiredNutrients.get(1).getVitaminA()
                || nutrientsSum.getVitaminB() < dailyRequiredNutrients.get(0).getVitaminB() || nutrientsSum.getVitaminB() > dailyRequiredNutrients.get(1).getVitaminB()
                || nutrientsSum.getVitaminC() < dailyRequiredNutrients.get(0).getVitaminC() || nutrientsSum.getVitaminC() > dailyRequiredNutrients.get(1).getVitaminC()) {
            return false;
        }
        return true;
    }

}
