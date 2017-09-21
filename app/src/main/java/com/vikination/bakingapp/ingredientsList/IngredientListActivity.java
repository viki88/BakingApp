package com.vikination.bakingapp.ingredientsList;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

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
        setHomeButtonVisible(true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_ingredient_list, IngredientsListFragment.getInstance()
                        .setIngredients(ingredientsResponse))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
