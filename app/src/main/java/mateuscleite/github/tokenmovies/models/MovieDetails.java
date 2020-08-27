package mateuscleite.github.tokenmovies.models;

import java.util.ArrayList;

public class MovieDetails extends Movie {

    private String backdrop_url;
    private String imdb_id;
    private String tagline;
    private String overview;
    private float vote_average;
    private int runtime;
    private String runtimeHours;
    private ArrayList<String> genres;

    public MovieDetails(int id, String title, String release_date, String poster_url) {
        super(id, title, release_date, poster_url);
    }

    public String getBackdrop_url() {
        return backdrop_url;
    }

    public void setBackdrop_url(String backdrop_url) {
        this.backdrop_url = backdrop_url;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average + "/10";
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getRuntimeHours() {
        int hour = runtime/60;
        int minute = runtime%60;
        runtimeHours = hour + "h " + minute + "min";
        return runtimeHours;
    }

    public void setRuntimeHours(String runtimeHours) {
        this.runtimeHours = runtimeHours;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
}
