package com.gmail.patrykheciak.movies2.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private final String FRAGMENT_A = "FRAGMENT_A";
    private final String FRAGMENT_B = "FRAGMENT_B";
    private final String FRAGMENT_C = "FRAGMENT_C";
    private final String A_TO_BC = "A_TO_BC";

    public DetailsActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        movie = (Movie) getIntent().getSerializableExtra(MainActivity.EXTRA_MOVIE);
        top = (FrameLayout) findViewById(R.id.top);
        bottom = (FrameLayout) findViewById(R.id.bottom);

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

        showDetailsFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_C) != null) {
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
        } else {
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_C) != null) {
            getSupportFragmentManager().popBackStack(A_TO_BC,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottom.setVisibility(View.GONE);
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.EXTRA_RESULT, (Serializable) movie);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private void showDetailsFragment() {
        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_C) == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.top, fDetails, FRAGMENT_A)
                    .addToBackStack("")
                    .commit();
            bottom.setVisibility(View.GONE);
        }
    }

    private void showGalleryAndCastFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.top, fGallery, FRAGMENT_B)
                .replace(R.id.bottom, fCast, FRAGMENT_C)
                .addToBackStack(A_TO_BC)
                .commit();

        bottom.setVisibility(View.VISIBLE);
    }
}
