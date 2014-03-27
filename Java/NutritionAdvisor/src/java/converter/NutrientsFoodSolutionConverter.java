/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import entity.FoodSolutionWS;

/**
 *
 * @author Mada
 */
public final class NutrientsFoodSolutionConverter {
    
    public static FoodSolutionWS convertNutrientsToFoodSolution(entity.NutrientsWS nutrients,FoodSolutionWS foodSolution){
        foodSolution.setCalories((int)nutrients.getCalories());
        foodSolution.setCarbohydrates(nutrients.getCarbohydrates());
        foodSolution.setProteins(nutrients.getProteins());
        foodSolution.setFats(nutrients.getFats());
        foodSolution.setIron(nutrients.getIron());
        foodSolution.setSodium(nutrients.getSodium());
        foodSolution.setVitaminA(nutrients.getVitaminA());
        foodSolution.setVitaminB(nutrients.getVitaminB());
        foodSolution.setVitaminC(nutrients.getVitaminC());
        return foodSolution;
    }
    
}
