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
public enum FoodType {

    SOUP("Soup"),
    MAIN_DISH("Main dish"),
    DESSERT("Dessert");

    private String name;

    private FoodType(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return name;
    }
}
