package clm.recipe;

/**
 * Created by CLM on 20/07/2016.
 */
public class myRecipe {

    String recipe_name;
    String recipe_description;
    int recipe_rating;
    int sqlID;

    public myRecipe(String recipe_name, String recipe_description, int recipe_rating) {
        this.recipe_name = recipe_name;
        this.recipe_description = recipe_description;
        this.recipe_rating = recipe_rating;
        this.sqlID=-1;
    }
}
