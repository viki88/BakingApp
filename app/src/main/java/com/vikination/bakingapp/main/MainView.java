package com.vikination.bakingapp.main;

import com.vikination.bakingapp.base.BaseView;
import com.vikination.bakingapp.model.IngredientsResponse;

import java.util.ArrayList;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public interface MainView extends BaseView{
    void onSuccessLoadData(ArrayList<IngredientsResponse> ingredientsResponses);
}
