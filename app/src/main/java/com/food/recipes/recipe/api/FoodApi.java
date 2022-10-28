package com.food.recipes.recipe.api;

import com.food.recipes.recipe.Models.HomeRecipeModel;
import com.food.recipes.recipe.Models.Nutrition;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FoodApi {

    @GET("random")
    Call<HomeRecipeModel> getRandomRecipes(@QueryMap HashMap<String,String> queries);

    @GET("{id}/nutritionWidget.json")
    Call<Nutrition> getNutritionByID(@Path("id") Integer id, @Query("apiKey") String KEY);

}
