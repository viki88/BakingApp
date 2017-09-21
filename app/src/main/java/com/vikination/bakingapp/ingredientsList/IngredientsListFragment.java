package com.vikination.bakingapp.ingredientsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.FragmentBase;
import com.vikination.bakingapp.model.IngredientsResponse;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class IngredientsListFragment extends FragmentBase{

    public static final String INGREDIENT_KEY = "ingredient-key";

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
        if (ingredientsResponse != null) adapter.swapData(ingredientsResponse.getIngredients());
    }

    public static IngredientsListFragment getInstance(){
        if (ingredientsListFragment == null){
            ingredientsListFragment = new IngredientsListFragment();
        }
        return ingredientsListFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        String ingredientString = new Gson().toJson(ingredientsResponse);
        Timber.i("data ingredient onsave : %s",ingredientString);
        outState.putString(INGREDIENT_KEY, ingredientString);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(INGREDIENT_KEY)){
            String ingredientString = savedInstanceState.getString(INGREDIENT_KEY);
            Timber.i("data ingredient oncreateActivity : %s",ingredientString);
            ingredientsResponse = new Gson().fromJson(ingredientString, IngredientsResponse.class);
        }
    }

    public IngredientsListFragment setIngredients(IngredientsResponse ingredientsResponse){
        this.ingredientsResponse = ingredientsResponse;
        return this;
    }

}
