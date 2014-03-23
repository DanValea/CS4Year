/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mada
 */
@XmlType(name="FoodSolutionWS")
public class FoodSolutionWS {
    private int maxTime;
    private double totalPrice;
     private int calories;
    private double proteins;
    private double carbohydrates;
    private double fats;
    private double iron;
    private double sodium;
    private double vitaminA;
    private double vitaminB;
    private double vitaminC;
    private boolean isSuitable;
    private List<FoodWS> foodWS;

    /**
     * @return the maxTime
     */
    public int getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * @return the proteins
     */
    public double getProteins() {
        return proteins;
    }

    /**
     * @param proteins the proteins to set
     */
    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    /**
     * @return the carbohydrates
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * @param carbohydrates the carbohydrates to set
     */
    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    /**
     * @return the fats
     */
    public double getFats() {
        return fats;
    }

    /**
     * @param fats the fats to set
     */
    public void setFats(double fats) {
        this.fats = fats;
    }

    /**
     * @return the iron
     */
    public double getIron() {
        return iron;
    }

    /**
     * @param iron the iron to set
     */
    public void setIron(double iron) {
        this.iron = iron;
    }

    /**
     * @return the sodium
     */
    public double getSodium() {
        return sodium;
    }

    /**
     * @param sodium the sodium to set
     */
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    /**
     * @return the vitaminA
     */
    public double getVitaminA() {
        return vitaminA;
    }

    /**
     * @param vitaminA the vitaminA to set
     */
    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }

    /**
     * @return the vitaminB
     */
    public double getVitaminB() {
        return vitaminB;
    }

    /**
     * @param vitaminB the vitaminB to set
     */
    public void setVitaminB(double vitaminB) {
        this.vitaminB = vitaminB;
    }

    /**
     * @return the vitaminC
     */
    public double getVitaminC() {
        return vitaminC;
    }

    /**
     * @param vitaminC the vitaminC to set
     */
    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    /**
     * @return the isSuitable
     */
    public boolean isIsSuitable() {
        return isSuitable;
    }

    /**
     * @param isSuitable the isSuitable to set
     */
    public void setIsSuitable(boolean isSuitable) {
        this.isSuitable = isSuitable;
    }

    /**
     * @return the foodWS
     */
    public List<FoodWS> getFoodWS() {
        return foodWS;
    }

    /**
     * @param foodWS the foodWS to set
     */
    public void setFoodWS(List<FoodWS> foodWS) {
        this.foodWS = foodWS;
    }
    
}
