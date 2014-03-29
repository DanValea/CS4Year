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
public class NutrientRestrictionWS {

    private String restrictionName;
    private Double value;

    public NutrientRestrictionWS(String bindingVar, double aDouble) {
        this.restrictionName = bindingVar;
        this.value = value;
    }

    /**
     * @return the restrictionName
     */
    public String getRestrictionName() {
        return restrictionName;
    }

    /**
     * @param restrictionName the restrictionName to set
     */
    public void setRestrictionName(String restrictionName) {
        this.restrictionName = restrictionName;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
