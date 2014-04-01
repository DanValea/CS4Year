/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mada
 */
@XmlType(name="DiseaseWS") 
public class DiseaseWS implements Serializable{
    
    protected String name;
    protected List<NutrientRestrictionWS> nutrientRestrictions;

    public DiseaseWS(){
    nutrientRestrictions=new ArrayList<NutrientRestrictionWS>();
    }
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
     * @return the nutrientRestrictions
     */
    public List<NutrientRestrictionWS> getNutrientRestrictions() {
        return nutrientRestrictions;
    }

    /**
     * @param nutrientRestrictions the nutrientRestrictions to set
     */
    public void setNutrientRestrictions(List<NutrientRestrictionWS> nutrientRestrictions) {
        this.nutrientRestrictions = nutrientRestrictions;
    }
}
