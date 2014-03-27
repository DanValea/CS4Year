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
public class UserPreferenceConstraintWS {
 private String favouriteCategory;
 private String dislikedCategory;
 
 private boolean menuHasFavouriteCategory;
 private boolean menuHasDislikedCategory;   

    /**
     * @return the favouriteCategory
     */
    public String getFavouriteCategory() {
        return favouriteCategory;
    }

    /**
     * @param favouriteCategory the favouriteCategory to set
     */
    public void setFavouriteCategory(String favouriteCategory) {
        this.favouriteCategory = favouriteCategory;
    }

    /**
     * @return the dislikedCategory
     */
    public String getDislikedCategory() {
        return dislikedCategory;
    }

    /**
     * @param dislikedCategory the dislikedCategory to set
     */
    public void setDislikedCategory(String dislikedCategory) {
        this.dislikedCategory = dislikedCategory;
    }

    /**
     * @return the menuHasFavouritCategory
     */
    public boolean isMenuHasFavouriteCategory() {
        return menuHasFavouriteCategory;
    }

    /**
     * @param menuHasFavouritCategory the menuHasFavouritCategory to set
     */
    public void setMenuHasFavouriteCategory(boolean menuHasFavouriteCategory) {
        this.menuHasFavouriteCategory = menuHasFavouriteCategory;
    }

    /**
     * @return the menuHasDislikedCategory
     */
    public boolean isMenuHasDislikedCategory() {
        return menuHasDislikedCategory;
    }

    /**
     * @param menuHasDislikedCategory the menuHasDislikedCategory to set
     */
    public void setMenuHasDislikedCategory(boolean menuHasDislikedCategory) {
        this.menuHasDislikedCategory = menuHasDislikedCategory;
    }
}
