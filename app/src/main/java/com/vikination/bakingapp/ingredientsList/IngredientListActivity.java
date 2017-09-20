package com.vikination.bakingapp.ingredientsList;

import android.content.Context;
import android.content.Intent;

import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.BaseActivity;
import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.utils.PreferencesUtils;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class IngredientListActivity extends BaseActivity{

    public static String KEY_INGREDIENT_RESPONSE = "ingredient-key";

    IngredientsResponse ingredientsResponse;

    @Override
    public int getLayoutViewResource() {
        return R.layout.activity_ingredient_list;
    }

    @Override
    public void loadView() {
        ingredientsResponse = (IngredientsResponse)
                getIntent().getSerializableExtra(KEY_INGREDIENT_RESPONSE);

        setToolbarTitle(ingredientsResponse.getName());
        setHomeButtonVisible(R.drawable.ic_menu_white_24dp, true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_ingredient_list, IngredientsListFragment.getInstance()
                        .setIngredients(ingredientsResponse))
                .commit();
    }

}
