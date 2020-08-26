package mateuscleite.github.tokenmovies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    private static final String TAG = "MovieClient";
    private static final String BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/";

    private Retrofit retrofit;
    private static MovieClient instance;
    private MovieClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MovieClient getInstance(){
        if(instance == null){
            instance = new MovieClient();
        }
        return instance;
    }

    public MovieApi getMovieClient(){
        return retrofit.create(MovieApi.class);
    }

}
