/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Mada
 */
public enum FoodMeal {
 
    
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner");

    private String name;

    private FoodMeal(String name) {
        this.name = name;
    }

    public String getMealName() {
        return name;
    }
    
}
