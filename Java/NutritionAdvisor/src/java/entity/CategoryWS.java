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
@XmlType(name = "CategoryWS")
public class CategoryWS {

    private String name;
    private List<CategoryWS> subcategories;

    public CategoryWS(String name) {

        this.name = name;
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
     * @return the subcategories
     */
    public List<CategoryWS> getSubcategories() {
        return subcategories;
    }

    /**
     * @param subcategories the subcategories to set
     */
    public void setSubcategories(List<CategoryWS> subcategories) {
        this.subcategories = subcategories;
    }

}
