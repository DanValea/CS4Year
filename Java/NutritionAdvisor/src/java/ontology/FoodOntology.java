/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import entity.FoodEntryWS;
import entity.FoodWS;
import entity.NutrientsWS;
import entity.UserPreferenceConstraintWS;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mada
 */
public final class FoodOntology {

    public static NutrientsWS getFoodNutrients(HashMap<String, NutrientsWS> ingredients, List<FoodWS> foods) {
        NutrientsWS nutrients = new NutrientsWS();
//        long start = System.currentTimeMillis();
//        System.out.println("time start" + start);
        for (FoodWS f : foods) {
            for (FoodEntryWS foodEntry : f.getFoodEntries()) {
                nutrients.setCalories(ingredients.get(foodEntry.getIngredientName()).getCalories() * foodEntry.getQuantity() / 100.0 + nutrients.getCalories());
                nutrients.setCarbohydrates(ingredients.get(foodEntry.getIngredientName()).getCarbohydrates() * foodEntry.getQuantity() / 100.0 + nutrients.getCarbohydrates());
                nutrients.setProteins(ingredients.get(foodEntry.getIngredientName()).getProteins() * foodEntry.getQuantity() / 100.0 + nutrients.getProteins());
                nutrients.setFats(ingredients.get(foodEntry.getIngredientName()).getFats() * foodEntry.getQuantity() / 100.0 + nutrients.getFats());
                nutrients.setIron(ingredients.get(foodEntry.getIngredientName()).getIron() * foodEntry.getQuantity() / 100.0 + nutrients.getIron());
                nutrients.setSodium(ingredients.get(foodEntry.getIngredientName()).getSodium() * foodEntry.getQuantity() / 100.0 + nutrients.getSodium());
                nutrients.setVitaminA(ingredients.get(foodEntry.getIngredientName()).getVitaminA() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminA());
                nutrients.setVitaminB(ingredients.get(foodEntry.getIngredientName()).getVitaminB() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminB());
                nutrients.setVitaminC(ingredients.get(foodEntry.getIngredientName()).getVitaminC() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminC());
            }

        }
        
        long end = System.currentTimeMillis();
//        System.out.println("time end" + end);
//        System.out.println("time food" + (end - start));
        return nutrients;
    }

    private static NutrientsWS getNutrientsSum(ResultSet result, NutrientsWS nutrients, FoodWS food) {

        while (result.hasNext()) {
            QuerySolution binding = result.nextSolution();
            boolean ingredientFound = false;
            int index = 0;
            while (!ingredientFound && index < food.getFoodEntries().size()) {
                if (food.getFoodEntries().get(index).getIngredientName().equals(binding.getLiteral("ingredientName").getString())) {

                    ingredientFound = true;
                    nutrients.setCalories(binding.getLiteral("calories").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getCalories());
                    nutrients.setProteins(binding.getLiteral("proteins").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getProteins());
                    nutrients.setCarbohydrates(binding.getLiteral("carbohydrates").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getCarbohydrates());
                    nutrients.setFats(binding.getLiteral("fats").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getFats());
                    nutrients.setIron(binding.getLiteral("iron").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getIron());
                    nutrients.setSodium(binding.getLiteral("sodium").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getSodium());
                    nutrients.setVitaminA(binding.getLiteral("vitaminA").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminA());
                    nutrients.setVitaminB(binding.getLiteral("vitaminB").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminB());
                    nutrients.setVitaminC(binding.getLiteral("vitaminC").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminC());
                }
                index++;

            }
            if (!ingredientFound) {
                System.out.println("ingredient not found");
            }
        }
        return nutrients;

        /*
         while (result.hasNext()) {
         QuerySolution binding = result.nextSolution();
            
              
         nutrients.setCalories(binding.getLiteral("calories").getDouble() * foodEntry.getQuantity() / 100.0 + nutrients.getCalories());
         nutrients.setProteins(binding.getLiteral("proteins").getDouble() * foodEntry.getQuantity() / 100.0 + nutrients.getProteins());
         nutrients.setCarbohydrates(binding.getLiteral("carbohydrates").getDouble() * foodEntry.getQuantity() / 100.0 + nutrients.getCarbohydrates());
         nutrients.setFats(binding.getLiteral("fats").getDouble() * foodEntry.getQuantity()/ 100.0 + nutrients.getFats());
         nutrients.setIron(binding.getLiteral("iron").getDouble() * foodEntry.getQuantity()/ 100.0 + nutrients.getIron());
         nutrients.setSodium(binding.getLiteral("sodium").getDouble() * foodEntry.getQuantity() / 100.0 + nutrients.getSodium());
         nutrients.setVitaminA(binding.getLiteral("vitaminA").getDouble() * foodEntry .getQuantity()/ 100.0 + nutrients.getVitaminA());
         nutrients.setVitaminB(binding.getLiteral("vitaminB").getDouble() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminB());
         nutrients.setVitaminC(binding.getLiteral("vitaminC").getDouble() * foodEntry .getQuantity()/ 100.0 + nutrients.getVitaminC());
         }
          
         return nutrients;
         */
    }

    public static boolean foodSolutionRespectsUserConstraints(Model model, List<FoodWS> foods, UserPreferenceConstraintWS userConstraint) {
        String foodFilterCondition = getFoodFilterCondition(foods);

        if (menuHasCategory(model, userConstraint.getFavouriteCategory(), foodFilterCondition)) {
            return !menuHasCategory(model, userConstraint.getDislikedCategory(), foodFilterCondition);
        }
        return false;

    }

    private static String getFoodFilterCondition(List<FoodWS> foods) {

        String filterCondition = "(";

        for (int foodIndex = 0; foodIndex < foods.size() - 1; foodIndex++) {
            //string builder
            filterCondition = filterCondition + "?foodName= '" + foods.get(foodIndex).getName() + "' || ";
        }
        filterCondition = filterCondition + "?foodName= '" + foods.get(foods.size() - 1).getName() + "')";

        return filterCondition;

    }

    private static boolean menuHasCategory(Model model, String category, String foodFilterCondition) {
        String queryString = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + " SELECT ?food WHERE {"
                + "?food  foaf:name ?foodName"
                + "{ ?food rdf:type ?subclass"
                + "}"
                + " UNION {?food rdf:type ?superclass "
                + ".?superclass rdfs:subClassOf ?subclass "
                + "}"
                + "   FILTER (" + foodFilterCondition
                + "  && ?subclass =foaf:" + category + ")"
                + " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
        boolean menuHasCategory = result.hasNext();
        qe.close();
        return menuHasCategory;
    }

}
