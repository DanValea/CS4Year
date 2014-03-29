/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import entity.FoodWS;
import entity.NutrientsWS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mada
 */
public final class IngredientOntology {
    
    private static final String SELECT_ALL_INGREDIENTS_QUERY = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
            + "SELECT"
            + " ?ingredientName ?calories ?proteins ?carbohydrates ?fats"
            + " ?iron ?sodium ?vitaminA ?vitaminB ?vitaminC"
            + " WHERE { ?food  foaf:name ?foodName"
            + ".?food foaf:contains ?ingredient"
            + ".?ingredient foaf:name ?ingredientName"
            + ".?ingredient foaf:calories ?calories"
            + ".?ingredient foaf:proteins ?proteins"
            + ".?ingredient foaf:carbohydrates ?carbohydrates"
            + ".?ingredient foaf:fats ?fats"
            + ".?ingredient foaf:iron ?iron"
            + ".?ingredient foaf:sodium ?sodium"
            + ".?ingredient foaf:vitaminA ?vitaminA"
            + ".?ingredient foaf:vitaminB ?vitaminB"
            + ".?ingredient foaf:vitaminC ?vitaminC"
            + "}";
    
    public static HashMap<String,NutrientsWS> getAllIngredients(Model model) {
        
        Query query = QueryFactory.create(SELECT_ALL_INGREDIENTS_QUERY);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
        HashMap<String,NutrientsWS> ingredients = new HashMap<String,NutrientsWS>();
        while (result.hasNext()) {
            QuerySolution binding = result.nextSolution();
            NutrientsWS nutrients = new NutrientsWS();
            nutrients.setCalories(binding.getLiteral("calories").getDouble());
            nutrients.setCarbohydrates(binding.getLiteral("carbohydrates").getDouble());
            nutrients.setProteins(binding.getLiteral("proteins").getDouble());
            nutrients.setFats(binding.getLiteral("fats").getDouble());
            nutrients.setIron(binding.getLiteral("iron").getDouble());
            nutrients.setSodium(binding.getLiteral("sodium").getDouble());
            nutrients.setVitaminA(binding.getLiteral("vitaminA").getDouble());
            nutrients.setVitaminB(binding.getLiteral("vitaminB").getDouble());
            nutrients.setVitaminC(binding.getLiteral("vitaminC").getDouble());
            
            ingredients.put(binding.getLiteral("ingredientName").getString(), nutrients);
        }
        
        qe.close();
        
        return ingredients;
    }
    
   
}
