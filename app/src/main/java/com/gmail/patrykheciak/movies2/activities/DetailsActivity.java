package com.gmail.patrykheciak.movies2.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.entities.Movie;
import com.gmail.patrykheciak.movies2.fragments.CastFragment;
import com.gmail.patrykheciak.movies2.fragments.DetailsFragment;
import com.gmail.patrykheciak.movies2.fragments.GalleryFragment;
import com.gmail.patrykheciak.movies2.interfaces.OnGalleryAndCastRequestedListener;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity {

    private Movie movie;
    private DetailsFragment fDetails;
    private CastFragment fCast;
    private GalleryFragment fGallery;

    private FrameLayout bottom;
    private FrameLayout top;

    public DetailsActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d("DetailsActivity", "onCreate");

        movie = (Movie) getIntent().getSerializableExtra("extra");
        top = (FrameLayout) findViewById(R.id.top);
        bottom = (FrameLayout) findViewById(R.id.bottom);

//        if (savedInstanceState == null) {

        Log.d("DetailsActivity", "Created all the fragments");
        fDetails = new DetailsFragment();
        fDetails.setMovie(movie);
        fDetails.setGalleryAndCastListener(new OnGalleryAndCastRequestedListener() {
            @Override
            public void onGalleryAndCastRequested() {
                showGalleryAndCastFragments();
            }
        });

        fCast = new CastFragment();
        fCast.setMovie(movie);

        fGallery = new GalleryFragment();
        fGallery.setMovie(movie);
//        }

        showDetailsFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportFragmentManager().findFragmentByTag("B") != null) {
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
        } else {
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("B") != null) {
            getSupportFragmentManager().popBackStack("A_BC",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottom.setVisibility(View.GONE);
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", (Serializable) movie);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private void showDetailsFragment() {
        if (getSupportFragmentManager().findFragmentByTag("B") == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.top, fDetails, "A")
                    .addToBackStack("0_A")
                    .commit();
            bottom.setVisibility(View.GONE);
        }
    }

    private void showGalleryAndCastFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.top, fGallery, "C")
                .replace(R.id.bottom, fCast, "B")
                .addToBackStack("A_BC")
                .commit();

        bottom.setVisibility(View.VISIBLE);
    }
}
