package mateuscleite.github.tokenmovies.viewmodels;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
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

    public MainActivityViewModel(Movie movie){
        movieModel = movie;
    }

    public MutableLiveData<ArrayList<MainActivityViewModel>> getMutableLiveData() {
        MovieApi api = MovieClient.getInstance().getMovieClient();
        Call<ArrayList<Movie>> call = api.getMovies();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "Error in response");
                    return;
                }
                movies = response.body();
                for(int i = 0; i < movies.size(); i++){
                    Movie myk = movies.get(i);
                    MainActivityViewModel mainActivityViewModel = new MainActivityViewModel(myk);
                    arrayList.add(mainActivityViewModel);
                    mutableLiveData.setValue(arrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                Log.d(TAG, "No response");
            }
        });

        return mutableLiveData;

    }
}
