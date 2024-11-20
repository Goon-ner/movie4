package com.example.movie4;

import android.net.Uri;

class Movie {
    private String id;
    private String name;
    private String year;
    private String country;
    private String genre;
    private String director;
    private String rating;
    private String description;
    private String imageurl;

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getGenre() {
        return this.genre;
    }
    public String getImageurl() { return this.imageurl; }
    public String getYear(){return this.year;}
    public String getCountry(){return this.country;}
    public String getDirector(){return this.director;}
    public String getRating(){return this.rating;}
}

