package com.vikination.bakingapp.recipedetail;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.BaseActivity;
import com.vikination.bakingapp.base.FragmentBase;
import com.vikination.bakingapp.ingredientsList.IngredientListActivity;
import com.vikination.bakingapp.ingredientsList.IngredientsListFragment;
import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.stepdetail.StepDetailActivity;

import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/16/17.
 */

public class RecipeDetailActivity extends BaseActivity{

    public static String ING_KEY = "ing-key";

    public static final String INGREDIENTS_LIST_TAG = "ingredient-list-tag";
    public static final String RECIPE_DETAIL_LIST_TAG = "recipe-detail-list-tag";

    public IngredientsResponse ingredientsResponse;
    Fragment currentFragment;

    @Override
    public int getLayoutViewResource() {
        return R.layout.activity_recipe_detail;
    }

    @Override
    public void loadView() {
        ingredientsResponse = (IngredientsResponse) getIntent().getSerializableExtra(ING_KEY);
        setToolbarTitle(ingredientsResponse.getName());
        setHomeButtonVisible(true);
        replaceFragmentWithRecipeDetailList();
        Timber.i("ingredient response : %s",new Gson().toJson(ingredientsResponse));
    }

    void replaceFragment(FragmentBase fragmentBase, String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_detail_recipe, fragmentBase, tag)
                .commit();
    }

    void replaceFragmentWithRecipeDetailList(){
        replaceFragment(FragmentRecipeDetailList.getInstance(),RECIPE_DETAIL_LIST_TAG);
    }

    void replaceFragmentWithIngredientList(){
        replaceFragment(IngredientsListFragment.getInstance(), INGREDIENTS_LIST_TAG);
    }

    void showIngredientList(){
        Intent intent = new Intent(this, IngredientListActivity.class);
        intent.putExtra(IngredientListActivity.KEY_INGREDIENT_RESPONSE, ingredientsResponse);
        startActivity(intent);
    }

    void showStepDetail(int indexStep){
        Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putExtra(StepDetailActivity.STEP_KEY, ingredientsResponse.getSteps().get(indexStep));
        intent.putExtra(StepDetailActivity.RECIPE_NAME, ingredientsResponse.getName());
        intent.putExtra(StepDetailActivity.RESTART_PLAYER, true);
        startActivity(intent);
    }

    public static void start(BaseActivity baseActivity, IngredientsResponse ingredientsResponse) {
        Intent starter = new Intent(baseActivity, RecipeDetailActivity.class);
        starter.putExtra(RecipeDetailActivity.ING_KEY, ingredientsResponse);
        baseActivity.startActivity(starter);
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
