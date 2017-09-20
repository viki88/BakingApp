package com.vikination.bakingapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Viki Andrianto on 9/16/17.
 */

abstract public class FragmentBase extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        ButterKnife.bind(this, view);
        viewLoad();

        return view;
    }

    protected abstract int getLayoutView();

    protected abstract void viewLoad();
}
