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
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.ARQConstants;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
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

    public OntologyProvider(String ontologyFile) throws IOException {
        createModel(ontologyFile);
    }

    private void createModel(String ontologyFile) throws IOException {
        InputStream in = new FileInputStream(new File(ontologyFile));
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
             //   + " (SUM( ?calcium*?quantity/100) AS ?calciumSum)"
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
               // + ".?ingredient foaf:calcium ?calcium"
                + ".?ingredient foaf:iron ?iron"
                + ".?ingredient foaf:sodium ?sodium"
                + ".?ingredient foaf:vitaminA ?vitaminA"
                + ".?ingredient foaf:vitaminB ?vitaminB"
                + ".?ingredient foaf:vitaminC ?vitaminC"
                + " FILTER " + getFoodFilterCondition(foods, providers)
                + "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
        Nutrients nutrients = getNutrientsSum(result);

        qe.close();

        return nutrients;
    }

    private Nutrients getNutrientsSum(ResultSet result) {
        Nutrients nutrients = new Nutrients();
        if (result.hasNext()) {

            QuerySolution binding = result.nextSolution();
            nutrients.setCalories(binding.getLiteral("caloriesSum").getDouble());
            nutrients.setProteins(binding.getLiteral("proteinsSum").getDouble());
            nutrients.setCarbohydrates(binding.getLiteral("carbohydratesSum").getDouble());
            nutrients.setFats(binding.getLiteral("fatsSum").getDouble());
          //  nutrients.setCalcium(binding.getLiteral("calciumSum").getDouble());
            nutrients.setIron(binding.getLiteral("ironSum").getDouble());
            nutrients.setSodium(binding.getLiteral("sodiumSum").getDouble());
            nutrients.setVitaminA(binding.getLiteral("vitaminASum").getDouble());
            nutrients.setVitaminB(binding.getLiteral("vitaminBSum").getDouble());
            nutrients.setVitaminC(binding.getLiteral("vitaminCSum").getDouble());
        }
        return nutrients;
    }

    private String getFoodFilterCondition(List<String> foods, List<String> providers) {
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

    private List<Nutrients> getNutrientsFromOntologyOrFile(QuerySolution binding, PropertiesLoader propertiesLoader) {

        Nutrients minRequieredNutrientsForUserDiseases = new Nutrients();
        Nutrients maxRequieredNutrientsForUserDiseases = new Nutrients();
        List<Nutrients> requieredNutrientsForUserDiseases = new ArrayList<Nutrients>();

        if (binding.getLiteral("minCarbohydrates") != null) {
            System.out.println(binding.getLiteral("minCarbohydrates"));
            minRequieredNutrientsForUserDiseases.setCarbohydrates(binding.getLiteral("minCarbohydrates").getDouble());
        } else {
            minRequieredNutrientsForUserDiseases.setCarbohydrates(Double.valueOf(propertiesLoader.getProperty("minCarbohydrates")));
        }

        if (binding.getLiteral("maxCarbohydrates") != null) {
            maxRequieredNutrientsForUserDiseases.setCarbohydrates(binding.getLiteral("maxCarbohydrates").getDouble());
        } else {
            maxRequieredNutrientsForUserDiseases.setCarbohydrates(Double.valueOf(propertiesLoader.getProperty("maxCarbohydrates")));
        }

        if (binding.getLiteral("minFats") != null) {
            minRequieredNutrientsForUserDiseases.setFats(binding.getLiteral("minFats").getDouble());
        } else {
            minRequieredNutrientsForUserDiseases.setFats(Double.valueOf(propertiesLoader.getProperty("minFats")));
        }

        if (binding.getLiteral("maxFats") != null) {
            maxRequieredNutrientsForUserDiseases.setFats(binding.getLiteral("maxFats").getDouble());
        } else {
            maxRequieredNutrientsForUserDiseases.setFats(Double.valueOf(propertiesLoader.getProperty("maxFats")));
        }

        if (binding.getLiteral("minSodium") != null) {
            minRequieredNutrientsForUserDiseases.setSodium(binding.getLiteral("minSodium").getDouble());
        } else {
            minRequieredNutrientsForUserDiseases.setSodium(Double.valueOf(propertiesLoader.getProperty("minSodium")));
        }

        if (binding.getLiteral("maxSodium") != null) {
            maxRequieredNutrientsForUserDiseases.setSodium(binding.getLiteral("maxSodium").getDouble());
        } else {
            maxRequieredNutrientsForUserDiseases.setSodium(Double.valueOf(propertiesLoader.getProperty("maxSodium")));
        }
        requieredNutrientsForUserDiseases.add(minRequieredNutrientsForUserDiseases);
        requieredNutrientsForUserDiseases.add(maxRequieredNutrientsForUserDiseases);
        return requieredNutrientsForUserDiseases;
    }

    private List<Nutrients> getRemainingNutrientsFromFile(List<Nutrients> nutrients, PropertiesLoader propertiesLoader) {

        nutrients.get(0).setCalories(Double.valueOf(propertiesLoader.getProperty("minCalories")));
        nutrients.get(1).setCalories(Double.valueOf(propertiesLoader.getProperty("maxCalories")));
        nutrients.get(0).setProteins(Double.valueOf(propertiesLoader.getProperty("minProteins")));
        nutrients.get(1).setProteins(Double.valueOf(propertiesLoader.getProperty("maxProteins")));
      //  nutrients.get(0).setCalcium(Double.valueOf(propertiesLoader.getProperty("minCalcium")));
       // nutrients.get(1).setCalcium(Double.valueOf(propertiesLoader.getProperty("maxCalcium")));
        nutrients.get(0).setIron(Double.valueOf(propertiesLoader.getProperty("minIron")));
        nutrients.get(1).setIron(Double.valueOf(propertiesLoader.getProperty("maxIron")));
        nutrients.get(0).setVitaminA(Double.valueOf(propertiesLoader.getProperty("minVitaminA")));
        nutrients.get(1).setVitaminA(Double.valueOf(propertiesLoader.getProperty("maxVitaminA")));
        nutrients.get(0).setVitaminB(Double.valueOf(propertiesLoader.getProperty("minVitaminB")));
        nutrients.get(1).setVitaminB(Double.valueOf(propertiesLoader.getProperty("maxVitaminB")));
        nutrients.get(0).setVitaminC(Double.valueOf(propertiesLoader.getProperty("minVitaminC")));
        nutrients.get(1).setVitaminC(Double.valueOf(propertiesLoader.getProperty("maxVitaminC")));
        return nutrients;

    }

    public List<Nutrients> getDailyRequieredNutrients(List<String> diseases) throws IOException {
        PropertiesLoader propertiesLoader = new PropertiesLoader("files/nutrientsF.config");
        List<Nutrients> requieredNutrients = new ArrayList<Nutrients>();

        if (diseases.size() > 0) {
            String queryString = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
                    + "SELECT  ?minCarbohydrates ?maxCarbohydrates "
                    + "?minFats ?maxFats ?minSodium ?maxSodium "
                    + "WHERE { ?disease foaf:diseaseName ?diseaseName "
                    + "OPTIONAL{?disease foaf:minCarbohydrates ?minCarbohydrates} "
                    + "OPTIONAL{?disease foaf:maxCarbohydrates ?maxCarbohydrates} "
                    + "OPTIONAL{?disease foaf:minFats ?minFats} "
                    + "OPTIONAL{?disease foaf:maxFats ?maxFats} "
                    + "OPTIONAL{?disease foaf:minSodium ?minSodium} "
                    + "OPTIONAL{?disease foaf:maxSodium ?maxSodium} "
                    + "FILTER( " + getDiseasesFilterCondition(diseases) + ")}";
            Query query = QueryFactory.create(queryString);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet result = qe.execSelect();

            if (result.hasNext()) {
                QuerySolution binding = result.nextSolution();
                requieredNutrients = getNutrientsFromOntologyOrFile(binding, propertiesLoader);
            }
            qe.close();
        } else {
            requieredNutrients.add(new Nutrients());

            requieredNutrients.add(new Nutrients());

        }
        requieredNutrients = getRemainingNutrientsFromFile(requieredNutrients, propertiesLoader);
        return requieredNutrients;
    }

    private String getDiseasesFilterCondition(List<String> diseases) {
        String filterCondition = "";
        if (diseases.size() > 1) {
            for (int diseaseIndex = 0; diseaseIndex < diseases.size() - 1; diseaseIndex++) {
                //string builder
                filterCondition = filterCondition + "?diseaseName= '" + diseases.get(diseaseIndex) + "' || ";
            }
            filterCondition = filterCondition + "?diseaseName= '" + diseases.get(diseases.size() - 1) + "'";

        } else {
            filterCondition = filterCondition + "?diseaseName= '" + diseases.get(0) + "'";
        }
        return filterCondition;

    }

}
