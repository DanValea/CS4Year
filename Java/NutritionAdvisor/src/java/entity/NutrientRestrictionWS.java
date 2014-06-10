/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Mada
 */
public class NutrientRestrictionWS implements Serializable{

    protected String nutrientName;
    protected double minValue;
    protected double maxValue;

    public NutrientRestrictionWS(String name, double min,double max) {
        this.nutrientName = name;
        this.minValue=min;
        this.maxValue=max;
    }

    

    /**
     * @return the nutrientName
     */
    public String getNutrientName() {
        return nutrientName;
    }

    /**
     * @param nutrientName the nutrientName to set
     */
    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    /**
     * @return the minValue
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
}
