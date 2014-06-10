/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mada
 */
@XmlType(name="FoodEntryWS") 
public class FoodEntryWS implements Serializable{
        private String ingredientName;
        private Double quantity;

    /**
     * @return the ingredientName
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * @param ingredientName the ingredientName to set
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
}
