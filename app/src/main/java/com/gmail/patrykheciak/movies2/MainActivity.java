package com.gmail.patrykheciak.movies2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.entities.Actor;
import com.gmail.patrykheciak.movies2.entities.Movie;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieItemClickListener;
import com.gmail.patrykheciak.movies2.interfaces.OnMovieUpdatedListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;

    private RecyclerView recyclerView;
    private MovieListAdapter myAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.setMovies(movieList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("d", "onCreate sIS" + String.valueOf(savedInstanceState == null));

        if (savedInstanceState == null)
            prepareMovieData();
        else
            movieList = savedInstanceState.getParcelableArrayList("movies");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myAdapter = new MovieListAdapter(movieList, new OnMovieItemClickListener() {
            @Override
            public void onMovieClicked(Movie item, int position) {
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("extra", (Serializable) movieList.get(position));
                startActivityForResult(i, position);
            }
        });
        myAdapter.setOnMovieDataUpdatedListener(new OnMovieUpdatedListener() {
            @Override
            public void onMovieUpdated(Movie movie, int position) {
                movieList.set(position, movie);
            }
        });
        recyclerView.setAdapter(myAdapter);
        ItemTouchHelper.Callback callback = new MovieTouchHelper(myAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Movie m = data.getParcelableExtra("result");
            Log.d("d", "result " + m);
            movieList.set(requestCode, m);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) movieList);
    }

    private void prepareMovieData() {
        movieList = new ArrayList<Movie>();

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movie.setImageUrl("http://site2.close-upfilm.com/wp-content/uploads/2015/12/star_wars_episode_vii_the_force_awakens-wide-840x525.jpg");
        movieList.add(movie);
        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);
        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);
        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);
        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);
        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);
        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);
        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);
        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);
        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);
        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);
        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);
        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);
        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        polluteMoviesWithCast();
        polluteMoviesWithOnStagePhotos();
    }

    private void polluteMoviesWithCast() {
        // concerning Star Wars 7
        List<Actor> cast = new ArrayList<>();
        cast.add(new Actor("Mark", "Hammil", "http://media.todaybirthdays.com/2015/11/07/mark-hamill.jpg"));
        cast.add(new Actor("John", "Boyega", "https://pbs.twimg.com/profile_images/683179590165131264/qFgHuZSp_400x400.jpg"));
        cast.add(new Actor("Daisy", "Ridley", "https://pbs.twimg.com/profile_images/677624077641732097/6Gs2Palh.jpg"));
        cast.add(new Actor("Carrie", "Fisher", "https://pbs.twimg.com/profile_images/706287567768064000/1UdeW1_u_400x400.jpg"));
        cast.add(new Actor("Ford", "Harrison", "https://s-media-cache-ak0.pinimg.com/736x/1a/98/fa/1a98fa0072fafa5d6b4df0b924cfd2b5.jpg"));
        movieList.get(2).setCast(cast);
    }

    private void polluteMoviesWithOnStagePhotos(){
        // concerning Star Wars 7
        List<String> onStagePhotos = new ArrayList<>();
        onStagePhotos.add("https://i.ytimg.com/vi/edYpUuPYEao/maxresdefault.jpg");
        onStagePhotos.add("http://cdn.collider.com/wp-content/uploads/2015/11/star-wars-the-force-awakens-j-j-abrams-daisy-ridley.jpg");
        onStagePhotos.add("http://fr.web.img3.acsta.net/newsv7/15/07/11/10/56/163676.jpg");
        onStagePhotos.add("http://cdn.collider.com/wp-content/uploads/2015/11/star-wars-the-force-awakens-set-image.jpg");
        onStagePhotos.add("http://blog.top250.fr/wp-content/uploads/2016/02/star-wars-7-making-of.jpg");
        onStagePhotos.add("http://fr.web.img6.acsta.net/r_640_360/videothumbnails/15/07/11/10/52/562454.jpg");
        movieList.get(2).setOnStagePhotos(onStagePhotos);
    }
}
