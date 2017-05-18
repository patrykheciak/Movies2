package com.gmail.patrykheciak.movies2.entities;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Actor implements Parcelable, Serializable{

    private String name;
    private String surname;
    private String photoUrl;

    public Actor() {}

    public Actor(String name, String surname, String photoUrl) {
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
    }
    protected Actor(Parcel parcel) {
        name = parcel.readString();
        surname = parcel.readString();
        photoUrl = parcel.readString();
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(photoUrl);
    }

    public static final Parcelable.Creator<Actor> CREATOR
            = new Parcelable.Creator<Actor>() {
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
