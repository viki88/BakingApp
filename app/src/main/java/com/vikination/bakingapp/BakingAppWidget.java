package com.vikination.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.utils.PreferencesUtils;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                IngredientsResponse ingredientsResponse, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        if (ingredientsResponse != null){
            views.setTextViewText(R.id.title_recipe_widget, ingredientsResponse.getName());
            views.setTextViewText(R.id.ingredient_recipe_widget,
                    setupDataIngredientList(ingredientsResponse.getIngredients()));

            Intent nextDataIntent = new Intent(context, BakingWidgetUpdateService.class);
            nextDataIntent.setAction(BakingWidgetUpdateService.ACTION_UPDATE_DATA_WIDGET_NEXT);
            PendingIntent pendingIntentNext =
                    PendingIntent.getService(context, 0, nextDataIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.btn_right_widget, pendingIntentNext);

            Intent prevDataIntent = new Intent(context, BakingWidgetUpdateService.class);
            prevDataIntent.setAction(BakingWidgetUpdateService.ACTION_UPDATE_DATA_WIDGET_PREV);
            PendingIntent pendingIntentPrev =
                    PendingIntent.getService(context, 0, prevDataIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.btn_left_widget, pendingIntentPrev);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        BakingWidgetUpdateService.updateFavBakingDatatoWidget(context);
    }

    public static void onUpdateBakingData(Context context, AppWidgetManager appWidgetManager,
                                          IngredientsResponse ingredientsResponse, int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager,ingredientsResponse, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static String setupDataIngredientList(ArrayList<IngredientsResponse.Ingredient> ingredients){
        String resultData = "";
        for (IngredientsResponse.Ingredient ingredient : ingredients){
            resultData = resultData + String.format("%s %s %s", ingredient.getQuantity(), ingredient.getMeasure()
                    , ingredient.getIngredient()) + "\n";
        }
        return resultData;
    }
}

