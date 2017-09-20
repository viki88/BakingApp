package com.vikination.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vikination.bakingapp.model.IngredientsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class NetworkUtils {

    public static final String URL_BASE =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public static final String BAKING_PATH = "baking.json";

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface GetAllIngredients{
        @GET(BAKING_PATH)
        Call<ArrayList<IngredientsResponse>> getAllIngredients();
    }

    public static GetAllIngredients getAllIngredients(){
        return retrofit.create(GetAllIngredients.class);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
