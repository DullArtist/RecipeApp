
package com.food.recipes.recipe.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeRecipeModel {

    @SerializedName("recipes")
    @Expose
    private List<Recipe> recipes = null;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

}
