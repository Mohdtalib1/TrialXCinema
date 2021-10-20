package com.mbytessolution.trialxcinema.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<Movies> moviesArrayList;

    public MoviesResponse(ArrayList<Movies> moviesArrayList) {
        this.moviesArrayList = moviesArrayList;
    }

    public MoviesResponse() {
    }

    public ArrayList<Movies> getMoviesArrayList() {
        return moviesArrayList;
    }

    public void setMoviesArrayList(ArrayList<Movies> moviesArrayList) {
        this.moviesArrayList = moviesArrayList;
    }
}
