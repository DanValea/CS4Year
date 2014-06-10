/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mada
 */

@XmlType(name="UserPreferenceConstraintWS") 
public class UserPreferenceConstraintWS {
    
    private String favouriteCategory;
    private String dislikedCategory;
    
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
}
