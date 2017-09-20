package com.vikination.bakingapp.ingredientsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vikination.bakingapp.R;
import com.vikination.bakingapp.model.IngredientsResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientsListViewHolder>{

    ArrayList<IngredientsResponse.Ingredient> ingredients = new ArrayList<>();
    Context context;

    public IngredientsListAdapter(Context context){
        this.context = context;
    }

    @Override
    public IngredientsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_ingredient_list, parent, false);
        return new IngredientsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsListViewHolder holder, int position) {
        IngredientsResponse.Ingredient ingredient = ingredients.get(position);
        String ingredientList = String.format("%s %s %s",
                ingredient.getQuantity(),ingredient.getMeasure(),ingredient.getIngredient());

        holder.tvIngredient.setText(ingredientList);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientsListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_ingredient) TextView tvIngredient;

        public IngredientsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void swapData(ArrayList<IngredientsResponse.Ingredient> ingredients){
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }
}
