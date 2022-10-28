package com.food.recipes.recipe.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private static final String KEY = "3a429584c48840cb997b831ade0cbde6";

    public Retrofit getRetrofit() {

//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .addInterceptor(chain -> {
//                    Request request = chain.request().newBuilder().addHeader("apiKey",KEY).build();
//                    return chain.proceed(request);
//                });

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }

}
