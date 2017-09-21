package com.vikination.bakingapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vikination.bakingapp.R;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ProgressDialog progressDialog;

    @BindString(R.string.text_progress_bar) String progressbarMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutViewResource());
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(progressbarMsg);
        loadView();
    }

    public abstract int getLayoutViewResource();

    public abstract void loadView();

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void setToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }

    protected void setToolbarTitle(int title){
        getSupportActionBar().setTitle(title);
    }

    protected void setToolbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    protected void setHomeButtonVisible(boolean isVisible){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isVisible);
    }

    protected void setHomeButtonVisible(int icRes, boolean isVisible){
        setHomeButtonVisible(isVisible);
        getSupportActionBar().setHomeAsUpIndicator(icRes);
    }

    protected void loadProgress(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    protected void stopProgress(){
        progressDialog.dismiss();
    }
}
