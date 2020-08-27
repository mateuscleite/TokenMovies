package mateuscleite.github.tokenmovies;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import mateuscleite.github.tokenmovies.viewmodels.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    //UI related attributes
    private RelativeLayout movieDetails;
    private ProgressBar progressBar;
    private ImageView imgPoster;
    private MovieDetailsViewModel movieDetailsViewModel;
    private TextView txtTitle;
    private TextView txtVoteAverage;
    private TextView txtRuntime;
    private TextView txtTagline;
    private TextView txtDetailsOverview;

    //Data received from an activity
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle info = getIntent().getExtras();
        //if info if null, there was some communication error in the app
        if(info != null) {
            movieId = info.getInt(MainActivity.intentMovieId);
        }

        //Initialize UI elements
        setContentView(R.layout.movie_details_activity);
        imgPoster = findViewById(R.id.imgDetailsPoster);
        txtTitle = findViewById(R.id.txtDetailsTitle);
        movieDetails = findViewById(R.id.detailsPage);
        progressBar = findViewById(R.id.progressBar);
        txtVoteAverage = findViewById(R.id.txtDetailsVoteAverage);
        txtRuntime = findViewById(R.id.txtDetailsRuntime);
        txtTagline = findViewById(R.id.txtDetailsTagline);
        txtDetailsOverview = findViewById(R.id.txtDetailsOverview);
        hideProgressBar();
        hideMovieDetails();


        //Initialize the ViewModel
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        movieDetailsViewModel.getMutableLiveData(movieId).observe(this, new Observer<MovieDetailsViewModel>() {
            @Override
            public void onChanged(MovieDetailsViewModel movieDetailsViewModel) {
                Glide.with(imgPoster.getContext())
                        .asBitmap()
                        .error(R.drawable.movieroll)
                        .load(movieDetailsViewModel.movieDetails.getPoster_url())
                        .into(imgPoster);
                txtTitle.setText(movieDetailsViewModel.movieDetails.getTitle());
                txtVoteAverage.setText(movieDetailsViewModel.movieDetails.getVoteAverageAndCount());
                txtRuntime.setText(movieDetailsViewModel.movieDetails.getRuntimeHours());
                txtTagline.setText(movieDetailsViewModel.movieDetails.getTagline());
                txtDetailsOverview.setText(movieDetailsViewModel.movieDetails.getOverview());
            }
        });

        //Observes if data is being requested to determine if the progress bar and default textViews should be visible
        MovieDetailsViewModel.progressBar.observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean makingRequest) {
                if(makingRequest) {
                    showProgressBar();
                    hideMovieDetails();
                }
                else {
                    hideProgressBar();
                    showMovieDetails();
                }
            }
        });


    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void showMovieDetails(){
        movieDetails.setVisibility(View.VISIBLE);
    }

    private void hideMovieDetails(){
        movieDetails.setVisibility(View.GONE);
    }

}