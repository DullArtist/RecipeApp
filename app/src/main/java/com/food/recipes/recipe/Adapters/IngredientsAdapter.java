package com.food.recipes.recipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food.recipes.recipe.Models.ExtendedIngredient;
import com.food.recipes.recipe.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    Context context;
    List<ExtendedIngredient> ingredientList;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }


    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {

        ExtendedIngredient ingredients = ingredientList.get(position);
        holder.Name.setText(ingredients.getName());
        holder.Amount_Unit.setText(ingredients.getAmount()+" "+ingredients.getUnit());

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Amount_Unit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.ingredientName);
            Amount_Unit = itemView.findViewById(R.id.ingredientAmount_Unit);
        }
    }
}
