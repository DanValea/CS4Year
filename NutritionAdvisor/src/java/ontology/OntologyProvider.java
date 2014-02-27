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
import com.hp.hpl.jena.rdf.model.ModelFactory;
import entity.Nutrients;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import properties.PropertiesLoader;

/**
 *
 * @author Mada
 */
public class OntologyProvider {

    Model model;

    public OntologyProvider(String ontologyFile) throws IOException{
        createModel(ontologyFile);
    }

    private void createModel(String ontologyFile) throws IOException{
        //InputStream in = new FileInputStream(new File(ontologyFile)); 
      InputStream in = new FileInputStream(new File("D:/facultate/licenta/NutritionAdvisor/files/food24.02.2014.owl"));
            model = ModelFactory.createDefaultModel();
            model.read(in, null);
       
    }

    public Nutrients getFoodNutrients(List<String> foods, List<String> providers) {
        String queryString = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
                + "SELECT"
                + " (SUM( ?calories*?quantity/100) AS ?caloriesSum)"
                + " (SUM( ?proteins*?quantity/100) AS ?proteinsSum)"
                + " (SUM( ?carbohydrates*?quantity/100) AS ?carbohydratesSum)"
                + " (SUM( ?fats*?quantity/100) AS ?fatsSum)"
                + " (SUM( ?calcium*?quantity/100) AS ?calciumSum)"
                + " (SUM( ?iron*?quantity/100) AS ?ironSum)"
                + " (SUM( ?sodium*?quantity/100) AS ?sodiumSum)"
                + " (SUM( ?vitaminA*?quantity/100) AS ?vitaminASum)"
                + " (SUM( ?vitaminB*?quantity/100) AS ?vitaminBSum)"
                + " (SUM( ?vitaminC*?quantity/100) AS ?vitaminCSum)"
                + " WHERE { ?food  foaf:name ?foodName"
                + ".?food foaf:provider ?foodProvider"
                + ".?food foaf:contains ?foodComponent"
                + ".?foodComponent foaf:quantity ?quantity"
                + ".?foodComponent foaf:contains ?ingredient"
                + ".?ingredient foaf:name ?ingredientName"
                + ".?ingredient foaf:calories ?calories"
                + ".?ingredient foaf:proteins ?proteins"
                + ".?ingredient foaf:carbohydrates ?carbohydrates"
                + ".?ingredient foaf:fats ?fats"
                + ".?ingredient foaf:calcium ?calcium"
                + ".?ingredient foaf:iron ?iron"
                + ".?ingredient foaf:sodium ?sodium"
                + ".?ingredient foaf:vitaminA ?vitaminA"
                + ".?ingredient foaf:vitaminB ?vitaminB"
                + ".?ingredient foaf:vitaminC ?vitaminC"
                + " FILTER " + getFilterCondition(foods, providers)
                + "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
        
         Nutrients nutrients = new Nutrients();
      /*if (result.hasNext()) {

            QuerySolution binding = result.nextSolution();
            nutrients.setCalories(binding.getLiteral("caloriesSum").getDouble());
            nutrients.setProteins(binding.getLiteral("proteinsSum").getDouble());
            nutrients.setCarbohydrates(binding.getLiteral("carbohydratesSum").getDouble());
            nutrients.setFats(binding.getLiteral("fatsSum").getDouble());
            nutrients.setCalcium(binding.getLiteral("calciumSum").getDouble());
            nutrients.setIron(binding.getLiteral("ironSum").getDouble());
            nutrients.setSodium(binding.getLiteral("sodiumSum").getDouble());
            nutrients.setVitaminA(binding.getLiteral("vitaminASum").getDouble());
            nutrients.setVitaminB(binding.getLiteral("vitaminBSum").getDouble());
            nutrients.setVitaminC(binding.getLiteral("vitaminCSum").getDouble());
        }
         */
        
         if (result.hasNext()) {

         QuerySolution binding = result.nextSolution();
         System.out.println(binding.getLiteral("caloriesSum").getDouble());
         System.out.println(binding.getLiteral("proteinsSum").getDouble());
         System.out.println(binding.getLiteral("carbohydratesSum").getDouble());
         System.out.println(binding.getLiteral("fatsSum").getDouble());
         System.out.println(binding.getLiteral("calciumSum").getDouble());
         System.out.println(binding.getLiteral("ironSum").getDouble());
         System.out.println(binding.getLiteral("sodiumSum").getDouble());
         System.out.println(binding.getLiteral("vitaminASum").getDouble());
         System.out.println(binding.getLiteral("vitaminBSum").getDouble());
         System.out.println(binding.getLiteral("vitaminCSum").getDouble());
         }
         
        qe.close();

        return nutrients;
    }

    private String getFilterCondition(List<String> foods, List<String> providers) {
        String filterCondition = "";
        if (foods.size() > 1) {
            for (int foodComponentIndex = 0; foodComponentIndex < foods.size() - 1; foodComponentIndex++) {
                //string builder
                filterCondition = "(" + filterCondition + "(" + "?foodName= '" + foods.get(foodComponentIndex) + "' && ?foodProvider= '" + providers.get(foodComponentIndex) + "')) || ";
            }
            filterCondition = "(" + filterCondition + "(" + "?foodName='" + foods.get(foods.size() - 1) + "'&& ?foodProvider='" + providers.get(foods.size() - 1) + "'))";

        } else {
            filterCondition = filterCondition + "(" + "?foodName='" + foods.get(0) + "'&& ?foodProvider='" + providers.get(0) + "')";

        }
        return filterCondition;
    }

    public List<Nutrients> getDailyRequieredNutrients(String personName)throws IOException{
        PropertiesLoader propertiesLoader=new PropertiesLoader("files/nutrients.config");
    Nutrients minRequieredNutrients=new Nutrients();
    Nutrients maxRequieredNutrients=new Nutrients();
    minRequieredNutrients.setCalories(Double.valueOf(propertiesLoader.getProperty("minCalories")));
    maxRequieredNutrients.setCalories(Double.valueOf(propertiesLoader.getProperty("maxCalories")));
    minRequieredNutrients.setProteins(Double.valueOf(propertiesLoader.getProperty("minProteins")));
    maxRequieredNutrients.setProteins(Double.valueOf(propertiesLoader.getProperty("maxProteinWs")));
    
    //add all nutrients
    
    List<Nutrients> requieredNutrients=new ArrayList<Nutrients>();
    //check diseases
    
    return requieredNutrients;
    }

}
