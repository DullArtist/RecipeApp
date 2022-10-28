
package com.food.recipes.recipe.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Measures implements Serializable {

    @SerializedName("us")
    @Expose
    private Us us;
    @SerializedName("metric")
    @Expose
    private Metric metric;

    public Us getUs() {
        return us;
    }

    public void setUs(Us us) {
        this.us = us;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

}
