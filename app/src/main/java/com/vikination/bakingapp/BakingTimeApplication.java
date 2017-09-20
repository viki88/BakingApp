package com.vikination.bakingapp;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class BakingTimeApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
