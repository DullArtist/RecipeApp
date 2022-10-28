package com.food.recipes.recipe;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;

import com.food.recipes.recipe.Adapters.IngredientsAdapter;
import com.food.recipes.recipe.Models.ExtendedIngredient;
import com.food.recipes.recipe.Models.Nutrition;
import com.food.recipes.recipe.Models.Recipe;
import com.food.recipes.recipe.api.FoodApi;
import com.food.recipes.recipe.api.RetrofitBuilder;
import com.food.recipes.recipe.databinding.ActivityRecipeDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeDetailActivity extends AppCompatActivity {

    ActivityRecipeDetailBinding binding;
    private static final String KEY = "3a429584c48840cb997b831ade0cbde6";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setExpandableCardTransitions();
        setRecipeData();

    }

    private void setExpandableCardTransitions() {

        binding.ingredientsCard.setOnClickListener(view -> {

            int v = binding.ingredientsRv.getVisibility() == View.GONE ? View.VISIBLE : View.GONE ;

            binding.moreIb.setImageDrawable(v == View.GONE ? getDrawable(R.drawable.ic_baseline_expand_more_24) : getDrawable(R.drawable.ic_baseline_expand_less_24));
            TransitionManager.beginDelayedTransition(binding.getRoot(),new AutoTransition());
            binding.ingredientsRv.setVisibility(v);

        });

        binding.instructionssCard.setOnClickListener(view -> {

            int v = binding.instructionsTV.getVisibility() == View.GONE ? View.VISIBLE : View.GONE ;

            binding.moreIbIns.setImageDrawable(v == View.GONE ? getDrawable(R.drawable.ic_baseline_expand_more_24) : getDrawable(R.drawable.ic_baseline_expand_less_24));
            TransitionManager.beginDelayedTransition(binding.getRoot(),new AutoTransition());
            binding.instructionsTV.setVisibility(v);

        });

        binding.nutritionsCard.setOnClickListener(view -> {

            int v = binding.nutritionsLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE ;

            binding.moreIbNut.setImageDrawable(v == View.GONE ? getDrawable(R.drawable.ic_baseline_expand_more_24) : getDrawable(R.drawable.ic_baseline_expand_less_24));
            TransitionManager.beginDelayedTransition(binding.getRoot(),new AutoTransition());
            binding.nutritionsLayout.setVisibility(v);
        });

    }

    private void setRecipeData() {

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("Recipe");

        Picasso.get().load(recipe.getImage()).into(binding.FoodImage);
        binding.title.setText(recipe.getTitle());

        List<ExtendedIngredient> ingredientList = recipe.getExtendedIngredients();
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this,ingredientList);
        binding.ingredientsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.ingredientsRv.setAdapter(ingredientsAdapter);

        binding.instructionsTV.setText(recipe.getInstructions());

        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Retrofit retrofit = retrofitBuilder.getRetrofit();

        FoodApi foodApi = retrofit.create(FoodApi.class);
        foodApi.getNutritionByID(recipe.getId(),KEY).enqueue(new Callback<Nutrition>() {
            @Override
            public void onResponse(@NonNull Call<Nutrition> call, @NonNull Response<Nutrition> response) {
                if(response.isSuccessful()) {
                    Log.d("NUTRITION",response.code()+"");
                    binding.CaloriesTV.append(" "+response.body().getCalories());
                    binding.CarbsTV.append(" "+response.body().getCarbs());
                    binding.FatsTV.append(" "+response.body().getFat());
                    binding.ProteinsTV.append(" "+response.body().getProtein());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Nutrition> call, @NonNull Throwable t) {
                Log.d("NUTRITION",t.getMessage());

            }
        });

    }

}