/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mada
 */
@XmlType(name="FoodWS") 
public class FoodWS implements Serializable{
    
     private String name;
    
     private List<FoodEntryWS> foodEntries;

   
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
     * @return the foodEntries
     */
    public List<FoodEntryWS> getFoodEntries() {
        return foodEntries;
    }

    /**
     * @param foodEntries the foodEntries to set
     */
    public void setFoodEntries(List<FoodEntryWS> foodEntries) {
        this.foodEntries = foodEntries;
    }

   
     

}
