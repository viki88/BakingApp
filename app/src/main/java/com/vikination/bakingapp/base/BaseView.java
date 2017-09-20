package com.vikination.bakingapp.base;

import android.content.Context;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public interface BaseView {
    void progressStart();
    void progressStop();
    Context getContextView();
}
