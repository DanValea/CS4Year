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
import entity.DiseaseWS;
import entity.FoodWS;
import entity.Nutrients;
import entity.PersonWS;
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

    public Nutrients getFoodNutrients(List<FoodWS> foods) {
        Nutrients nutrients = new Nutrients();
        for (FoodWS f : foods) {
            System.out.println(f.getName());
            String queryString = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
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
                    + " FILTER (?foodName='" + f.getName() + "')"
                    + "}";

            Query query = QueryFactory.create(queryString);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet result = qe.execSelect();
            nutrients = getNutrientsSum(result, nutrients, f);

            qe.close();
        }
        return nutrients;
    }

    private Nutrients getNutrientsSum(ResultSet result, Nutrients nutrients, FoodWS food) {

        while (result.hasNext()) {
            QuerySolution binding = result.nextSolution();
            boolean ingredientFound = false;
            int index = 0;
            while (!ingredientFound && index < food.getFoodEntries().size()) {
                if (food.getFoodEntries().get(index).getIngredientName().equals(binding.getLiteral("ingredientName").getString())) {
                    System.out.println(food.getFoodEntries().get(index).getIngredientName());
                    ingredientFound = true;
                    nutrients.setCalories(binding.getLiteral("calories").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getCalories());
                    nutrients.setProteins(binding.getLiteral("proteins").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getProteins());
                    nutrients.setCarbohydrates(binding.getLiteral("carbohydrates").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getCarbohydrates());
                    nutrients.setFats(binding.getLiteral("fats").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getFats());
                    nutrients.setIron(binding.getLiteral("iron").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getIron());
                    nutrients.setSodium(binding.getLiteral("sodium").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getSodium());
                    nutrients.setVitaminA(binding.getLiteral("vitaminA").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminA());
                    nutrients.setVitaminB(binding.getLiteral("vitaminB").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminB());
                    nutrients.setVitaminC(binding.getLiteral("vitaminC").getDouble() * food.getFoodEntries().get(index).getQuantity() / 100.0 + nutrients.getVitaminC());
                }
                index++;

            }
            if (!ingredientFound) {
                System.out.println("ingredient not found");
            }
        }
        return nutrients;
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

    private List<Nutrients> getRemainingNutrientsFromFile(List<Nutrients> nutrients, PropertiesLoader propertiesLoader, PersonWS person) {
        double brm = calculateCaloriesNeeded(propertiesLoader, person);
        
     nutrients.get(0).setCalories((Double.valueOf(propertiesLoader.getProperty("minCaloriesPercentage"))/100.0+1)*brm);
        nutrients.get(1).setCalories((Double.valueOf(propertiesLoader.getProperty("maxCaloriesPercentage"))/100.0+1)*brm);
        nutrients.get(0).setProteins(Double.valueOf(propertiesLoader.getProperty("minProteinsPercentage"))/100.0*nutrients.get(0).getCalories()/4.0);
        nutrients.get(1).setProteins(Double.valueOf(propertiesLoader.getProperty("maxProteinsPercentage"))/100.0*nutrients.get(1).getCalories()/4.0);
        nutrients.get(0).setFats(Double.valueOf(propertiesLoader.getProperty("minFatsPercentage"))/100.0*nutrients.get(0).getCalories()/9.0);
        nutrients.get(1).setFats(Double.valueOf(propertiesLoader.getProperty("maxFatsPercentage"))/100.0*nutrients.get(1).getCalories()/9.0);
       nutrients.get(0).setCarbohydrates(Double.valueOf(propertiesLoader.getProperty("minCarbohydratesPercentage"))/100.0*nutrients.get(0).getCalories()/4.0);
        nutrients.get(1).setCarbohydrates(Double.valueOf(propertiesLoader.getProperty("maxCarbohydratesPercentage"))/100.0*nutrients.get(1).getCalories()/4.0);
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

    public List<Nutrients> getDailyRequieredNutrients(PersonWS person) throws IOException {
        PropertiesLoader propertiesLoader = new PropertiesLoader("files/nutrientsF.config");
        List<Nutrients> requieredNutrients = new ArrayList<Nutrients>();

        if (person.getDiseases().size() > 0) {
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
                    + "FILTER( " + getDiseasesFilterCondition(person.getDiseases()) + ")}";
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
        requieredNutrients = getRemainingNutrientsFromFile(requieredNutrients, propertiesLoader, person);
        
        System.out.println("mincal"+requieredNutrients.get(0).getCalories());
        System.out.println("maxcal"+requieredNutrients.get(1).getCalories());
        System.out.println("mincar"+requieredNutrients.get(0).getCarbohydrates());
        System.out.println("maxcar"+requieredNutrients.get(1).getCarbohydrates());
        System.out.println("minpr"+requieredNutrients.get(0).getProteins());
        System.out.println("maxpr"+requieredNutrients.get(1).getProteins());
        System.out.println("minf"+requieredNutrients.get(0).getFats());
        System.out.println("minf"+requieredNutrients.get(1).getFats());
        
        return requieredNutrients;
    }

    private String getDiseasesFilterCondition(List<DiseaseWS> diseases) {
        String filterCondition = "";
        if (diseases.size() > 1) {
            for (int diseaseIndex = 0; diseaseIndex < diseases.size() - 1; diseaseIndex++) {
                //string builder
                filterCondition = filterCondition + "?diseaseName= '" + diseases.get(diseaseIndex).getName() + "' || ";
            }
            filterCondition = filterCondition + "?diseaseName= '" + diseases.get(diseases.size() - 1).getName() + "'";

        } else {
            filterCondition = filterCondition + "?diseaseName= '" + diseases.get(0).getName() + "'";
        }
        System.out.println(filterCondition);
        return filterCondition;

    }

    private double calculateCaloriesNeeded(PropertiesLoader propertiesLoader, PersonWS p) {
        double brm = Double.valueOf(propertiesLoader.getProperty("addTerm")) + Double.valueOf(propertiesLoader.getProperty("weightMultiplier")) * p.getWeight() + Double.valueOf(propertiesLoader.getProperty("heightMultiplier")) * p.getHeight() - Double.valueOf(propertiesLoader.getProperty("ageMultiplier")) * p.getAge();
        return (Double.valueOf(propertiesLoader.getProperty(p.getActivityLevel())) / 100.0 + 1) * brm;

    }

}
