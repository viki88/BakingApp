package com.vikination.bakingapp.stepdetail;

import com.google.gson.Gson;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.BaseActivity;
import com.vikination.bakingapp.model.IngredientsResponse;

import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class StepDetailActivity extends BaseActivity{

    public static final String STEP_KEY = "step-key";
    public static final String RESTART_PLAYER = "restart-player";

    boolean restartPlayer = false;

    IngredientsResponse.Step step;

    @Override
    public int getLayoutViewResource() {
        return R.layout.activity_step_detail;
    }

    @Override
    public void loadView() {

        step = (IngredientsResponse.Step) getIntent().getSerializableExtra(STEP_KEY);
        restartPlayer = getIntent().getBooleanExtra(RESTART_PLAYER, false);

        StepDetailFragment stepDetailFragment = new StepDetailFragment().setStep(step);
        if (restartPlayer)stepDetailFragment.restartPlayer();

        Timber.i("step data : %s , restart %b",new Gson().toJson(step),restartPlayer);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containter_step_detail, stepDetailFragment)
                .commit();
    }
}
