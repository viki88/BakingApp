package com.vikination.bakingapp.recipedetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vikination.bakingapp.R;
import com.vikination.bakingapp.model.IngredientsResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki Andrianto on 9/16/17.
 */

public class RecipeListDetailAdapter extends RecyclerView.Adapter<RecipeListDetailAdapter.DetailRecipeViewHolder>{

    IngredientsResponse ingredientsResponse;

    private static final int ITEM_VIEW_HEADER = 0;
    private static final int ITEM_VIEW_LIST = 1;

    Context context;
    OnListStepRecipeClick onListStepRecipeClick;

    public RecipeListDetailAdapter(Context context, OnListStepRecipeClick onListStepRecipeClick){
        this.context = context;
        this.onListStepRecipeClick = onListStepRecipeClick;
    }

    interface OnListStepRecipeClick{
        void onClickList(int idxList);
    }

    @Override
    public DetailRecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_detail_recipe_list, parent, false);
        return new DetailRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailRecipeViewHolder holder, final int position) {

        String string;

        if (getItemViewType(position) == ITEM_VIEW_HEADER){
            string = context.getResources().getString(R.string.text_recipe_ingredient);
        }else {
            string = ingredientsResponse.getSteps().get(position-1).getShortDescription();
        }

        holder.titleList.setText(string);
        holder.titleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListStepRecipeClick.onClickList(position);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)) return ITEM_VIEW_HEADER;
        return ITEM_VIEW_LIST;
    }

    private boolean isHeaderPosition(int position){
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return ingredientsResponse.getSteps().size() + 1;
    }

    public void swapData(IngredientsResponse ingredientsResponse){
        this.ingredientsResponse = ingredientsResponse;
        notifyDataSetChanged();
    }

    class DetailRecipeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.detail_recipe_text) TextView titleList;

        public DetailRecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
