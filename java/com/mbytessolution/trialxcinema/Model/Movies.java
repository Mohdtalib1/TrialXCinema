package com.mbytessolution.trialxcinema.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movies {

    @SerializedName("backdrop_path")
    @Expose
    String second_banner;

    @SerializedName("poster_path")
    @Expose
    String first_banner;

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("vote_average")
    @Expose
    String rating;

    @SerializedName("release_date")
    @Expose
    String release_date;


    @SerializedName("overview")
    @Expose
    String overview;

    @SerializedName("original_language")
    @Expose
    String original_language;

    public Movies(String second_banner, String first_banner, String title, String rating, String release_date, String overview, String original_language) {
        this.second_banner = second_banner;
        this.first_banner = first_banner;
        this.title = title;
        this.rating = rating;
        this.release_date = release_date;
        this.overview = overview;
        this.original_language = original_language;
    }

    public Movies() {
    }

    public String getSecond_banner() {
        return second_banner;
    }

    public void setSecond_banner(String second_banner) {
        this.second_banner = second_banner;
    }

    public String getFirst_banner() {
        return first_banner;
    }

    public void setFirst_banner(String first_banner) {
        this.first_banner = first_banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
}
