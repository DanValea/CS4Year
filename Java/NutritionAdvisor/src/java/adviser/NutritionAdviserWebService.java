/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adviser;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import entity.Nutrients;
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
    private static final String ontologyConfigFile = "files/configuration-file.config";

    public NutritionAdviserWebService() throws IOException {
        propertiesLoader = new PropertiesLoader(ontologyConfigFile);
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "foodIsSuitable")
    public boolean foodIsSuitable(@WebParam(name = "foods") List<String> foods, @WebParam(name = "providers") List<String> providers, @WebParam(name = "diseases") List<String> diseases) throws IOException {
        /*foods = new ArrayList<>(); foods.add("Apple soup");providers = new ArrayList<>();providers.add("Provider_1");
         foods.add("Beef sour soup with cabbage");
         foods.add("Vegetables with grilled sirloin beef");
         providers.add("Provider_1");
         providers.add("Provider_1");
         */
        OntologyProvider ontologyProvider = new OntologyProvider(propertiesLoader.getProperty("ontology_file"));
        Nutrients nutrientsSum = ontologyProvider.getFoodNutrients(foods, providers);
         /*diseases=new ArrayList<String>();
         diseases.add("Hypertension");
         */
        List<Nutrients> dailyRequiredNutrients = ontologyProvider.getDailyRequieredNutrients(diseases);
        return dailyNutrientsIntakeIsReached(nutrientsSum, dailyRequiredNutrients);

    }

    private boolean dailyNutrientsIntakeIsReached(Nutrients nutrientsSum, List<Nutrients> dailyRequiredNutrients) {
        System.out.println("Calori:" + nutrientsSum.getCalories() + " Proteine:" + nutrientsSum.getProteins()
                + " Carbo:" + nutrientsSum.getCarbohydrates() + " Fats:" + nutrientsSum.getFats() + 
                //" Ca:"
                //+ nutrientsSum.getCalcium() +
                " Fe:" + nutrientsSum.getIron() + " Na:"
                + nutrientsSum.getSodium() + " vitA:" + nutrientsSum.getVitaminA() + " vitB:"
                + nutrientsSum.getVitaminB() + " vitC:" + nutrientsSum.getVitaminC());
        if (nutrientsSum.getCalories() < dailyRequiredNutrients.get(0).getCalories() || nutrientsSum.getCalories() > dailyRequiredNutrients.get(1).getCalories()
                || nutrientsSum.getProteins() < dailyRequiredNutrients.get(0).getProteins() || nutrientsSum.getProteins() > dailyRequiredNutrients.get(1).getProteins()
                || nutrientsSum.getCarbohydrates() < dailyRequiredNutrients.get(0).getCarbohydrates() || nutrientsSum.getCarbohydrates() > dailyRequiredNutrients.get(1).getCarbohydrates()
                || nutrientsSum.getFats() < dailyRequiredNutrients.get(0).getFats() || nutrientsSum.getFats() > dailyRequiredNutrients.get(1).getFats()
//                || nutrientsSum.getCalcium() < dailyRequiredNutrients.get(0).getCalcium() || nutrientsSum.getCalcium() > dailyRequiredNutrients.get(1).getCalcium()
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
