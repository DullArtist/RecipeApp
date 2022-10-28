package com.food.recipes.recipe.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food.recipes.recipe.Models.Recipe;
import com.food.recipes.recipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<Recipe> recipeList;
    RecipeClickListener recipeClickListener;

   public interface RecipeClickListener {
        void onClick(int position);
    }

    public HomeAdapter(Context context, List<Recipe> recipeList,RecipeClickListener recipeClickListener) {
        this.context = context;
        this.recipeList = recipeList;
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_food_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position);
        List<String> diets = recipe.getDiets();
        List<String> mealtypes = recipe.getDishTypes();

        String diet,mealType;

        if(diets.size() != 0) {
            diet = diets.get(0);
        }else {
            diet = "....";
        }
        if(mealtypes.size() != 0) {
            mealType = mealtypes.get(0);
        }else {
            mealType = "....";
        }
        
        String image = recipe.getImage();
        
        Log.d("FOOD",diets.size()+"");
        Log.d("FOOD",mealtypes.size()+" ...");

        holder.Title.setText(recipe.getTitle());
        holder.MealType.setText(mealType);
        holder.Diet.setText(diet);
        Picasso.get().load(image).into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Title,MealType,Diet;
        ImageView food_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Food_Title);
            MealType = itemView.findViewById(R.id.Meal_Type);
            Diet = itemView.findViewById(R.id.Meal_Diet);
            food_image = itemView.findViewById(R.id.Food_Image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            recipeClickListener.onClick(getAdapterPosition());
        }
    }
}
