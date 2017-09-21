package com.vikination.bakingapp.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.model.IngredientsResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListHolder>{

    ArrayList<IngredientsResponse> ingredientsResponses = new ArrayList<>();
    Context context;
    OnIngredientsListSelected listener;

    interface OnIngredientsListSelected{
        void onSelectedIngredient(IngredientsResponse ingredientsResponse);
    }

    public RecipeListAdapter(Context context, OnIngredientsListSelected listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecipeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recipe_list, parent, false);
        return new RecipeListHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListHolder holder, int position) {
        final IngredientsResponse ingredientsResponse = ingredientsResponses.get(position);
        if (!ingredientsResponse.getImage().isEmpty()){
            Picasso.with(context).load(ingredientsResponse.getImage()).into(holder.prevImageView);
        }

        holder.ingredientNameText.setText(ingredientsResponse.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectedIngredient(ingredientsResponse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsResponses.size();
    }

    public static class RecipeListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ingredient_name_text) TextView ingredientNameText;
        @BindView(R.id.bg_cake_image) ImageView prevImageView;

        public RecipeListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void swapData(ArrayList<IngredientsResponse> ingredientsResponses){
        this.ingredientsResponses = ingredientsResponses;
        notifyDataSetChanged();
    }

}
