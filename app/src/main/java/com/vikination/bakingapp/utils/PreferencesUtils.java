package com.vikination.bakingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vikination.bakingapp.model.IngredientsResponse;

import java.util.ArrayList;

/**
 * Created by Viki Andrianto on 9/16/17.
 */

public class PreferencesUtils {

    private static String INGREDIENTS_DATA = "ingredients-data";
    private static String MARKED_RECIPE = "marked-recipe";

    public static void saveDataIngredients(Context context, String data){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(INGREDIENTS_DATA, data);
        editor.commit();
    }

    public static ArrayList<IngredientsResponse> getIngredientsData(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String dataIngredients = preferences.getString(INGREDIENTS_DATA, "");
        return new Gson().fromJson(dataIngredients, new TypeToken<ArrayList<IngredientsResponse>>(){}.getType());
    }

    public static void saveFavRecipeData(Context context, IngredientsResponse ingredientsResponse){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MARKED_RECIPE, new Gson().toJson(ingredientsResponse));
        editor.commit();
    }

    public static IngredientsResponse getIngredientsResponseMarked(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String ingredientData = preferences.getString(MARKED_RECIPE,"");
        return new Gson().fromJson(ingredientData, IngredientsResponse.class);
    }
}
