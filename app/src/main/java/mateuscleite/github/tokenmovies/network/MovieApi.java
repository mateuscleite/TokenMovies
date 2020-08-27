package mateuscleite.github.tokenmovies.network;

import java.util.ArrayList;

import mateuscleite.github.tokenmovies.models.Movie;
import mateuscleite.github.tokenmovies.models.MovieDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {

    @GET("movies")
    Call<ArrayList<Movie>> getMovies();

    @GET("movies/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") int movieId);

}
