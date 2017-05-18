package com.gmail.patrykheciak.movies2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.patrykheciak.movies2.CastAdapter;
import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.entities.Movie;

public class CastFragment extends Fragment {

    private Movie movie;
    private CastAdapter adapter;
    private RecyclerView rv;

    public CastFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null)
            movie = (Movie) savedInstanceState.getSerializable("movie");

        View v = inflater.inflate(R.layout.fragment_cast, container, false);
        rv = (RecyclerView) v.findViewById(R.id.cast_recycler);
        Context ctx= getActivity().getApplicationContext();
        adapter = new CastAdapter(movie.getCast(), ctx);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        return v;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("movie", movie);
    }
}
