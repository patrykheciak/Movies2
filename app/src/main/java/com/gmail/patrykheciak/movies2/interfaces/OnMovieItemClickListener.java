package com.gmail.patrykheciak.movies2.interfaces;

import com.gmail.patrykheciak.movies2.entities.Movie;

public interface OnMovieItemClickListener {
    void onMovieClicked(Movie item, int position);
}
