package com.vikination.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.utils.PreferencesUtils;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/20/17.
 */

public class BakingWidgetUpdateService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static int currentIdxIngredients = 0;
    public static ArrayList<IngredientsResponse> ingredientsResponses;

    public static final String ACTION_UPDATE_DATA_WIDGET = "com.vikination.bakingapp.action.update_data";
    public static final String ACTION_UPDATE_DATA_WIDGET_NEXT = "com.vikination.bakingapp.action.update_data_next";
    public static final String ACTION_UPDATE_DATA_WIDGET_PREV = "com.vikination.bakingapp.action.update_data_prev";

    public BakingWidgetUpdateService() {
        super("BakingAppService");
    }

    public static void updateFavBakingDatatoWidget(Context context){
        Intent intent = new Intent(context, BakingWidgetUpdateService.class);
        intent.setAction(ACTION_UPDATE_DATA_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            Timber.i("action : %s",action);
            if (ACTION_UPDATE_DATA_WIDGET.equals(action)){
                handleUpdateDataFav();
            }else if (ACTION_UPDATE_DATA_WIDGET_NEXT.equals(action)){
                handleUpdateDataNext();
            }else if (ACTION_UPDATE_DATA_WIDGET_PREV.equals(action)){
                handleUpdateDataPrev();
            }
        }
    }

    private void handleUpdateDataFav(){
        ingredientsResponses = PreferencesUtils.getIngredientsData(this);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appwidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));
        BakingAppWidget.onUpdateBakingData(this, appWidgetManager, ingredientsResponses.get(currentIdxIngredients)
                ,appwidgetIds);
    }

    private void handleUpdateDataNext(){
        currentIdxIngredients++;
        if (currentIdxIngredients == ingredientsResponses.size()) currentIdxIngredients = 0;
        handleUpdateDataFav();
    }

    private void handleUpdateDataPrev(){
        currentIdxIngredients--;
        if (currentIdxIngredients < 0) currentIdxIngredients = ingredientsResponses.size()-1;
        handleUpdateDataFav();
    }
}
