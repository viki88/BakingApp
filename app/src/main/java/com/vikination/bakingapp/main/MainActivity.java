package com.vikination.bakingapp.main;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.vikination.bakingapp.BakingWidgetUpdateService;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.BaseActivity;
import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.recipedetail.RecipeDetailActivity;
import com.vikination.bakingapp.utils.PreferencesUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, RecipeListAdapter.OnIngredientsListSelected{

    @BindView(R.id.recipe_list) RecyclerView recipeList;
    @BindView(R.id.swipe_view) SwipeRefreshLayout swipeRefreshLayout;

    private static final int COL_COUNT = 3;

    MainPresenter presenter;
    RecipeListAdapter adapter;

    @Override
    public int getLayoutViewResource() {
        return R.layout.activity_main;
    }

    @Override
    public void loadView() {
        presenter = new MainPresenter(this);
        setToolbarTitle(R.string.app_name);
        setHomeButtonVisible(R.drawable.ic_menu_white_24dp, true);

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet){
            recipeList.setLayoutManager(new GridLayoutManager(this, COL_COUNT));
        }else {
            recipeList.setLayoutManager(new LinearLayoutManager(this));
        }

        adapter = new RecipeListAdapter(this,this);
        recipeList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadAllIngredients();
            }
        });

        presenter.loadAllIngredients();
    }

    @Override
    public void progressStart() {
        loadProgress();
    }

    @Override
    public void progressStop() {
        stopProgress();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Context getContextView() {
        return this;
    }

    @Override
    public void onSuccessLoadData(ArrayList<IngredientsResponse> ingredientsResponses) {
        PreferencesUtils.saveDataIngredients(this,new Gson().toJson(ingredientsResponses));
        PreferencesUtils.saveFavRecipeData(this, ingredientsResponses.get(0));
        adapter.swapData(ingredientsResponses);
        BakingWidgetUpdateService.updateFavBakingDatatoWidget(this);
    }

    @Override
    public void onSelectedIngredient(IngredientsResponse ingredientsResponse) {
//        showToast(ingredientsResponse.getName());
        RecipeDetailActivity.start(this, ingredientsResponse);
    }
}
