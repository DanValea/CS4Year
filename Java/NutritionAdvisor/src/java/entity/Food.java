/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.List;

/**
 *
 * @author Mada
 */
public class Food {
    
     private String name;
     private String description;
     private FoodType type;
     private FoodMeal meal;
     private List<FoodEntry> foodEntries;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public FoodType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(FoodType type) {
        this.type = type;
    }

    /**
     * @return the meal
     */
    public FoodMeal getMeal() {
        return meal;
    }

    /**
     * @param meal the meal to set
     */
    public void setMeal(FoodMeal meal) {
        this.meal = meal;
    }

    /**
     * @return the foodEntries
     */
    public  List<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    /**
     * @param foodEntries the foodEntries to set
     */
    public void setFoodEntries(List<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }
     

}
