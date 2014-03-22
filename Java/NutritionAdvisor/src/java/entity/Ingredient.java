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
public class Ingredient {
    
    private String name;
    private Nutrients nutrients;

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
     * @return the nutrients
     */
    public Nutrients getNutrients() {
        return nutrients;
    }

    /**
     * @param nutrients the nutrients to set
     */
    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }
    
    
}
