
package com.food.recipes.recipe.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nutrition {

    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("carbs")
    @Expose
    private String carbs;
    @SerializedName("fat")
    @Expose
    private String fat;
    @SerializedName("protein")
    @Expose
    private String protein;

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }



}
