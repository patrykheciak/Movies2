package com.gmail.patrykheciak.movies2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.patrykheciak.movies2.entities.Movie;
import com.gmail.patrykheciak.movies2.MovieListAdapter;
import com.gmail.patrykheciak.movies2.MovieTouchHelper;
import com.gmail.patrykheciak.movies2.interfaces.OnDetailsRequestedListener;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieItemClickListener;
import com.gmail.patrykheciak.lab2.movies.R;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieListAdapter myAdapter;
    private OnMovieItemClickListener listener;

    OnDetailsRequestedListener detailsCallback;

    public ListFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ////List<Movie> movieList = ((MainActivity) getActivity()).movieList;

        listener = new OnMovieItemClickListener() {
            @Override
            public void onMovieClicked(Movie item, int position) {
                detailsCallback.onDetailsRequested(item, position);
            }
        };

        ////myAdapter = new MovieListAdapter(movieList, listener);
        ////myAdapter.setOnMovieDataUpdatedListener((MainActivity) getActivity());


        recyclerView.setAdapter(myAdapter);
        ItemTouchHelper.Callback callback = new MovieTouchHelper(myAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        return v;
    }

    public void setOnDetailsRequestedListener(OnDetailsRequestedListener detailsCallback) {
        this.detailsCallback = detailsCallback;
    }
}
