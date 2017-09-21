package com.vikination.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vikination.bakingapp.main.MainActivity;
import com.vikination.bakingapp.recipedetail.RecipeDetailActivity;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Viki Andrianto on 9/21/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckDataResponseHasIngredients {

    public static String INGREDIENT_TEXT = "Recipe Ingredients";

    @Rule
    public ActivityTestRule<RecipeDetailActivity> recipeDetailActivityActivityTestRule =
            new ActivityTestRule<>(RecipeDetailActivity.class);

    @Test
    public void testContentHasDataIngredient(){
        Espresso.onData(CoreMatchers.is(INGREDIENT_TEXT))
                .perform(ViewActions.click());
    }

}
