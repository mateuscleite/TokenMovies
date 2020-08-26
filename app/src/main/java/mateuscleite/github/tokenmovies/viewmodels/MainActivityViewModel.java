package mateuscleite.github.tokenmovies.viewmodels;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mateuscleite.github.tokenmovies.R;
import mateuscleite.github.tokenmovies.models.Movie;
import mateuscleite.github.tokenmovies.network.MovieApi;
import mateuscleite.github.tokenmovies.network.MovieClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    public Movie movieModel;
    public MutableLiveData<ArrayList<MainActivityViewModel>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<MainActivityViewModel> arrayList = new ArrayList<>();
    private ArrayList<Movie> movies;
    public MutableLiveData<Boolean> progressBar = new MutableLiveData<>();

    public String getMoviePosterUrl(){
        return movieModel.getPoster_url();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .asBitmap()
                .error(R.drawable.movieroll)
                .load(imageUrl)
                .into(imageView);
    }

    public MainActivityViewModel(){

    }

    private void showProgressBar(){
        progressBar.postValue(true);
    }

    private void hideProgressBar(){
        progressBar.postValue(false);
    }

    public MainActivityViewModel(Movie movie){
        movieModel = movie;
    }

    public LiveData<ArrayList<MainActivityViewModel>> getMutableLiveData() {

        showProgressBar();

        MovieApi api = MovieClient.getInstance().getMovieClient();
        Call<ArrayList<Movie>> call = api.getMovies();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {

                hideProgressBar();
                if(!response.isSuccessful()){
                    Log.d(TAG, "Error in response");
                    return;
                }
                if(response.body() != null) {
                    movies = response.body();
                    for (int i = 0; i < movies.size(); i++) {
                        Movie parameterMovie = movies.get(i);
                        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel(parameterMovie);
                        arrayList.add(mainActivityViewModel);
                        mutableLiveData.setValue(arrayList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                hideProgressBar();
                Log.d(TAG, "No response");
            }
        });

        return mutableLiveData;

    }
}
