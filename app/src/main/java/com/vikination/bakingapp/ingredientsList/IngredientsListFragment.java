package com.vikination.bakingapp.ingredientsList;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.FragmentBase;
import com.vikination.bakingapp.model.IngredientsResponse;

import butterknife.BindView;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class IngredientsListFragment extends FragmentBase{

    static IngredientsListFragment ingredientsListFragment;

    IngredientsResponse ingredientsResponse;
    IngredientsListAdapter adapter;

    @BindView(R.id.rv_ingredient_list) RecyclerView rvList;
    @BindView(R.id.swipe_view_ingredient_list) SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_ingredients_list;
    }

    @Override
    protected void viewLoad() {
        adapter = new IngredientsListAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(adapter);
        adapter.swapData(ingredientsResponse.getIngredients());
    }

    public static IngredientsListFragment getInstance(){
        if (ingredientsListFragment == null){
            ingredientsListFragment = new IngredientsListFragment();
        }
        return ingredientsListFragment;
    }

    public IngredientsListFragment setIngredients(IngredientsResponse ingredientsResponse){
        this.ingredientsResponse = ingredientsResponse;
        return this;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        IngredientListActivity ingredientListActivity = (IngredientListActivity) context;
//        if (ingredientListActivity != null)ingredientsResponse = ingredientListActivity.ingredientsResponse;
//    }
}
