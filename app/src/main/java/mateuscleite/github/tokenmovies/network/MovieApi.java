package mateuscleite.github.tokenmovies.network;

import java.util.ArrayList;
import java.util.List;

import mateuscleite.github.tokenmovies.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("movies")
    Call<ArrayList<Movie>> getMovies();
}
