package com.vikination.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vikination.bakingapp.main.MainActivity;

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
}
