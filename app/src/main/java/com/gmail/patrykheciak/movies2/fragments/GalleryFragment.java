package com.gmail.patrykheciak.movies2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.adapters.ImageListAdapter;
import com.gmail.patrykheciak.movies2.entities.Movie;

public class GalleryFragment extends Fragment {

    private String TAG = "GalleryFragment";
    private Movie movie;
    private GridView gridView;

    public GalleryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState");
            movie = savedInstanceState.getParcelable("movie");
        }

        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        gridView = (GridView) v.findViewById(R.id.gridview);
        Context ctx = getActivity().getApplicationContext();

        String[] photos = new String[movie.getOnStagePhotos().size()];
        movie.getOnStagePhotos().toArray(photos);

        gridView.setAdapter(new ImageListAdapter(ctx, photos));

        return v;
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

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


}
