package com.vikination.bakingapp.main;

import android.widget.Toast;

import com.google.gson.Gson;
import com.vikination.bakingapp.model.IngredientsResponse;
import com.vikination.bakingapp.utils.NetworkUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class MainPresenter{

    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    void loadAllIngredients(){
        view.progressStart();
        NetworkUtils.getAllIngredients().getAllIngredients().enqueue(new Callback<ArrayList<IngredientsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<IngredientsResponse>> call, Response<ArrayList<IngredientsResponse>> response) {
                ArrayList<IngredientsResponse> ingredientsResponse = response.body();
                Timber.i("ingredients response %s ", new Gson().toJson(ingredientsResponse));
                view.onSuccessLoadData(ingredientsResponse);
                view.progressStop();
            }

            @Override
            public void onFailure(Call<ArrayList<IngredientsResponse>> call, Throwable t) {
                Toast.makeText(view.getContextView(), t.getMessage(), Toast.LENGTH_SHORT).show();
                view.progressStop();
            }
        });
    }
}
