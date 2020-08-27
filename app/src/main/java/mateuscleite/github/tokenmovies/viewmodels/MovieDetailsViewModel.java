package mateuscleite.github.tokenmovies.viewmodels;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import mateuscleite.github.tokenmovies.R;
import mateuscleite.github.tokenmovies.models.MovieDetails;
import mateuscleite.github.tokenmovies.network.MovieApi;
import mateuscleite.github.tokenmovies.network.MovieClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsViewModel extends ViewModel {

    private static final String TAG = "MovieDetailsViewModel";

    public MovieDetails movieDetails;
    public MutableLiveData<MovieDetailsViewModel> mutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<Boolean> progressBar = new MutableLiveData<>();

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .asBitmap()
                .error(R.drawable.movieroll)
                .load(imageUrl)
                .into(imageView);
    }

    public MovieDetailsViewModel(){

    }

    private void showProgressBar(){
        progressBar.postValue(true);
    }

    private void hideProgressBar(){
        progressBar.postValue(false);
    }

    public MovieDetailsViewModel(MovieDetails movie){
        movieDetails = movie;
    }

    public LiveData<MovieDetailsViewModel> getMutableLiveData(int movieId) {

        showProgressBar();

        MovieApi api = MovieClient.getInstance().getMovieClient();
        Call<MovieDetails> call = api.getMovieDetails(movieId);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                hideProgressBar();
                if(response.isSuccessful() && response.body() != null){
                    movieDetails = response.body();
                    MovieDetailsViewModel movieDetailsViewModel = new MovieDetailsViewModel(movieDetails);
                    mutableLiveData.setValue(movieDetailsViewModel);
                }
                else{
                    Log.d(TAG, "Error in response");
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                hideProgressBar();
                Log.d(TAG, "No response");
            }
        });

        return mutableLiveData;

    }
}
