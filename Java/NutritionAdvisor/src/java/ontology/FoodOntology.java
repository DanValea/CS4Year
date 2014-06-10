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
import entity.CategoryWS;
import entity.DiseaseWS;
import entity.FoodEntryWS;
import entity.FoodWS;
import entity.NutrientsWS;
import entity.UserPreferenceConstraintWS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mada
 */
public final class FoodOntology {

    private static final String queryCategory = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>\n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            + "SELECT Distinct ?category WHERE { "
            + "{?category rdfs:subClassOf ?food; "
            + ".?ind rdf:type ?category} "
            + "UNION {?subCateg rdfs:subClassOf ?category "
            + ".?category rdfs:subClassOf ?food "
            + ".?ind rdf:type ?subCateg }"
            + "FILTER(?food=foaf:Food) "
            + "} ";

    public static NutrientsWS getFoodNutrients(HashMap<String, NutrientsWS> ingredients, List<FoodWS> foods) {
        NutrientsWS nutrients = new NutrientsWS();
//        long start = System.currentTimeMillis();
//        System.out.println("time start" + start);
        for (FoodWS f : foods) {
            for (FoodEntryWS foodEntry : f.getFoodEntries()) {
                nutrients.setCalories(ingredients.get(foodEntry.getIngredientName()).getCalories() * foodEntry.getQuantity() / 100.0 + nutrients.getCalories());
                nutrients.setCarbohydrates(ingredients.get(foodEntry.getIngredientName()).getCarbohydrates() * foodEntry.getQuantity() / 100.0 + nutrients.getCarbohydrates());
                nutrients.setProteins(ingredients.get(foodEntry.getIngredientName()).getProteins() * foodEntry.getQuantity() / 100.0 + nutrients.getProteins());
                nutrients.setFats(ingredients.get(foodEntry.getIngredientName()).getFats() * foodEntry.getQuantity() / 100.0 + nutrients.getFats());
                nutrients.setIron(ingredients.get(foodEntry.getIngredientName()).getIron() * foodEntry.getQuantity() / 100.0 + nutrients.getIron());
                nutrients.setSodium(ingredients.get(foodEntry.getIngredientName()).getSodium() * foodEntry.getQuantity() / 100.0 + nutrients.getSodium());
                nutrients.setVitaminA(ingredients.get(foodEntry.getIngredientName()).getVitaminA() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminA());
                nutrients.setVitaminB(ingredients.get(foodEntry.getIngredientName()).getVitaminB() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminB());
                nutrients.setVitaminC(ingredients.get(foodEntry.getIngredientName()).getVitaminC() * foodEntry.getQuantity() / 100.0 + nutrients.getVitaminC());
            }

        }

        long end = System.currentTimeMillis();
//        System.out.println("time end" + end);
//        System.out.println("time food" + (end - start));
        return nutrients;
    }


    public static boolean foodSolutionRespectsUserConstraints(List<FoodWS> foods, UserPreferenceConstraintWS userConstraint) {
        String foodFilterCondition = getFoodFilterCondition(foods);

        if (menuHasCategory(userConstraint.getFavouriteCategory(), foodFilterCondition)) {
            return !menuHasCategory( userConstraint.getDislikedCategory(), foodFilterCondition);
        }
        return false;

    }

    private static String getFoodFilterCondition(List<FoodWS> foods) {

        String filterCondition = "(";

        for (int foodIndex = 0; foodIndex < foods.size() - 1; foodIndex++) {
            //string builder
            filterCondition = filterCondition + "?foodName= '" + foods.get(foodIndex).getName() + "' || ";
        }
        filterCondition = filterCondition + "?foodName= '" + foods.get(foods.size() - 1).getName() + "')";

        return filterCondition;

    }

    private static boolean menuHasCategory(String category, String foodFilterCondition) {
        String queryString = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + " SELECT ?food WHERE {"
                + "?food  foaf:name ?foodName"
                + "{ ?food rdf:type ?subclass"
                + "}"
                + " UNION {?food rdf:type ?superclass "
                + ".?superclass rdfs:subClassOf ?subclass "
                + "}"
                + "   FILTER (" + foodFilterCondition
                + "  && ?subclass =foaf:" + category + ")"
                + " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, OntologyProvider.getInstance().getModel());
        ResultSet result = qe.execSelect();
        boolean menuHasCategory = result.hasNext();
        qe.close();
        return menuHasCategory;
    }

    public static List<CategoryWS> getCategories() {
        List<CategoryWS> categories = new ArrayList<CategoryWS>();
        Query queryCateg = QueryFactory.create(queryCategory);
        QueryExecution qeCategory = QueryExecutionFactory.create(queryCateg, OntologyProvider.getInstance().getModel());
        ResultSet resultCategory = qeCategory.execSelect();

        while (resultCategory.hasNext()) {
            QuerySolution bindingCateg = resultCategory.nextSolution();
            String categoryName = bindingCateg.getResource("category").getLocalName();
            CategoryWS category = new CategoryWS(categoryName);
            List<CategoryWS> subcategories = new ArrayList<CategoryWS>();
            category.setSubcategories(subcategories);
            String querySubcategory = "PREFIX foaf: <http://www.pips.eu.org/ontologies/food#>\n"
                    + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "SELECT Distinct ?subcategory WHERE { "
                    + "?subcategory rdfs:subClassOf ?category; "
                    + ".?ind rdf:type ?subcategory "
                    + "FILTER(?category=foaf:" + categoryName + ")"
                    + "} ";

            Query querySubcateg = QueryFactory.create(querySubcategory);
            QueryExecution qeSubcateg = QueryExecutionFactory.create(querySubcateg, OntologyProvider.getInstance().getModel());
            ResultSet resultSubcateg = qeSubcateg.execSelect();
            while (resultSubcateg.hasNext()) {
                QuerySolution bindingSubcateg = resultSubcateg.nextSolution();
                subcategories.add(new CategoryWS(bindingSubcateg.getResource("subcategory").getLocalName()));
            }
            qeSubcateg.close();
            categories.add(category);
        }
        qeCategory.close();
        return categories;
    }

}
