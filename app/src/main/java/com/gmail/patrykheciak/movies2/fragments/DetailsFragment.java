package com.gmail.patrykheciak.movies2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gmail.patrykheciak.movies2.entities.Movie;
import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.interfaces.OnGalleryAndCastRequestedListener;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieUpdatedListener;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private String TAG = "DetailsFragment";
    private Movie movie;
    private TextView title;
    private RatingBar ratingBar;
    private ImageView imageView;
    private OnMovieUpdatedListener callback;
    private OnGalleryAndCastRequestedListener galleryAndCastCallback;

    public DetailsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
            movie = savedInstanceState.getParcelable("movie");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("movie", movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        title = (TextView) v.findViewById(R.id.title);
        imageView = (ImageView) v.findViewById(R.id.imageView);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable("movie");
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (galleryAndCastCallback != null)
                    galleryAndCastCallback.onGalleryAndCastRequested();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                movie.setRate(v);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        polluteMovieData(movie);
        Picasso.with(getActivity())
                .load(movie.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (callback != null) {
            callback.onMovieUpdated(movie, -1);
        }
    }

    public void setGalleryAndCastListener(OnGalleryAndCastRequestedListener galleryAndCastCallback) {
        this.galleryAndCastCallback = galleryAndCastCallback;
    }

    public void setOnMovieDataUpdatedListener(OnMovieUpdatedListener callback) {
        this.callback = callback;
    }

    private void polluteMovieData(Movie movie) {
        title.setText(movie.getTitle());
        ratingBar.setRating(movie.getRate());
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
