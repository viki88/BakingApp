package com.vikination.bakingapp.base;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class BasePresenter {

    protected BaseView view;

    public BasePresenter(BaseView view){
        this.view = view;
    }

}
