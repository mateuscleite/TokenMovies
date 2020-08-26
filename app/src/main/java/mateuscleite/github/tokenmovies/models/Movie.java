package mateuscleite.github.tokenmovies.models;

public class Movie {

    private int id;
    private String title;
    private String release_date;
    private String poster_url;

    public Movie(int id, String title, String release_date, String poster_url) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_url = poster_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date.substring(0, 4);
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }
}
