
package com.food.recipes.recipe.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Temperature implements Serializable {

    @SerializedName("number")
    @Expose
    private Double number;
    @SerializedName("unit")
    @Expose
    private String unit;

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
