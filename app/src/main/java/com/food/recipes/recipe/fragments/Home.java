package com.food.recipes.recipe.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.recipes.recipe.Adapters.HomeAdapter;
import com.food.recipes.recipe.Models.HomeRecipeModel;
import com.food.recipes.recipe.Models.Recipe;
import com.food.recipes.recipe.RecipeDetailActivity;
import com.food.recipes.recipe.SettingsActivity;
import com.food.recipes.recipe.api.FoodApi;
import com.food.recipes.recipe.api.RetrofitBuilder;
import com.food.recipes.recipe.databinding.FragmentHomeBinding;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home extends Fragment implements HomeAdapter.RecipeClickListener {

    private FoodApi foodApi;
    private FragmentHomeBinding binding;
    private static final String KEY = "3a429584c48840cb997b831ade0cbde6";
    private List<Recipe> recipeList;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Retrofit retrofit = retrofitBuilder.getRetrofit();

        foodApi = retrofit.create(FoodApi.class);
        binding.prefIb.setOnClickListener(view -> startActivity(new Intent(getActivity(), SettingsActivity.class)));

        getRecipes();

        return binding.getRoot();
    }


    private void getRecipes() {

        HashMap<String,String> QueryMap = new HashMap<>();
        QueryMap.put("number","10");
        QueryMap.put("apiKey",KEY);

        foodApi.getRandomRecipes(QueryMap).enqueue(new Callback<HomeRecipeModel>() {
            @Override
            public void onResponse(@NonNull Call<HomeRecipeModel> call, @NonNull Response<HomeRecipeModel> response) {
                if(response.isSuccessful()) {

                    Log.d("FOOD",response.code()+"");
                    recipeList = response.body().getRecipes();

                    //TODO : pass list to Adapter
                    HomeAdapter adapter = new HomeAdapter(getActivity(),recipeList,Home.this);
                    binding.homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.homeRv.setAdapter(adapter);
                    binding.progressCircular.setVisibility(View.GONE);

                }else {
                    Log.d("FOOD",response.code()+"");
                    Toast.makeText(getActivity(), "ReadTimeOut : check connection and retry", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeRecipeModel> call, @NonNull Throwable t) {
                Log.d("FOOD",t.getMessage());
                binding.progressCircular.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(int position) {
        Recipe recipe = recipeList.get(position);
        Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
        intent.putExtra("Recipe",recipe);
        startActivity(intent);
    }
}