package com.gmail.patrykheciak.movies2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.entities.Movie;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieItemClickListener;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieUpdatedListener;

import java.util.List;


public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies;
    private OnMovieItemClickListener listener;
    private OnMovieUpdatedListener callback;


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieListAdapter(List<Movie> movies, OnMovieItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    public void setOnMovieDataUpdatedListener(OnMovieUpdatedListener callback) {
        this.callback = callback;
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvGenre;
        TextView tvYear;
        ImageView seen;
        View root;  //unnecessary?

        public ViewHolder0(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.title);
            tvGenre = (TextView) view.findViewById(R.id.genre);
            tvYear = (TextView) view.findViewById(R.id.year);
            seen = (ImageView) view.findViewById(R.id.seen);
            root = view.findViewById(R.id.root);

            root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Movie movie = toggleSeen(getLayoutPosition());
                    callback.onMovieUpdated(movie, getLayoutPosition());
                    seen.setVisibility(movie.isSeen() ? View.VISIBLE : View.INVISIBLE);
                    return true;
                }
            });
        }

        public void bind(final Movie movie, final OnMovieItemClickListener listener, final int position) {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMovieClicked(movie, position);
                }
            });
        }

    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvGenre;
        TextView tvYear;
        ImageView seen;
        View root;  //unnecessary?

        public ViewHolder1(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.title);
            tvGenre = (TextView) view.findViewById(R.id.genre);
            tvYear = (TextView) view.findViewById(R.id.year);
            seen = (ImageView) view.findViewById(R.id.seen);
            root = view.findViewById(R.id.root);

            root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Movie movie = toggleSeen(getLayoutPosition());
                    callback.onMovieUpdated(movie, getLayoutPosition());
                    seen.setVisibility(movie.isSeen() ? View.VISIBLE : View.INVISIBLE);
                    return true;
                }
            });
        }

        public void bind(final Movie movie, final OnMovieItemClickListener listener, final int position) {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMovieClicked(movie, position);
                }
            });
        }
    }

    private Movie toggleSeen(int position) {
        Movie movie = movies.get(position);
        movie.setSeen(!movie.isSeen());
        movies.set(position, movie);
        return movie;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_row, parent, false);
            return new ViewHolder0(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_row_rtl, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            ViewHolder0 vh0 = (ViewHolder0) holder;
            vh0.tvTitle.setText(movies.get(position).getTitle());
            vh0.tvGenre.setText(movies.get(position).getGenre());
            vh0.tvYear.setText(movies.get(position).getYear());
            vh0.seen.setVisibility(movies.get(position).isSeen() ? View.VISIBLE : View.INVISIBLE);
            vh0.bind(movies.get(position), listener, position);
        } else {
            ViewHolder1 vh1 = (ViewHolder1) holder;
            vh1.tvTitle.setText(movies.get(position).getTitle());
            vh1.tvGenre.setText(movies.get(position).getGenre());
            vh1.tvYear.setText(movies.get(position).getYear());
            vh1.seen.setVisibility(movies.get(position).isSeen() ? View.VISIBLE : View.INVISIBLE);
            vh1.bind(movies.get(position), listener, position);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void remove(int position) {
        movies.remove(position);
        notifyItemRemoved(position);
    }
}
