package com.vikination.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.vikination.bakingapp.main.MainActivity;
import com.vikination.bakingapp.main.RecipeListAdapter;
import com.vikination.bakingapp.main.RecipeListAdapter.RecipeListHolder;
import com.vikination.bakingapp.recipedetail.RecipeDetailActivity;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Viki Andrianto on 9/21/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuRecipeClickTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listClicked(){
        Espresso.onView(ViewMatchers.withId(R.id.recipe_list)).perform(RecyclerViewActions.scrollToPosition(0))
                .perform(ViewActions.click());
    }

    public static String BROWNIES_TEXT = "Brownies";

    @Test
    public void testContentHasDataIngredient(){
        Espresso.onView(ViewMatchers.withId(R.id.recipe_list)).perform(RecyclerViewActions.scrollToHolder(
                withHolderTimeView(BROWNIES_TEXT)
        ));
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderTimeView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecipeListHolder>(RecipeListHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(RecipeListHolder item) {
                TextView timeViewText = (TextView) item.itemView.findViewById(R.id.ingredient_name_text);
                if (timeViewText == null) {
                    return false;
                }
                return timeViewText.getText().toString().contains(text);
            }
        };
    }
}
