package com.gmail.patrykheciak.movies2.interfaces;

import com.gmail.patrykheciak.movies2.entities.Movie;

public interface OnDetailsRequestedListener {
    void onDetailsRequested(Movie movie, int position);
}
