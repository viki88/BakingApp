package com.vikination.bakingapp.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.vikination.bakingapp.R;
import com.vikination.bakingapp.base.FragmentBase;
import com.vikination.bakingapp.model.IngredientsResponse;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Viki Andrianto on 9/19/17.
 */

public class StepDetailFragment extends FragmentBase{

    IngredientsResponse.Step step;

    SimpleExoPlayer mExoPlayer;
    Uri mediaUri;
    static long currentState = 0;
    boolean isRestartPlayer = false;

    public static final String PLAYBACK_STATE = "plaback-state";

    @BindView(R.id.step_detail) TextView textStepDetail;
    @BindView(R.id.exo_player_view) SimpleExoPlayerView mPlayerView;
    @BindView(R.id.thumbnail_image_view) ImageView thumbnailView;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_step_detail;
    }

    @Override
    protected void viewLoad() {
        if (step != null){
            if (textStepDetail != null)textStepDetail.setText(step.getDescription());
            mediaUri = Uri.parse(step.getVideoURL());

            if (!step.getThumbnailURL().isEmpty()){
                thumbnailView.setVisibility(View.VISIBLE);
                Picasso.with(getActivity()).load(step.getThumbnailURL()).into(thumbnailView);
            }else {
                thumbnailView.setVisibility(View.GONE);
            }

            initializePlayer();
        }
    }

    public StepDetailFragment setStep(IngredientsResponse.Step step) {
        this.step = step;
        return this;
    }

    public void restartPlayer(){
        Timber.i("restart player : %s","restart");
        currentState = 0;
        isRestartPlayer = true;
    }

    void initializePlayer(){
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
        mPlayerView.setPlayer(mExoPlayer);

//        mPlayerView.set

        // Prepare the MediaSource.
        String userAgent = Util.getUserAgent(getActivity(), "Baking Time");
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        Timber.i("seek to : "+currentState);
        mExoPlayer.seekTo(currentState);
        mExoPlayer.setPlayWhenReady(true);
    }

    void releasePlayer(){
        if (mExoPlayer != null){
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        currentState = mExoPlayer.getCurrentPosition();
        Timber.i("current state : %s",String.valueOf(currentState));
        outState.putLong(PLAYBACK_STATE, currentState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(PLAYBACK_STATE)){
            if (!isRestartPlayer)currentState = savedInstanceState.getLong(PLAYBACK_STATE);
            Timber.i("current state restore: %s",String.valueOf(currentState));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
