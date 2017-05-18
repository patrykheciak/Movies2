package com.gmail.patrykheciak.movies2.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable, Serializable {

    private String title, genre, year, imageUrl;
    private float rate;
    private boolean seen;
    private List<String> onStagePhotos;

    private List<Actor> cast;

    public Movie() {
        onStagePhotos = new ArrayList<>();
    }

    public Movie(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        onStagePhotos = new ArrayList<String>();
    }

    public void setOnStagePhotos(List<String> onStagePhotos) {
        this.onStagePhotos = onStagePhotos;
    }

    public List<String> getOnStagePhotos() {
        return onStagePhotos;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return title + " " + genre + " " + year + " " + rate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isSeen() {
        return seen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(genre);
        parcel.writeString(year);
        parcel.writeString(imageUrl);
        parcel.writeFloat(rate);
        parcel.writeInt(seen ? 1 : 0);
        parcel.writeList(cast);
        parcel.writeList(onStagePhotos);
    }

    protected Movie(Parcel parcel) {
        title = parcel.readString();
        genre = parcel.readString();
        year = parcel.readString();
        imageUrl = parcel.readString();
        rate = parcel.readFloat();
        seen = parcel.readInt() == 1;
        cast = parcel.readArrayList(Movie.class.getClassLoader());
        onStagePhotos = parcel.readArrayList(String.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
