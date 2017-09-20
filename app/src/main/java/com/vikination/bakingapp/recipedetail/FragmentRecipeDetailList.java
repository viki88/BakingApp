package com.vikination.bakingapp.recipedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.FragmentBase;
import com.vikination.bakingapp.ingredientsList.IngredientsListFragment;
import com.vikination.bakingapp.stepdetail.StepDetailFragment;

import butterknife.BindView;

/**
 * Created by Viki Andrianto on 9/16/17.
 */

public class FragmentRecipeDetailList extends FragmentBase implements RecipeListDetailAdapter.OnListStepRecipeClick{

    private static final String IDX_LIST_SELECTED = "idx-list-selected";

    private static FragmentRecipeDetailList fragmentRecipeDetailList;
    RecipeListDetailAdapter adapter;
    RecipeDetailActivity recipeDetailActivity;
    boolean isTablet;
    int currentIdxSelected;

    @BindView(R.id.list_detail_recipe) RecyclerView listDetail;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_recipe_detail_list;
    }

    @Override
    protected void viewLoad() {
        isTablet = getResources().getBoolean(R.bool.isTablet);
        adapter = new RecipeListDetailAdapter(getActivity(), this);
        listDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        listDetail.setAdapter(adapter);
        adapter.swapData(recipeDetailActivity.ingredientsResponse);

        if (isTablet){
            if (currentIdxSelected == 0){
                replaceFragmentWithIngredientList();
            }else {
                replaceFragmentWithStepDetail(currentIdxSelected-1, false);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recipeDetailActivity = (RecipeDetailActivity) context;
    }

    public static FragmentRecipeDetailList getInstance(){
        if (fragmentRecipeDetailList == null){
            fragmentRecipeDetailList = new FragmentRecipeDetailList();
        }
        return fragmentRecipeDetailList;
    }

    void replaceFragmentWithIngredientList(){
        replaceFragment(IngredientsListFragment.getInstance().setIngredients(
                recipeDetailActivity.ingredientsResponse));
    }

    void replaceFragmentWithStepDetail(int idxStep, boolean isResetPlayer){
        StepDetailFragment stepDetailFragment = new StepDetailFragment().setStep(
                recipeDetailActivity.ingredientsResponse.getSteps().get(idxStep));
        if (isResetPlayer)stepDetailFragment.restartPlayer();

        replaceFragment(stepDetailFragment);
    }

    void replaceFragment(FragmentBase fragmentBase){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_detail_recipe_w600, fragmentBase)
                .commit();
    }

    @Override
    public void onClickList(int idxList) {
//        Toast.makeText(recipeDetailActivity, "ingredients : "+idxList, Toast.LENGTH_SHORT).show();
        currentIdxSelected = idxList;
        updateDetailScreen();
    }

    void updateDetailScreen(){
        if (currentIdxSelected == 0) {
//            recipeDetailActivity.replaceFragmentWithIngredientList();
            if (isTablet){
                replaceFragmentWithIngredientList();
            }else {
                recipeDetailActivity.showIngredientList();
            }
        }else {
            if (isTablet){
                replaceFragmentWithStepDetail(currentIdxSelected-1, true);
            }else {
                recipeDetailActivity.showStepDetail(currentIdxSelected-1);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(IDX_LIST_SELECTED, currentIdxSelected);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(IDX_LIST_SELECTED))
            currentIdxSelected = savedInstanceState.getInt(IDX_LIST_SELECTED);
    }
}
